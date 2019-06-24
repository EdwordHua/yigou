package com.cn.service.impl;
/**
 * Created by Administrator on 2019/6/19 0019.
 */
 import com.cn.dao.IUserDao;
 import com.cn.model.User;
 import com.cn.service.IUserService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import javax.annotation.Resource;
@Service("userService")
public class UserServiceImpl implements IUserService
{
   @Resource
    private IUserDao userDao;
    public User selectUser(long userId)  //查找用户
    {
        return this.userDao.selectUser(userId);
    }

 @Override
 public int insertUser(User user) {   //插入用户
  return this.userDao.insertUser(user);
 }

 @Override
 public int updataUser(User user) {    //更新用户
  return this.userDao.updataUser(user);
 }

 @Override
 public int deleteUser(long userId) {   //删除用户
  return this.userDao.deleteUser(userId);
 }
 //登录检测
 @Override
 public User checkLogin(long userId,String password) {
  User user = this.userDao.selectUser(userId);
  if(user != null && user.getUpassword().equals(password)){
    return user;
  }
  return null;
 }
}
