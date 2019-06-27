package com.cn.controller;

import com.cn.model.Merchandise;
import com.cn.service.IMerchandiseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2019/6/24 0024.
 */
@Controller
@RequestMapping("/generaMerch")
public class indexController {
    @Resource
    private IMerchandiseService merchService;
    ObjectMapper mapper = new ObjectMapper();
    // 得到商品
    @RequestMapping("/findMerchs.do")
    public void findMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String savedDir1 = request.getSession().getServletContext().getRealPath("");
        System.out.println("路径：" + savedDir1);
        List<Merchandise> merchsAll=merchService.selectAllMerchs();

        if(merchsAll != null){
            System.out.println(mapper.writeValueAsString(merchsAll));
            JSONObject jsonObject=getMerchsJson(merchsAll);
            response.getWriter().print(jsonObject);
            response.getWriter().close();
        }

    }
    //得到类型
    @RequestMapping("/findMerchsByType.do")
    public void findMerchsByType(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String mtype=request.getParameter("mtype");
        List<Merchandise> merchsAll=merchService.selectMerchByType(mtype);
        if(merchsAll != null){
            System.out.println(mapper.writeValueAsString(merchsAll));
            JSONObject jsonObject=getMerchsJson(merchsAll);
            response.getWriter().print(jsonObject);
            response.getWriter().close();
        }
        }
    //搜索接口
    @RequestMapping("/searchMerchs.do")
    public void searchMerchs(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String mname=request.getParameter("mname");
        List<Merchandise> merchsAll=merchService.searchMerchs(mname);
        if(merchsAll != null){
            System.out.println(mapper.writeValueAsString(merchsAll));
            JSONObject jsonObject=getMerchsJson(merchsAll);
            response.getWriter().print(jsonObject);
            response.getWriter().close();
        }

    }
    @RequestMapping("/findMerchsByMID.do")
    public void findMerchsByMID(HttpServletRequest request, HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        long mid = Long.parseLong(request.getParameter("mid"));
        Merchandise merch=merchService.selectMerchByID(mid);
        if(merch != null){
            System.out.println("json-bug:" + mapper.writeValueAsString(merch));
            response.getWriter().write(mapper.writeValueAsString(merch));
            response.getWriter().close();
        }else{
            response.getWriter().write(mapper.writeValueAsString("flase"));
            response.getWriter().close();
        }
    }
    public JSONObject getMerchsJson(List<Merchandise> merchsAll) {
        JSONArray merchs=new JSONArray();
        JSONObject json=new JSONObject();
        for(int i=0;i<merchsAll.size();i++)
          {
                JSONObject member_temp=new JSONObject();
                member_temp.put("mid", merchsAll.get(i).getMid());
                member_temp.put("mname", merchsAll.get(i).getMname());
                member_temp.put("mimage", merchsAll.get(i).getMimage());
                member_temp.put("mprice", merchsAll.get(i).getMprice());
                member_temp.put("mstock", merchsAll.get(i).getMstock());
                member_temp.put("mtype", merchsAll.get(i).getMtype());
                member_temp.put("mtime", merchsAll.get(i).getMtime());
                member_temp.put("mrecommend", merchsAll.get(i).getMrecommend());
                merchs.add(member_temp);
            }
        json.put("allMerchandise", merchs);
        json.put("num",merchsAll.size());
        return json;
    }

}
