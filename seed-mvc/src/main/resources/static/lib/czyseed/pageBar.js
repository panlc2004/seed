//====================分页组件封装=====================
var czyPageBar = {
    data: function () {
        return {
            pageSize: 10,
            pageNum: 1,
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
            // for (var key in params) {
            //     this.queryParam[key] = params[key];
            // }
            this.param = param
            this.pageNum = 1;
            this.loadData();
        },
        refresh: function (param) {
            // for (var key in params) {
            //     this.queryParam[key] = params[key];
            // }
            this.param = param;
            this.pageNum = this.currentPageNum;
            this.loadData();
        },
        loadData: function () {
            var _this = this;
            _this.loading = true;
            $.getJSON(this.url + "/" + this.pageNum + "/" + this.pageSize, {param:{}}, function (status, data) {
                _this.pageData = data.data.page;
                _this.total = data.data.total;
            });
        },
        turnPage: function (pageNum) {
            this.currentPageNum = pageNum;
            this.pageNum = pageNum;
            this.loadData();
        }
    },
    created: function () {
        if (this.autoLoad) {
            this.loadData();
        }
    }
};
