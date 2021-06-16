import {post} from './http.js'
import {get} from './http.js'
import {patch} from "./http.js";

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
export function
  like(url,params){
  return post(url,params)
}
export function
  isLike(url,params){
  return get(url,params)
}
export function
  getComment(url,params){
  return get(url,params)
}
export function
  addComment(params){
  return post('apis/comment',params)
}
export function
  addAnswer(url,params){
  return post(url,params)
}
export function
  changeHistory(params){
  return patch('apis/history',params)
}
export function
  isHistory(params){
  return get('apis/isHistory',params)
}
export function
  get_user_history(params){
  return get('apis/history',params)
}
export  function
  get_user_likes(params){
  return get('apis/likes',params)
}
export  function
  get_user_question(params){
  return get('apis/myask',params)
}
export  function
  get_user_answer(params){
  return get('apis/myanswer',params)
}
