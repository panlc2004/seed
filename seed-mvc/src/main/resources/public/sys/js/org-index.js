var app = new Vue({
    el: '#indexDiv',
    mixins: [czyPageBar],
    data: {
        url: ctx + '/sys/org/selectPageByParams'
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