$(function () {
    var t = new Vue({
        el: '#indexDiv',
        mixins: [czyPageBar],
        data: {
            url:ctx + 'sys/org/selectPageByParams'
        }
    })
})