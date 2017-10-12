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
        data:function(){
            return{

            }
        },
        methods: {
            searchItem: function (groupRow) {
                if(groupRow!==null){
                    var item = this.$refs.item;
                    item.dictId = groupRow.id;
                    item.search();
                }
            }
        }
    };

    return {
        component: component         //返回组件
    }

});