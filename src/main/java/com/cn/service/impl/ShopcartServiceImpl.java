package com.cn.service.impl;

import com.cn.dao.IShopcartDao;
import com.cn.model.Shopcart;
import com.cn.service.IShopcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Service("shopcartService")
public class ShopcartServiceImpl implements IShopcartService{
    @Resource
    private IShopcartDao shopDao;

    @Override
    public Shopcart selectShopBySID(long Sid) {
        return this.shopDao.selectShopcartBySID(Sid);
    }

    @Override
    public List<Shopcart> selectShopByUID(long Uid) {
        return this.shopDao.selectShopcartByUID(Uid);
    }

    @Override
    public int insertShop(Shopcart shopcart) {
        return this.shopDao.insertShopcart(shopcart);
    }

    @Override
    public int deleteShop(long Sid) {
        return this.deleteShop(Sid);
    }

    @Override
    public int updataShop(Shopcart shopcart) {
        return this.updataShop(shopcart);
    }
}
