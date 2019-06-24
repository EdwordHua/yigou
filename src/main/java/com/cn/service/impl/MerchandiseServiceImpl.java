package com.cn.service.impl;

import com.cn.dao.IMerchandiseDao;
import com.cn.model.Merchandise;
import com.cn.service.IMerchandiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
@Service("merchService")
public class MerchandiseServiceImpl implements IMerchandiseService{


    @Resource
    private IMerchandiseDao merchDao;
    @Override
    public Merchandise selectMerchByID(long Mid) {     //按照Mid搜索商品
        return this.merchDao.selectMerchByID(Mid);
    }

    @Override
    public List<Merchandise> selectMerchByName(String Uname) {  //按照商品名搜索商品
        return this.merchDao.selectMerchByName(Uname);
    }

    @Override
    public List<Merchandise> selectMerchByType(String Type) {  //按照商品类型搜索商品
        return this.merchDao.selectMerchByType(Type);
    }
    @Override
    public int insertMerch(Merchandise merchandise) {        //插入商品
        return this.merchDao.insertMerch(merchandise);
    }

    @Override
    public int updataMerch(Merchandise merchandise) {    //更新商品
        return this.merchDao.updataMerch(merchandise);
    }

    @Override
    public int deleteMerch(long Mid) {          //删除商品
        return this.deleteMerch(Mid);
    }
}
