define(['text!sys/param/param-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                buttonshow: false,
                entity: {},
                rules: {
                    code: [
                        {required: true, message: '请输入编码'},
                        seed.validate.englishNumberUnderLine(1, 300)
                    ]
                    ,
                    name: [
                        {required: true, message: '请输入名称'},
                        seed.validate.chineseEnglishNumber(1, 25)
                    ],
                    value: [
                        {required: true, message: '请输入参数值'},
                        seed.validate.englishNumberUnderLine(1, 500)
                    ],
                    memo: [
                        {max: 1000, message: '输入长度不能超过1000字符'}
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
            save: function (entity) {
                var _this = this;
                this.$refs.editForm.validate(function (valid) {
                    if (valid) {
                        _this.buttonshow = true;
                        seed.ajax.postJson({
                            url: 'sys/param/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success")
                                }
                                _this.buttonshow = false;
                            },
                            error: function (data, status) {
                                _this.buttonshow = false;
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        },
        watch: {
            show: function (val, oldVal) {
                if (!val) {
                    this.$refs['editForm'].resetFields();
                }
            }
        }
    };
    return component;
})