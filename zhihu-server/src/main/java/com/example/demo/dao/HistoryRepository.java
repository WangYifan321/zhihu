package com.example.demo.dao;

import com.example.demo.pojo.History;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
      public List<History> getHistoriesByUserId(Integer uid);
      public void deleteByUserId(Integer uid);


}
