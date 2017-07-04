var cfg_table_list = new Vue({
    el: "#dt-grid",
    data: function () {
        return {
            tableData: this.loadData(),
        }
    },
    methods: {
        loadData: function () {
            var data = {};//组装的data数据
            $.ajax({
                url: "cfg/flightTypeConfig/list",
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
            for (var key in row) {
                //alert(row.id);
            }
        },
        typeConfEdit: function () {
            debugger
            var form = $("<form method='post'></form>");
            form.attr({"action": "/wbm/cfg/turnTypeConfig"});
            var input = $("<input type='hidden'>");
            input.attr("name", "flightTypeConfigId");
            input.attr("value",12312312);
            form.append(input);
            $(document.body).append(form);
            form.submit();
        }
    }
})
