<template>
    <div>
        <el-container>

            <el-header style="padding: 0px;display:flex;justify-content:space-between;align-items: center">
                <div style="display: inline">
                    <el-input
                            placeholder="通过登录名名搜索,记得回车哦..."
                            clearable
                            @change="keywordsChange"
                            style="width: 300px;margin: 0px;padding: 0px;"
                            size="mini"
                            :disabled="advanceSearchViewVisible"
                            @keyup.enter.native="initData"
                            prefix-icon="el-icon-search"
                            v-model="queryparm.username">
                    </el-input>
                    <el-button type="primary" size="mini" style="margin-left: 5px" icon="el-icon-search"
                               @click="initData">搜索
                    </el-button>
                    <el-button slot="reference" type="primary" size="mini" style="margin-left: 5px"
                               @click="showAdvanceSearchView"><i
                            class="fa fa-lg"
                            v-bind:class="[advanceSearchViewVisible ? faangledoubleup:faangledoubledown]"
                            style="margin-right: 5px"></i>高级搜索
                    </el-button>
                </div>
                <div style="margin-left: 5px;margin-right: 20px;display: inline">
                    <el-button type="primary" size="mini" icon="el-icon-plus"
                               @click="showAddEmpView">
                        添加
                    </el-button>
                </div>
            </el-header>
            <el-main style="padding-left: 0px;padding-top: 0px">
                <!--高级搜索-->
                <transition name="slide-fade">
                    <div
                            style="margin-bottom: 10px;border: 1px;border-radius: 5px;border-style: solid;padding: 5px 0px 5px 0px;box-sizing:border-box;border-color: #20a0ff"
                            v-show="advanceSearchViewVisible">
                        <el-row>
                        </el-row>
                        <el-row style="margin-top: 10px">
                        </el-row>
                    </div>
                </transition>
                <mybasetable :tableHeader="tableHeader" :tableData="listData" :loading="loading"
                             :rowDblclick="rowDblclick"
                             @tableSelectionChange="tableSelectionChange"
                >
                </mybasetable>

                <div style="display: flex;justify-content: space-between;margin: 2px">
                    <el-button ref="deleteManyButton" type="danger" size="mini"
                               v-if="listData.length>0"
                               :disabled="multipleSelection.length==0"
                               @click="deleteManyRows">批量删除
                    </el-button>
                    <mypage ref="mypage" :totalCount="pageTotal"
                            :handleSizeChange="handleSizeChange" :handleCurrentChange="handleCurrentChange"></mypage>

                </div>
            </el-main>
        </el-container>

        <!--添加弹出框-->
        <addEditDialog  ref="addEditDialog"
              v-on:dialogClose="addDialogCloseHandle"></addEditDialog>
        <mydetail ref="detailDialog" ></mydetail>
    </div>
</template>
<script>

    import mybasetable from '@/components/common/table'
    import mypage from '@/components/common/page'
    import mydetail from '@/components/common/detail'
    import addEditDialog from './AddEditDialog'

    export default {
        components: {
            mybasetable, mypage , addEditDialog,mydetail
        },
        data() {
            let that = this ;
            return {
                //高级搜索是否显示
                advanceSearchViewVisible: false,
                // 条件
                queryparm: {
                    username: ''
                },
                faangledoubleup: 'fa-angle-double-up',
                faangledoubledown: 'fa-angle-double-down',
                //分页组件使用，总页数
                pageTotal: 0,
                pageSize: 10,
                pageNO: 1,
                //列表数据
                listurl: '/user/listForPage',
                addurl: '/user/add',
                editurl: '/user/edit',
                deleteurl: '/user/deleteBatch',
                loading: true,
                listData: [],
                multipleSelection: [],
                tableHeader: [
                    {prop: 'id', label: '主键', minWidth: '100px',isHidden: true},
                    {prop: 'username', label: '登录名', fixed: true, minWidth: '100px'},
                    {prop: 'password', label: '密码', minWidth: '140px'},
                    {prop: 'salt', label: '盐', minWidth: '100px'},
                    {prop: 'locked', label: '是否锁定'
                        , formatData: this.codeTransf.lockedTransf
                    },
                    {prop: 'oper', label: '操作', fixed: 'right', minWidth: '100px'
                        ,oper: [
                            {
                                name: '查看',
                                type: "primary",
                                clickFun: function (row) {
                                    //console.log(row.locked);
                                    that.showDetailView(row);
                                }
                            },
                            {
                                name: '编辑',
                                type: "primary",
                                clickFun: function (row) {
                                    //console.log(row.locked);
                                    that.showEditView(row);
                                }
                            },
                            {
                                name: '删除',
                                type: "danger",
                                clickFun: function (row) {
                                    //console.log(row.locked);
                                    that.deleteHandle(row);
                                }
                            }
                        ]
                    }
                ],
            }
        },
        mounted: function () {
            this.initData();
        },
        methods: {
            initData() {
                var _this = this;
                var param = {
                    pageNO: this.pageNO,
                    pageSize: this.pageSize
                };
                //合并查询参数
                param = Object.assign(this.queryparm, param);
                this.loading = true;
                this.httpOperate.fetchPost(this.listurl, param)
                    .then((response) => {
                        //window.console.log(response)
                        if (response.data.result) {
                            // _this.listData = response.data.result;
                            _this.listData = response.data.result.list;
                            _this.pageTotal = response.data.result.total;
                        }
                        _this.loading = false;
                    })
                    .catch((error) => {
                        window.console.log(error);
                        _this.$alert('username or password wrong!' + error.data.msg, 'info', {
                            confirmButtonText: 'ok'
                        });
                        _this.loading = false;
                    });
            },
            //添加按钮
            showAddEmpView(){
                this.$refs.addEditDialog.saveUrl = this.addurl;
                this.$refs.addEditDialog.dialogIsShow = true ;
                this.$refs.addEditDialog.resetForm();
                this.$refs.addEditDialog.formData.locked = '0';
            },
            //详情按钮
            showDetailView(row){
                this.$refs.addEditDialog.dialogTitle = '详情';
                this.$refs.detailDialog.dialogIsShow = true ;
                this.$refs.detailDialog.tableHeader = this.tableHeader;
                this.$refs.detailDialog.detailData = row ;
            },
            //修改按钮
            showEditView(row){
                this.$refs.addEditDialog.saveUrl = this.editurl;
                this.$refs.addEditDialog.dialogTitle = '修改';
                //先显示，在赋值，才可以清空
                this.$refs.addEditDialog.dialogIsShow = true ;
                //将行 中 属性 放到form表单中
                let formData = this.$refs.addEditDialog.formData ;
                //回显数据$nextTick将回调延迟到下次 DOM 更新循环之后执行
                this.$nextTick(function(){
                    for (var a in formData) {
                        formData[a] = row[a];
                    }
                    window.console.log(row['locked'])
                    //因为是 下拉菜单需要转换 类型
                    this.$refs.addEditDialog.formData.locked = String(row['locked']);
                });
            },
            //删除按钮
            deleteHandle(row){
                this.$confirm('此操作将永久删除[' + row.username + '], 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.doDelete(row.id);
                }).catch(() => {
                });
            },
            //批量删除
            deleteManyRows(){
                var ids = '';
                for(var i=0; i<this.multipleSelection.length; i++){
                    ids = ids+this.multipleSelection[i].id
                    if(i!=this.multipleSelection.length){
                        ids = ids+','
                    }
                }
                this.$confirm(
                    '此操作将永久删除[' + this.multipleSelection[0].username + '等'+this.multipleSelection.length+'个用户], 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.doDelete(ids);
                }).catch(() => {
                });
            },
            doDelete(ids){
                var _this = this;
                this.loading = true;
                var param = {ids: ids} ;
                this.httpOperate.fetchPost(this.deleteurl, param)
                    .then((response) => {
                        //window.console.log(response)
                        if (response.data.result) {
                            _this.initData();
                            _this.$message({
                                message: '操作成功',
                                type: 'success'
                            });
                        }
                        _this.loading = false;
                    })
                    .catch((error) => {
                        _this.loading = false;
                        window.console.log("error: "+error);
                        _this.$alert(error.message, 'info', {
                            confirmButtonText: 'ok'
                        });
                    });
            },
            addDialogCloseHandle(){
            },
            // 条件修改时 查询列表数据
            keywordsChange(val) {
                if (val == '') {
                    this.initData();
                }
            },
            showAdvanceSearchView() {
                this.advanceSearchViewVisible = !this.advanceSearchViewVisible;
                this.queryparm.username = '';
                // if (!this.advanceSearchViewVisible) {
                //     this.emptyEmpData();
                //     this.beginDateScope = '';
                //     this.loadEmps();
                // }
            },
            rowDblclick(row, event, column) {
                console.log('default: ' + row + '---' + event + '---' + column);
            },
            //当列表中多选时
            tableSelectionChange(val){
                // console.log(val);
                this.multipleSelection = val ;
            },
            //当每页大小改变时
            handleSizeChange(pagesize) {
                //console.log(pagesize);
                this.pageSize = pagesize;

                this.initData();
            },
            //当页数改变时
            handleCurrentChange(currentPage) {
                //console.log(currentPage);
                this.pageNO = currentPage;

                this.initData();
            }
        }
    }
</script>