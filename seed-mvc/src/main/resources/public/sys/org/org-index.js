define(['text!sys/org/org-index.html'],function (Template) {
    var component = {
        template: Template,
        components:{'edit':function(resolve){
            require(['sys/org/org-edit'], resolve);
        }},
        mixins: [czyPageBar],
        data: function () {
            return {
                url: 'sys/org/selectPageByParams',
                queryParams:{
                    like:{
                        orgName:''
                    }
                }
            }
        },
        methods: {
            search: function () {
                this.reload();
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.entity = {};
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = $.extend({},entity);
                edit.open();
            },
            del: function () {

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
        component:component         //返回组件
        // subRoute:subRoute        //返回子路由
    }

});