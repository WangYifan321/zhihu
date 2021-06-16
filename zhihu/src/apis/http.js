import Vue from 'vue'
import axios from 'axios'

import qs from 'qs'
import { Message, Loading } from 'element-ui'
// 响应时间
axios.defaults.timeout = 30 * 1000
// 配置cookie
// axios.defaults.withCredentials = true
// 配置请求头
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8'
// 静态资源
Vue.prototype.$static = ''
axios.defaults.responseType='json'
// 配置接口地址
//axios.defaults.baseURL = 'http://localhost:1227'
// POST传参序列化(添加请求拦截器)
/*axios.interceptors.request.use(
  config => {
    loadingInstance = Loading.service({
      lock: true,
      text: '数据加载中，请稍后...',
      spinner: 'el-icon-loading',
      background: 'rgba(0, 0, 0, 0.7)'
    })
    if (config.method === 'post') {
      config.data = qs.stringify(config.data)
    }
    return config
  },
  err => {
    loadingInstance.close()
    Message.error('请求错误')
    return Promise.reject(err)
  }
)*/
// 返回状态判断(添加响应拦截器)
/*axios.interceptors.response.use(
  res => {
    if (res.data.code === 200) {
      loadingInstance.close()
      return res
    } else {
      loadingInstance.close()
      Message.error(res.data.msg)
    }
  },
  err => {
    loadingInstance.close()
    Message.error('请求失败，请稍后再试')
    return Promise.reject(err)
  }
)*/
// 添加请求拦截器，在请求头中加token
axios.interceptors.request.use(
  config => {
    if (localStorage.getItem('Authorization')) {
      config.headers.token = localStorage.getItem('Authorization');
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  });
// 发送请求
export function post (url, params) {
  return new Promise((resolve, reject) => {

    axios
      .post(url, params)
      .then(
        res => {
          resolve(res.data)
        },
        err => {
          reject(err.data)
        }
      )
      .catch(err => {
        reject(err.data)
      })
  })
}
export function get (url, params) {
  return new Promise((resolve, reject) => {
    axios
      .get(url, {
        params: params
      })
      .then(res => {
          resolve(res.data)
        },
        err => {
          reject(err.data)
        })
      .catch(err => {
        reject(err.data)
      })
  })
}
export function patch (url, params) {
  return new Promise((resolve, reject) => {
    axios
      .patch(url, {
        params: params
      })
      .then(res => {
          resolve(res.data)
        },
        err => {
          reject(err.data)
        })
      .catch(err => {
        reject(err.data)
      })
  })
}
