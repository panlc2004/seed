<template>
    <div class="login-container max-height max-width abs">
        <el-form class="login-form" :model="loginForm" :rules="loginFormRules" ref="loginForm">
            <el-form-item prop="username">
                <el-input placeholder="请输入账号" v-model="loginForm.username">
                    <template slot="prepend"><i class="el-icon-edit"></i></template>
                </el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input placeholder="请输入密码" type="password" v-model="loginForm.password">
                    <template slot="prepend"><i class="el-icon-edit"></i></template>
                </el-input>
            </el-form-item>
            <el-form-item>
                <div class="login-input">
                    <el-button class="login-btn" type="primary" @click="login">
                        登&nbsp;&nbsp;录
                    </el-button>
                </div>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                loginForm: {
                    username: 'admin',
                    password: 'admin'
                },
                loginFormRules: {
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
        },
        methods: {
            login() {
                this.axios.post('/login', {
                    username: this.loginForm.username,
                    lastName: this.loginForm.password
                }).then(function (response) {
                    console.log(response);
                });
            },
        }
    }
</script>
<style>
    .abs {
        position: absolute;
    }

    .max-width {
        width: 100%;
    }

    .max-height {
        height: 100%;
    }

    .login-container {
        background: url("../assets/image/bg_sky.jpg") no-repeat;
        background-size: cover;
    }

    .login-input {
        margin-top: 30px;
    }

    .login-btn {
        width: 100%;
        background: #f40;
        border-color: #f40;
        font-size: 16px;
        font-weight: 700;
        font-family: "PingFang SC";
    }

    .login-form {
        width: 300px;
        height: 250px;
        position: absolute;
        top: 200px;
        right: 300px;
    }

</style>