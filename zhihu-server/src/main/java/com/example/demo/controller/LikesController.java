package com.example.demo.controller;

import com.example.demo.Service.AnswerServiceImpl;
import com.example.demo.Service.HduUserServiceImpl;
import com.example.demo.Service.LikesServiceImpl;
import com.example.demo.Service.QuestionServiceImpl;
import com.example.demo.pojo.Answer;
import com.example.demo.pojo.HduUser;
import com.example.demo.pojo.Likes;
import com.example.demo.pojo.Question;
import com.example.demo.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LikesController {
    @Resource
    private LikesServiceImpl likesService;
    @Resource
    private AnswerServiceImpl answerService;
    @Resource
    private QuestionServiceImpl questionService;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Resource
    private RedisUtil redisUtil;



    @PostMapping("like/change/{aid}")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public void change(@PathVariable Integer aid, HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        if(likesService.isLike(aid,uid)){
            likesService.delete(likesService.getLikesByUserIdAndAnswerId(uid,aid).getId());
        }else{
            Likes likes = new Likes();
            likes.setAnswerId(aid);
            likes.setUserId(uid);
            likesService.insert(likes);
        }
    }



    @GetMapping("isLike/{aid}")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Boolean isLike(@PathVariable Integer aid, HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        return likesService.isLike(aid,uid);
    }



    @GetMapping(path = "likes")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public List<Map<String,Object>> getLikes(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        List<Likes> list = likesService.getLikesByUserId(uid);
        List<Map<String ,Object>> result = new ArrayList<>();
        for (Likes likes : list) {
            Answer answer = answerService.getByAnswerId(likes.getAnswerId());
            Question question = questionService.getById(answer.getQuestionId());
            Map<String,Object> map = new HashMap<>();
            map.put("qid",question.getId());
            map.put("content",question.getContent());
            map.put("aid",answer.getId());
            map.put("article",answer.getArticle());
            map.put("photo",hduUserService.getUserByToken(answer.getHduUserId()).getPhoto());
            map.put("name",hduUserService.getUserByToken(answer.getHduUserId()).getName());
            result.add(map);
        }
        return result;
    }
}
