package com.cn.controller;

import com.cn.service.IMerchandiseService;
import com.cn.service.IUserService;
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
@RequestMapping("/merch")
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
    @RequestMapping("/insertMerch.do")
    public String insertMerch(HttpServletRequest request, HttpServletResponse response,MultipartFile uploadFile){

        return "";
    }

}
