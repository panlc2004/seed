/**
 * Created by PLC on 2017/5/30.
 */
$(document).ready(function () {

    //主窗口
    var main = new Vue({
        el: '#main',
        data: {
            //组织结构树
            treeData: [],   //机构树数据
            defaultExpandedKeys: [0],
            defaultProps: {
                children: 'children',
                label: 'name'
            },
            //操作窗口
            sysOrg: {},
            operateDialogShow: false,    //操作窗口默认不显
            formLabelWidth: '70px'      //标题宽度
        },
        methods: {
            loadTree(){
                this.$http.post("/sys/org/selectOrgTree").then(
                    success => {
                        this.treeData = success.body;
                    }, failure => {
                        this.$message({
                            message: '获取机构数据失败，请联系管理员',
                            type: 'error',
                            showClose: 'true',
                        });
                    }
                )
            },
            //机构树点击事件
            handleNodeClick(node) {
                console.log(node);
            },
            //渲染图标
            renderContent(createElement, {node, data, store}) {
                return createElement('span', [
                    createElement('span', node.label),
                    createElement('span',
                        {attrs: {style: "float: right; margin-right: 20px"}},
                        [
                            // createElement('el-button', {
                            //     attrs: {size: "mini", type: "primary"}, on: {
                            //         click: function (event) {
                            //             event.stopPropagation();    //点击按钮时，树不自动打开
                            //             store.append({id: null, name: 'TEST', children: []}, data);
                            //             // openOperateWin(store);
                            //         }
                            //     }
                            // }, "添加"),
                            createElement('el-button', {
                                attrs: {size: "mini", type: "warning"}, on: {
                                    click: function (event) {
                                        event.stopPropagation();    //点击按钮时，树不自动打开
                                        openEditWin(store, data.id)
                                    }
                                }
                            }, "修改"),
                            createElement('el-button', {
                                attrs: {size: "mini", type: "danger"}, on: {
                                    click: function (event) {
                                        // store.remove({id: null, name: 'TEST', children: []}, data);
                                        event.stopPropagation();
                                        deleteOrg(data.id);
                                    }
                                }
                            }, "删除")
                        ])
                ]);

            },
            //获取选中的树节点
            //打开组织机构保存弹窗
            openAddWin(){
                // 获取选中的树节点
                var selectedNode = this.$refs.orgTree.getCheckedNodes();
                if (selectedNode.length == 0) {          //未选中
                    this.sysOrg.parentId = 0;           //未选中时默认增加最高级组织机构：parentId = 0
                } else if (selectedNode.length > 1) {    //选中超过一条
                    this.$message({
                        message: '只能指定一个所属机构',
                        type: 'error',
                        showClose: 'true',
                        duration: '1000'
                    });
                    return;
                } else {
                    this.sysOrg.parentId = selectedNode[0].id;
                }
                // 将选中的节点的id值做为新增机构的parentId
                this.operateDialogShow = true;
            },

            // 保存组织机构
            saveOrg(){
                this.$http.post("/sys/org/save", this.sysOrg).then(
                    success => {
                        this.operateDialogShow = false;  //关闭窗口
                        this.loadTree();
                    }, failure => {
                        console.log(this.data);
                    }
                )
            },
        },
        created: function () {
            this.loadTree();
        }
    });

    function openEditWin(store, id) {
        Vue.http.post("/sys/org/loadData", {params:{id:id}}).then(
            success => {
                main.sysOrg = success.body;
                main.operateDialogShow = true;
            }, failure => {
                alert(error + failure);
            }
        )
    };

    function deleteOrg(id) {
        main.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            main.$message({
                type: 'success',
                message: '删除成功!'
            });
        }).catch(() => {
            main.$message({
                type: 'info',
                message: '已取消删除'
            });
        });



        Vue.http.post("/sys/org/deleteOrg", {params:{id:id}}).then(
            success => {
                main.sysOrg = success.body;
                main.operateDialogShow = true;
            }, failure => {
                alert(error + failure);
            }
        )

    }
});