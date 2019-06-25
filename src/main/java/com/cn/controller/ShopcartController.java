package com.cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Controller
@RequestMapping("/shopcart")
public class ShopcartController {
    @RequestMapping("/addShop.do")
    public void addShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
    @RequestMapping("/delShop.do")
    public void delShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
    @RequestMapping("/showShop.do")
    public void showShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
