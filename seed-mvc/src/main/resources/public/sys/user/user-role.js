define(['text!sys/user/user-role.html', 'css!sys/user/user-role.css'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                userId: '',
                show: false,    //编辑页面是否弹出
                allRoles: [],        //角色数据
                userRoles: []
            };
        },
        methods: {
            open: function (userId) {
                this.userId = userId;
                this.show = true;
                this.selectRoleInfo();
            },
            close: function () {
                this.show = false;
            },
            saveRole: function () {
                var _this = this;
                var param = {
                    userId: _this.userId,
                    roleIds: _this.userRoles
                };
                $.ajax({
                    type: 'post',
                    url: 'sys/userRole/saveUserRole',
                    data: param,
                    success: function (response) {
                        _this.close();
                        _this.$emit('role-save')
                    }
                })
            },
            filterRole: function (query, item) {
                if (item && item.name) {
                    return item.name.indexOf(query) > -1;
                } else {
                    return false;
                }
            },
            selectRoleInfo: function (userId) {
                var _this = this;
                seed.ajax.post({
                    url: 'sys/user/selectRoleInfo/' + _this.userId,
                    success: function (response) {
                        //渲染所有角色
                        _this.allRoles = response.data.allRoles;
                        //渲染已有角色
                        response.data.userRoles.forEach(function (item) {
                            _this.userRoles.push(item.sysRoleId);
                        })
                    }
                })
            },
            afterClose: function () {
                this.userRoles = [];
            }
        }
    };

    return component;

})

