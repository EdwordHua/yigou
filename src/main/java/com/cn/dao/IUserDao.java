package com.cn.dao;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
import com.cn.model.User;

public interface IUserDao {
    User selectUser(long Uid);
    User selectUserByin(User user);
    int insertUser(User user);
    int updataUser(User user);
    int deleteUser(long Uid);
}