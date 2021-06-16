<template>
<div>
  <el-link icon="el-icon-close" :underline="false" style="color: darkgrey;  font-size: xx-large;margin: 5px"@click="back()"></el-link>
  <div class="box-card" v-for="(comment,i) in info" :key="i">
    <div  class="text item">
      <div class="left_above_id">
        <div style="margin-top: 5px">
          <div style="float: left"><img :src="comment.photo" style="height: 25px;width: 25px;background: #fff; border-radius: 20px;"/></div>
          <div style="width: 200px;margin-left: 10px;float: left;font-size: revert; margin-top: 3px;   font-weight: bolder; font-family: 宋体;   color: #0505058f;">{{comment.username}}</div>
        </div>
        <br/>
        <div style="margin-left: 33px">
          <h3 class="HotItem-title" >{{comment.content}}</h3>
        </div>
        <div style="margin-left: 33px">
          {{comment.commentAt.substring(5,10)}}
          <div style="float: right;margin-right: 33px">
            <el-link icon="el-icon-chat-round" href="#" :underline="false"></el-link>
          </div>
        </div>
      </div>
    </div>
    <!--分割线-->
    <div class="boundary"></div>
  </div>
</div>
</template>

<script>
import {getComment} from "../apis/api";

export default {
  name: "comment",
  data(){
    return{
      info: []
    }
  },
  props: ['aid'],
  methods: {
    back(){
      this.$router.go(-1)
    },
    getComment(){
      let url = '/apis/comment/'+this.aid
      let params = {}
      getComment(url,params).then(res =>{
        this.info = res
      })

    }
  },
  mounted() {
    this.getComment()
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
</style>
