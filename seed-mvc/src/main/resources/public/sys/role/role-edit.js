define(['text!sys/role/role-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                disabled: false,
                entity: {},
                rules: {
                    name: [
                        {required: true, message: '请输入角色名称'},
                        {max: 50, message: '输入长度不能超过50字符'},
                        seed.validate.chineseEnglishNumber(1, 50)
                    ],
                    code: [
                        {required: true, message: '请输入角色编码'},
                        seed.validate.number(1, 50, 100)
                    ],
                    memo: [
                        {max: 1000, message: '输入长度不能超过1000字符'}
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
                        seed.ajax.postJson({
                            url: 'sys/role/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                                _this.disabled = false;
                            },
                            error: function (data, status) {
                                _this.disabled = false;
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        },
    };
    return component;
})

