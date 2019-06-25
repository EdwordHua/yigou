package com.cn.controller;
import com.cn.model.User;
import com.cn.service.IUserService;
import com.cn.tools.Constants;
import com.cn.tools.MD5T;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    private MD5T md5T;
    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("uid"));
        System.out.println("Uid:"+userId);
        User user = this.userService.selectUser(userId);
        if(user != null){
            System.out.println("BUG-TEST:Uid:"+user.getUid()+",u_password:"+user.getUpassword()+",Uname:"+user.getUname()+",ULevel:"+user.getULevel()+",address:"+user.getUaddress()+",Utime:"+user.getUtime());
        }
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("BUG-TEST-json:"+mapper.writeValueAsString(user));
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }
    @RequestMapping( "/dologin.do")
    public String doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // User user = iUserService.login(userCode, userPassword);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session=request.getSession();
        long userId = Long.parseLong(request.getParameter("uid"));
        String upassword = request.getParameter("upassword");
        System.out.println("1:UID:" + userId + ",Upassword:" + upassword);
        try {
           // String md5str = MD5T.md5(upassword, "a");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        User user = this.userService.checkLogin(userId,upassword); //登录检测
        String path = request.getServletPath();//获取当前访问路径
        String root = request.getContextPath();//获取项目的上下文
        System.out.println("path:"+path+",root:"+root);
        if (null != user) {
            session.setAttribute(Constants.USER_SESSION, user);
            System.out.println("BUG-TEST-json:"+mapper.writeValueAsString(user));
            //response.getWriter().write(mapper.writeValueAsString(user));
            //response.getWriter().close();
            //return "redirect:hello.action";
           if(user.getULevel()==0)
           {
               return "redirect:/admin/index.html";
           }
           return  "redirect:/index.html";
        }else{
            response.getWriter().write(mapper.writeValueAsString("flase"));
            response.getWriter().close();
            return "redirect:/login.html";
        }
    }
    @RequestMapping( "/loginOut.do")  //注销
    public String LoginOut(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.getSession().invalidate();
        return  "index";
    }
    @RequestMapping("/insertUser.do")
    public void insertUser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("时间："+df.format(new Date()));// new Date()为获取当前系统时间
        User user = new User();
        String uname = request.getParameter("uname");
        String upassword = request.getParameter("upassword");
        String uaddress = request.getParameter("uaddress");
        user.setUname(uname);
        user.setUpassword(upassword);
        user.setULevel(1);
        user.setUtime(df.format(new Date()));
        user.setUaddress(uaddress);
        user = this.userService.insertUser(user);
        ObjectMapper mapper = new ObjectMapper();

        System.out.println("Result:"+mapper.writeValueAsString(user));
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }
}

