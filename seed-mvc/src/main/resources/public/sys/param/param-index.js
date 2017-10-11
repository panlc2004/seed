define(['text!sys/param/param-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/param/param-edit'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                pageData: null,
                total: 0,
                dialogVisible: false,
                dialogVisible1: false,
                url: 'sys/param/selectPageRelativeByParams',
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
                        url: "sys/param/deleteByPrimaryKey/" + entity.id,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                }).catch(function () {
                });
            },
            delActive: function (entity) {
                var _this = this;
                _this.$confirm('是否取消激活?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    seed.ajax.postJson({
                        url: "sys/param/updateActiveByPrimaryKey/" + entity.id+"/"+entity.active,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                }).catch(function () {
                });
            },
            setActive: function (entity) {
                var _this = this;
                debugger;
                _this.$confirm('是否激活?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    seed.ajax.postJson({
                        url: "sys/param/updateActiveByPrimaryKey/" +  entity.id+"/"+entity.active,
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
            this.loadData();
        }
    }

    return {
        component:component
    }
});