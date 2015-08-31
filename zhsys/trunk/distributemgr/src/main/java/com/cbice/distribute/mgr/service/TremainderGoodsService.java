package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TRemainderGoods;

public interface TremainderGoodsService {
	
     int insertSelective(TRemainderGoods record);
	 
	 int updateByPrimaryKeySelective(TRemainderGoods record);
	 
	 List<TRemainderGoods> selectbuyUserid(Map<String,Object> map);

	 int updateNumByUserAndGoods(Map<String, Object> map);
	 int updateTRemainderGoodsByUserIdAndGoodId(TRemainderGoods tRemainderGoods);
}
