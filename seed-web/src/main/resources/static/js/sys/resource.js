/**
 * Created by PLC on 2017/6/3.
 */
var main_panel = new Vue({
        el: '#main-panel',
        data: {
            formData: 1231231,
            queryParam: {
                params: {}
            },
            //组织结构树
            treeData: [],   //机构树数据
            defaultExpandedKeys: [0],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            // 表单数据
            formData: {},            //表单数据
            editDialogShow: false,   //新增、修改表单是否显示
            formLabelWidth: '70px'   //表单标题宽度
        },

        methods: {
            query: function (val) {
                this.$refs.resTree.filter(this.queryParam.name);
            },
            loadData: function () {
                var _this = this;
                $.post("/sys/resource/selectResourceTree", this.queryParam, function (data) {
                    _this.treeData = data.data;
                });
            },
            filterNode:function(value, data) {
                console.log(data);
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            },
            add: function () {
                this.formData = {}; //清空表单数据
                // 获取选中的树节点
                var selectedNode = this.$refs.resTree.getCheckedNodes();
                if (selectedNode.length == 0) {          //未选中
                    this.formData.parentId = 0;          //未选中时默认增加最高级菜单：parentId = 0
                } else if (selectedNode.length > 1) {    //选中超过一条
                    this.$message({
                        message: '只能指定一个父级菜单',
                        type: 'error'
                    });
                    return;
                } else {
                    this.formData.parentId = selectedNode[0].id
                }
                ;
                // 将选中的节点的id值做为新增机构的parentId
                this.editDialogShow = true;
            },
            edit: function (data) {
                this.formData = data;
                this.editDialogShow = true;
            },
            save: function () {
                czy.ajax.post("/sys/resource/save", this.formData, function (data, o) {
                    main_panel.editDialogShow = false;
                    main_panel.loadData();
                });
            },
            delete: function (data, store) {
                this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(
                    function () {
                        $.post("/sys/resource/deleteByPrimary/" + data.id, function (res) {
                            if (res.code == 200) {
                                czy.msg.info(res.msg);
                                store.remove(data);
                            } else {
                                czy.msg.error(res.msg);
                            }
                        });
                    }).catch(function () {
                });
            },
            //渲染图标
            renderContent: function (createElement, param) {
                return this.buildOpeBtn(createElement, param.node, param.data, param.store);
            },
            changePage: function (pageNum) {
                this.queryParam.pageNum = pageNum;
                this.loadData();
            },
            getSelectedRow: function () {

            },
            buildOpeBtn: function (createElement, node, data, store) {
                var editBtn = createElement('el-button', {
                    attrs: {size: "mini", type: "warning"}, on: {
                        click: function (event) {
                            event.stopPropagation();    //点击按钮时，树不自动打开
                            main_panel.edit(data);
                        }
                    }
                }, "修改");

                var delBtn = createElement('el-button', {
                    attrs: {size: "mini", type: "danger"}, on: {
                        click: function (event) {
                            // czy.confirm(this, "此操作将永久删除该数据, 是否继续?", "提示",
                            //     "/sys/resource/deleteByPrimary/", data.id,
                            //     function () {
                            //         store.remove(data);
                            //     })
                            main_panel.delete(data, store);
                        }
                    }
                }, "删除");
                var btns;
                if (data.children.length > 0) {
                    btns = [editBtn];
                } else {
                    btns = [editBtn, delBtn];
                }
                return createElement('span', [
                    createElement('span', node.label),
                    // createElement('span', {attrs: {style: "background-color:red; min-width:300px; min-height:60px"}}, data.url),
                    createElement(
                        'span',
                        {attrs: {style: "float: right; margin-right: 20px;"}},
                        btns
                    )
                ])
            }
        },
        created: function () {
            this.loadData();
        }
    })
    ;
