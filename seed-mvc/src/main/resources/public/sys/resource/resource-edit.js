define(['text!sys/resource/resource-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                buttonshow: false,
                entity: {},
                rules: {
                    code: [
                        {required: true, message: '请输入资源编码'},
                        seed.validate.englishNumberUnderLine(1, 30)
                    ]
                    ,
                    name: [
                        {required: true, message: '请输入资源名称'},
                        seed.validate.chineseEnglishNumber(1, 25)

                    ],
                    url: [
                        {required: true, message: '请输入资源标识'},
                        seed.validate.englishNumber(1, 50)
                    ],
                    types: [
                        {required: true, message: '请输入资源标识'},
                    ]
                }
            };
        },
        methods: {
            open1: function () {
                // this.$nextTick(function() {
                //     alert(document.querySelector('.v-modal').parentNode.tagName);
                // });
            },
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
                            url: 'sys/resource/save',
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
        }
    };
    return component;
})