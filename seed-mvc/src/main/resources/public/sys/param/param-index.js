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
                dialogVisible: false,
                currentRow: {},
                tips: '',
                pageData: null,
                total: 0,
                url: 'sys/param/selectPageRelativeByParams',
                entity: {},
                queryParam: seed.queryParam.create(),
            }
        },
        methods: {
            test: function (entity) {
                var _this = this;
                _this.currentRow = entity;
                if (this.currentRow.active === 1) {
                    _this.$confirm('已激活, 是否取消?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(function () {
                        _this.currentRow.active === 1 ? _this.currentRow.active = 0 : _this.currentRow.active = 1;
                        seed.ajax.post({
                            url: "sys/param/updateActiveByPrimaryKey/" + _this.currentRow.id + "/" + _this.currentRow.active,
                            success: function (data, status) {
                                if (status) {
                                    this.search();
                                }
                            }
                        });
                    }).catch(function () {
                    });
                }
                else {

                    _this.$confirm('未激活, 是否激活?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(function () {
                        _this.currentRow.active === 1 ? _this.currentRow.active = 0 : _this.currentRow.active = 1;
                        seed.ajax.post({
                            url: "sys/param/updateActiveByPrimaryKey/" + _this.currentRow.id + "/" + _this.currentRow.active,
                            success: function (data, status) {
                                if (status) {
                                    this.search();
                                }
                            }
                        });
                    }).catch(function () {
                    });
                }


            },

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

                console.log(entity);
                var _this = this;
                _this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    seed.ajax.postJson({
                        url: "sys/param/deleteByPrimaryKey/" + entity.id,
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

        }
    }

    return {
        component: component         //返回组件
    }

});