package com.cn.service;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
import com.cn.model.User;

public interface IUserService {
    User selectUser(long userId);
    int insertUser(User user);
    int updataUser(User user);
    int deleteUser(long userId);
    User checkLogin(long userId,String password);
}

