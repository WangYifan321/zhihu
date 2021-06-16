package com.example.demo.controller;

import com.example.demo.Service.*;
import com.example.demo.dao.QuestionRepository;
import com.example.demo.pojo.Answer;
import com.example.demo.pojo.HduUser;
import com.example.demo.pojo.Question;
import com.example.demo.utils.RedisUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "/")
public class QuestionController {
    @Resource
    private QuestionServiceImpl questionService;
    @Resource
    private AnswerServiceImpl answerService;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Resource
    private RedisUtil redisUtil;



    @PostMapping(path = "ask")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Question addQuestion(@RequestBody Question question, HttpServletRequest request){
        String token = request.getHeader("token");
        Integer id = Integer.parseInt(redisUtil.get(token).toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        //date类型只有一种格式，但是可以格式化输出为多种格式得字符串，所以我下面得simpledateformat多此一举
        try {
            question.setAskAt(simpleDateFormat.parse(simpleDateFormat.format(date)));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        question.setClicked(1);
        question.setUserId(id);
        Random random = new Random();
        if(random.nextInt()%2==0)
        question.setSelected(true);
        else
            question.setSelected(false);

        return questionService.insertQuestion(question);
    }



    @GetMapping(path = "question/{type}")//type为1代表热搜，2代表推荐，3代表精选
    @CrossOrigin(origins = "*",maxAge = 3600)//获取问题的时候带上第一条回答
    public List<Map<String,String>> getQuestion(@PathVariable Integer type) {
        List<Question> list = new ArrayList<>();
        List<Map<String, String>> result = new ArrayList<>();
        if (type == 1) {
            list = questionService.getHot();
        } else {
            list = questionService.getRecommend();
        }
            for (Question question : list) {
                Map<String, String> map = new HashMap<>();
                List<Answer> answer = answerService.getAllAnswerByQuestion(question.getId());
                if (answer.size() != 0) {
                    Answer firstAnswer = answer.get(0);
                    map.put("content", question.getContent());
                    map.put("qid", question.getId().toString());
                    map.put("selected", question.getSelected().toString());
                    map.put("questioner_id", question.getUserId().toString());
                    map.put("askAt", question.getAskAt().toString());
                    int x = answer.size();
                    map.put("count", Integer.toString(x));
                    map.put("uid", firstAnswer.getHduUserId().toString());
                    map.put("aid", firstAnswer.getId().toString());
                    map.put("article", firstAnswer.getArticle());
                    map.put("answerAt", firstAnswer.getAnswerAt().toString());
                    map.put("uid_photo", hduUserService.getUserByToken(firstAnswer.getHduUserId()).getPhoto());
                    map.put("uid_name", hduUserService.getUserByToken(firstAnswer.getHduUserId()).getName());
                    map.put("clicked",question.getClicked().toString());
                } else {
                    map.put("content", question.getContent());
                    map.put("qid", question.getId().toString());
                    map.put("selected", question.getSelected().toString());
                    map.put("questioner_id", question.getUserId().toString());
                    map.put("askAt", question.getAskAt().toString());
                    map.put("count", "0");
                    map.put("clicked",question.getClicked().toString());


                }
                result.add(map);
            }
            return result;

        }
    @GetMapping(path = "question/one/{qid}")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Map<String,String> getOne(@PathVariable Integer qid){
        Question question = questionService.getById(qid);
        Map<String,String> map = new HashMap<>();
        map.put("content",question.getContent());
        List<Answer> answer = answerService.getAllAnswerByQuestion(question.getId());
        map.put("count",Integer.toString(answer.size()));
        return map;

    }

    @GetMapping(path = "myask")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public List<Question> getMyQuestion(HttpServletRequest request){
        //通过token获得用户对象部分
        String token = request.getHeader("token");
        return questionService.getMyQuestion(Integer.parseInt(redisUtil.get(token).toString()));

    }
}
