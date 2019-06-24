package com.cn.controller;

import com.cn.service.IMerchandiseService;
import com.cn.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Controller
@RequestMapping("/masterMerch")
public class MerchandiseController {
    @Resource
    private IMerchandiseService merchService;
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


    }
//    上传商品
    @RequestMapping("/updataMerch.do")
    public void updataMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException{


    }
//    删除商品
    @RequestMapping("/deleteMerch.do")
    public void deleteMerchs(HttpServletRequest request, HttpServletResponse response)throws IOException{


    }
    private String getUrl(String filename){
        String url="";
        return url;
    }

}
