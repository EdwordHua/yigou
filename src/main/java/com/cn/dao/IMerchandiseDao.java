package com.cn.dao;

import com.cn.model.Merchandise;

import java.util.List;

/**
 * Created by Administrator on 2019/6/20 0020.
 */
public interface IMerchandiseDao {
    Merchandise selectMerchByID(long Mid);
    List<Merchandise> selectAllMerchs();
    List<Merchandise> searchMerchs(String mname);
    List<Merchandise> selectMerchByName(String Uname);
    List<Merchandise> selectMerchByType(String mtype);
    int insertMerch(Merchandise merchandise);
    int updataMerch(Merchandise merchandise);
    int deleteMerch(long Mid);
}
