package com.cn.model;

/**
 * Created by Administrator on 2019/6/19 0019.
 * author:YX
 * class:用户表
 */
import java.util.Date;
public class User {
    private long Uid;        //用户ID （登录用） ---自增
    private String Uname;    //用户名  （可修改）
    private String Upassword;//用户密码
    private String Uaddress; //用户地址
    private String Utime;    //创建时间
    private int ULevel;      //用户权限

    public long getUid() {
        return Uid;
    }

    public void setUid(long uid) {
        Uid = uid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUpassword() {
        return Upassword;
    }

    public void setUpassword(String upassword) {
        Upassword = upassword;
    }

    public String getUaddress() {
        return Uaddress;
    }

    public void setUaddress(String uaddress) {
        Uaddress = uaddress;
    }

    public String getUtime() {
        return Utime;
    }

    public void setUtime(String utime) {
        Utime = utime;
    }

    public int getULevel() {
        return ULevel;
    }

    public void setULevel(int ULevel) {
        this.ULevel = ULevel;
    }
}
