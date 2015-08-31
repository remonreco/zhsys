package com.cbice.distribute.agency.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TRemainderGoods;

public interface TremainderGoodsService {
	
	 List<TRemainderGoods> selectbuyUserid(Map<String,Object> map);
	int updateByPrimaryKeySelective(String t_goods_num ,Long user_id, Long goods_num, String returnGoodsReason,String goods_name);
	 TRemainderGoods selectbuyUseridAndGoodsid(Map<String,Object> map);
}
