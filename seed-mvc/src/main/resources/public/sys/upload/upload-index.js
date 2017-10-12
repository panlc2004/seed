define(['text!sys/upload/upload-index.html'], function (Template) {
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
                queryParam: seed.queryParam.create(),
                fileList3: [{
                    name:'',
                    url:'',
                    status:''
                }]
            }
        },
        methods: {
            handleChange: function(file, fileList) {
                this.fileList3 = fileList.slice(-3);
            },
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
                    var url = 'sys/param/deleteByPrimaryKey/' + entity.id
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
                //
                _this.$confirm('此操作将取消激活, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    entity.active=0;
                    var url = 'sys/param/updateActive';
                    seed.ajax.postJson({
                        url: url,
                        data: entity,
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
                //
                _this.$confirm('此操作将激活, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(function () {
                    entity.active=1;
                    var url = 'sys/param/updateActive';
                    seed.ajax.postJson({
                        url: url,
                        data: entity,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    });
                    button.type("success");
                }).catch(function () {
                });
            }
        }
    };
    return {
        component: component         //返回组件
        // subRoute:subRoute        //返回子路由
    }
});