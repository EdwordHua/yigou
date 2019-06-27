package com.cn.interceptor;

import com.cn.model.User;
import com.cn.tools.Constants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2019/6/27 0027.
 */
public class SysInterceptor2 extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("SysInterceptor2...preHandle...");
        //这里可以根据session的用户来判断角色的权限，根据权限来转发不同的页面
        User user = (User) session.getAttribute(Constants.USER_SESSION);
        String username = (String) session.getAttribute("username");
        if (null == user) {
            // response.sendRedirect(request.getContextPath() + "/401.html");
            System.out.println("SysInterceptor2:false");
//            Filter
            return false;
        } else {
            if(user.getULevel()==0)
            {
            System.out.println("SysInterceptor2:True");
            // request.getRequestDispatcher("/404.jsp").forward(request, response);
            return true;
            }else {
                System.out.println("SysInterceptor2:false ---Level error");
                return false;
            }
        }

    }
}
