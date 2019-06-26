package com.cn.service.impl;
/**
 * Created by Administrator on 2019/6/19 0019.
 */
 import com.cn.dao.IUserDao;
 import com.cn.model.User;
 import com.cn.service.IUserService;
 import com.cn.tools.MD5T;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import javax.annotation.Resource;
 import java.util.List;

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
 public User insertUser(User user) {   //插入用户
  String password=user.getUpassword();
  try{
      user.setUpassword(MD5T.md5(user.getUpassword(),"a"));   //加密
  }catch (Exception e){
      e.printStackTrace();
  }
  this.userDao.insertUser(user);
  user=this.userDao.selectUserByin(user); //查询
  user.setUpassword(password);
  return user;
 }

 @Override
 public int updataUser(User user) {    //更新用户
  try{
      user.setUpassword(MD5T.md5(user.getUpassword(),"a"));   //加密
  }catch (Exception e){
      e.printStackTrace();
  }
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
  boolean userflag=false;
  try {
       userflag=MD5T.verify(password,"a",user.getUpassword());
  }catch (Exception e)
  {
       e.printStackTrace();
  }
  if(user != null && userflag){
    user.setUpassword(password);  //将密文改成明文
    return user;
  }
  return null;
 }

 @Override
 public List<User> selectAllUser() {
  return this.userDao.selectAllUser();
 }
 public List<User> selectAllUserAdmin(){
  return this.userDao.selectAllUserAdmin();
 }
}
