define(['text!sys/dict/dict-index.html'], function (Template) {
    var component = {
        template: Template,
        components: {
            'group': function (resolve) {
                require(['sys/dict/dict-group'], resolve);
            },
            'item': function (resolve) {
                require(['sys/dict/dict-item'], resolve);
            }
        },
        methods: {
            searchItem: function (groupRow) {
                var item = this.$refs.item;
                item.cacheDictId(groupRow.id)
                item.search();
            }
        }
    };

    return {
        component: component         //返回组件
    }

});