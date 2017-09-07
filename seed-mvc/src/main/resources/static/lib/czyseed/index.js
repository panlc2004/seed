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
        reload: function (params) {
            this.param = buildQueryParams(params);
            this.currentPage = 1;
            this.loadData();
        },
        refresh: function (param) {
            this.param = buildQueryParams(params);
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

/**
 * 构建查询参数
 * @param params
 * @returns {*}
 */
function buildQueryParams(params) {
    if (!params) {
        return {};
    }
    var paramArray = [];
    if (!isArray(params)) {
        paramArray[0] = buildQueryParam(params)
    } else {
        for (var i = 0; i < params.length; i++) {
            paramArray[i] = buildQueryParam(params[i]);
        }
    }
    return {or: paramArray};
}

/**
 * 判断是否为Array类型
 * @param o
 * @returns {boolean}
 */
function isArray(o) {
    return Object.prototype.toString.call(o) == '[object Array]';
}

/**
 * 构建查询参数
 * @param param
 */
function buildQueryParam(param) {
    var or = {};
    for (var key in param) {
        if(JSON.stringify(param[key]) == "{}") {
            continue;
        }
        if (key == "between" || key == "notBetween") {
            var betweenVal = param[key];
            var begins = betweenVal["begin"];
            if(JSON.stringify(begins) == "{}") {
                continue;
            }
            var ends = betweenVal["end"];
            var temp = {};
            for (var attr in begins) {
                var begin = begins[attr];
                var end = ends[attr];
                if (begin != undefined && begin != ""
                    && end != undefined && end != "") {
                    temp[attr] = {begin: begin, end: end};
                }
            }
            or[key] = temp;
        } else if (key == "in" || key == "notIn") {
            var vals = param[key];
            var temp = {};
            for (var k in vals) {
                temp[k] = vals[k].split(",");
            }
            or[key] = temp;
        } else {
            or[key] = param[key];
        }
    }

    return or;
}


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


//=======================查询参数组件=========================
