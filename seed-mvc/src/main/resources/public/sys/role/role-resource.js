define(['text!sys/role/role-resource.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                roleId: '',
                show: false,    //编辑页面是否弹出
                resources: [{
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
                this.getResources(roleId);
            },
            close: function () {
                this.show = false;
            },

            getCheckedKeys: function () {
                var _this = this;
                _this.resources.checkIds = this.$refs.tree.getCheckedKeys();
                var param = [];
                this.resources.checkIds.forEach(function (t) {
                    param.push({sysRoleId: _this.roleId, sysResourceId: t});
                })
                seed.ajax.postJson({
                    url: 'sys/roleResource/saveRoleResource',
                    data: param,
                    success: function (data, status) {
                        if (status) {
                            _this.close();
                        }
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