/**
 * Created by lenovo on 2017-06-01.
 */
new Vue({
    el: '.login-box',
    data: function () {
        return {
            ruleForm: {
                username: 'admin',
                password: 'admin'
            },
            rules: {
                username: [
                    {required: true, message: '请填写账号', trigger: 'blur'},
                    {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '请填写密码', trigger: 'blur'},
                    {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                ]
            }
        };
    }
});