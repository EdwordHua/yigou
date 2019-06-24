package com.cn.model;

/**
 * Created by Administrator on 2019/6/20 0020.
 * author:YX
 * class:商品表
 */
public class Merchandise {
    private long Mid;     //商品ID
    private String Mname; //商品名
    private String Mimage; //商品图片路径
    private int Mprice;   //商品价格
    private int Mstock;   //商品库存
    private String Mtype;  //商品类型
    private String Mtime;  //上架时间
    private String Mrecommend; //粉丝评价

    public long getMid() {
        return Mid;
    }

    public void setMid(long mid) {
        Mid = mid;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getMimage() {
        return Mimage;
    }

    public void setMimage(String mimage) {
        Mimage = mimage;
    }

    public int getMprice() {
        return Mprice;
    }

    public void setMprice(int mprice) {
        Mprice = mprice;
    }

    public int getMstock() {
        return Mstock;
    }

    public void setMstock(int mstock) {
        Mstock = mstock;
    }

    public String getMtype() {
        return Mtype;
    }

    public void setMtype(String mtype) {
        Mtype = mtype;
    }

    public String getMtime() {
        return Mtime;
    }

    public void setMtime(String mtime) {
        Mtime = mtime;
    }

    public String getMrecommend() {
        return Mrecommend;
    }

    public void setMrecommend(String mrecommend) {
        Mrecommend = mrecommend;
    }
}
