package com.cn.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/6/25 0025.
 */
//得到系统时间
public class STime {
    public static String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(new Date());
    }
}
