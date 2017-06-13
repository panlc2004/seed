/**
 * Created by lenovo on 2017-06-12.
 */
var mainPanel = new Vue({
    el: "#main-panel",
    data:{
        queryParam:{},
        dictForm:{},
        dictFormShow:false,
        dictFormLabelWidth: '90px',   //表单标题宽度
    },
    methods: {
        // 获取数据字典项表格
        getDictGrid:function () {
            var dictGrid = this.$refs.dictGrid;
            return dictGrid;
        },
        // 获取当前选中字典项
        getDictSelectedRow: function () {
            return this.getDictGrid().getSelectedRows();
        },
        //字典项查询
        queryDict: function () {
            this.getDictGrid().reload(this.queryParam)
        },
        //角色新增
        addDict: function () {
            this.dictForm = {};
            this.dictFormShow = true;
        },
        // 打开字典项修改界面
        editDict: function () {
            var selectedRow = this.getDictSelectedRow();
            if (selectedRow == null) {
                czy.msg.error("请选择要操作的数据");
                return;
            }
            var editData = $.extend({}, selectedRow);
            this.dictForm = editData;
            this.dictFormShow = true;
        },
        // 删除字典项
        delDict: function () {
            var selectedRow = this.getDictSelectedRow();
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
                    $.post("/sys/dict/deleteByPrimaryKey/" + selectedRow.id, function (response) {
                        mainPanel.queryDict();
                    });
                }).catch(function () {});
        },
        // 保存字典项
        saveDict: function () {
            mainPanel.dictFormShow = false;
            czy.ajax.postJson({
                url: '/sys/dict/save',
                data: this.dictForm,
                success: function (result) {
                    mainPanel.queryDict();
                    czy.msg.success(result.msg);
                }
            });
        },
        //刷新字典值
        dictGridClick:function (row) {
            var dictItemGrid = this.$refs.dictItemGrid;
            var param = {or: []};
            var equals = {equalTo:{sysDictCode : row.code}}
            param.or.push(equals);
            dictItemGrid.reload(param);
        }
    },
    created:function () {

    }

});