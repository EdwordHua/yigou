package com.cn.controller;

import com.cn.model.Merchandise;
import com.cn.service.IMerchandiseService;
import com.cn.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Controller
@RequestMapping("/masterMerch")
public class MerchandiseController {
    @Resource
    private IMerchandiseService merchService;
    ObjectMapper mapper = new ObjectMapper();
    @RequestMapping("/upload.do")
    public String upload(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile) throws IOException {
        if (uploadFile != null) {
            String filename = uploadFile.getOriginalFilename();
            System.out.print(filename);
            File newFile = new File("C:/" + filename);
            try {
                uploadFile.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
//上传商品
   @RequestMapping("/insertMerch.do")
    public void insertMerch(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("BUG-TEST-json:"+mapper.writeValueAsString(""));
        response.getWriter().write(mapper.writeValueAsString(""));
        response.getWriter().close();
    }
 // 得到商品
    @RequestMapping("/getMerch.do")
    public void getMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException{
        List<Merchandise> merchsAll=merchService.selectAllMerchs();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        if(merchsAll != null){
            System.out.println(mapper.writeValueAsString(merchsAll));
            JSONObject jsonObject=getMerchsJson(merchsAll);
            response.getWriter().print(jsonObject);
            response.getWriter().close();
        }

    }
//    上传商品
    @RequestMapping("/updataMerch.do")
    public void updataMerchs(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)throws IOException{
        String savedDir1 = request.getSession().getServletContext().getRealPath("");
        System.out.println("路径："+savedDir1);

    }
//    删除商品
    @RequestMapping("/deleteMerch.do")
    public void deleteMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException{


    }
    private String getUrl(String filename){
        String url="";
        return url;
    }

    public JSONObject getMerchsJson(List<Merchandise> merchsAll) {
        JSONArray merchs=new JSONArray();
        JSONObject json=new JSONObject();
        for(int i=0;i<merchsAll.size();i++)
        {
//
//                long mid = merchsAll.get(i).getMid();
//                String mname = merchsAll.get(i).getMname();
//                String mimage = merchsAll.get(i).getMimage();
//                int mprice = merchsAll.get(i).getMprice();
//                int mstock = merchsAll.get(i).getMstock();
//                String mtype = merchsAll.get(i).getMtype();
//                String mtime = merchsAll.get(i).getMtime();
//                String mrecommend = merchsAll.get(i).getMrecommend();

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

        json.put("data", merchs);
        json.put("count",merchsAll.size());
        json.put("msg","");
        json.put("code",0);
        return json;
    }

}
