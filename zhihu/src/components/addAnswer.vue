<template>
  <div>
    <div>
  <el-link icon="el-icon-close" :underline="false" style="color: darkgrey;  font-size: xx-large;margin: 5px"@click="back()"></el-link>
      <el-link icon="el-icon-s-promotion" class="commit" :underline="false" @click="commit()"></el-link>
    </div>
  <h2 style="margin: 5px">{{content}}</h2>
    <br>
    <el-input type="textarea" placeholder="写回答..." v-model="answer" style="width: 100%">

    </el-input>
  </div>
</template>

<script>
import {getOneQuestion} from "../apis/api";
import {addAnswer} from "../apis/api";

export default {
  name: "addAnswer",
  methods: {
    back(){
      this.$router.go(-1)
    },
    getDescription() {
      let url = '/apis/question/one/'+this.qid
      let params = {}
      getOneQuestion(url,params).then(res => {
        this.content = res.content
      })
    },
    commit(){
      let url = '/apis/answer/'+this.qid
      let params = {article: this.answer}
      addAnswer(url,params).then(res =>{
        if(res!=null){
          this.$message({
            message: '恭喜你，回答成功！',
            type: 'success'
          })
          this.$router.go(-1)
        }else{
          this.$message.error("回答失败，网络异常")
        }

      })
    }
  },
  mounted() {
    this.getDescription()
  },
  data(){
    return{
      content: "",
      answer: ''
    }
  },
  props: ['qid']
}
</script>

<style scoped>
.commit{
  float: right;
  margin: 5px;
  font-size: xx-large;
}
/deep/ .el-textarea__inner:hover{
  min-height: 700px !important;
}
</style>
