var t = new Vue({
    el: '#indexDiv',
    mixins: [czyPageBar],
    data: {
        url: ctx + '/sys/org/selectPageByParams'
    },
    methods: {
        test: function () {
            czy.win.open('机构信息维护', ctx + '/sys/org-edit.html' ,["400px","450px"]);
        }
    }
})