package com.example.demo.Service;

import com.example.demo.pojo.Likes;

import java.util.List;

public interface LikesService {
    public void insert(Likes likes);
    public List<Likes> getLikesByUserId(Integer id);
    public void delete(Integer id);
    public Boolean isLike(Integer aid,Integer uid);
    public Likes getLikesByUserIdAndAnswerId(Integer uid,Integer aid);

}
