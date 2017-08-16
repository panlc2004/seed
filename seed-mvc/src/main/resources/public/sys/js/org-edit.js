var app = new Vue({
    el: '#app',
    data: function () {
        return {
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
        validatePass: function (rule, value, callback) {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (app.entity.name !== '') {
                    // this.$refs.ruleForm2.validateField('checkPass');
                }
                callback();
            }
        },
        cancel: function () {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
        },
        save: function () {
            $.getJSON(ctx + '/sys/org/insert', JSON.stringify(this.entity), function (status, data) {
                console.log(data);
            })
        }
    }
});


