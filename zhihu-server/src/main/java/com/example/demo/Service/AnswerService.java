package com.example.demo.Service;

import com.example.demo.pojo.Answer;

import java.util.List;
import java.util.Map;

public interface AnswerService {
    public List<Answer> getAllAnswerByQuestion(Integer questionId);
    public List<Answer> getAllAnswerByUser(Integer userId);
    public List<Map<String,String>> getAll(Integer questionId);
    public Answer insertAnswer(Answer answer);
    public Answer getByAnswerId(Integer id);


}
