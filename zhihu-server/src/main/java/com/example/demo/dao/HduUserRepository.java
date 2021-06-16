package com.example.demo.dao;

import com.example.demo.pojo.HduUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HduUserRepository extends JpaRepository<HduUser,Integer> {
     HduUser findByAccountAndPassword(String account,String password);
     HduUser findByAccount(String account);


}
