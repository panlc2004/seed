sys$org_index_html = Vue.extend({
    template: '#indexDiv',
    mixins: [czyPageBar],
    data: function () {
        return {
            url: ctx + '/sys/org/selectPageByParams'
        }
    },
    methods: {
        search: function () {
            alert(123);
        },
        toEdit: function (entity) {
            czy.win.open('机构信息维护', ctx + '/sys/org-edit.html', ["400px", "450px"]);
            czy.param.temp.setEntity(entity);
        },
        del: function () {

        }
    },
    created: function () {
        //创建定时器一定要在destroyed里销毁！
        this.timer = window.setInterval(function () {
            console.log(123123)
        }, 1000)
    },
    destroyed: function () {
        //创建定时器一定要在destroyed里销毁！
        window.clearInterval(this.timer);
    }
});