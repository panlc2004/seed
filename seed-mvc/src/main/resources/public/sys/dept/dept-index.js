define(['text!sys/dept/dept-index.html'], function (Template) {
    var component = {
        template: Template,
        mixins: [czyPageBar],
        components: {
            'edit': function (resolve) {
                require(['sys/dept/dept-edit'], resolve);
            }
        },
        data: function () {
            return {
                url: 'sys/dept/selectPageByParams',
                queryParam: seed.queryParam.create(),
            }
        },
        methods: {
            search: function () {
                this.reload(this.queryParam);
            },
            selectSubDept: function (row, callback) {
                this.selectListByParentId(row.id, function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, callback) {
                seed.ajax.post({
                    url: 'sys/dept/selectListByParentId',
                    data: {
                        parentId: parentId
                    },
                    success: function (response) {
                        callback(response);
                    }
                })
            },
            toAdd: function (entity) {
                var addParam = {};
                if(entity != null) {
                    addParam.depth = parseInt(entity.depth) + 1;
                    addParam.parentId = entity.id;
                }
                var edit = this.$refs.edit;
                edit.entity = addParam;
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将敏同时删除所有子级数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    var url = 'sys/dept/deleteAllSubDeptById/' + entity.id;
                    $.get(url, function (data, status) {
                            if (status) {
                                _this.$message({
                                    type: 'success',
                                    message: '操作成功!'
                                });
                                _this.search();
                            }
                        }
                    );
                }).catch(function () {
                });
            }
        },
        created: function () {
            //查找一级部门
            var _this = this;
            this.selectListByParentId(0, function (response) {
                _this.pageData = response.data;
            });
        }
    };

    return {
        component: component         //返回组件
    }

});