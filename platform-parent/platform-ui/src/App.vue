<template>
  <div id="app" style="height: 100%;">
    <!--<img alt="Vue logo" src="./assets/logo.png">-->
    <!--<HelloWorld msg="Welcome to Your Vue.js App"/>-->
    <!--<element1></element1>-->
    <!--<login></login>-->
    <router-view></router-view>
  </div>
</template>

<script>
// import element1 from './components/element1.vue'
// import HelloWorld from './components/HelloWorld.vue'
// import login from './login/login.vue'

export default {
  name: 'app',
  components: {
    // HelloWorld,
    // login
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
