package com.cn.controller;

import com.cn.model.Merchandise;
import com.cn.service.IMerchandiseService;
import com.cn.service.IUserService;
import com.cn.tools.STime;
import com.cn.tools.deleteFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Controller
@RequestMapping("/masterMerch")
public class MerchandiseController {
    @Resource
    private IMerchandiseService merchService;
    ObjectMapper mapper = new ObjectMapper();

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
    public void insertMerch(HttpServletRequest request, HttpServletResponse response)throws IOException{
       request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
       response.setContentType("text/html;charset=utf-8");
       Merchandise merch=new Merchandise();
       //String savedDir1 = request.getSession().getServletContext().getRealPath("")+"\\webapp\\image\\products\\";
       String mimage="image/products"+request.getParameter("mimage");
       System.out.println("mimage:"+mimage);
       merch.setMname(request.getParameter("mname"));
       merch.setMtime(STime.getTime());
       merch.setMrecommend(request.getParameter("mrecommend"));
       merch.setMtype(request.getParameter("mtype"));
       int mprice=Integer.valueOf(request.getParameter("mprice"));
       int mstock=Integer.valueOf(request.getParameter("mstock"));

       merch.setMprice(mprice);
       merch.setMstock(mstock);
       System.out.println("Mname："+merch.getMname()+"Mtype："+merch.getMtype());
//       System.out.println("路径："+savedDir1);
     //  merchService.insertMerch(merch);

        response.getWriter().write(mapper.writeValueAsString("true"));
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
//    更新商品
    @RequestMapping("/updataMerch.do")
    public void updataMerchs(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        long mid=Long.parseLong(request.getParameter("mid"));
        String mname=request.getParameter("mname");
        int mprice=Integer.valueOf(request.getParameter("mprice"));
        int mstock=Integer.valueOf(request.getParameter("mstock"));
        Merchandise merchandise=new Merchandise();
        merchandise.setMid(mid);
        merchandise.setMname(mname);
        merchandise.setMprice(mprice);
        merchandise.setMstock(mstock);
        merchandise.setMimage(request.getParameter("mimage"));
        merchandise.setMtype(request.getParameter("mtype"));
        merchandise.setMrecommend(request.getParameter("mrecommend"));
        merchandise.setMtime(request.getParameter("mtime"));
        int res= merchService.updataMerch(merchandise);
        System.out.println(res);
        System.out.println("mid:"+mid+",mname:"+mname);

        response.getWriter().write("true");
        response.getWriter().close();
    }


//
//    @ResponseBody
//    public Items jsonTest(@RequestBody Items items) {
//        return items;
//    }

    @RequestMapping("/Test.do")
    @ResponseBody
    public String Test(@RequestBody Map<String,String> params, HttpServletRequest request,HttpServletResponse response) throws Exception{
        System.out.println("总共获取到："+params.size()+"个参数");
        for(String key : params.keySet()){
            System.out.println(key + " : " + params.get(key));
        }
        return "";
    }

//    删除商品
    @RequestMapping("/deleteMerch.do")
    public void deleteMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException{
        long mid = Long.parseLong(request.getParameter("mid"));
        System.out.println(mid);
        Merchandise merch=merchService.selectMerchByID(mid);
        String fileDir = request.getSession().getServletContext().getRealPath("")+"\\webapp"+merch.getMimage();
        System.out.println(fileDir);
        //deleteFile.delete(fileDir);
        int res=merchService.deleteMerch(mid);
        System.out.println("res:"+res);
       // getMerchs( request,response);
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
        json.put("data", merchs);
        json.put("count",merchsAll.size());
        json.put("msg","");
        json.put("code",0);
        return json;
    }

@RequestMapping("/uploadimage.do")   //上传图片
    public Map<String,Object> image(MultipartFile file,HttpServletRequest request,@PathVariable String type)throws  Exception{
    System.out.println("path:");
    Map<String,Object> map = new HashMap<String,Object>();
        String path=request.getSession().getServletContext().getRealPath("\\image\\products\\");
        String image=uploadFile(file,path);
        System.out.println("path:"+image);
        map.put("code",0);
        map.put("image",image);
        return map;
    }

    public static String uploadFile(MultipartFile file,String path)throws  Exception{
        String name=file.getOriginalFilename();
        String suffixName =name.substring(name.lastIndexOf("."));
        String hash=Integer.toHexString(new Random().nextInt());
        String fileName=hash + suffixName;
        File tempFile = new File(path,fileName);
        if(!tempFile.getParentFile().exists()){
            tempFile.getParentFile().mkdir();
        }
        if(tempFile .exists()){
            tempFile.delete();
        }
        tempFile.createNewFile();
        file.transferTo(tempFile);
        return  tempFile.getName();
    }
}
