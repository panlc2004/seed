define(['text!sys/dict/dict-group.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/dict/dict-group-edit'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                autoLoad: true,
                code: null,
                deptData: [],
                url: 'sys/dict/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                var _this = this;
                this.selectListByParentId(0, _this.code, function (response) {
                    _this.deptData = response.data;
                })
            },

            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
                edit.open();
            },
            toAddChild: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = {
                    depth: entity.depth + 1,
                    parentId: entity.id
                };
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            selectSubDept: function (row,callback) {
                this.selectListByParentId(row.id, "null", function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, code, callback) {
                if(code == null || code == "") {
                    code = "null";
                }
                seed.ajax.postJson({
                    url: 'sys/dict/selectListByParentId/' + parentId + "/" + code,
                    success: function (response) {
                        callback(response);
                    }
                })
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    var url = 'sys/dict/deleteChildByPrimaryKey/' + entity.id
                    seed.ajax.postJson({
                        url: url,
                        success: function (data, status) {
                            if (status) {
                                _this.$message({
                                    type: 'success',
                                    message: '操作成功!'
                                });
                                _this.search();
                            }
                        }
                    });
                }).catch(function () {
                });
            },
            setRole: function (row) {
                var role = this.$refs.role;
                role.open(row.id);
            },
            groupChange: function (currentRow) {
                if (currentRow != null) {
                    this.$emit("group-changed", currentRow);
                }
            }
        },
        created: function () {
            //查找一级部门
            this.search();
        }
    };

    return component         //返回组件

});