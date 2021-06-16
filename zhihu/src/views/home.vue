<template>
  <div>
    <el-link icon="el-icon-back" :underline="false" style="color: white;  font-size: x-large;" href="/recommend"></el-link>
    <div id = "myhome_top">
      <el-image :src="src"></el-image>
<!--      <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>-->

    </div>
    <div id = "myhome_body">
      <h2>{{myname}}</h2>
    </div>

    <div id = 'myhome_menu'>
      <van-tabs v-model="active">
        <van-tab title="浏览历史" >
          <el-card class="box-card" v-for="(question,i) in info" :key="i" >
            <router-link :to="{name:'answer',params:{id:question.qid}}" style="text-decoration: none;color: #121212;">
              <div class="text item">
                <div style="margin-left:0px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
                  <a href="#" style="color: inherit; text-decoration: none;"><h2 class="Item-title" >{{question.content}}</h2></a>
                </div>
                <div style="margin-top: 5px">
                  <div style="float: left"><img :src="question.photo" style="height: 20px;width: 20px;background: #fff; border-radius: 20px;"/></div>
                  <div style="width: 200px;margin-left: 10px;float: left;font-size: revert; margin-top: 3px;   font-weight: bolder; font-family: 宋体;   color: #0505058f;">{{question.name}}</div>
                </div><br>
                <div style="margin-top: 10px;line-height: 1.7;font-family: 宋体">
                  {{question.article ? question.article.substring(0,44) : ''}}...
                </div>
                <div style="    margin-top: 7px;margin-bottom: -28px; font-family: 宋体; color:#0505058f ">{{question.clicked}} 点赞·{{question.count}}评论</div>
              </div></router-link>
          </el-card>
        </van-tab>
        <van-tab title="喜欢内容">
          <el-card class="box-card" v-for="(question,i) in info_my_likes" :key="i" >
            <router-link :to="{name:'answer',params:{id:question.qid}}" style="text-decoration: none;color: #121212;">
              <div class="text item">
                <div style="margin-left:0px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
                  <a href="#" style="color: inherit; text-decoration: none;"><h2 class="Item-title" >{{question.content}}</h2></a>
                </div>
                <div style="margin-top: 5px">
                  <div style="float: left"><img :src="question.photo" style="height: 20px;width: 20px;background: #fff; border-radius: 20px;"/></div>
                  <div style="width: 200px;margin-left: 10px;float: left;font-size: revert; margin-top: 3px;   font-weight: bolder; font-family: 宋体;   color: #0505058f;">{{question.name}}</div>
                </div><br>
                <div style="margin-top: 10px;line-height: 1.7;font-family: 宋体">
                  {{question.article ? question.article.substring(0,44) : ''}}...
                </div>
                <div style="    margin-top: 7px;margin-bottom: -28px; font-family: 宋体; color:#0505058f ">{{question.clicked}} 点赞·{{question.count}}评论</div>
              </div></router-link>
          </el-card>        </van-tab>
        <van-tab title="我的问题">
          <el-card class="box-card" v-for="(question,i) in info_my_question" :key="i" >
            <router-link :to="{name:'answer',params:{id:question.id}}" style="text-decoration: none;color: #121212;">
              <div class="text item">
                <div style="margin-left:0px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
                  <a href="#" style="color: inherit; text-decoration: none;"><h2 class="Item-title" >{{question.content}}</h2></a>
                </div>
                <div style="margin-top: 5px">
                </div><br>
                <div style="    margin-top: 7px;margin-bottom: -28px; font-family: 宋体; color:#0505058f ">{{question.askAt}}</div>

              </div></router-link>
          </el-card>
        </van-tab>
        <van-tab title="我的回答">
          <el-card class="box-card" v-for="(question,i) in info_my_answer" :key="i" >
            <router-link :to="{name:'answer',params:{id:question.qid}}" style="text-decoration: none;color: #121212;">
              <div class="text item">
                <div style="margin-left:0px;-webkit-box-flex: 1;  -ms-flex: 1 1; flex: 1 1;overflow: hidden;">
                  <a href="#" style="color: inherit; text-decoration: none;"><h2 class="Item-title" >{{question.content}}</h2></a>
                </div>
                <div style="margin-top: 10px;line-height: 1.7;font-family: 宋体">
                  {{question.article ? question.article.substring(0,44) : ''}}...
                </div>
                <div style="    margin-top: 7px;margin-bottom: -28px; font-family: 宋体; color:#0505058f "> <p>{{question.answerAt}}</p></div>
              </div></router-link>
          </el-card>
        </van-tab>
      </van-tabs>
    </div>
  </div>
</template>
<script>
import {getRecommend} from "../apis/api";
import {get_user_history} from "../apis/api";
import {get_user_likes} from "../apis/api";
import {get_user_answer} from "../apis/api";
import {get_user_question} from "../apis/api";

export default {
  name: "home",
  data() {
    return{
      info: [],
      src: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      myname: '爱生活的豆子',
      active: 0,
      info_my_question: [],
      info_my_likes: [],
      info_my_answer: [],
      imageUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
    }
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    getHistory(){
       let params = {}
       get_user_history(params).then(res =>{
         this.info = res
       })
    },
    getLikes(){
      let params = {}
      get_user_likes(params).then(res=>{
        this.info_my_likes = res
      })
    },
    getMyAnswer() {
      let params = {}
      get_user_answer(params).then(res=>{
        this.info_my_answer = res
      })
    },
    getMyQuestion(){
      let params = {}
      get_user_question(params).then(res=>{
        this.info_my_question = res
      })
    }
  },
  mounted() {
    this.getHistory()
    this.getLikes()
    this.getMyAnswer()
    this.getMyQuestion()
  }
}
</script>
<style lang="scss" scoped>
#myhome_top{
  width: 100%;
  height: 200px;
  background-color: gray;
  margin-top: -30px;
}
#myhome_body{
  width: 100%;
  background-color: white;
  font-family: "微软雅黑";
}
#myhome_menu{
  padding-top: 25px;
}
.el-link{
  background-color: gray;
  height: 30px;
  width: 30px;
}
.el-icon-back:before {
  content: "\E6EA";


}
.el-image {
  width:120px;
  height:120px;
  border-radius: 60px;
  margin-top: 140px;
  margin-left: 20px;
}
h2{
  margin-left:160px;
}
h5{
}
/deep/ .van-tabs__line {
  bottom: 15px;
  width: 80px;
  height: 3px;
  background-color: gray;
}
/deep/ .van-tab {
  font-size: 16px;
  line-height: 20px;
  cursor: pointer;
}

//img{
//  width: 20px;
//  height: 20px;
//  float: right;
//  margin-right: 10px;
//  margin-top: -30px;
//}

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

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  width:120px;
  height:120px;
  border-radius: 60px;
  margin-top: 140px;
  margin-left: 20px;
  font-size: 28px;
  color: #8c939d;

}
.avatar {
  width:120px;
  height:120px;
  border-radius: 60px;
  margin-top: 140px;
  margin-left: 20px;
  display: block;
}
</style>
