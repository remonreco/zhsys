package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsExchangeOrderMapper;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.HsExchangeOrderDbService;

public class HsExchangeOrderDbServiceImpl implements HsExchangeOrderDbService{
	HsExchangeOrderMapper hsExchangeOrderMapper;
	public void setHsExchangeOrderMapper(HsExchangeOrderMapper hsExchangeOrderMapper) {
		this.hsExchangeOrderMapper = hsExchangeOrderMapper;
	}
	@Override
	public List<HsExchangeOrder> queryExchangeOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsExchangeOrderMapper.queryExchangeOrder(map);
	}
	@Override
	public int updateByPrimaryKeySelective(HsExchangeOrder hsExchangeOrder) {
		// TODO Auto-generated method stub
		return hsExchangeOrderMapper.updateByPrimaryKeySelective(hsExchangeOrder);
	}
	@Override
	public List<HsExchangeOrder> queryExchangeOrderAndGift(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsExchangeOrderMapper.queryExchangeOrderAndGift(map);
	}
	@Override
	public HsExchangeOrder selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return hsExchangeOrderMapper.selectByPrimaryKey(id);
	}

}
