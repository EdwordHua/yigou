package com.cn.service;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
import com.cn.model.User;

import java.util.List;

public interface IUserService {
    User selectUser(long userId);
    User insertUser(User user);
    List<User> selectAllUser();
    int updataUser(User user);
    int deleteUser(long userId);
    User checkLogin(long userId,String password);
}

