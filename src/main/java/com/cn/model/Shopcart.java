package com.cn.model;

/**
 * Created by Administrator on 2019/6/20 0020.
 * author:YX
 * class:购物车
 */
public class Shopcart {
    private long Sid;    //购物车ID编号
    private long Uid;    //用户ID
    private long Mid;    //商品ID
    private int Snum;    //购买的数量
    private int Ssum;    //应支付钱
    private int Sisbuy;  //是否已支付

    public long getSid() {
        return Sid;
    }

    public void setSid(long sid) {
        Sid = sid;
    }

    public long getUid() {
        return Uid;
    }

    public void setUid(long uid) {
        Uid = uid;
    }

    public long getMid() {
        return Mid;
    }

    public void setMid(long mid) {
        Mid = mid;
    }

    public int getSnum() {
        return Snum;
    }

    public void setSnum(int snum) {
        Snum = snum;
    }

    public int getSsum() {
        return Ssum;
    }

    public void setSsum(int ssum) {
        Ssum = ssum;
    }

    public int getSisbuy() {
        return Sisbuy;
    }

    public void setSisbuy(int sisbuy) {
        Sisbuy = sisbuy;
    }
}
