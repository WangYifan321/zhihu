package com.example.demo.Service;

import com.example.demo.pojo.History;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    public History insert(History history);
    public List<Map<String,Object>> getHistoriesByUserId(Integer uid);
    public void deleteByUserId(Integer uid);
}
