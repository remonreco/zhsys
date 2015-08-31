package com.cbice.distribute.core.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TGoodsServiceTest{
    @Resource
    private TGoodsService tGoodsService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testGenOutTradeNo(){
      Map<String,Object> map=new HashMap<String,Object>();
      List<TGoods> list=tGoodsService.selectBygoodsNumOrGoodsName(map);
      System.out.println(list.size());
      System.out.println(list.get(0).getGoodsPrice());
    }
    @Test
    public void testGenOutTradeNoa(){
    	String originPwdDigest = new Md5PasswordEncoder().encodePassword("1", null);
    	System.out.println(originPwdDigest);
//      Map<String,Object> map=new HashMap<String,Object>();
//      map.put("goodsNum", "98956");
//      map.put("goodsName", "魅族6");
//     TGoods list=tGoodsService.selectOnlyGoodsNumOrGoodsName(map);
//      System.out.println(list.getId());
//      System.out.println(list.getGoodsName());
//      System.out.println(list.getGoodsPrice());
    	
    }
}
