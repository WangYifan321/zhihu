package com.example.demo.Service;

import com.example.demo.dao.CommentRepository;
import com.example.demo.pojo.Comment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Resource
    private CommentRepository commentRepository;
    @Resource
    private HduUserServiceImpl hduUserService;
    @Override
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Integer id) {
        commentRepository.deleteById(id);

    }

    @Override
    public List<Comment> getAllByAnswerId(Integer aid) {
        return commentRepository.findAllByAnswerId(aid);
    }

    @Override
    public Comment getPid(Integer pid) {
        return commentRepository.getOne(pid);
    }

    @Override
    public List<Map<String, Object>> getCommentToFront(Integer aid) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Comment> list = getAllByAnswerId(aid);
        for (Comment comment : list) {
            Map<String,Object> map = new HashMap<>();
            int flag = comment.getParent();
            Comment comment1 = null;
            while(flag!=0){
                comment1 = getPid(flag);
                flag = comment1.getParent();
            }
            if(comment1!=null)
            map.put("pid",comment1.getId());
            else map.put("pid",0);
            map.put("content",comment.getContent());
            map.put("commentAt",comment.getCommentAt());
            map.put("cid",comment.getId());
            map.put("username",hduUserService.getUserByToken(comment.getUserId()).getName());
            map.put("photo",hduUserService.getUserByToken(comment.getUserId()).getPhoto());

            result.add(map);


        }

        return result;
    }
}
