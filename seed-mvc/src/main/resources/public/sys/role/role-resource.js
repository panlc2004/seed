define(['text!sys/role/role-resource.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                data2: [{
                    id: 1,
                    label: '一级 1',
                    children: [{
                        id: 4,
                        label: '二级 1-1',
                        children: [{
                            id: 9,
                            label: '三级 1-1-1'
                        }, {
                            id: 10,
                            label: '三级 1-1-2'
                        }]
                    }]
                }, {
                    id: 2,
                    label: '一级 2',
                    children: [{
                        id: 5,
                        label: '二级 2-1'
                    }, {
                        id: 6,
                        label: '二级 2-2'
                    }]
                }, {
                    id: 3,
                    label: '一级 3',
                    children: [{
                        id: 7,
                        label: '二级 3-1'
                    }, {
                        id: 8,
                        label: '二级 3-2'
                    }]
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
                this.selectRoleInfo();
            },
            close: function () {
                this.show = false;
            },
            // open: function (userId) {
            //     this.userId = userId;
            //     this.show = true;
            //     this.selectRoleInfo();
            // },
            // close: function () {
            //     this.show = false;
            // },
            saveRole: function () {
                var _this = this;
                var param = {
                    userId: _this.userId,
                    roleIds: _this.userRoles
                };
                $.ajax({
                    type: "POST",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    url: 'sys/user/saveUserRoles/',
                    data: JSON.stringify(param),
                    success: function (response) {

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
            selectRoleInfo: function (roleId) {
                var _this = this;
                seed.ajax.post({
                    url: 'sys/user/selectRoleInfo/' + _this.roleId,
                    success: function (response) {
                        _this.allRoles = response.data.allRoles;
                        response.data.userRoles.forEach(function (item) {
                            _this.userRoles.push(item.id);
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

