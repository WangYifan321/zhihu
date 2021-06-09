package com.example.demo.Service;

import com.example.demo.dao.AnswerRepository;
import com.example.demo.pojo.Answer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Resource
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswerByQuestion(Integer questionId) {

        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> getAllAnswerByUser(Integer userId) {
        return answerRepository.findByHduUserId(userId);
    }

    @Override
    public Answer insertAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public Answer getByAnswerId(Integer id) {
        return answerRepository.getOne(id);
    }
}
