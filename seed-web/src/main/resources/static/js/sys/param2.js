/**
 * Created by PLC on 2017/6/3.
 */

var ms = new Vue()

var tool_bar = Vue.extend({
    template: "#tool-bar-template",
    methods: {
        query: function () {
        },
        add: function () {
        },
        edit: function () {
        }
    }
});

var page_grid = Vue.extend({
    template: "#page-grid-template",
    props:{url:""},
    data: function () {
        return {
            data: [],
            pageSize: 10,
            pages: "",
            total: 0
        };
    },
    methods: {
        loadData: function () {
            alert(13123);
        },
        selectChange: function () {

        },
        changePage: function (pageNum) {

        }
    },
    created: function () {
        var _this = this;
        bus.$on('id-selected', function (id) {
            alert(id);
            _this.loadData();
        })
        // $.post(url)
    }
});

Vue.component("tool-bar", tool_bar);
Vue.component("page-grid", page_grid);

var man = new Vue({
    el: "#main-panel"
})


// 触发组件 A 中的事件
bus.$emit('id-selected', 123)