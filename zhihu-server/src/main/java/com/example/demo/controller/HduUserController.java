package com.example.demo.controller;

import com.example.demo.Service.HduUserService;
import com.example.demo.Service.HduUserServiceImpl;
import com.example.demo.pojo.HduUser;
import com.example.demo.utils.RedisUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class HduUserController {
    @Resource
    private HduUserServiceImpl hduUserService;
    @Resource
    private RedisUtil redisUtil;



    //用户注册的接口
    @PostMapping(path = "register")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public HduUser insertHduUser(@RequestBody HduUser user){
        if(hduUserService.isExist(user.getAccount())){
            return null;
        }else
            user.setHistory(true);//默认开启历史记录
            user.setPhoto("http://localhost:9090/zhiphoto/1.jpg");//默认头像
        return hduUserService.insertUser(user);
    }



    //用户登录
    @PostMapping(path = "login")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public Map<String,Object> login(@RequestBody HduUser user,HttpServletRequest request){
        System.out.println(user);
        Map<String,Object> map = new HashMap<>();
        HduUser hduuser = hduUserService.login(user.getAccount(),user.getPassword());
        System.out.println(hduuser);
        if(hduuser!=null) {
            String token = UUID.randomUUID().toString();
            redisUtil.set(token,hduuser.getId().toString(),60*60*24*15);
            map.put("flag",1);
            map.put("token",token);
        }
        else{
            map.put("flag",0);
        }
        return map;
    }



    //用户上传头像
    @PostMapping(path = "photo" ,headers = "content-type=multipart/form-data")
    @CrossOrigin(origins = "*",maxAge = 3600)

    public Map<String,Object> upload(HttpServletRequest request, MultipartFile file){
        //通过token获得用户对象部分
        String token = request.getHeader("token");
        HduUser user = hduUserService.getUserByToken(Integer.parseInt(redisUtil.get(token).toString()));

        String filelocation = "/zhiphoto/";
        Map<String,Object> result = new HashMap<>();
        //拿到真实路径，往里面写文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().length()-4);//截取后缀
        String realpath = "D:\\xuexi\\zhiphoto\\"+user.getId()+suffix;
        File f = new File(realpath);

        //返回保存的url，根据url可以进行文件查看或者下载,存到数据库中
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + filelocation /*user.getAccount()*/+user.getId()+suffix;//端口 https443端口无需添加

        //将用户的数据库中头像的url更新
        user.setPhoto(url);
        hduUserService.insertUser(user);
        System.out.println(url);
        try {
            file.transferTo(f);
            result.put("result","上传头像成功");
        }catch(IOException e){
            result.put("result","上传头像失败");

        }
        return result;

    }


}
