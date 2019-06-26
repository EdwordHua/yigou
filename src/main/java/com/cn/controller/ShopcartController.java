package com.cn.controller;

import com.cn.model.Merchandise;
import com.cn.model.Shopcart;
import com.cn.model.User;
import com.cn.service.IMerchandiseService;
import com.cn.service.impl.ShopcartServiceImpl;
import com.cn.tools.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
            return "false";
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
    public JSONObject getShopJson(List<Shopcart> shoplist) {
//        JSONArray merchs=new JSONArray();
//        JSONObject json=new JSONObject();
//        for(int i=0;i<shoplist.size();i++)
//        {
////
////                long mid = merchsAll.get(i).getMid();
////                String mname = merchsAll.get(i).getMname();
////                String mimage = merchsAll.get(i).getMimage();
////                int mprice = merchsAll.get(i).getMprice();
////                int mstock = merchsAll.get(i).getMstock();
////                String mtype = merchsAll.get(i).getMtype();
////                String mtime = merchsAll.get(i).getMtime();
////                String mrecommend = merchsAll.get(i).getMrecommend();
//
//            JSONObject member_temp=new JSONObject();
//
//            member_temp.put("mid", merchsAll.get(i).getMid());
//            member_temp.put("mname", merchsAll.get(i).getMname());
//            member_temp.put("mimage", merchsAll.get(i).getMimage());
//            member_temp.put("mprice", merchsAll.get(i).getMprice());
//            member_temp.put("mstock", merchsAll.get(i).getMstock());
//            member_temp.put("mtype", merchsAll.get(i).getMtype());
//            member_temp.put("mtime", merchsAll.get(i).getMtime());
//            member_temp.put("mrecommend", merchsAll.get(i).getMrecommend());
//
//            merchs.add(member_temp);
//        }
//
//        json.put("allMerchandise", merchs);
//        json.put("num",merchsAll.size());
        return null;
    }
}
