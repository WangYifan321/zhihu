import Vue from 'vue'
import Router from 'vue-router'

import index from '../views/index'
import Login from '../views/Login'
import Notfound from '../views/notfound'
import Ask from '../views/ask'
import Register from '../views/register'
import Hot from '../views/hot'
import Recommend from '../components/recommend'
import Selected from '../components/selected'
import Answer from '../components/answer'
import Setting from '../components/setting'
import Home from '../views/home'
import AddAnswer from '../components/addAnswer'
import Comment from '../components/comment'
Vue.use(Router);

const router = new Router({
    routes:[
        {
            path: '/',
            redirect: '/hot',
            components: {
              default: index,
              hot: Hot
            },
            children: [
              {
                path: 'hot',
                name: 'hot',
                component: Hot
              },
              {
                path: 'recommend',
                name: 'recommend',
                component: Recommend
              },{
                path: 'selected',
                name: 'selected',
                component: Selected

              }
            ]
        },
        {
            path: '/login',
            component: Login
        },
      {
        name: 'answer',
        path: '/answer/:id',
        component: Answer,
        props: true
      },
      {
            path: '/ask',
            component: Ask
      },
      {
            path: '/register',
            component: Register
      },
      {
            path: '/setting',
            component: Setting
      },{
            path: '/home',
            component: Home
      },{
            path: '/addAnswer/:qid',
            props: true,
            component: AddAnswer,
            name: 'addAnswer'
      },{
            path: '/comment/:aid',
            name: 'comment',
            props: true,
            component: Comment
      },
        {
            path: '*',
            component: Notfound
        }
    ],
    mode: 'history'
});

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/' || to.path === '/register') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');

    if (token === null || token === '') {
      next('/login');
    } else {
      next();
    }
  }
});
export default router
