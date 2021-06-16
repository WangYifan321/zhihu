package com.example.demo.Service;

import com.example.demo.dao.AnswerRepository;
import com.example.demo.pojo.Answer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Resource
    private AnswerRepository answerRepository;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Override
    public List<Answer> getAllAnswerByQuestion(Integer questionId) {

        return answerRepository.findByQuestionId(questionId);
    }

    @Override
    public List<Answer> getAllAnswerByUser(Integer userId) {
        return answerRepository.findByHduUserId(userId);
    }

    @Override
    public List<Map<String, String>> getAll(Integer questionId) {
        ArrayList<Answer> list = (ArrayList<Answer>) answerRepository.findByQuestionId(questionId);
        List<Map<String,String>> result = new ArrayList<>();
        for (Answer answer : list) {
            Map<String,String> map = new HashMap<>();
            map.put("id",answer.getId().toString());
            map.put("question_id",answer.getQuestionId().toString());
            map.put("article",answer.getArticle());
            map.put("answer_at",answer.getAnswerAt().toString());
            map.put("photo",hduUserService.getUserByToken(answer.getHduUserId()).getPhoto());
            map.put("name",hduUserService.getUserByToken(answer.getHduUserId()).getName());
            result.add(map);
        }
        return result;
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
