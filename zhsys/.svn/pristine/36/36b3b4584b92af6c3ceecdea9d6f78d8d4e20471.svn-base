package com.cbice.distribute.core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.util.constantList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class HsExchangeOrderServiceTest {
	@Resource
	HsExchangeOrderDbService hsExchangeOrderDbService ;
	@Resource
	HsCommodityDbservice hsCommodityDbservice ;
	@Resource
	HsUserRightsDbService hsUserRightsDbService;
	
	@Test
    public void testSelectKey(){
		HsExchangeOrder hsExchangeOrder = new HsExchangeOrder();
		hsExchangeOrder.setId(20150525101L);
		hsExchangeOrder.setOrderState(constantList.ORDER_STATE_2);
		int a =hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder);
    	System.out.println(a);
    	
    }
	
	@Test
    public void testupdate(){
		HsCommodity hsCommodity = new HsCommodity();
		hsCommodity.setComId(100012L);
		hsCommodity.setFreezeNum(10L);
		int a =hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity);
    	System.out.println(a);
    	
    }
	
	@Test
    public void testupdateRights(){
		HsUserRights hsUserRights = new HsUserRights();
		hsUserRights.setCustomerNum(1107864L);
		hsUserRights.setRightsCode(100010L);
		hsUserRights.setAvailableNum(100L);
		int a =hsUserRightsDbService.updateAvailableNum(hsUserRights);
    	System.out.println(a);
    	
    }
	@Test
    public void testOrderGift(){
		Map map1 =new HashMap();
		List list2 = new ArrayList();
		map1 = new HashMap<String, Object>();
		map1.put("orderType", constantList.ORDER_TYPE_1);
		map1.put("orderState2",constantList.ORDER_STATE_2);
		map1.put("orderState3",constantList.ORDER_STATE_3);
		String id1 = "";
		for(int i=0;i<list2.size();i++){
			if(i==list2.size()-1){
				id1 = id1 + list2.get(i);
			}else{
				id1 = id1+list2.get(i)+",";
			}
		}
		List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
    	
    }
}
