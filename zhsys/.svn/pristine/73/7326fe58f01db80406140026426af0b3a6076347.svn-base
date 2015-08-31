package com.cbice.distribute.core.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TAgency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TAgencyDbServiceTest{
    @Resource
    private TAgencyDbService tAgencyDbService;

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testGenPreorder(){
        boolean result = tAgencyDbService.genPreorder();
        assertTrue(result);
    }
   
    

    @Test
    public void testGenasdfa(){
    	
        TAgency agency = tAgencyDbService. selectByAgencyId(8000001);
        System.out.println(".....");
       
    }
}
