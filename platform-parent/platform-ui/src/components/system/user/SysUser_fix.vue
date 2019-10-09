<template>
    <el-table
            :data="users"
            border
            element-loading-text="拼命加载中"
            element-loading-spinner="el-icon-loading"
            size="mini"
            style="width: 100%">
        <el-table-column type="selection" align="left" width="30"></el-table-column>
        <el-table-column fixed prop="username" label="姓名" width="120"></el-table-column>
        <el-table-column prop="password" label="密码" width="120"></el-table-column>
        <el-table-column prop="salt" label="盐" width="120"></el-table-column>
        <el-table-column prop="locked" label="是否锁定" width="300"></el-table-column>
        <el-table-column
                fixed="right"
                label="操作"
                width="100">
            <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>
<script>

    export default {
        data() {
            return {
                listurl: 'http://127.0.0.1:8083/user/list',
                users: [],
                tableData: [{
                    date: '2016-05-02',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市普陀区金沙江路 1518 弄',
                    zip: 200333
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市普陀区金沙江路 1517 弄',
                    zip: 200333
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市普陀区金沙江路 1519 弄',
                    zip: 200333
                }, {
                    date: '2016-05-03',
                    name: '王小虎',
                    province: '上海',
                    city: '普陀区',
                    address: '上海市普陀区金沙江路 1516 弄',
                    zip: 200333
                }]
            }
        },
        mounted: function () {
            this.initData();
        },
        methods: {
            initData() {
                var _this = this;
                this.httpOperate.fetchPost(this.listurl)
                    .then((response) => {
                        window.console.log(response)
                        // _this.$alert(response.data.result, 'info', {
                        //     confirmButtonText: 'ok'
                        // });
                        if(response.data.result){
                            _this.users = response.data.result;
                        }
                    })
                    .catch((error) => {
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