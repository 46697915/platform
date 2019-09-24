<template>
    <div>
        <el-container class="home-container">
            <el-header class="home-header">
                <span class="home_title">权限系统</span>
                <div style="display: flex;align-items: center;margin-right: 7px">
                    <el-badge style="margin-right: 30px"  >
                        <i class="fa fa-bell-o" @click="goChat" style="cursor: pointer"></i>
                    </el-badge>
                    <el-dropdown >
  <span class="el-dropdown-link home_userinfo" style="display: flex;align-items: center">

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
            <el-container>
                <el-aside width="180px" class="home-aside">
                    <div style="display: flex;justify-content: flex-start;width: 180px;text-align: left;">
                        <el-menu style="background: #ececec;width: 180px;" unique-opened router>
                        </el-menu>
                    </div>
                </el-aside>
                <el-main>
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item  >首页</el-breadcrumb-item>
                        <el-breadcrumb-item ></el-breadcrumb-item>
                    </el-breadcrumb>
                    <keep-alive>
                        <router-view ></router-view>
                    </keep-alive>
                    <router-view ></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script>
</script>
<style>
    .home-container {
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
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

    .home-aside {
        background-color: #ECECEC;
    }

    .home-main {
        background-color: #fff;
        color: #000;
        text-align: center;
        margin: 0px;
        padding: 0px;;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
        margin-left: 8px;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }

    .el-submenu .el-menu-item {
        width: 180px;
        min-width: 175px;
    }
</style>
