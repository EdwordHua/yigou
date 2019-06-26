package com.cn.controller;

import com.cn.model.Shopcart;
import com.cn.model.User;
import com.cn.service.IMerchandiseService;
import com.cn.service.impl.ShopcartServiceImpl;
import com.cn.tools.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Controller
@RequestMapping("/shopcart")
public class ShopcartController {
    @Resource
    private ShopcartServiceImpl shopcartService;
    @Resource
    private IMerchandiseService merchService;
    @RequestMapping("/addShop.do")
    public String addShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        int snum=1;
        long uid=0;
        long mid;
        User user = (User)session.getAttribute(Constants.USER_SESSION);
        if(user != null){
            uid = user.getUid();
        }else
        {
            return "redirect:/login.html";
        }
        mid=Long.parseLong(request.getParameter("mid"));
        snum=Integer.valueOf(request.getParameter("snum"));
        Shopcart shopcart=new Shopcart();
        shopcart.setMid(mid);
        shopcart.setUid(uid);
        shopcart.setSnum(snum);
        shopcart.setSisbuy(0);
        shopcart.setSsum(merchService.selectMerchByID(mid).getMprice() * snum);

        shopcartService.insertShop(shopcart);
        return "true";
    }
    @RequestMapping("/delShop.do")
    public void delShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
    @RequestMapping("/showShop.do")
    public void showShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute(Constants.USER_SESSION);
        List<Shopcart> shoplist=shopcartService.selectShopByUID(user.getUid());


    }
}
