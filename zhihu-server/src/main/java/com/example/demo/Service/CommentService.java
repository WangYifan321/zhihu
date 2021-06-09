package com.example.demo.Service;

import com.example.demo.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public Comment insert(Comment comment);
    public void delete(Integer id);
    public List<Comment> getAllByAnswerId(Integer aid);
    public Comment getPid(Integer pid);
    public List<Map<String,Object>> getCommentToFront(Integer aid);
}
