package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
   List<User> queryAll();
   User queryById(int id);
   int insert(User user);
   int deleteById(int id);
   int update(User user);

}
