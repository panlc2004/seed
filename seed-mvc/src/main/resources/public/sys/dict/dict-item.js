define(['text!sys/dict/dict-item.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/dict/dict-item-edit'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                autoLoad: false,
                dictId: '',
                deptData: [],
                url: 'sys/dictItem/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function (parentId) {
                if(parentId == undefined) {
                    parentId = 0;
                }
                var _this = this;
                this.selectListByParentId(parentId, _this.dictId, function (response) {
                    _this.pageData = response.data;
                })
            },
            cacheDictId: function (dictId) {
                this.dictId = dictId;
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
                edit.open(this.dictId);
            },
            toAddChild: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = {
                    depth: entity.depth + 1,
                    parentId: entity.id
                };
                edit.open(this.dictId);
            },
            selectSubItem: function (row, callback) {
                this.selectListByParentId(row.id, this.dictId, function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, sysDictId, callback) {
                seed.ajax.postJson({
                    url: 'sys/dictItem/selectListByParentId/' + parentId + "/" + sysDictId,
                    success: function (response) {
                        callback(response);
                    }
                })
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open(this.dictId);
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据及其子级数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    var url = 'sys/dictItem/deleteChildByPrimaryKey/' + entity.id
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
            }
        },
        created: function () {
            //查找一级部门
        }
    };

    return component         //返回组件

});