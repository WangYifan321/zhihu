<template>
  <div id="app">
    <!--<router-link to="/login">login</router-link>-->
    <router-view></router-view>
<!--    <router-view class="login" name="login"></router-view>-->

  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
html,
body,
#app{
  height: 100%;
  margin: 0;
  padding: 0;
}

</style>
