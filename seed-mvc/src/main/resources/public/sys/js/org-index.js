var orgIndex = Vue.extend({
    template: '#indexDiv',
    mixins: [czyPageBar],
    data:function() {
        return{
            url: ctx + '/sys/org/selectPageByParams'

        }
    },
    methods: {
        search:function () {
            alert(123);
        },
        toEdit: function (entity) {
            czy.win.open('机构信息维护', ctx + '/sys/org-edit.html' ,["400px","450px"]);
            czy.param.temp.setEntity(entity);
        },
        del:function () {
            
        }
    }
})