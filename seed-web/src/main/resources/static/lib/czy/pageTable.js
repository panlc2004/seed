/**
 * Created by PLC on 2017/6/4.
 */
var pageGrid = Vue.extend({
    props: {
        url: '',
        autoinit: true
    },

    data: function () {
        return {
            pageData: null,
            queryParam: {
                pageSize: 10,
                pageNum: 1
            },
            total: 0,
            currentPageNum: 1,
            _url: this.url
        }
    },
    template: [
        '<el-row>',
        '<el-table :data="pageData" :stripe="true" :border="true" style="width: 100%" :highlight-current-row="true"',
        ' max-height="100%" :page-size="queryParam.pageSize" @current-change="rowSelect">',
        '<slot></slot>',
        '</el-table>',
        '<el-pagination layout="total, prev, pager, next" :total="total"',
        ' @current-change="turnPage" :page-size="queryParam.pageSize"',
        ' :current-page="queryParam.pageNum"></el-pagination>',
        '</el-row>'
    ].join(''),
    methods: {
        reload: function (params) {
            for (var key in params) {
                this.queryParam[key] = params[key];
            }
            console.log(this.queryParam);
            this.queryParam.pageNum = 1;
            this.loadData();
        },
        refresh: function (params) {
            this.queryParam.pageNum = this.currentPageNum;
            this.loadData();
        },
        getSelectedRows: function () {
            return this.selectedRow;
        },
        rowSelect: function (selectedRow) {
            this.selectedRow = selectedRow;
            this.$emit('current-change', selectedRow)
        },
        turnPage: function (pageNum) {
            this.currentPageNum = pageNum;
            this.queryParam.pageNum = pageNum;
            this.loadData();
        },
        loadData: function () {
            var _this = this;
            $.post(this.url, this.queryParam, function (data) {
                _this.pageData = data.data.page;
                _this.total = data.data.total;
            })
        }
    },
    created: function () {
        if (!this.autoinit) {
            this.loadData();
        }
    }
});

var test = Vue.extend({
    template: "{{}}"
})


Vue.component("page-grid", pageGrid)