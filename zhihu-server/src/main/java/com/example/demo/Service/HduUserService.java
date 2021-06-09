package com.example.demo.Service;

import com.example.demo.pojo.HduUser;

public interface HduUserService {
    public HduUser insertUser(HduUser user);
    public HduUser login(String account,String password);
    public HduUser getUserByToken(Integer id);
    public Boolean isExist(String account);
    public Boolean isHistory(Integer uid);
    public void changeHistory(Integer uid);
}
