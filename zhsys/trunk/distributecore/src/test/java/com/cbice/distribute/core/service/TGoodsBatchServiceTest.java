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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.model.TrancationReport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TGoodsBatchServiceTest{
    @Resource
    private TGoodsBatchService tGoodsBatchService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }


    
    
    
    @Test
    public void testhigerDealer(){
    Map<String,Object> map=new HashMap<String,Object>();
      map.put("userId", 1);
    	List<TrancationReport> list=tGoodsBatchService.saleCodeOut(map);
    	System.out.println(list.size());
    }

    
    @Test
    public void outTra(){
    Map<String,Object> map=new HashMap<String,Object>();
      map.put("agenylft", 27);
      map.put("agenyrgt", 2);
      map.put("goodsNum", "1avoc7");
      map.put("t_user_id", 8000000);
      List<TrancationReport> list=tGoodsBatchService.outTrancationReport(map);
      System.out.println(list.size());
    }
}
