/**
 * Created by PLC on 2017/5/30.
 */
$(document).ready(function () {

    // 工具栏
    var toobar = new Vue({
        el: '#toolbar',
        data: {}
    })

    // 组织结构树
    var orgTree = new Vue({
        el: '#orgTree',
        data: {
            data: [],
            defaultProps: {
                children: 'children',
                label: 'name'
            }
        },
        methods: {
            handleNodeClick(node) {
                console.log(node);
            },
            renderContent(createElement, {node, data, store}) {
                // console.log(h);
                // // return '123123';
                // return node;
                // '<span>'+
                //     '<span>'+
                //         '<span>{node.label}</span>'+
                //     ' </span>'+
                //     '<span style="float: right; margin-right: 20px">'+
                //         '<el-button size="mini" on-click={ () => this.append(store, data) }>Append</el-button>'+
                //         '<el-button size="mini" on-click={ () => this.remove(store, data) }>Delete</el-button>'+
                //     '</span>'+
                // '</span>';

                return createElement('span', [
                    createElement('span', node.label),
                    createElement('span',
                        {attrs: {style: "float: right; margin-right: 20px"}},
                        [
                            createElement('el-button', {
                                attrs: {size: "mini"}, on: {
                                    click: function (event) {
                                        store.append({id: null, name: 'TEST', children: []}, data);
                                        event.stopPropagation();
                                    }
                                }
                            }, "添加"),
                            createElement('el-button', {
                                attrs: {size: "mini"}, on: {
                                    click: function (event) {
                                        store.remove({id: null, name: 'TEST', children: []}, data);
                                        event.stopPropagation();
                                    }
                                }
                            }, "删除")
                        ])
                ]);

            }
        },
        created: function () {
            this.$http.post("/sys/org/selectOrgTree").then(
                success => {
                    this.data = success.body;
                    console.log(this.data);
                }, failure => {
                    alert(failure.body)
                }
            )
        }
    });

});