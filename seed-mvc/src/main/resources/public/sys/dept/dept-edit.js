define(['text!sys/dept/dept-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                entity: {},
                rules: {
                    name: [
                        {required: true, message: '请输入部门名称'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    code: [
                        {required: true, message: '请输入部门编码'},
                        seed.validate.englishNumberUnderLine(1,50)
                    ],
                    memo: [
                        {max: 600, message: '输入长度不能超过500字符'}
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
                            url: 'sys/dept/save',
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

