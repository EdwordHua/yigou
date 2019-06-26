package com.cn.controller;
import com.cn.model.Merchandise;
import com.cn.model.User;
import com.cn.service.IUserService;
import com.cn.tools.Constants;
import com.cn.tools.MD5T;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
//    private MD5T md5T;
    ObjectMapper mapper = new ObjectMapper();
    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<User> userlist = this.userService.selectAllUser();
        System.out.println(mapper.writeValueAsString(userlist));
        JSONObject jsonObject=getUserJson(userlist);
        response.getWriter().print(jsonObject);
        response.getWriter().close();
    }
    @RequestMapping("/showUserAdmin.do")
    public void selectUserAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<User> userlist = this.userService.selectAllUserAdmin();
        System.out.println(mapper.writeValueAsString(userlist));
        JSONObject jsonObject=getUserJson(userlist);
        response.getWriter().print(jsonObject);
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
    @RequestMapping( "/islogin.do")
    public void isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // User user = iUserService.login(userCode, userPassword);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute(Constants.USER_SESSION);
        if (null != user) {
            System.out.println("BUG-TEST-json:"+mapper.writeValueAsString(user));
            response.getWriter().write(mapper.writeValueAsString(user));
            response.getWriter().close();
            //return "redirect:hello.action";
        }else{
            response.getWriter().write("false");
            response.getWriter().close();
        }
    }
    @RequestMapping( "/loginOut.do")  //注销
    public String LoginOut(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.getSession().invalidate();
        return   "redirect:/index.html";
    }
    @RequestMapping("/insertUser.do")
    public void insertUser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("时间：" + df.format(new Date()));// new Date()为获取当前系统时间
        User user ;
        user = new User();
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
        if(user != null) {
            System.out.println("Result:" + mapper.writeValueAsString(user));
            response.getWriter().write(mapper.writeValueAsString(user));
            response.getWriter().close();
        }else{
            response.getWriter().write("false");
            response.getWriter().close();
        }
    }
    public JSONObject getUserJson(List<User> userAll) {
        JSONArray users=new JSONArray();
        JSONObject json=new JSONObject();
        for(int i=0;i<userAll.size();i++)
        {
            JSONObject member_temp=new JSONObject();
            member_temp.put("uid", userAll.get(i).getUid());
            member_temp.put("uname", userAll.get(i).getUname());
            member_temp.put("uaddress", userAll.get(i).getUaddress());
            member_temp.put("ulevel", userAll.get(i).getULevel());
            member_temp.put("upassword", userAll.get(i).getUpassword());
            member_temp.put("utime", userAll.get(i).getUtime());
            users.add(member_temp);
        }
        json.put("data", users);
        json.put("count",userAll.size());
        json.put("msg","");
        json.put("code",0);
        return json;
    }
}

