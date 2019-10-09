<template>
    <div style="text-align: left">
        <el-dialog :title="dialogTitle" :visible.sync="dialogIsShow" width="60%" @close="dialogClose">
            <el-row v-for="(dd, index) in tableHeader" v-if="index%2==0">
                <el-col :span="4">
                    {{dd.oper}}
                    <div v-if="!dd.oper" style="text-align: right">
                        {{tableHeader[index].label}}：
                    </div>
                </el-col>
                <el-col :span="8">
                    <div v-if="!dd.oper" >
                        <span v-if="!tableHeader[index].formatData">{{detailData[tableHeader[index].prop]}}</span>
                        <span v-else>{{ formatters(detailData[tableHeader[index].prop],tableHeader[index].formatData) }}</span>
                    </div>
                </el-col>
                <el-col :span="4">
                    <div v-if="index+1<tableHeader.length && !tableHeader[index+1].oper" style="text-align: right">
                        {{tableHeader[index+1].label}}：
                    </div>
                </el-col>
                <el-col :span="8">
                    <div v-if="index+1<tableHeader.length && !tableHeader[index+1].oper">
                        <span v-if="!tableHeader[index+1].formatData">{{detailData[tableHeader[index+1].prop]}}</span>
                        <span v-else>{{ formatters(detailData[tableHeader[index+1].prop],tableHeader[index+1].formatData) }}</span>
                    </div>
                </el-col>
            </el-row>
        </el-dialog>
    </div>
</template>
<script>

    export default {
        name: "baseDetail",
        props: {},
        data() {
            return {
                dialogTitle: '详情',
                dialogIsShow: false,
                tableHeader: [],
                detailData: []
            }
        },
        methods: {
            formatters(val, format) {
                //window.console.log("formatters is begin。" + typeof (format));
                if (typeof (format) === 'function') {
                    return format(val)
                } else return val
            },
            dialogClose() {

            }
        }
    }
</script>