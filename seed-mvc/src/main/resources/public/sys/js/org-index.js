sys__org_index_html = {
    template: '#sys__org_index_html',

    mixins: [czyPageBar],
    data: function () {
        return {
            url: ctx + '/sys/org/selectPageByParams',
            a:{}
        }
    },
    methods: {
        search: function () {
            alert(123);
        },
        toEdit: function (entity) {
            this.a.entity = entity;
            this.a.open();
        },
        del: function () {

        }
    },
    created: function () {

    },
    mounted:function () {
        czy.loadComponent(ctx + '/sys/org-edit.html', '#test', function (component) {
            component.$mount('#test');  //挂载组件到指定dom
            this.a = component;
        });
    },
    destroyed: function () {

    }
};