package com.example.demo.Service;

import com.example.demo.dao.HduUserRepository;
import com.example.demo.pojo.HduUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HduUserServiceImpl implements HduUserService{

    @Resource
    private HduUserRepository hduUserRepository;
    @Override
    public HduUser insertUser(HduUser user) {
        return hduUserRepository.save(user);
    }

    @Override
    public HduUser login(String account, String password) {
        return hduUserRepository.findByAccountAndPassword(account,password);
    }

    @Override
    public HduUser getUserByToken(Integer id) {
        return hduUserRepository.getOne(id);
    }

    @Override
    public Boolean isExist(String account) {
        if(hduUserRepository.findByAccount(account)==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean isHistory(Integer uid) {
        return hduUserRepository.getOne(uid).getHistory();
    }

    @Override
    public void changeHistory(Integer uid) {
        HduUser hduUser = hduUserRepository.getOne(uid);
        hduUser.setHistory(!hduUser.getHistory());
        hduUserRepository.save(hduUser);

    }


}
