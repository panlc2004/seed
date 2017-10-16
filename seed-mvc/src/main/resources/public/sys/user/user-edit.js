define(['text!sys/user/user-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,    //编辑页面是否弹出
                sysDeptList: [], //部门下拉数据
                entity: {
                    sysDeptId: '',

                },     //保存表单提交数据
                    rules: {
                    sysDeptId: [
                        {required: true, message: '请选择用户部门'},
                        {type:"number", max: 50, message: '输入长度不能超过50字符'}
                    ],
                    name: [
                        {required: true, message: '请输入用户姓名'},
                        {max: 60, message: '输入长度不能超过50字符'}
                    ],
                    telephone: [
                        seed.validate.number(0, 60)
                    ],
                    email: [
                        {type: "email", message: '请输入邮箱'},
                        {max: 100, message: '长度不能超过100字符'}
                    ],
                    username: [
                        seed.validate.englishNumber(6,100)
                    ]
                }
            };
        },
        methods: {
            open: function () {
                this.show = true;
            },
            close: function () {
                this.show = false;
            },
            save: function () {
                var _this = this;
                this.$refs.editForm.validate(function (valid) {
                    if (valid) {
                        seed.ajax.postJson({
                            url: 'sys/user/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            getSysDeptList: function () {
                var _this = this;
                seed.ajax.post({
                    url: 'sys/dept/selectOwnOrgDeptList',
                    success: function (response) {
                        _this.sysDeptList = response.data;
                    }
                })
            }
        },
        created: function () {
            this.getSysDeptList();
        }
    };
    return component;
})

