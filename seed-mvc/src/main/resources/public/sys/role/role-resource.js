define(['text!sys/role/role-resource.html', 'css!sys/role/role-resource.css'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                roleId: -1,
                show: false,    //编辑页面是否弹出
                resources: [{
                    parentIds: [],//checkIds的父ID
                    checkIds: [],//用户拥有的资源的ID
                    resourceData: []//所有的资源
                }],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
            }
        },
        methods: {
            open: function (roleId) {
                this.roleId = roleId;
                this.show = true;
                this.getResources(roleId)
            },
            close: function () {
                this.show = false;
            },
            getCheckedKeys: function () {
                var _this = this;
                var roleResources = [];
                this.$refs.tree.getCheckedKeys().forEach(function (t) {
                    roleResources.push({sysRoleId: _this.roleId, sysResourceId: t});
                })
                console.log(roleResources);
                seed.ajax.postJson({
                    url: 'sys/roleResource/saveRoleResource',
                    data: roleResources,
                    success: function (data, status) {
                        if (status) {
                            _this.close();
                        }
                    },
                    error: function (data, status) {
                    }
                })
            },
            getResources: function (roleId) {
                var _this = this;
                seed.ajax.postJson({
                    url: 'sys/roleResource/selectResources/' + roleId,
                    success: function (data, status) {
                        if (status) {
                            _this.resources = data.data.resourceData;
                            _this.resources.checkIds = data.data.checkIds;
                        }
                    },
                    error: function (data, status) {
                        alert(data.message);
                    }
                })
            }
        }
    };
    return component;
});