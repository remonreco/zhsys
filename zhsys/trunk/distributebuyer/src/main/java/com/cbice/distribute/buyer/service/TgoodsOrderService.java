package com.cbice.distribute.buyer.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoodsOrder;

public interface TgoodsOrderService {
	
	List<TGoodsOrder> selectUserMemberbyOrder(Map<String,Object> map);
	/**
	 * 查询历史购买记录
	 * @param map
	 * @return
	 */
	List<TGoodsOrder> selectHistoryGoodsOrder(Map<String, Object> map);
	
	TGoodsOrder selectByPrimaryKey(long id);
	
	int updateByPrimaryKeySelective(TGoodsOrder tGoodsOrder);
	
	int fromASingle(long id,long user_id,String t_goods_num,String goods_num, String returnGoodsReason);
	
	Map<String,Object> selectHistoryGoodsOrderCount(Map<String, Object> map);
	TGoodsOrder  selectOrderbySerial(Map<String,Object> paramMap);
	int countSelectMemberHistoryGoodsOrderBy(Map<String, Object> paramMap);
	List<TGoodsOrder> selectMemberHistoryGoodsOrderBy(Map<String, Object> map);
}
