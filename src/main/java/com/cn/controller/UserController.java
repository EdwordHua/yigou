package com.cn.controller;
import com.cn.model.User;
import com.cn.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        long userId = Long.parseLong(request.getParameter("id"));
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

