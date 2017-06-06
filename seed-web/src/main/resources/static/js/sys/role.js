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
            formLabelWidth: '70px',   //表单标题宽度

            //组织结构树
            treeData: [],   //机构树数据
            defaultExpandedKeys: [0],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
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
                var selectedRow = this.getSelectedRow();
                if (selectedRow == null) {
                    czy.msg.error("请选择要操作的数据");
                    return;
                }
                this.formData = selectedRow;
                this.editDialogShow = true;
            },
            del: function () {
                var selectedRow = this.getSelectedRow();
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
                    }).catch(function () {
                });
            },
            getSelectedRow: function () {
                var grid = this.$refs.grid;
                return grid.getSelectedRows();
            },
            save: function () {
                czy.ajax.post("/sys/role/save", this.formData, function (data, o) {
                    main_panel.editDialogShow = false;
                    main_panel.query();
                });
            },
            //获取选中的菜单id
            getCheckedResource: function () {
                return this.$refs.resTree.getCheckedKeys();
            },
            saveRoleResource: function () {
                var resources = this.getCheckedResource();
                var roleId = this.getSelectedRow().id;
                var param = [];
                resources.forEach(function (id) {
                    param.push({"sysRoleId": roleId, "sysResourceId": id});
                });
                $.ajax({
                    type: "POST",
                    url: "sys/roleResource/saveRoleResource",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(param),
                    success: function (result) {
                        czy.msg.success(result.msg);
                    }
                });
            },
            roleGridClick: function (row) {
                var _this = this;
                $.post("sys/roleResource/selectResourceForRole/" + row.id,
                    function (result) {
                        var resTree = _this.$refs.resTree;
                        var existResourceId = [];           //用户现有资源ID
                        resTree.setCheckedKeys([])          //取消现有选中项
                        result.data.forEach(function (item) {   //开始选中
                            existResourceId.push(item.sysResource.id);
                        })
                        console.log(existResourceId);
                        resTree.setCheckedKeys(existResourceId);
                    }
                )
            }
        },

        created: function () {
            var _this = this;
            $.post("/sys/resource/selectResourceTree", this.queryParam, function (data) {
                _this.treeData = data.data;
            });
        }

    })
    ;