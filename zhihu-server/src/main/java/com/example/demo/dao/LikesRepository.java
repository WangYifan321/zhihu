package com.example.demo.dao;

import com.example.demo.pojo.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Integer> {
    public Likes findByUserIdAndAnswerId(Integer UserId,Integer AnswerId);
    public List<Likes> findByUserId(Integer uid);

}
