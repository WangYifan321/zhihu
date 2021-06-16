<template>
<div style="margin: 0 auto">
  <el-link :underline="false" icon="el-icon-close" id="close" href="/"></el-link>

  <h3 style="float: left;
    margin-left: 15px;margin-top: 54px">无痕浏览</h3>
  <el-switch
    v-model="value"
    active-color="#13ce66"
    inactive-color="#ff4949"
  style=" margin-top: 50px;
    float: right;
    margin-right: 10px;">
  </el-switch>
</div>
</template>

<script>
export default {
  name: "setting",
  data(){
    return{
      value:true
    }
  }
}
</script>

<style scoped>

#close{
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: -77px;
  font-size: 20px;
}
</style>
