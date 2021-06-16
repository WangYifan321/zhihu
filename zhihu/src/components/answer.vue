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
           <router-link :to="{name:'addAnswer',params:{qid:this.id}}" type="primary" :underline="false" style="margin-top: -35px;  font-size: large;  font-size: unset;
    float: right;">写回答</router-link>
         </div>
       </div>

     </el-card>
     <div style="margin-top: 100px; padding-left: 15px;padding-right: 15px" >
       <div style="margin-top: -25px;height: 30px">
         <div style="float: left"><img :src="answer[flag].photo" style="height: 30px;width: 30px;background: #fff; border-radius: 20px;"/></div>
         <div style="width: 200px;margin-left: 10px;float: left;font-size: revert; margin-top: 7px;   font-weight: bolder; font-family: 宋体;   color: #050505;">{{answer[flag].name}}</div>
       </div>
       <div>
         <p v-html="answer[flag].article"></p>
         <br/>
         <br/>
         <br/>
       </div>
     </div>

     <!--底部-->
     <div class="bottom" style="height: 50px; background-color: #fff">
        <el-button type="primary" style="margin-left: 26px; margin-top: 6px;background-color: #409effc2" @click="agree()" v-if="! isLike"><span>赞同</span></el-button>
       <el-button type="primary" style="margin-left: 26px; margin-top: 6px;background-color: #409effc2" @click="agree()"  v-else><span>已赞同</span></el-button>
       <el-link icon="el-icon-chat-round" :href="'/comment/'+answer[flag].id" id="comment" :underline="false"></el-link>
     </div>


     <div
       class="callback float"
       @mousedown="down"
       @touchstart="down"
       @click="onClick"
       @mousemove="move"
       @touchmove="move"
       @mouseup="end"
       @touchend="end"
       ref="fu"
     >
       <el-link :underline="false" style="margin-top: -13px" @click="nextAnswer()"><span style="color: whitesmoke">下一个回答</span></el-link>
     </div>
   </div>
</template>

<script>
import {getOneQuestion} from "../apis/api";
import {getAnswer} from "../apis/api";
import {like} from "../apis/api";

export default {
  name: "answer",
  props: ['id'],
  data(){
    return{
      content: '',
      num: '',
      flag: 0,
      answer: [],
      isLike: false,
      isLoading: false,
      flags: false, //控制使用
      position: {
        x: 0,
        y: 0,
      },
      nx: "",
      ny: "",
      dx: "",
      dy: "",
      xPum: "",
      yPum: ""
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
    },
    agree(){
      let url = '/apis/like/change/'+this.answer[this.flag].id
      let params = {}
      like(url,params).then(res =>{
         this.isLike = !this.isLike
      })

    },
    nextAnswer(){
      if(this.flag<= this.num-1){
        this.flag++
      }else {
        this.$message.error("这已经是最后一条了喔！")
      }
    },

    callback() {
      this.$router.go(-1);
    },
    onRefresh() {
      // window.location.reload();
      setTimeout((res) => {
        console.log(res);
        this.isLoading = false;
      }, 1000);
    },
    down() {
      this.flags = true;
      var touch;
      if (event.touches) {
        touch = event.touches[0];
      } else {
        touch = event;
      }
      this.position.x = touch.clientX;
      this.position.y = touch.clientY;
      this.dx = this.$refs.fu.offsetLeft;
      this.dy = this.$refs.fu.offsetTop;
    },
    move() {
      if (this.flags) {
        var touch;
        if (event.touches) {
          touch = event.touches[0];
        } else {
          touch = event;
        }
        this.nx = touch.clientX - this.position.x;
        this.ny = touch.clientY - this.position.y;
        this.xPum = this.dx + this.nx;
        this.yPum = this.dy + this.ny;
        let width = window.innerWidth - this.$refs.fu.offsetWidth; //屏幕宽度减去自身控件宽度
        let height = window.innerHeight - this.$refs.fu.offsetHeight; //屏幕高度减去自身控件高度
        this.xPum < 0 && (this.xPum = 0);
        this.yPum < 0 && (this.yPum = 0);
        this.xPum > width && (this.xPum = width);
        this.yPum > height && (this.yPum = height);
        // if (this.xPum >= 0 && this.yPum >= 0 && this.xPum<= width &&this.yPum<= height) {
        this.$refs.fu.style.left = this.xPum + "px";
        this.$refs.fu.style.top = this.yPum + "px";
        // }
        //阻止页面的滑动默认事件
        document.addEventListener(
          "touchmove",
          function () {
            event.preventDefault();
          },
          false
        );
      }
    },
    //鼠标释放时候的函数
    end() {
      this.flags = false;
    },
    onClick() {
      //在这里我是作为子组件来使用的
      this.$emit("click");
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
 #comment{
   margin-right: 22px;
   margin-top: 13px;
   float: right;
   font-size: x-large;
   font-weight: normal;
 }


.callback p {
  font-size: 16px;
  color: #fff;
  background: rgba(64, 158, 255, 0.76);
  border-radius: 10%;
  text-align: center;
  width: 100px;
  height: 50px;
  line-height: 50px;
  font-family: PingFang SC;
  font-weight: 600;
  box-shadow: 0 0 10px #fff;
}
.callback img {
  display: block;
  width: 50px;
  height: 50px;
  box-shadow: 0 0 10px rgb(133, 129, 129);
  border-radius: 50%;
  background: #fff;
}
.callback {
  position: fixed;
  top: 40px;
  left: 20px;
  z-index: 99999;
}
.float {
  position: fixed;
  right: 20px;
  top: 60%;
  touch-action: none;
  text-align: center;
  width: 100px;
  height: 40px;
  border-radius: 10px;
  line-height: 48px;
  color: white;
  background-color: rgba(64, 158, 255, 0.76);
}
</style>
