<template>
  <div>

    <div>
      <el-link href="/" className="close" icon="el-icon-close" style="margin: 10px;font-size: 20px"></el-link>

    <div style="margin: 20px;"></div>
    <el-form :label-position="labelPosition" label-width="80px" :model="loginForm">
      <el-form-item>
        <el-input v-model="loginForm.account" placeholder="请输入账号或手机号"></el-input>
      </el-form-item>
      <hr style="margin-top: -20px;width: 92%">
      <br>
      <el-form-item>
        <el-input v-model="loginForm.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <hr style="width: 92%; margin-top: -20px;">
      <br>
      <el-form-item>
        <el-input v-model="loginForm.repassword" type="password" placeholder="请确认密码"></el-input>
      </el-form-item>
      <hr style="width: 92%; margin-top: -20px;">

      <br>
      <br>
      <el-button type="primary" @click="doRegister()" id="login">点击注册</el-button>
    </el-form>


    </div></div>
</template>

<script>
import {register} from '../apis/api'

export default {
  name: "register",
  data() {
    return {
      labelPosition: 'right',
      loginForm: {
        account: '',
        password: '',
        repassword: ''
      },
      flag: ''
    };
  },
  methods: {
    doRegister() {
      let _this = this
      let params = {account: this.loginForm.account, password: this.loginForm.password}
      if(this.loginForm.password !== this.loginForm.repassword){
        this.$message.error("两次密码输入不一致！")
        return 0;
      }
      register(params).then(res => {
        if (res !== null) {
          this.$message.info("注册成功，请登录！")
          _this.$router.push("/login")
        } else {
          this.$message.error("用户已存在！")
        }
      })
    }
  },
  mounted() {

  }
}
</script>
<style scoped>


#login {
  width: 350px;
  margin-left: 14px;
  background-color: #06f;
}
/deep/ .el-input__inner{
  border: 0px;
  margin-left: -80px
}

</style>
