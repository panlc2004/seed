/**
 * Created by lenovo on 2017-06-12.
 */
var dictPanel = new Vue({
    el: "#dict-panel",
    data:{
        queryParam:{},
        dictForm:{},
        dictFormShow:false,
        dictFormLabelWidth: '90px',   //表单标题宽度
        //字典值属性
        itemForm:{},
        itemFormShow:false,
        itemFormLabelWidth:'90px',

        showItemOperateBtn: false
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
            var param = {
                or: [
                    {
                        like: {name:this.queryParam.name},
                        equalTo:{id:10}
                    },
                    {
                        equalTo:{id:11}
                    }
                    ]
            };
            this.getDictGrid().reload(param)
            this.showItemOperateBtn = false;
        },
        //新增字典项
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
                    $.post("sys/dict/deleteByPrimaryKey/" + selectedRow.id, function (response) {
                        czy.msg.success(response.msg);
                        dictPanel.queryDict();
                    });
                }).catch(function () {});
        },
        // 保存字典项
        saveDict: function () {
            dictPanel.dictFormShow = false;
            czy.ajax.postJson({
                url: '/sys/dict/save',
                data: this.dictForm,
                success: function (result) {
                    dictPanel.queryDict();
                    czy.msg.success(result.msg);
                },
                error:function (result) {
                    czy.msg.error("系统异常，请联系管理员");
                }
            });
        },
        //=====================================字典值操作=========================================
        //刷新字典值
        dictGridClick:function (row) {
            var dictItemGrid = this.$refs.itemGrid;
            var param = {or: []};
            var equals = {equalTo:{sysDictCode : row.code}}
            param.or.push(equals);
            dictItemGrid.reload(param);
            this.showItemOperateBtn = true;
        },
        // 获取数据字典项表格
        getItemGrid:function () {
            var dictGrid = this.$refs.itemGrid;
            return dictGrid;
        },
        // 获取当前选中字典项
        getItemSelectedRow: function () {
            return this.getItemGrid().getSelectedRows();
        },
        //新增字典值
        addItem: function () {
            this.itemForm = {};
            this.itemFormShow = true;
        },
        // 打开字典值修改界面
        editItem: function () {
            var selectedRow = this.getItemSelectedRow();
            if (selectedRow == null) {
                czy.msg.error("请选择要操作的数据");
                return;
            }
            var editData = $.extend({}, selectedRow);
            this.itemForm = editData;
            this.itemFormShow = true;
        },
        // 删除字典值
        delItem: function () {
            var selectedRow = this.getItemSelectedRow();
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
                    $.post("sys/dictItem/deleteByPrimaryKey/" + selectedRow.id, function (response) {
                        czy.msg.success(response.msg);
                        dictPanel.dictGridClick(dictPanel.getDictSelectedRow());
                    });
                }).catch(function () {});
        },
        // 保存字典值
        saveItem: function () {
            this.itemFormShow = false;
            this.itemForm.sysDictCode = this.getDictSelectedRow().code;
            czy.ajax.postJson({
                url: 'sys/dictItem/save',
                data: dictPanel.itemForm,
                success: function (result) {
                    dictPanel.dictGridClick(dictPanel.getDictSelectedRow());
                    czy.msg.success(result.msg);
                }
            });
        }

    },
    created:function () {

    }

});