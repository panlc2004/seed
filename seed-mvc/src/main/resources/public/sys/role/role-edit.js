define(['text!sys/role/role-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                entity: {},
                rules: {
                    name: [
                        {required: true, message: '请输入组织名称'},
                        seed.validate.chineseEnglishNumber(1, 50)
                    ],
                    code: [
                        {required: true, message: '请输入组织编码'},
                        seed.validate.englishNumberUnderLine(1, 100)
                    ],
                    memo: [
                        {max: 1000, message: '输入长度不能超过500字符'}
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
                        debugger;
                        seed.ajax.postJson({
                            url: 'sys/role/save',
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
            }
        }
    };
    return component;
})

