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
            if (!param) {
                param = {};
            }
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

//=======================查询工具栏布局控件=========================

var czy_toolbar_btn_line = {
    template: [
        '<el-col :span=24 class="czy-toolbar-btn-line">',
        '<slot></slot>',
        '</el-col>'
    ].join('')
}

var czy_toolbar_btn = {
    template: [
        '<el-col :xs="24" :sm="12" :md="8" :lg="6" class="czy-toolbar-btn">',
        '<slot></slot>',
        '</el-col>'
    ].join('')
}

var czy_toolbar_item = Vue.extend({
    props: {
        label: {
            type: Number,
            default: 9
        },
        layout2: {
            type: String,
            default: ':xs="24" :sm="12" :md="8" :lg="6"'
        }
    },
    data:function () {
        return {
            _layout:this.layout2
        }
    },
    template: [
        '<el-col :xs="24" :sm="12" :md="8" :lg="6" class="czy-toolbar-item">',
        '<el-col :span="' + 'this.label' + '" class="czy-toolbar-item-label"><slot name="label"></slot>:</el-col>',
        '<el-col :span="' + '24 - this.label' + '" class="czy-toolbar-item"><slot name="item"></slot></el-col>',
        '</el-col>'
    ].join('')
})

var czy_toolbar = Vue.extend({
    template: [
        '<el-row class="czy-toolbar">',
        // '<div><h5>查询</h5></div>',
        '<slot></slot>',
        '</el-row>',
    ].join('')
});

Vue.component('czyToolbar', czy_toolbar);
Vue.component('czy-toolbar-btn', czy_toolbar_btn);
Vue.component('czy-toolbar-btn-line', czy_toolbar_btn_line);
Vue.component('czy-toolbar-item', czy_toolbar_item);

//=======================XXX控件=========================