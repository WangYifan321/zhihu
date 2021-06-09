package com.example.demo.Service;

import com.example.demo.dao.QuestionRepository;
import com.example.demo.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Resource
    QuestionRepository questionRepository;
    @Override
    public Question insertQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getHot() {
        Pageable pageable = PageRequest.of(0,50,Sort.by(Sort.Direction.DESC,"clicked"));
        List<Question> page = questionRepository.findAll(pageable).getContent();
        return page;
    }

    @Override
    public List<Question> getMyQuestion(Integer userId) {
        return questionRepository.findByUserId(userId);
    }

    @Override
    public List<Question> getRecommend() {
        return questionRepository.getRecommend();
    }
    @Override
    public Question getById(Integer id){
        return questionRepository.getOne(id);
    }

    @Override
    public Question updateClicked(Question question) {
        int x = question.getClicked();
        x++;
        question.setClicked(x);

        return questionRepository.save(question);
    }
}
