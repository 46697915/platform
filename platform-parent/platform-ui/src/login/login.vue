<template>
    <div>
        <el-row :gutter="20">
            <el-col></el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col></el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col></el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col></el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col></el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="6" :offset="12">
                <!--<el-radio-group v-model="labelPosition" size="small">-->
                <!--<el-radio-button label="left">左对齐</el-radio-button>-->
                <!--<el-radio-button label="right">右对齐</el-radio-button>-->
                <!--<el-radio-button label="top">顶部对齐</el-radio-button>-->
                <!--</el-radio-group>-->
                <div style="margin: 20px;">
                    <h3 class="title">登录</h3>
                </div>
                <el-form :label-position="labelPosition" label-width="80px" :model="loginForm" ref="loginForm">
                    <el-form-item label="用户名">
                        <el-input v-model="loginForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="loginForm.password"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码">
                        <el-input v-model="loginForm.verification"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="width:100%;" @click="onSubmit">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>
<script>
    //import https from '/src/https.js'

    export default {
        name: 'Login',
        data() {
            return {
                labelPosition: 'right',
                logining: true,
                url: 'http://127.0.0.1:8083/user/login',
                loginForm: {
                    username: '',
                    password: '',
                    verification: ''
                }
            };
        },
        methods: {
            onSubmit() {
                let that = this ;
                this.httpOperate.fetchGet(this.url, this.loginForm)
                    .then((response) => {
                        //console.log(response.data);
                        if(response.data.success){
                            that.$router.push({path: '/home'});
                        }else{
                            that.$alert(response.data.msg, 'info', {
                                confirmButtonText: 'ok'
                            });
                        }
                    })
                    .cache((error) => {
                        //console.log(error);
                        that.logining = false;
                        that.$alert('username or password wrong!'+error.data.msg, 'info', {
                            confirmButtonText: 'ok'
                        });
                    });
            }
        }
    }
</script>
<style lang="scss">
    $bg: #2d3a4b;
    .login-container {
        min-height: 100%;
        width: 100%;
        background-color: $bg;
        overflow: hidden;
    }

    .el-row {
        margin-bottom: 20px;

        &
        :last-child {
            margin-bottom: 0;
        }

    }

    .el-col {
        border-radius: 4px;
    }

    .bg-purple-dark {
        background: #99a9bf;
    }

    .bg-purple {
        background: #d3dce6;
    }

    .bg-purple-light {
        background: #e5e9f2;
    }

    .grid-content {
        border-radius: 4px;
        min-height: 36px;
    }

    .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
    }
</style>