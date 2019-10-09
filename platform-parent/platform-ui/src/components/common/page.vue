<template>
    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 50, 100]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCount">
    </el-pagination>
</template>
<script>
    export default {
        props: {
            currentPage: {
                type: Number,
                default: 1
            },
            totalCount: {
                type: Number,
                default: 0
            },
            //当页面大小发生变化的时候，应调用父页面的toPage加载页面
            handleSizeChange: {
                type: Function,
                default: (pagesize) => {
                    console.log('当前pagesize: ' + pagesize)
                }
            },
            //当当前页数发生变化的时候调用父页面的toPage加载页面
            handleCurrentChange: {
                type: Function,
                default: (currentPage) => {
                    console.log('当前page: ' + currentPage)
                }
            }
        },
        data() {
            return {
            }
        },
        methods: {
        }
    }
</script>