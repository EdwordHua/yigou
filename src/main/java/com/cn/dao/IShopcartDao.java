package com.cn.dao;

import com.cn.model.Shopcart;

import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 * author:YX
 */
public interface IShopcartDao {
    Shopcart selectShopcartBySID(long Sid);
    List<Shopcart> selectShopcartByUID(long Uid);
    List<Shopcart> selectShopcartBuyByUID(long uid);
    int insertShopcart(Shopcart shopcart);
    int deleteShopcart(long Sid);
    int updataShopcart(Shopcart shopcart);
}
