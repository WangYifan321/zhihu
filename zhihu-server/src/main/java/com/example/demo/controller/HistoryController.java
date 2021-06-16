package com.example.demo.controller;

import com.example.demo.Service.AnswerServiceImpl;
import com.example.demo.Service.HduUserServiceImpl;
import com.example.demo.Service.HistoryServiceImpl;
import com.example.demo.Service.QuestionServiceImpl;
import com.example.demo.pojo.Answer;
import com.example.demo.utils.RedisUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HistoryController {
    @Resource
    private HistoryServiceImpl historyService;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Resource
    private QuestionServiceImpl questionService;
    @Resource
    private AnswerServiceImpl answerService;
    @Resource
    private RedisUtil redisUtil;


    @DeleteMapping(path = "history")//清空历史记录
    @CrossOrigin(origins = "*",maxAge = 3600)
    @Transactional//先查询再删除，因此必须在顶级方法上加上事务
    public void deleteAll(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        historyService.deleteByUserId(uid);
    }


    @GetMapping(path = "history")//查询所有得历史记录，按时间排序
    @CrossOrigin(origins = "*",maxAge = 3600)

    public List<Map<String,Object>> getAll(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        return historyService.getHistoriesByUserId(uid);
    }


    @PatchMapping(path = "history")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public void change(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        hduUserService.changeHistory(uid);
    }


    @GetMapping(path = "isHistory")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Boolean isHistory(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer uid = Integer.parseInt(redisUtil.get(token).toString());
        return hduUserService.isHistory(uid);
    }

}
