package com.cn.service;

import com.cn.model.Shopcart;

import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
public interface IShopcartService {
    Shopcart selectShopBySID(long Sid);
    List<Shopcart> selectShopByUID(long Uid);
    int insertShop(Shopcart shopcart);
    int deleteShop(long Sid);
    int updataShop(Shopcart shopcart);
}
