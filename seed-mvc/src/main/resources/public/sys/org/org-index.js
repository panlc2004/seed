define(['text!sys/org/org-index.html'],function (Template) {
    var component = {
        template: Template,
        components:{'edit':function(resolve){
            require(['sys/org/org-edit'], resolve);
        }},
        mixins: [czyPageBar],
        data: function () {
            return {
                url: 'sys/org/selectPageByParams'
            }
        },
        methods: {
            search: function () {
                alert(123);
            },
            toAdd: function () {
                var edit = this.$refs.edit;
                edit.open();
            },
            toEdit: function (entity) {
                var edit = this.$refs.edit;
                edit.entity = entity;
                edit.open();
            },
            del: function () {

            }
        }
    };

    // var subRoute = [{
    //     path: 'edit/:entity',
    //     component: function (resolve) {
    //         require(['sys/org/org-edit'], function (o) {
    //             resolve(o)
    //         });
    //     }
    // }];

    return {
        component:component
        // subRoute:subRoute
    }

});