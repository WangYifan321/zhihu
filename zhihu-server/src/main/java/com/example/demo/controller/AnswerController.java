package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.Service.*;
import com.example.demo.pojo.Answer;
import com.example.demo.pojo.History;
import com.example.demo.utils.RedisUtil;
import com.tdunning.math.stats.Histogram;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/")
public class AnswerController {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private AnswerServiceImpl answerService;
    @Resource
    private QuestionServiceImpl questionService;
    @Resource
    private HistoryServiceImpl historyService;
    @Resource
    private HduUserServiceImpl hduUserService;


    @GetMapping(path = "myanswer")//获取某个用户得回答
    @CrossOrigin(origins = "*",maxAge = 3600)

    @ResponseBody
    public List<Map<String,Object>> getAllByUserId(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer userId = Integer.parseInt(redisUtil.get(token).toString());
        List<Answer> list = answerService.getAllAnswerByUser(userId);
        List<Map<String,Object>> result = new ArrayList<>();
        for (Answer answer : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("qid",answer.getQuestionId());
            map.put("content",questionService.getById(answer.getQuestionId()).getContent());
            map.put("article",answer.getArticle());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            map.put("answerAt",simpleDateFormat.format(answer.getAnswerAt()));//防止date无法序列化，直接搞成字符串
            map.put("aid",answer.getId());
            result.add(map);

        }
        return result;
    }


    @GetMapping(path = "question/{id}/answer/{aid}")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Answer getOneMyAnswer(@PathVariable Integer aid, @PathVariable String id){

        return answerService.getByAnswerId(aid);
    }//头像、昵称暂未写进去


    @GetMapping(path = "question/{id}/answer")//列出一个问题得所有回答
    @ResponseBody
    @CrossOrigin(origins = "*",maxAge = 3600)

    public List<Map<String,String>> getAllByQuestion(@PathVariable Integer id,HttpServletRequest request){
        String token = request.getHeader("token");
        if(redisUtil.get(token)!=null ) {
            Integer uid = Integer.parseInt(redisUtil.get(token).toString());
            if(hduUserService.getUserByToken(uid).getHistory()) {
                History history = new History();
                history.setHistoryAt(new Date());
                history.setQuestionId(id);
                history.setUserId(uid);
                historyService.insert(history);
            }//判断用户有没有打开历史记录
        }//插入历史记录
        questionService.updateClicked(questionService.getById(id));
        return answerService.getAll(id);
    }

    //没用的api
    @GetMapping(path = "question/{id}/answer/count")
    @ResponseBody
    @CrossOrigin(origins = "*",maxAge = 3600)
    public int getAnswerCount(@PathVariable Integer id){
        List<Answer> list = answerService.getAllAnswerByQuestion(id);
        System.out.println(list.size());
        return list.size();


    }


    @PostMapping(path = "answer/{qid}")//对某一个问题提交回答
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Answer insert(@RequestBody Answer answer, @PathVariable Integer qid,HttpServletRequest request){
        String token = request.getHeader("token");
        Integer userId = Integer.parseInt(redisUtil.get(token).toString());
        Date date = new Date();
        answer.setAnswerAt(date);
        answer.setHduUserId(userId);
        answer.setQuestionId(qid);

        return answerService.insertAnswer(answer);
    }
}
