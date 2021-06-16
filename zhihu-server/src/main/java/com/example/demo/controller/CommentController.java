package com.example.demo.controller;

import com.example.demo.Service.CommentServiceImpl;
import com.example.demo.pojo.Comment;
import com.example.demo.utils.RedisUtil;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.mustachejava.codes.CommentCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class CommentController {
    @Resource
    private CommentServiceImpl commentService;
    @Resource
    private RedisUtil redisUtil;
    @PostMapping(path = "comment")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Comment addComment(@RequestBody Comment comment, HttpServletRequest request){

        String token = request.getHeader("token");
        Integer userId = Integer.parseInt(redisUtil.get(token).toString());

        return commentService.insert(comment);
    }
    @GetMapping(path = "comment/{aid}")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public List<Map<String, Object>> getComment(@PathVariable String aid){
        return commentService.getCommentToFront(Integer.parseInt(aid));
    }
}
