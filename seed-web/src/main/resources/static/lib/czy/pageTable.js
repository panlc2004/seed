/**
 * Created by PLC on 2017/6/4.
 */
var pageGrid = Vue.extend({
    props: {
        url: '',
        autoinit: true,
        pageBar:null,
        pageBarSmall:true
    },

    data: function () {
        return {
            maxHeight: '400',
            pageData: null,
            queryParam: {
                pageSize: 20,
                pageNum: 1
            },
            total: 0,
            currentPageNum: 1,
            _url: this.url,
            _pageBar: this.pageBar,
            _pageBarSmall: this.pageBarSmall
        };
    },
    template: [
        '<el-row>',
        '<el-table :data="pageData" :stripe="true" :border="true" style="width: 100%" :highlight-current-row="true"',
        ' :max-height="maxHeight" small="pageBarSmall" :page-size="queryParam.pageSize" @current-change="rowSelect">',
        '<slot></slot>',
        '</el-table>',
        '<el-pagination :layout="pageBar != null ? pageBar : \'total, sizes, prev, pager, next, jumper\'"',
        ':total="total" @size-change="sizeChange"',
        ' @current-change="turnPage" :page-size="queryParam.pageSize"',
        ' :current-page="queryParam.pageNum"></el-pagination>',
        '</el-row>'
    ].join(''),
    methods: {
        sizeChange: function (size) {
            this.queryParam.pageSize = size;
            this.loadData();
        },
        reload: function (params) {
            for (var key in params) {
                this.queryParam[key] = params[key];
            }
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
            if (selectedRow) {   //有选中数据时才触发该事件
                this.$emit('current-change', selectedRow);
            }
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
        this.maxHeight = document.body.scrollHeight - 172;
        if (!this.autoinit) {
            this.loadData();
        }
    }
});

Vue.component("page-grid", pageGrid)