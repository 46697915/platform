<template>

    <!--窗口-->
    <el-form :model="formData" ref="formData"
             label-position="right"
             label-width="100px" size="mini"  style="margin: 0px;padding: 0px;">
        <div style="text-align: left">
            <el-dialog :title="dialogTitle" :visible.sync="dialogIsShow"
                       @close="dialogClose"
                       v-loading="loading"
                       element-loading-text="加载中"
                       element-loading-spinner="el-icon-loading"
                       element-loading-background="rgba(0, 0, 0, 0.3)"
                       width="60%" >
<#assign fields = []>
<#list table.fields as field>
    <#if field.propertyName!='id'>
        <#assign fields=fields+[field]>
    </#if>
</#list>
<#list fields as field>
    <#if (field_index%2==0) >
                <el-row>
    </#if>
                    <el-col :span="12">
                        <el-form-item label="${field.comment}" prop="${field.propertyName}">
                            <el-input v-model="formData.${field.propertyName}"></el-input>
                        </el-form-item>
                    </el-col>
    <#if (field_index%2==1 || !field_has_next) >
                </el-row>
    </#if>
</#list>
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
<#list table.fields as field>
                    ${field.propertyName}: ''<#if field_has_next >,</#if>
</#list>
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
                loading: false,
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
                this.loading = true;
                this.httpOperate.fetchPost(this.saveUrl, param)
                    .then((response) => {
                        //window.console.log(response)
                        if (response.data.result) {
                            _this.dialogIsShow = false;
                            _this.resetForm();
                            _this.$parent.initData();
                        }
                        _this.loading = false;
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
                        _this.loading = false;
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
