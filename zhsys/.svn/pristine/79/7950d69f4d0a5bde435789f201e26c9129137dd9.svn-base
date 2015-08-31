package com.cbice.distribute.core.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TConfig;
import com.cbice.distribute.core.db.model.TUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TConfigDbServiceTest{
    @Resource
    private TConfigDbService tConfigDbService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testSelectKey(){
    	TConfig tconfig=tConfigDbService.selectByPrimaryKey("order.payRate");
    	System.out.println(tconfig.getValue());
    	
    }
    
    
    
    @Test
    public void testhigerDealer(){
    	}
    	
    

}
