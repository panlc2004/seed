define(['text!sys/org/org-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'edit': function (resolve) {
                require(['sys/org/org-edit'], resolve);
            }
        },
        mixins: [czyPageBar],
        data: function () {
            return {
                url: 'sys/org/selectPageByParams',
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
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(function () {
                    var delUrl = 'sys/org/deleteByPrimaryKey/' + entity.id;
                    $.get(delUrl,function (response,status) {
                        if(status) {
                            _this.$message({
                                type: 'success',
                                message: '操作成功!'
                            });
                            _this.search();
                        }
                    })
                }).catch(function () {});
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