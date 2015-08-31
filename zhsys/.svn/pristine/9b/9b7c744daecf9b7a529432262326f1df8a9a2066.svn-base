package com.cbice.distribute.mgr.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.mgr.service.TremainderGoodsService;

public class TremainderGoodsServiceImpl implements TremainderGoodsService {

	private TRemainderGoodsService tRemainderGoodsService;
	
	
	public void settRemainderGoodsService(
			TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
	}


	@Override
	public int insertSelective(TRemainderGoods record) {
		return tRemainderGoodsService.insertSelective(record);
	}


	@Override
	public int updateByPrimaryKeySelective(TRemainderGoods record) {
		return tRemainderGoodsService.updateByPrimaryKeySelective(record);
	}


	@Override
	public  List<TRemainderGoods> selectbuyUserid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tRemainderGoodsService.selectbuyUserid(map);
	}


	@Override
	public int updateNumByUserAndGoods(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tRemainderGoodsService.updateNumByUserAndGoods(map);
	}


	@Override
	public int updateTRemainderGoodsByUserIdAndGoodId(TRemainderGoods tRemainderGoods) {
		
		return tRemainderGoodsService.updateTRemainderGoodsByUserIdAndGoodId(tRemainderGoods);
	}

}
