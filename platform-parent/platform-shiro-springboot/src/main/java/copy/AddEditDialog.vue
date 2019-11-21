<template>

    <!--窗口-->
    <el-form :model="formData" ref="formData"
             label-position="right"
             label-width="100px" size="mini"  style="margin: 0px;padding: 0px;">
        <div style="text-align: left">
            <el-dialog :title="dialogTitle" :visible.sync="dialogIsShow" width="60%" @close="dialogClose">
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="权限" prop="permission">
                            <el-input v-model="formData.permission"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="名称" prop="description">
                            <el-input v-model="formData.description"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="地址" prop="url">
                            <el-input v-model="formData.url"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="类型" prop="type">
                            <el-input v-model="formData.type"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="路径" prop="path">
                            <el-input v-model="formData.path"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="父id" prop="parentid">
                            <el-input v-model="formData.parentid"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="12">
                        <el-form-item label="图标" prop="icon">
                            <el-input v-model="formData.icon"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="是否可用" prop="enabled">
                            <el-input v-model="formData.enabled"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-form-item>
                    <el-button type="primary" @click="submitForm()">保存</el-button>
                    <el-button @click="resetForm()">重置</el-button>
                </el-form-item>
            </el-dialog>
        </div>
    </el-form>
</template>

<script>

    export default {
        //属性，接收父页面传值
        data() {
            return {
                dialogTitle: '新增',
                dialogIsShow: false,
                formData: {
                    permission: '',
                    id: '',
                    description: '',
                    url: '',
                    type: '',
                    path: '',
                    parentid: '',
                    icon: '',
                    enabled: ''
                },
                lockedoptions:[
                    {
                        value: '1',
                        label: '已锁定'
                    },{
                        value: '0',
                        label: '未锁定'
                    }
                ],

                saveUrl: ""
            }
        },
        methods: {
            submitForm() { //添加
                //保存新增加的菜单数据
                let param = this.$data.formData;
                //获取查询参数，将参数解析成键值对
                let qs = require("qs");

                var _this = this;
                this.$parent.loading = true;
                this.httpOperate.fetchPost(this.saveUrl, param)
                    .then((response) => {
                        //window.console.log(response)
                        if (response.data.result) {
                            _this.dialogIsShow = false;
                            _this.resetForm();
                            _this.$parent.initData();
                        }
                        _this.$parent.loading = false;
                    })
                    .catch((error) => {
                        window.console.log(error);
                        if(error.data){
                            _this.$alert('错误消息 : ' + error.data.msg, 'info', {
                                confirmButtonText: 'ok'
                            });
                        }
                        if(error){
                            _this.$alert('错误消息 : ' + error, 'info', {
                                confirmButtonText: 'ok'
                            });
                        }
                        _this.$parent.loading = false;
                    });
            },
            resetForm() { //重置
                this.$refs['formData'].resetFields();
                // this.$refs.formData.resetFields();
            },
            dialogClose() { //窗口关闭事件
                //弹出框关闭是，触发dialogClose事件，父组件处理此事件
                // this.$emit("dialogClose", false);
                // this.$parent.addDialogIsShow = false ;
                this.dialogIsShow = false;
            }
        },
        watch: {
            // addDialogIsShow: function () {  //监控父组件传过来的属性变化
            //   this.dialogIsShow = this.addDialogIsShow;
            // }
        }
    }
</script>

<style>

</style>
