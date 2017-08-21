sys$org_edit_html = Vue.extend({
    template: '#sys__org_edit_html',
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
                    {max: 600, message: '输入长度不能超过50字符'}
                ]
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
                    czy.ajax.postJson({
                        url: ctx + '/sys/org/save',
                        data: app.entity,
                        success: function (data, status) {
                            if (status) {
                                _this.cancel();
                                parent.app.search();    //TODO 刷新页面
                            }
                        }
                    })
                } else {
                    return false;
                }
            });

        }
    },
    created: function () {
        //创建定时器一定要在关闭方法里销毁！
        this.timer = window.setInterval(function () {
            console.log(66666)
        }, 1000)
    },
    destroyed: function () {
        //创建定时器一定要在destroyed里销毁！
        window.clearInterval(this.timer);
    }
});


