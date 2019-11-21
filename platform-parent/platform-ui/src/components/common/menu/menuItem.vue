<template>
    <div v-if="item.subList">
        <template v-if="item.subList.length == 0">
            <el-menu-item :index="item.path">
                <i class="el-icon-menu"></i>
                {{item.description}}
            </el-menu-item>
        </template>

        <el-submenu v-else :index="item.path">
            <template slot="title" >
                <i class="el-icon-menu"></i>
                {{item.description}}
            </template>

            <template v-for="child in item.subList">
                <sidebar-item
                        v-if="child.subList&&child.subList.length>0"
                        :item="child"
                        :key="child.path"/>
                <el-menu-item v-else :key="child.path" :index="child.path">
                    <i class="el-icon-location"></i>
                    {{child.description}}
                </el-menu-item>
            </template>
        </el-submenu>
    </div>
</template>

<script>
    export default {
        name: 'SidebarItem',
        props: {
            item: {
                type: Object,
                required: true
            }
        }
    }
</script>