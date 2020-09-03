package com.sample.dao;

import com.sample.domin.QueryVo;
import com.sample.domin.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(Integer userId);
    int saveUser(User user);
    int updateUser(User user);
    int deleteUser(Integer userId);
    List<User> findByName(String username);
    int count();
    List<User> findByVo(QueryVo queryVo);
}
