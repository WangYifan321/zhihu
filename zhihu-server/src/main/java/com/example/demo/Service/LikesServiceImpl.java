package com.example.demo.Service;

import com.example.demo.dao.LikesRepository;
import com.example.demo.pojo.Likes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService{

    @Resource
    private LikesRepository likesRepository;
    @Override
    public void insert(Likes likes) {
        likesRepository.save(likes);
    }

    @Override
    public List<Likes> getLikesByUserId(Integer uid) {
        return likesRepository.findByUserId(uid);
    }

    @Override
    public void delete(Integer id) {
         likesRepository.deleteById(id);
    }

    @Override
    public Boolean isLike(Integer aid, Integer uid) {
        if(likesRepository.findByUserIdAndAnswerId(uid,aid)==null){
            return false;
        }
        return true;
    }

    @Override
    public Likes getLikesByUserIdAndAnswerId(Integer uid, Integer aid) {
        return likesRepository.findByUserIdAndAnswerId(uid,aid);
    }
}
