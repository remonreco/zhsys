package com.cbice.distribute.core.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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

import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.util.SequenceCreaterUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TGoodsOrderTest {
	@Resource
    private TGoodsBatchService tGoodsBatchService;
	@Resource
    private TGoodsOrderService tGoodsOrderService;
    

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void testGenOutTradeNo(){
    	//VGoodOrderUser goods =new VGoodOrderUser();
    	//goods.setUid(3L);
    	//goods.setAssetsAccount("fdff");
    	//goods.setUserName("fdsfdsfds");
    	//goods.setLimit(4);
    	//goods.setOffset(0);
//    	Map<String, Object> map =new HashMap<String, Object>();
//    	map.put("userId", 1L);
//    	List<TGoodsBatch> orders =tGoodsBatchService.selectByUserId(map);
//         for (TGoodsBatch tGoodsBatch : orders) {
//			System.out.println(tGoodsBatch.getId());
//		}
//        int count =tGoodsBatchService.countSelectDealer(map);
//        System.out.println(count);
    	String show =SequenceCreaterUtil.createSequence("DB");
    	System.out.println(show);
    	System.out.println(show.length());
    }

    @Test
    public void testToSendbyUserId(){
    	Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", Integer.parseInt("1"));
		map.put("limit", 5);
		map.put("offset", 1 - 1);
		
		List<TGoodsOrder> toBeSendList=new ArrayList<TGoodsOrder>();
		List<TGoodsOrder> list=new ArrayList<TGoodsOrder>();
		toBeSendList = tGoodsOrderService.selectToSendBuyUserid(map);
		for(TGoodsOrder tGoodsOrder:toBeSendList){
			tGoodsOrder.setAgencyName(tGoodsOrderService.selectDealerNameById(tGoodsOrder.getAgencyId()));
		}
		int count = tGoodsOrderService.countSelectToSendBuyUserid(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		System.out.println(jsonmap);
    }
    
    @Test
    public void testService(){
    	Map<String,Object> map=new HashMap<String, Object>();
        map.put("userId", 8000001);
		map.put("goodsName", "测试数据2");
        map.put("goodsNum", "0007");
       List<TGoodsOrder> order= tGoodsOrderService.selectOrderbyUserIdgoodsNameGoodsNum(map);
       System.out.println(order.size());
    }
}
