package com.example.demo.Service;

import com.example.demo.pojo.Question;

import java.util.List;

public interface QuestionService {
    public Question insertQuestion(Question question);
    public List<Question> getHot();
    public List<Question> getMyQuestion(Integer userId);
    public List<Question> getRecommend();
    public Question getById(Integer id);
    public Question updateClicked(Question question);
}