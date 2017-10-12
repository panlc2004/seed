define(['text!sys/dict/dict-group-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,    //编辑页面是否弹出
                sysDeptList: [], //部门下拉数据
                disabled: false,
                entity: {
                    sysDeptId: ''
                },     //保存表单提交数据
                rules: {
                    code: [
                        {required: true, message: '请选择用户编码'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    name: [
                        {required: true, message: '请输入用户名称'},
                        {max: 60, message: '输入长度不能超过50字符'}
                    ],
                    memo: [
                        {required: true, message: '请输入备注'},
                        {max: 60, message: '输入长度不能超过50字符'}
                    ]
                }
            };
        },
        watch: {
            show: function (val, oldVal) {
                if (!val) {
                    this.$refs['editForm'].resetFields();
                }
            }
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
                        _this.disabled = true;
                        seed.ajax.postJson({
                            url: 'sys/dict/save',
                            data: _this.entity,
                            success: function (data, status) {
                                _this.disabled = false;
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

