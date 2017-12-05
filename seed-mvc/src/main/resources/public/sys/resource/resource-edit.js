define(['text!sys/resource/resource-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                saveDisabled: false,
                entity: {},
                rules: {
                    name: [
                        {required: true, message: '请输入资源名称'},
                        {max: 25, message: '输入长度不能超过25字符'},
                        seed.validate.chineseEnglishNumber(1, 25)
                    ],
                    code: [
                        {required: true, message: '请输入资源编码'},
                        {max: 30, message: '输入长度不能超过30字符'}
                    ],
                    url: [
                        {required: true, message: '请输入资源标识'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    types: [
                        {required: true, message: '请选择类型'},

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
                        _this.saveDisabled = true;
                        _this.parentId = 0;
                        seed.ajax.postJson({
                            url: 'sys/resource/save',
                            data: _this.entity,
                            success: function (data, status) {
                                if (status) {
                                    _this.close();
                                    _this.$emit("save-success");
                                }
                                _this.saveDisabled = false;
                            },
                            error: function (data, status) {
                                _this.saveDisabled = false;
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