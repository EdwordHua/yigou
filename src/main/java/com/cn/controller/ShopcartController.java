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
    public void addShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            response.getWriter().write("false");
            response.getWriter().close();
        }
        mid=Long.parseLong(request.getParameter("mid"));
        snum=Integer.valueOf(request.getParameter("snum"));
        Shopcart shopcart=new Shopcart();
        shopcart.setMid(mid);
        shopcart.setUid(uid);
        shopcart.setSnum(snum);
        shopcart.setSisbuy(0);
        shopcart.setSsum(merchService.selectMerchByID(mid).getMprice() * snum);

        int res=shopcartService.insertShop(shopcart);
        System.out.println(res);
        response.getWriter().write("true");
        response.getWriter().close();
    }
    @RequestMapping("/delShop.do")
    public void delShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        long sid=Long.parseLong(request.getParameter("sid")) ;
        shopcartService.deleteShop(sid);
        response.getWriter().write("true");
        response.getWriter().close();
    }
    @RequestMapping("/showShop.do")
    public void showShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute(Constants.USER_SESSION);
        List<Shopcart> shoplist=shopcartService.selectShopByUID(user.getUid());
        JSONObject jsonObject=getShopJson(shoplist);
        response.getWriter().print(jsonObject);
        response.getWriter().close();

    }
    public JSONObject getShopJson(List<Shopcart> shoplist) {
        float money=0;
        JSONArray shops=new JSONArray();
        JSONObject json=new JSONObject();
        for(int i=0;i<shoplist.size();i++)
        {
            Merchandise merch=merchService.selectMerchByID(shoplist.get(i).getMid());
            JSONObject member_temp=new JSONObject();
            member_temp.put("mid", shoplist.get(i).getMid());
            member_temp.put("mname", merch.getMname());
            member_temp.put("sid", shoplist.get(i).getSid());
            member_temp.put("snum", shoplist.get(i).getSnum());
            member_temp.put("ssum", shoplist.get(i).getSsum());
            member_temp.put("sisbuy", shoplist.get(i).getSisbuy());
            member_temp.put("mimage", merch.getMimage());
            member_temp.put("mprice", (float)merch.getMprice());
            member_temp.put("mstock", merch.getMstock());
            member_temp.put("mtype", merch.getMtype());
            member_temp.put("mtime", merch.getMtime());
            member_temp.put("mrecommend", merch.getMrecommend());
            money=money+shoplist.get(i).getSsum();
            shops.add(member_temp);
        }
        json.put("AllMerchandise", shops);
        json.put("num",shoplist.size());
        json.put("sum",money);
        return json;
    }
}
