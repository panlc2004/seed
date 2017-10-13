/**
 * Created by PLC on 2017/6/3.
 */
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
                url: 'sys/param/selectPageByParams',
                queryParam: seed.queryParam.create()
            }
        },
        methods: {
            search: function () {
                this.reload(this.queryParam);
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {
                    sysDeptId: ''
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
                    type: 'error'
                }).then(function () {
                    var url = 'sys/param/deleteByPrimaryKey/' + entity.id;
                    console.log(url);
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
            delActive: function (entity) {
                var button = this.$refs['ztbutton'];
                var _this = this;
                _this.$confirm('此操作将取消激活, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    var url = 'sys/param/deleteActive/' + entity.id;
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
            setActive: function (entity) {
                var button = this.$refs['ztbutton'];
                var _this = this;
                _this.$confirm('此操作将激活, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then(function () {
                    var url = 'sys/param/updateActive/' + entity.id;
                    seed.ajax.postJson({
                        url: url,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                    button.type("success");
                }).catch(function () {
                })
        },
            gridSelect:function () {


            }
        }
    };

    return {
        component: component         //返回组件
    }

});