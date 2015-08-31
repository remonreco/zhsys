package com.cbice.distribute.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TAgency;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/service.xml" })
public class CommidityTest {

	@Resource
	private CommodityRuleDbservice commodityRuleDbservice;

	@Test
	public void testGenasdfa() {
		
		Map<String,Object> mapr1=new HashMap<String,Object>();
		mapr1.put("comId", 9);
		mapr1.put("comName", "账户");
		mapr1.put("exchangeId", 99);
		mapr1.put("exchangeName","随便");
		mapr1.put("exchangeNum", 6);
		mapr1.put("comType", 1);

		int result1 = commodityRuleDbservice.insertNewComdityRole(mapr1);
		System.out.println(".....");

	}

}
