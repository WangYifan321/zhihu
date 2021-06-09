package com.example.demo.Service;

import com.example.demo.dao.HistoryRepository;
import com.example.demo.pojo.Answer;
import com.example.demo.pojo.History;
import com.example.demo.pojo.Question;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HistoryServiceImpl implements HistoryService{
    @Resource
    private HistoryRepository historyRepository;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Resource
    private AnswerServiceImpl answerService;
    @Resource
    private QuestionServiceImpl questionService;
    @Override
    public History insert(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<Map<String,Object>> getHistoriesByUserId(Integer uid) {//查询某个人得所有历史记录，按时间排序
        List<History> list = historyRepository.getHistoriesByUserId(uid);
        list.sort(Comparator.comparing(History::getHistoryAt,Comparator.reverseOrder()));//降序，新的在前面
        List<Map<String,Object>> result = new ArrayList<>();
        for (History history : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("qid",history.getQuestionId());
            map.put("content",questionService.getById(history.getQuestionId()).getContent());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            map.put("historyAt",simpleDateFormat.format(history.getHistoryAt()));
            List<Answer> answers = answerService.getAllAnswerByQuestion(history.getQuestionId());
            if(answers.size()!=0) {
                map.put("aid", answers.get(0).getId());
                map.put("article", answers.get(0).getArticle());
                map.put("photo", hduUserService.getUserByToken(answers.get(0).getHduUserId()).getPhoto());
                map.put("name", hduUserService.getUserByToken(answers.get(0).getHduUserId()).getName());
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public void deleteByUserId(Integer uid) {
        historyRepository.deleteByUserId(uid);
    }
}
