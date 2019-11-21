<template>

    <!--height="250"  增加高度实现固定表头 -->
    <!--@selection-change="handleSelectionChange"  多选时增加此选项-->
    <el-table :data="tableData"
              border fit highlight-current-row
              stripe
              style="width: 100%"
              :height="table_height"
              size="mini"
              @row-dblclick="rowDblclick"
              @selection-change="handleSelectionChange"
              v-loading="loading"
              element-loading-text="拼命加载中"
              element-loading-spinner="el-icon-loading"
              element-loading-background="rgba(0, 0, 0, 0.3)">
        <el-table-column type="index" align="center" fixed></el-table-column>
        <el-table-column type="selection" width="55" fixed></el-table-column>
        <!-- prop: 字段名name, label: 展示的名称, fixed: 是否需要固定(left, right), minWidth: 设置列的最小宽度(不传默认值), oper: 是否有操作列
             oper.name: 操作列字段名称, oper.clickFun: 操作列点击事件, formatData: 格式化内容
             show-overflow-tooltip 单行显示，多余的内容会在 hover 时以 tooltip 的形式显示出来-->
        <el-table-column v-for="(th, key) in tableHeader"
                         :key="key"
                         :prop="th.prop"
                         :label="th.label"
                         :fixed="th.fixed"
                         :min-width="th.minWidth"
                         v-if="!th.isHidden"
                         show-overflow-tooltip
                         align="center">
            <!-- 加入template主要是有操作一栏， 操作一栏的内容是相同的， 数据不是动态获取的，不过我这里操作一栏的名字定死了（oper表示是操作这一列，否则就不是） -->
            <template slot-scope="scope">
                <div v-if="th.oper">
                    <el-button v-for="(o, key) in th.oper" @click="onclick_oper(scope.row,o.clickFun)"
                               style="padding: 3px 4px 3px 4px;margin: 2px"
                               size="mini" :type="o.type">{{o.name}}
                    </el-button>
                </div>
                <div v-else>
                    <span v-if="!th.formatData">{{ scope.row[th.prop] }}</span>
                    <span v-else>{{ formatters(scope.row[th.prop],th.formatData) }}</span>
                </div>
            </template>
        </el-table-column>
    </el-table>
</template>
<script>

    export default {
        name: "basetable",
        //作为子组件的属性值
        //props: ["tableData","tableHeader"],
        //Prop 验证 的写法
        props: {
            table_height: {
                type: Number,
                default: 413
            },
            loading: {
                type: Boolean,
                default: false
            },
            tableData:{
                type: Array,
                default: function () {
                    return []
                }
            },
            tableHeader:{
                type: Array,
                default: function () {
                    return []
                }
            },
            rowDblclick: {
                type: Function,
                default: (row, event, column) => {
                    console.log('default: ' + row + '---' + event + '---' + column)
                }
            }
        },
        watch: {
            multipleSelection: function () {

            }

        },
        data(){
            return {
                // multipleSelection: []
            }
        },
        mounted(){
        },
        methods: {
            formatters(val, format) {
                //window.console.log("formatters is begin。" + typeof (format));
                if (typeof (format) === 'function') {
                    return format(val)
                } else return val
            },
            onclick_oper(row,fun){
                fun(row);
            },
            //当多选时
            handleSelectionChange(val) {
                // console.log("子组件："+val)
                this.$emit('tableSelectionChange', val) // 用来触发父组件定义的@change-type
            }
        }
    }
</script>