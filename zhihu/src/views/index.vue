<template>
  <div>
    <div class="top">
      <el-card shadow="always" style="margin-right: -30px;
    width: 109%;height: 90px;
    margin-top: -20px;
    margin-left: -20px;">
    <h2 style="float: left;color: blue;margin-top: 2px;margin-left: 2px">杭知</h2>
<!--   搜索框 -->
    <el-autocomplete
      v-model="state"
      :fetch-suggestions="querySearchAsync"
      placeholder="请输入内容"
      @select="handleSelect"
      prefix-icon="el-icon-search"
      v-bind:class="{search1 : isActive1,search2: isActive2}"
    ></el-autocomplete>
<!--   下拉菜单 -->
      <div v-if="isLogin === 1">
  <el-dropdown class="dropdown">
         <span class="el-dropdown-link">
          <i class="el-icon-more"></i>
         </span>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item><el-link href="/home" :underline="false">我的主页</el-link></el-dropdown-item>
      <el-dropdown-item><el-link href="/setting" :underline="false">设置</el-link></el-dropdown-item>
      <el-dropdown-item><el-link href="/ask" :underline="false">发布问题</el-link></el-dropdown-item>
      <el-dropdown-item><el-link @click="logout()" :underline="false">退出账号</el-link></el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
    </div>
        <div v-else>
          <el-dropdown class="dropdown">
         <span class="el-dropdown-link">
          <i class="el-icon-more"></i>
         </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><el-link :underline="false" href="/login">注册登录</el-link></el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>

        </div>

        <!--导航栏-->
        <center>
        <div style="margin-top: 5px">
          <el-link :underline="false" style="margin-left: -48px;" href="/selected"  v-bind:class="{tag1:istag1,tag11:!istag1}" >精选</el-link>
          <el-link :underline="false" style="margin-left: 30px;" href="/recommend" v-bind:class="{tag2:istag2,tag22:!istag2}">推荐</el-link>
          <el-link :underline="false" style="margin-left: 30px;" href="/hot" v-bind:class="{tag3:istag3,tag33:!istag3}">热榜</el-link>

        </div>
        </center>
      </el-card>
    </div>
    <router-view style=""></router-view>
  </div>
</template>
<script>
import '../assets/index.css'
import {mapMutations} from "vuex"
export default {
  name: "index",
  data() {
    return {
      restaurants: [],
      state: '',
      timeout:  null,
      isLogin: 0,
      isActive1: false,
      isActive2: true,
      activeName: '',
      istag1: false,
      istag2: true,
      istag3: true

    };
  },
  methods: {
    ...mapMutations(['changeLogin']),
    loadAll() {
      return [
        { "value": "如何评价轮子哥不建议大学生把时间花在ACM竞赛上？", "address": "长宁区新渔路144号" },
        { "value": "为何大司马在上交的待遇，和某流量明星在南大的待遇相差那么大？", "address": "上海市长宁区淞虹路661号" },
        { "value": "新如何看待 85 后夫妻不让孩子上幼儿园，辞去工作还花 30 多万买房车去旅行？", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113" },
        { "value": "如何看待林俊杰「线上演唱会」最低票价 188 元，最高票价 938 元，你觉得合理吗？", "address": "天山西路438号" },
        { "value": "如何评价《王者荣耀》西施「江南少女」皮肤？", "address": "上海市长宁区金钟路968号1幢18号楼一层商铺18-101" },
        { "value": "如何看待《摩尔庄园》玩家「星无火」以欺骗小朋友毁号为乐？", "address": "上海市长宁区金钟路633号" },
        { "value": "为什么最近恒大很多地区的楼盘打折很低并且必须要求付全款？真实情况是怎样的？", "address": "上海市嘉定区曹安公路曹安路1685号" },
        { "value": "茶如何看待长沙一律所因收费过低被警告处分：最低应收 41 万却仅收五千？", "address": "上海市普陀区同普路1435号" },
        { "value": "NBA 2020-21 赛季湖人 100:113 太阳，如何评价这场比赛？", "address": "上海市北翟路1444弄81号B幢-107" },
        { "value": "星移浓缩咖啡", "address": "上海市嘉定区新郁路817号" },
        { "value": "阿姨奶茶/豪大大", "address": "嘉定区曹安路1611号" },
        { "value": "新麦甜四季甜品炸鸡", "address": "嘉定区曹安公路2383弄55号" },
        { "value": "Monica摩托主题咖啡店", "address": "嘉定区江桥镇曹安公路2409号1F，2383弄62号1F" },
        { "value": "浮生若茶（凌空soho店）", "address": "上海长宁区金钟路968号9号楼地下一层" },
        { "value": "NONO JUICE  鲜榨果汁", "address": "上海市长宁区天山西路119号" },
        { "value": "CoCo都可(北新泾店）", "address": "上海市长宁区仙霞西路" },
        { "value": "快乐柠檬（神州智慧店）", "address": "上海市长宁区天山西路567号1层R117号店铺" },
        { "value": "Merci Paul cafe", "address": "上海市普陀区光复西路丹巴路28弄6号楼819" },
        { "value": "猫山王（西郊百联店）", "address": "上海市长宁区仙霞西路88号第一层G05-F01-1-306" },
        { "value": "枪会山", "address": "上海市普陀区棕榈路" },
        { "value": "纵食", "address": "元丰天山花园(东门) 双流路267号" },
        { "value": "钱记", "address": "上海市长宁区天山西路" },
        { "value": "壹杯加", "address": "上海市长宁区通协路" },
        { "value": "唦哇嘀咖", "address": "上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元" },
        { "value": "爱茜茜里(西郊百联)", "address": "长宁区仙霞西路88号1305室" },
        { "value": "爱茜茜里(近铁广场)", "address": "上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺" },
        { "value": "鲜果榨汁（金沙江路和美广店）", "address": "普陀区金沙江路2239号金沙和美广场B1-10-6" },
        { "value": "开心丽果（缤谷店）", "address": "上海市长宁区威宁路天山路341号" },
        { "value": "超级鸡车（丰庄路店）", "address": "上海市嘉定区丰庄路240号" },
        { "value": "妙生活果园（北新泾店）", "address": "长宁区新渔路144号" },
        { "value": "香宜度麻辣香锅", "address": "长宁区淞虹路148号" },
        { "value": "凡仔汉堡（老真北路店）", "address": "上海市普陀区老真北路160号" },
        { "value": "壹分米客家传统调制米粉(天山店)", "address": "天山西路428号" },
        { "value": "福荣祥烧腊（平溪路店）", "address": "上海市长宁区协和路福泉路255弄57-73号" },
        { "value": "速记黄焖鸡米饭", "address": "上海市长宁区北新泾街道金钟路180号1层01号摊位" },
        { "value": "红辣椒麻辣烫", "address": "上海市长宁区天山西路492号" },
        { "value": "(小杨生煎)西郊百联餐厅", "address": "长宁区仙霞西路88号百联2楼" },
        { "value": "阳阳麻辣烫", "address": "天山西路389号" },
        { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }
      ];
    },
    querySearchAsync(queryString, cb) {
      let restaurants = this.restaurants;
      let results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

      clearTimeout(this.timeout);
      this.timeout = setTimeout(() => {
        cb(results);
      }, 3000 * Math.random());
    },
    createStateFilter(queryString) {
      return (state) => {
        return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
      };
    },
    handleSelect(item) {
      console.log(item);
    },
    getIsLogin() {
      if(localStorage.getItem("Authorization") === "") {
        return  0
      }else{
        this.isActive1=false
        this.isActive2=true
        return  1
      }
    },
    logout() {
      this.changeLogin({ Authorization: ''})
      window.location.reload()
      this.$router.push("/")
    },
    getTag(){
      if(this.$route.path === '/selected'){
        this.istag1 = true
        this.istag2 = false
        this.istag3 = false

      }else if(this.$route.path === '/hot'){
        this.istag1 = false
        this.istag2 = false
        this.istag3 = true
      }else if(this.$route.path === '/recommend'){
        this.istag1 = false
        this.istag2 = true
        this.istag3 = false
      }else if(this.$route.path === '/'){
        this.istag1 = false
        this.istag2 = false
        this.istag3 = false
      }
    }
  },
  mounted() {
    this.restaurants = this.loadAll();
    this.isLogin=this.getIsLogin();
    this.getTag();
  },
  created() {
  }
};
</script>
<style scoped>
.top{
  margin: 10px;
}
.dropdown{
  float: right;
  font-size: 30px;
  margin-right: -10px;
  margin-top: -37px;
}
.el-dropdown-link {
  cursor: pointer;
  color: rgba(109, 111, 113, 0.61);
}
/*登录之前*/
.search1{
  margin-left: 10px;
}
/*登录之后,修改之后总是为登录之后的，此判断动态的css是固定的也就是这个判断没有用了*/
.search2{
  margin-left: 10px;
  width: 74%;
}

    .text {
      font-size: 14px;
    }

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 100%;
}
/deep/ .el-input__inner{
  border-radius: 20px !important;
  width: 100%;

}

.link2{
  font-size: 16px;
  line-height: 16px;
  margin-left: 81%;
  color: #0066ff;
  margin-top: -94px;
}
.tag1{
  color: blue;
  font-size: 20px;
}
.tag11{
  font-size: large;
}
.tag2{
  color: blue;
  font-size: 20px;
}
.tag22{
  font-size: large;
}
.tag3{
  color: blue;
 font-size: 20px;
}
.tag33{
  font-size: large;
}

</style>
