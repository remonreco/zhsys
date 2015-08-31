package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsExchangeOrder;

public interface HsExchangeOrderDbService {
	/**
	 * 用户交易记录及用户权益信息查询
	 * @param map
	 * @return
	 */
	List<HsExchangeOrder> queryExchangeOrder(Map<String, Object> map);
	
	/**
	 * 更新订单状态
	 * @param map
	 * @return
	 */
	int updateByPrimaryKeySelective(HsExchangeOrder hsExchangeOrder);
	/**
	 * 查询赠品相关信息
	 * @param map1
	 * @return
	 */
	List<HsExchangeOrder> queryExchangeOrderAndGift(Map<String, Object> map1);

	HsExchangeOrder selectByPrimaryKey(Long id);

}
