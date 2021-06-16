package com.example.demo.dao;

import com.example.demo.pojo.Comment;
import com.example.demo.pojo.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    public List<Comment> findAllByAnswerId(Integer aid);
}
