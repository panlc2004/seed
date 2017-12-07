define(['text!sys/attachment/attachment-index.html'], function (Template) {

    var component = {
        template: Template,
        mixins: [czyPageBar],
        data: function () {
            return {
                url: 'sys/attachment/selectPageByParams',
                queryParam: seed.queryParam.create(),
            }
        },
        methods: {
            search: function () {
                this.reload(this.queryParam);
            }
        }
    };

    return {
        component: component
    }

});