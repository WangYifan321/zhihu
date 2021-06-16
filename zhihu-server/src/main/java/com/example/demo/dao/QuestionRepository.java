package com.example.demo.dao;

import com.example.demo.pojo.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    public List<Question> findByUserId(Integer id);
    @Query(value = "select * from question  order by RAND()",nativeQuery = true)
    public List<Question> getRecommend();

}
