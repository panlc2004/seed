define(['text!sys/org/org-edit.html'], function (Template) {
    var component = {
        template: Template,
        data: function () {
            return {
                show: false,
                entity: {},
                ru: {required: true, message: 'test'},
                rules: {
                    orgName: [
                        {required: true, message: '组织名称不能为空'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    orgCode: [
                        {required: true, message: '组织编码不能为空'},
                        {max: 50, message: '输入长度不能超过50字符'}
                    ],
                    memo: [
                        {max: 600, message: '输入长度不能超过500字符'}
                    ]
                }
            }
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
                        czy.ajax.postJson({
                            url: 'sys/org/save',
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

