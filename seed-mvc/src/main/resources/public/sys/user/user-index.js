define(['text!sys/user/user-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/user/user-edit'], resolve);
            },
            "role":function (resolve) {
                require(['sys/user/user-role'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                url: 'sys/user/selectPageRelativeByParams',
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
                    seed.ajax.postJson({
                        url: 'sys/user/deleteByPrimaryKey/' + entity.id,
                        success: function (data, status) {
                            if (status) {
                                _this.search();
                            }
                        }
                    })
                }).catch(function () {});
            },
            setRole:function (row) {
                var role = this.$refs.role;
                role.open(row.id);
            }
        }
    };

    // 定义子路由
    // var subRoute = [{
    //     path: 'edit/:entity',
    //     component: function (resolve) {
    //         require(['sys/org/org-edit'], function (o) {
    //             resolve(o)
    //         });
    //     }
    // }];

    return {
        component: component         //返回组件
        // subRoute:subRoute        //返回子路由
    }

});