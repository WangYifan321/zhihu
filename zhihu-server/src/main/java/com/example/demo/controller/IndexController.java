package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    /*@Resource
    JdbcTemplate jdbcTemplate;
    jdbcTemplate.update()*/
    @Resource
    private UserMapper usermapper;

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","我是猪");
        model.addAttribute("users", Arrays.asList("wang","yi"));
       return "test";
    }
    @GetMapping("/query")
    public List<User> query(){
    List<User> list = usermapper.queryAll();
    return list;
    }
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
