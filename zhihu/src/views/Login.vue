<template>
  <div>

    <div class="top"> <el-link href="/" class="close" icon="el-icon-close"></el-link><p style="color: white;font-size: x-large;position: absolute">登录杭知</p><p style="display: block;margin-top: 70px;color: white;position: absolute">发现更多可信赖的回答</p></div>

    <div style="margin: 20px;"></div>
    <el-form :label-position="labelPosition" label-width="80px" :model="loginForm">
      <el-form-item>
        <el-input v-model="loginForm.account" placeholder="请输入账号或手机号"></el-input>
      </el-form-item>
      <hr style="margin-top: -20px;width: 92%"><br>
      <el-form-item>
        <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <hr style="width: 92%; margin-top: -20px;">
      <br>
      <br>
      <el-button type="primary" @click="doLogin()" id="login">登录</el-button>
    </el-form>

    <el-link type="primary" :underline="false" class="link1" href="/">忘记密码</el-link>
    <el-link type="primary" :underline="false" class="link2" href="/register">点击注册</el-link>




  </div>
</template>

<script>
import {login} from '../apis/api'
import { mapMutations } from 'vuex'
  export default {
    name: "Login",
    data() {
      return {
        labelPosition: 'right',
        loginForm: {
          account: '',
          password: ''
        },
        flag: ''
      };
    },
    methods:{
      ...mapMutations(['changeLogin']),
      doLogin() {
        let _this=this
        let params={account: this.loginForm.account,password: this.loginForm.password}
        login(params).then(res => {
          if(res.flag === 1){
            //console.log(res.token)
            _this.userToken=res.token
            _this.changeLogin({ Authorization: _this.userToken})
            _this.$router.push("/")
          }else{
            this.$message.error("密码错误")
          }
        })
      }
    },
    mounted() {

    }
  }
</script>
<style scoped>
  .login_button{
    display: block;
    margin: 0 auto;
  }
  .top{
    position: relative;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    flex-direction: column;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    height: 108px;
    padding: 0;
    background: #06f;
  }
  /deep/ .el-input__inner{
    border: 0px;
    margin-left: -80px
  }
  #login{
    width: 350px;
    margin-left: 14px;
    background-color: #06f;
  }
  .link1{
    margin-left: 13px;
    margin-top: 29px;
    font-size: 16px;
    line-height: 16px;
    color: #175199;
  }
  .link2{
    margin-top: 29px;
    font-size: 16px;
    line-height: 16px;
    margin-left: 58%;
    color: #175199;
  }
  .close{
    position: absolute;
    margin-top: -80px;
    color: #f5f7fa;
    margin-left: -350px;
    font-size: large;
  }

</style>
