define(['text!sys/dict/dict-group.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/dict/dict-group-edit'], resolve);
            },

        },
        mixins: [czyPageBar],
        data: function () {
            return {
                deptData: [],
                url: 'sys/dict/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        watch: {
            show: function (val, oldVal) {
                if (!val) {
                    this.$refs['editForm'].resetFields();
                }
            }
        },
        methods: {
            search: function () {
                var _this = this;
                this.selectListByParentId(0, function (response) {
                    _this.deptData = response.data;
                })
            },

            toAdd: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = {parentId:entity.id,depth:entity.depth+1};
                edit.open();
            },

            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            selectSubDept: function (row, callback) {
                this.selectListByParentId(row.id, function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, callback) {
                seed.ajax.post({
                    url: 'sys/dict/selectListByParentId',
                    data: {
                        parentId: parentId
                    },
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
                    var url = 'sys/dict/deleteByPrimaryKey/' + entity.id
                    seed.ajax.postJson({
                        url: url,
                        success: function (data, status) {
                            if (status) {
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
            groupChange:function (currentRow) {
                if(currentRow!==null){
                    this.$emit("group-changed", currentRow);
                }
            }
        },
        created: function () {
            this.search();
        }

    };

    return component         //返回组件

});