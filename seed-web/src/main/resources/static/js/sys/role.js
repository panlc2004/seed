/**
 * Created by PLC on 2017/6/3.
 */
var main_panel = new Vue({
    el: '#main-panel',
    data: {
        selectedRow: null,      //列表选中行
        queryParam: {           //查询参数
            pageNum: 1,
            pageSize: 10
        },
        pageData: null,         //分页数据
        total: 0,               //数据总量
        // 表单数据
        formData: {},            //表单数据
        editDialogShow: false,   //新增、修改表单是否显示
        formLabelWidth: '70px'   //表单标题宽度
    },

    methods: {
        query: function () {
            var grid = this.$refs.grid;
            grid.reload(this.queryParam)
        },
        add: function () {
            this.formData = {};
            this.editDialogShow = true;
        },
        edit: function () {
            var grid = this.$refs.grid;
            var selectedRow = grid.getSelectedRows();
            if (selectedRow == null) {
                czy.msg.error("请选择要操作的数据");
                return;
            }
            this.formData = selectedRow;
            this.editDialogShow = true;
        },
        del: function () {
            var grid = this.$refs.grid;
            var selectedRow = grid.getSelectedRows();
            if (selectedRow == null) {
                czy.msg.error("请选择要操作的数据");
                return;
            }
            this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(
                function () {
                    $.post("/sys/role/deleteByPrimary/" + selectedRow.id, function (res) {
                        main_panel.query();
                    });
                }).catch(function () {});
        },
        save: function () {
            czy.ajax.post("/sys/role/save", this.formData, function (data, o) {
                main_panel.editDialogShow = false;
                main_panel.query();
            });
        }
    }

});