package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2021/3/20
 */
@Repository
@Mapper
public interface UserDao {
    List<User> selectByExample(User user);
    void insert(User user);
}
