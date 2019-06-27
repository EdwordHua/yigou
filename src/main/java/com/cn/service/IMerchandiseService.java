package com.cn.service;

import com.cn.dao.IMerchandiseDao;
import com.cn.model.Merchandise;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
public interface IMerchandiseService {
    List<Merchandise> selectAllMerchs();
    Merchandise selectMerchByID(long Mid);
    List<Merchandise> searchMerchs(String mname);
    List<Merchandise> selectMerchByName(String Uname);
    List<Merchandise> selectMerchByType(String Type);
    int insertMerch(Merchandise merchandise);
    int updataMerch(Merchandise merchandise);
    int deleteMerch(long Mid);
}
