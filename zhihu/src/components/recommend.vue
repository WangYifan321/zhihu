<template>
  <div class="content">

    <el-card class="box-card" v-for="(question,i) in info" :key="i" >
      <router-link :to="{name:'answer',params:{id:question.qid}}" style="text-decoration: none;color: #121212;">
      <div class="text item">
        <div style="margin-left:0px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
          <a href="#" style="color: inherit; text-decoration: none;"><h2 class="Item-title" >{{question.content}}</h2></a>
        </div>
        <div style="margin-top: 5px">
          <div style="float: left"><img :src="question.uid_photo" style="height: 20px;width: 20px;background: #fff; border-radius: 20px;"/></div>
          <div style="width: 200px;margin-left: 10px;float: left;font-size: revert; margin-top: 3px;   font-weight: bolder; font-family: 宋体;   color: #0505058f;">{{question.uid_name}}</div>
        </div><br>
        <div style="margin-top: 10px;line-height: 1.7;font-family: 宋体">
          {{question.article.substring(0,44)}}...
        </div>
        <div style="    margin-top: 7px;margin-bottom: -28px; font-family: 宋体; color:#0505058f ">{{question.clicked}} 点赞·{{question.count}}评论</div>
      </div></router-link>
    </el-card>
  </div></template>

<script>
  import {getRecommend} from "../apis/api";

  export default {
    name: "recommend",
    data() {
      return{
        info: []
      }
    },
    methods: {

    },
    mounted() {
      let params = {}
      getRecommend(params).then(res => {
        this.info = res
      })
    }
  }
</script>

<style scoped>
.text {
  font-size: 14px;
  margin-top: -25px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
  margin-top: 5px;
}
.content{
  margin-top: -3px;
}
.Item-title{
  font-size: 18px;
  line-height: 28px;
  max-height: 56px;
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-weight: 600;
  margin: 0;
}
</style>
