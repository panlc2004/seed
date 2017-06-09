/**
 * Created by PLC on 2017/6/4.
 */
var pageGrid = Vue.extend({
    props: {
        url: '',
        autoinit: true,
        pageBar: null,
        pageBarSmall: true,
        tableHeight: ''    //设置表格高度百分比
    },

    data: function () {
        return {
            tableStyle:'',  //表格样式
            maxHeight:'400',
            loading: true,
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
        '<el-table v-loading="loading" :data="pageData" :stripe="true" :border="true" :style="tableStyle" :max-height=maxHeight ',
        ' small="pageBarSmall" :page-size="queryParam.pageSize" @current-change="rowSelect" :highlight-current-row="true">',
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
            for (var key in params) {
                this.queryParam[key] = params[key];
            }
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
            _this.loading = true;
            $.post(this.url, this.queryParam, function (data) {
                _this.loading = false;
                _this.pageData = data.data.page;
                _this.total = data.data.total;
            })
        }
    },
    created: function () {
        console.log(this.tableHeight);
        //动态计算表格高度
        var tableHeight = this.tableHeight == undefined ? '100%' : this.tableHeight;
        var percent = tableHeight.replace("%","")/100;
        var autoTableHeight = document.body.scrollHeight * percent;
        this.maxHeight = autoTableHeight;
        this.tableStyle = 'width:100%;height:' + autoTableHeight +  'px';
        if (!this.autoinit) {
            this.loadData();
        }
    }
});

Vue.component("czy-page-grid", pageGrid)