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

import com.cbice.distribute.core.db.model.TUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TUserServiceTest{
    @Resource
    private TUserService tUserService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testGenOutTradeNo(){
    	TUser user=new TUser();
    	List<TUser> list=tUserService.selectDealer(user);
    	System.out.println(list.size());
    }
    
    
    
    @Test
    public void testhigerDealer(){
    	List<TUser> list=tUserService.selectUserAndTest();
    	for(int i=0;i<list.size();i++){
    		TUser user=list.get(i);
    		String account=user.getAssetsAccount();
    		Integer test=Integer.parseInt(account);
    	}
    	
    }

}
