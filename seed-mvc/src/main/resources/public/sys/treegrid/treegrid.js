define(['text!sys/treegrid/treegrid.html'], function (Template) {
    Array.prototype.removeByValue = function (val) {
        //对数组原型添加删除指定项的方法
        for (var i = 0; i < this.length; i++) {
            if (this[i] == val) {
                this.splice(i, 1);
                break;
            }
        }
    };
    var treegrid = {
        name: 'TreeGrid',
        components: {},
        data: function () {
            return {
                TableDate: []
            }
        },
        computed: {
            allData: function () {
                var me = this;
                var newData = $.extend({}, me.$store.getters.Data);
                return newData;
            }
        },
        watch: {
            allData: function (val) {
                this.TableDate = $.extend({}, val);
            }
        },
        methods: {
            toggleIconShow: function (record) {
                /**
                 * 点击展开和关闭的时候，图标的切换
                 */
                var me = this;
                if (record.children && record.children.length > 0) {
                    return true
                }
                return false
            },
            toggle: function (rowData) {
                var me = this;
                /**
                 * 展开下级
                 */
                var childLen = rowData.children.length;
                if (rowData._expanded) {
                    var dataArr = [];
                    dataArr.push(rowData);
                    var arr = me.getChildFlowId(dataArr, []);
                    for (var i = 0; i < childLen; i++) {
                        me.TableDate.map(function (value) {
                            if (arr.indexOf(value.parentId) > -1
                            ) {
                                me.TableDate.removeByValue(value);
                            }
                        })
                        ;
                    }
                } else {
                    rowData.children = me.setSpaceIcon(rowData.children, rowData._level);
                    var index = me.TableDate.indexOf(rowData);
                    var pre = me.TableDate.slice(0, index + 1);
                    var last = me.TableDate.slice(index + 1);
                    var concatChildren = pre.concat(rowData.children);
                    me.TableDate = concatChildren.concat(last);
                }
                rowData._expanded = !rowData._expanded;
            },
            getChildFlowId: function (data, emptyArr) {
                // 获取子级的flowId
                var me = this;
                Array.from(data).forEach(function (record) {
                    emptyArr.push(record.flowId);
                    if (record.children && record.children.length > 0) {
                        var childFlowIdArr = me.getChildFlowId(record.children, emptyArr);
                        emptyArr.concat(childFlowIdArr);
                    }
                });
                return emptyArr;
            },
            setSpaceIcon: function (data, level) {
                // 设置第一列的空格和方向按钮
                var me = this;
                var _level = 0;
                data.forEach(function (value) {
                    value._expanded = false;
                    if (level !== undefined && level !== null) {
                        _level = level + 1;
                    } else {
                        _level = 1;
                    }
                    value._level = _level;
                    if (value.children && value.children.length > 0) {
                        me.setSpaceIcon(value.children, _level);
                    }
                })
                ;
                return data;
            }
        }
    }
});