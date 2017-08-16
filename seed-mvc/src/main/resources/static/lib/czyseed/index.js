//====================分页组件封装=====================
var czyPageBar = {
    data: function () {
        return {
            pageSize: 10,
            currentPage: 1,
            param: {},
            total: 0,
            currentPageNum: '1',
            url: '',
            pageData: [],
            autoLoad: true
        };
    },
    methods: {
        sizeChange: function (size) {
            this.pageSize = size;
            this.loadData();
        },
        reload: function (param) {
            this.param = param
            this.currentPage = 1;
            this.loadData();
        },
        refresh: function (param) {
            this.param = param;
            this.currentPage = this.currentPageNum;
            this.loadData();
        },
        loadData: function () {
            var _this = this;
            _this.loading = true;
            $.ajax({
                type: "POST",
                contentType: "application/json;charset=utf-8",
                url: _this.url + "/" + _this.currentPage + "/" + _this.pageSize,
                data: JSON.stringify(_this.param),
                success: function (response) {
                    _this.pageData = response.data.page;
                    _this.total = Number(response.data.total);
                },
                error: function (response) {
                    czy.msg.error("未知异常，请联系管理员");
                    _this.loading = false;
                }
            });
        },
        turnPage: function (pageNum) {
            this.currentPageNum = pageNum;
            this.currentPage = pageNum == undefined ? 1 : pageNum;
            this.loadData();
        }
    },
    created: function () {
        if (this.autoLoad) {
            this.turnPage();
        }
    }
};
