<template>
  <div class="content">
  <div class="box-card" v-for="(question,i) in info" :key="i" v-if="question" >
    <div  class="text item">
      <div class="left_above_id">
        <div style="text-align: center; width: 57px;display: block">
          <div style="font-weight: 600;line-height: 1.6;font-size: 18px;color: #999;width:20px; float:left;display: block">
            {{i+1}}
          </div>
        </div>
        <div style="margin-left:30px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
          <a href="#" style="color: inherit; text-decoration: none;"><h2 class="HotItem-title" >{{question.content}}</h2></a>
        </div>
    </div>
    </div>
    <!--热度与分享-->
    <div class="HotItem-metrics--bottom">
      <svg t="1622795096548" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1290" width="16" height="16"><path d="M770.016 332.768l-77.824 76.8s0-307.2-259.616-409.568c0 0-25.92 281.6-155.744 383.936-129.792 102.464-389.44 409.568 129.696 639.968 0 0-259.616-281.6 77.824-486.304 0 0-25.92 102.336 103.872 204.8 129.824 102.368 0 281.6 0 281.6s623.008-153.632 181.792-691.232z" p-id="1291" fill="#8590a6"></path></svg>
      {{question.clicked}} 万热度
       <div>

       </div>
    </div>
    <!--分割线-->
    <div class="boundary"></div>
  </div>


  </div>
</template>

<script>
import {getHot} from "../apis/api";

export default {
  name: "hot",
  data(){
    return{
      info: []

    }
  },
  methods: {
    getInfo(){
      let params = {}
      getHot(params).then(res => {
        this.info =  res
      })
    }

  },
  mounted() {
    this.getInfo()
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
  margin-top: -15px;
  width: 98%;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100%;
  margin-top: 0px;
}
.content{
  margin-top: -10px;
}
.boundary{
  width: 85%;margin-right: auto;
  margin-left: auto;
  padding: 15px;
  border-bottom: solid 1px #dcdfe6;
  height: 1px;
}
.left_above_id{
  margin-left: 15px;
}
.HotItem-title{
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
.HotItem-metrics--bottom{
  margin-left: 40px;
  font-size: 14px;
  height: 1px;
  color: #8590a6;
}
</style>
