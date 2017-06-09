$(function () {


    var vgrid = new Vue({
        el: '#dt-grid',
        data: function () {
            return {
                tableData: this.loadData(),
                multipleSelection: [],
                form: {
                    name: '',
                    region: '',
                    date1: '',
                    date2: '',
                    delivery: false,
                    type: [],
                    resource: '',
                    desc: ''
                },
                fcForm: {},
                ggForm: {
                    galleyGoods: [
                        {positionCode: "FWD"},// 前舱
                        {positionCode: "AFT"}// 后舱
                    ],
                    configuration: {"FWD": "前舱", "AFT": "后舱"}
                },
            }
        },
        methods: {
            loadData: function () {
                var data = {};//组装的data数据
                $.ajax({
                    url: "/cfg/flightTypeConfig/list",
                    data: {},
                    type: 'POST',
                    async: false,//需要添加这个参数使用同步功能
                    success: function (result) {
                        data = result;
                    }
                });
                return data;
            },
            rowClick: function (row, event, column) {

            }
        },
        created: function () {
            this.rowClick();
        }
    })


});