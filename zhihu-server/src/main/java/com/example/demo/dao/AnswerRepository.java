package com.example.demo.dao;

import com.example.demo.Service.LikesService;
import com.example.demo.pojo.Answer;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    public List<Answer> findByHduUserId(Integer HduUserId);
    public List<Answer> findByQuestionId(Integer QuestionId);
}
