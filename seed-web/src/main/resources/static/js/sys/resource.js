/**
 * Created by PLC on 2017/6/3.
 */
var main_panel = new Vue({
    el: '#main-panel',
    data: {
        formData:1231231,
        queryParam:{
            params:{}
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
        query: function () {
            this.queryParam.pageNum = 1;
            this.loadData();
        },
        loadData: function () {
            var _this = this;
            $.post("/sys/resource/selectResourceTree", this.queryParam, function (data) {
                _this.treeData = data.data;
            });
        },
        nodeClick:function () {
            
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
            };
            // 将选中的节点的id值做为新增机构的parentId
            this.editDialogShow = true;
        },
        edit: function () {
            if (this.selectedRow == null) {
                czy.msg.error("请选择要操作的数据");
                return;
            }
            this.formData = this.selectedRow;
            this.editDialogShow = true;
        },
        save: function () {
            console.log(this.formData);
            czy.ajax.post("/sys/resource/save", this.formData, function (data,o) {
                main_panel.editDialogShow = false;
                main_panel.loadData();
            });
        },
        //渲染图标
        renderContent: function (createElement, param) {
            return buildOpeBtn(createElement, param.node, param.data, param.store);
        },
        changePage: function (pageNum) {
            this.queryParam.pageNum = pageNum;
            this.loadData();
        },
        getSelectedRow:function(){

        }
    },
    created: function () {
        this.loadData();
    }
});

function buildOpeBtn(createElement, node, data, store) {
    var editBtn = createElement('el-button', {
        attrs: {size: "mini", type: "warning"}, on: {
            click: function (event) {
                event.stopPropagation();    //点击按钮时，树不自动打开
                openEditWin(store, data.id)
            }
        }
    }, "修改");

    var delBtn = createElement('el-button', {
        attrs: {size: "mini", type: "danger"}, on: {
            click: function (event) {
                // store.remove({id: null, name: 'TEST', children: []}, data);
                event.stopPropagation();
                deleteOrg(data.id);
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
        createElement(
            'span',
            {attrs: {style: "float: right; margin-right: 20px"}},
            btns
        )
    ])
}