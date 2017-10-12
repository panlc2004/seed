define(['text!sys/resource/resource-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/resource/resource-edit'], resolve);
            }
        },
        data: function () {
            return {

                deptData: [],
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                var _this = this;
                this.selectListByParentId(0, function (response) {
                    _this.deptData = response.data;

                    console.log(_this.deptData)
                });

                /* this.reload(this.queryParam);*/
            },
            selectSubRerouce: function (row, callback) {
                this.selectListByParentId(row.id, function (response) {
                    callback(response.data);
                })
            },
            selectListByParentId: function (parentId, callback) {
                seed.ajax.post({
                    url: 'sys/resource/selectListByParentId',
                    data: {
                        parentId: parentId
                    },
                    success: function (response) {
                        callback(response);
                    }
                })
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {parentId: 0};
                edit.open();
            },
            toAdd1: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = {
                    parentId: entity.id,
                    depth: entity.depth + 1
                };
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({}, entity);
                edit.open();
            },
            del: function (entity) {
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    seed.ajax.postJson({
                        url: "sys/resource/deleteByPrimaryKey/" + entity.id,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                }).catch(function () {
                });
            }
        },
        created: function () {
            //查找一级部门
            var _this = this;
            this.selectListByParentId(0, function (response) {
                _this.deptData = response.data;

                console.log(_this.deptData)
            });
        }
    };

    return {
        component: component         //返回组件
    }

});