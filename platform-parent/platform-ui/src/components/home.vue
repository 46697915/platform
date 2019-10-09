<template>
    <!--<div class="home-div">-->
        <el-container class="home-container" direction="vertical">
            <el-header class="home-header" height="50px">
                <span class="home_title">XXX系统</span>
                <div style="display: flex;align-items: center;margin-right: 7px">
                    <el-badge style="margin-right: 30px">
                        <i class="fa fa-bell-o" style="cursor: pointer">i</i>
                    </el-badge>
                    <el-dropdown>
                          <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center">
                            <!--{{user.name}}-->
                            <i><img
                                    style="width: 40px;height: 40px;margin-right: 5px;margin-left: 5px;border-radius: 40px"/></i>
                          </span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>个人中心</el-dropdown-item>
                            <el-dropdown-item>设置</el-dropdown-item>
                            <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container :style="main_container_style">
                <el-aside class="home-aside"  width="200px">
                    <myMenu :menuList="menuList"></myMenu>
                    <!--<div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">-->
                    <!--<el-menu style="background: #ececec;width: 180px;" unique-opened router>-->
                    <!--<el-submenu index="1">-->
                    <!--<template slot="title">-->
                    <!--<i class="el-icon-location"></i>-->
                    <!--<span>系统管理</span>-->
                    <!--</template>-->
                    <!--<el-menu-item index="sysUser">用户管理</el-menu-item>-->
                    <!--<el-menu-item index="sysUser1">资源管理</el-menu-item>-->
                    <!--<el-submenu index="2">-->
                    <!--<template slot="title">-->
                    <!--<i class="el-icon-location"></i>-->
                    <!--<span>系统管理</span>-->
                    <!--</template>-->
                    <!--<el-menu-item index="sysUser">用户管理</el-menu-item>-->
                    <!--</el-submenu>-->
                    <!--</el-submenu>-->
                    <!--<el-menu-item index="/home/HelloWorld">-->
                    <!--<i class="el-icon-menu"></i>-->
                    <!--<span slot="title">导航二</span>-->
                    <!--</el-menu-item>-->
                    <!--</el-menu>-->
                    <!--</div>-->
                </el-aside>
                <el-main class="home-main">
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item>首页</el-breadcrumb-item>
                        <el-breadcrumb-item></el-breadcrumb-item>
                    </el-breadcrumb>
                    <keep-alive>
                        <router-view></router-view>
                    </keep-alive>
                    <!--<router-view ></router-view>-->
                </el-main>
            </el-container>
            <el-footer class="el-footer" height="60px">Footer</el-footer>
        </el-container>
    <!--</div>-->
</template>
<script>
    import myMenu from '@/components/common/menu/menu'

    export default {
        components: {
            myMenu
        },
        data() {
            return {
                main_container_style:{
                    height: window.innerHeight - 110 + 'px'
                },
                menuList: [
                    {
                        "path": "/home",
                        "title": "系统管理",
                        "children": [
                            {
                                "path": "sysUser",
                                "title": "用户管理",
                                "children": []
                            },
                            {
                                "path": "/func32",
                                "title": "功能3-2",
                                "children": []
                            },
                            {
                                "path": "/func33",
                                "title": "功能3-3",
                                "children": []
                            },
                        ]
                    },
                    {
                        "path": "/func1",     //菜单项所对应的路由路径
                        "title": "功能1",     //菜单项名称
                        "children": []        //是否有子菜单，若没有，则为[]
                    },
                    {
                        "path": "/func2",
                        "title": "功能2",
                        "children": []
                    }
                ]
            }
        },
        mounted: function () {
            // this.$router.push({path: '/home/sysUser'});
        }
    }
</script>
<style  lang="scss">

    .home-div {
        padding: 0px;   //内部填充为0
        margin: 0px;    //外部间距为0
        //height: 100%;   //统一设置高度为100%
    }
    .home-container {
        top: 0px;
        left: 0px;
        width: 100%;
        //height: 100%;
        position: absolute;
    }

    .home-header {
        background-color: #20a0ff;
        color: #333;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: space-between;
        box-sizing: content-box;
        padding: 0px;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
        margin-left: 8px;
    }

    .home-aside {
        background-color: #ECECEC;
    }

    .home-main {
        background-color: #fff;
        color: #000;
        text-align: center;
        margin: 0px;
        padding: 4px;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }

    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 50px;
    }
</style>
