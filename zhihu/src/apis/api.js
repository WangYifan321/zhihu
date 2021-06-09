import {post} from './http.js'
import {get} from './http.js'
export  function
  login(params) {
    return post('/apis/login', params)
  }
export  function
  ask(params) {
    return post('/apis/ask',params)
}
export  function
  register(params) {
    return post('apis/register',params)
}
export function
  getHot(params) {
    return get('apis/question/1',params)
}
export function
  getRecommend(params) {
    return get('apis/question/2',params)
}
export function
  getOneQuestion(url,params) {
  return  get(url,params)
}
export function
  getAnswer(url,params) {
  return get(url,params)
}

