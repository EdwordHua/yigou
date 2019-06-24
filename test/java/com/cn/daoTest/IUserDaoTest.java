package com.cn.daoTest;

/**
 * Created by Administrator on 2019/6/19 0019.
 */
 import com.cn.dao.IMerchandiseDao;
 import com.cn.dao.IShopcartDao;
 import com.cn.dao.IUserDao;
 import com.cn.model.Merchandise;
 import com.cn.model.Shopcart;
 import com.cn.model.User;
 import com.fasterxml.jackson.databind.ObjectMapper;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.springframework.test.context.ContextConfiguration;
 import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

 import javax.annotation.Resource;
 import java.util.List;
// 加载spring配置文件

  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration({"classpath:spring-mybatis.xml"})
  public class IUserDaoTest {
      @Resource
      private IUserDao dao;
      @Resource
      private IMerchandiseDao merchdao;
      @Resource
      private IShopcartDao shopcartDao;
      @Test
      public void testSelectUser() throws Exception {
          long id = 1;
          User user = dao.selectUser(id);
          ObjectMapper mapper =new ObjectMapper();

          System.out.println("DEBUG-json:"+mapper.writeValueAsString(user));
      }
      @Test
      public void testInsertUser() throws Exception {
//          long id = 1;
          User user = new User();
          user.setUname("test");
          user.setUpassword("123");
          user.setULevel(1);
          user.setUtime("1019-6-19");
          user.setUaddress("湖北师范大学学舍6");
          int result=dao.insertUser(user);


          System.out.println("DEBUG-Result:"+result);
      }
      @Test
      public void testselectMerch() throws Exception {
          long id = 1;
          Merchandise merch = merchdao.selectMerchByID(id);
          if(merch != null) {
              ObjectMapper mapper = new ObjectMapper();
              System.out.println("DEBUG-json:" + mapper.writeValueAsString(merch));
          }else{
              System.out.print("BUG_DON! ");
          }
      }
      @Test
      public void testinsertMerch() throws Exception {
         Merchandise merchandise=new Merchandise();
          merchandise.setMimage("c:\\ccca");
          merchandise.setMname("iphone 6s");
          merchandise.setMprice(6000);
          merchandise.setMrecommend("牛逼！");
          merchandise.setMstock(100);
          merchandise.setMtype("手机");
          merchandise.setMtime("2019-6-20");
          int result=merchdao.insertMerch(merchandise);
          System.out.println("DEBUG-Result:"+result);
      }


      @Test
      public void testselectShop() throws Exception {
          long id = 1234567;
          List<Shopcart> shoplist = shopcartDao.selectShopcartByUID(id);
          if(shoplist != null) {
              ObjectMapper mapper = new ObjectMapper();
              for (int i=0;i<shoplist.size();i++)
              {
                  System.out.println("DEBUG-json:" + mapper.writeValueAsString(shoplist.get(i)));
              }

          }else{
              System.out.print("BUG_DON! ");
          }
      }
  }
