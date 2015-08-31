package com.cbice.distribute.core.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cbice.distribute.core.db.model.TRemainderGoods;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/service.xml"})
public class TRemainderGoodsServiceTest {
	@Resource
	private TRemainderGoodsService tremainderGoodsService;
	
	@Test
	public void testSelectbuyUserid() {
//		fail("Not yet implemented");
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userId", 1);
		List<TRemainderGoods> toBeSendList=new ArrayList<TRemainderGoods>();
//		TRemainderGoods tRemainderGoods = new TRemainderGoods();
		toBeSendList = tremainderGoodsService.selectbuyUserid(paramMap);
		System.out.println(toBeSendList.toString());
	}

}
