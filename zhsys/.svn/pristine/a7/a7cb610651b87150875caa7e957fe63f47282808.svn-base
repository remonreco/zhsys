package com.cbice.distribute.core.service;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.data.TAgencyMapper;
import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TAgencyUserServiceTest {
	/*  @Resource
	    private AgencyUserService agencyUserService;*/
	
	    @Resource
	    private TUserMapper tuserMapper;
	    
	    @Resource
	    private TAgencyMapper tAgencyMapper;
	    
	    @Before
	    public void setUp() throws Exception{
	    }

	    @After
	    public void tearDown() throws Exception{
	    }
	    
	    
	    @Test
	    public void testhigerDealer(){
	    	TUser user = tuserMapper.selectByPrimaryKey(1212l);
	    	System.out.println(user);
	    }
	    
	    @Test
	    public void selectById(){
	    	tAgencyMapper.selectByPrimaryKey(1212l);
	    	System.out.println(tAgencyMapper.selectByPrimaryKey(1212l));
	    }
}
