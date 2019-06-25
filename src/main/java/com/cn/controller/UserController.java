package com.cn.controller;
import com.cn.model.User;
import com.cn.service.IUserService;
import com.cn.tools.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource

    private IUserService userService;
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
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session=request.getSession();
        long userId = Long.parseLong(request.getParameter("uid"));
        String upassword = request.getParameter("upassword");
        System.out.println("1:UID:" + userId + ",Upassword:" + upassword);
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

           return  "index";
        }else{
            response.getWriter().write(mapper.writeValueAsString("flase"));
            response.getWriter().close();
            return "login";
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

        User user = new User();
        user.setUname("test_insert");
        user.setUpassword("123");
        user.setULevel(1);
        user.setUtime("Test_insert");
        user.setUaddress("湖北师范大学学舍6");
        int result = this.userService.insertUser(user);
        System.out.println("Result:"+result);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
        response.getWriter().close();
    }
}

