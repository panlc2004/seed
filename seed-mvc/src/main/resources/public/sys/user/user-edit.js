define(['text!sys/user/user-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                entity: {},
                rules: {
                    orgName: [
                        {required: true, message: '请输入组织名称'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    orgCode: [
                        {required: true, message: '请输入组织编码'},
                        seed.validate.englishNumberUnderLine(1,50)
                    ],
                    memo: [
                        {max: 600, message: '输入长度不能超过500字符'}
                    ]
                }
            };
        },
        methods: {
            open1:function () {
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
            }
        },
        created:function () {
            // seed.validate.test();
        }
    };
    return component;
})

