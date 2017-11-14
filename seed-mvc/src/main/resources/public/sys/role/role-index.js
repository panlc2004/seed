define(['text!sys/role/role-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/role/role-edit'], resolve);
            },
            'resource': function (resolve) {
                require(['sys/role/role-resource'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {

            return {

                url: 'sys/role/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                this.reload(this.queryParam);
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
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
                    type: 'error'
                }).then(function () {
                    seed.ajax.postJson({
                        url: "sys/role/deleteByPrimaryKey/" + entity.id,
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
            setResource: function (row) {
                var resource = this.$refs.resource;
                resource.open(row.id);
            }
        }
    };
    return {
        component: component         //返回组件
    }
});