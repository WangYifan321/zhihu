<template>
   <div>
     <!--头部部分-->
     <el-card shadow="always" style="height: 60px;  top: 0px; left: -10px;  width: 104%; position: fixed;">
       <el-link icon="el-icon-back" :underline="false" style="color: darkgrey;  font-size: x-large;" href="/recommend"></el-link>
       <div>
         <div class="title" v-if="content.length>14">
           {{content.substring(0,14)}}...
         </div>
         <div class="title" v-else>
           {{content.substring(0,14)}}
         </div>
         <div class="count">
            杭知 · 全部{{num}}条回答
         </div>
         <div class="write">
           <el-link type="primary" :underline="false" href="#" style="margin-top: -35px;  font-size: large;  font-size: unset;
    float: right;">写回答</el-link>
         </div>
       </div>

     </el-card>
     <div style="margin-top: 100px">
   {{answer[0].article}}
       <br>
       {{answer[0].article}}

       {{answer[0].article}}


     </div>

     <!--底部-->
     <div class="bottom">
      <el-card shadow="always">

      </el-card>
     </div>
   </div>
</template>

<script>
import {getOneQuestion} from "../apis/api";
import {getAnswer} from "../apis/api";

export default {
  name: "answer",
  props: ['id'],
  data(){
    return{
      content: '',
      num: '',
      answer: []
    }
  },
  methods: {
    getDescription(){
      let url = '/apis/question/one/'+this.id
      let params = {}
      getOneQuestion(url,params).then(res =>{
        this.content = res.content
        this.num = res.count
      })
    },
    getAnswer(){
      let url = '/apis/question/'+this.id+'/answer'
      let params = {}
      getAnswer(url,params).then(res => {
        this.answer = res
      })
    }
  },
  mounted() {
    this.getDescription()
    this.getAnswer()

  }
}
</script>

<style scoped>
.title{
  font-weight: 700;
  color: black;
  margin-left: 40px;
  margin-top: -30px;
  font-family: 宋体;
  font-size: large;
}
.count{
  margin-top: 2px;
  color: #a9a9a9a1;
  margin-left: 40px;
}
.bottom{
  position: fixed;
  bottom: 0px;
  width: 104%;
  left: -10px;
}
</style>
