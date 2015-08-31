package com.cbice.distribute.core.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class SeqServiceTest{
    @Resource
    private SeqService seqService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testGenOutTradeNo(){
        String outTradeNo = seqService.genOutTradeNo();
        assertNotNull(outTradeNo);
        System.out.println(outTradeNo);
    }
    
    
    @Test
    public void getTAgentId(){
//    	long outTradeNo = seqService.getTAgentId();
//        assertNotNull(outTradeNo);
//        System.out.println(outTradeNo);
        
//        System.out.println("==============================="+seqService.getExchangeOrderId());
    }

}
