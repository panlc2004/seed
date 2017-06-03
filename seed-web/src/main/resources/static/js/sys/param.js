/**
 * Created by PLC on 2017/6/3.
 */
var main_panel = new Vue({
    el: '#main-panel',
    data: {
        selectedRow: {},
        queryParam: {
            pageNum: 1,
            pageSize: 10
        },
        pageData: null,
        total: 0,
    },
    methods: {
        query:function () {
            this.queryParam.pageNum = 1;
            this.loadData();
        },
        loadData: function () {
            var _this = this;
            $.post("/sys/param/selectPageByParams", this.queryParam, function (data) {
                console.log(data);
                        _this.pageData = data.data.page;
                        _this.total = data.data.total;
            });
        },
        add: function () {

        },
        edit: function () {
        },
        selectRow: function () {

        },
        changePage: function (pageNum) {
            this.queryParam.pageNum = pageNum;
            this.loadData();
        }
    },
    created: function () {
        this.loadData();
    }

});