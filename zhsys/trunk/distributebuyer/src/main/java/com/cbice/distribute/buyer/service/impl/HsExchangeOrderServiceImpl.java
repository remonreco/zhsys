package com.cbice.distribute.buyer.service.impl;

import javax.annotation.Resource;

import com.cbice.distribute.buyer.service.HsExchangeOrderService;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.HsExchangeOrderDbService;

public class HsExchangeOrderServiceImpl implements HsExchangeOrderService{
	@Resource
	HsExchangeOrderDbService hsExchangeOrderDbService;
	public void setHsExchangeOrderDbService(
			HsExchangeOrderDbService hsExchangeOrderDbService) {
		this.hsExchangeOrderDbService = hsExchangeOrderDbService;
	}
	@Override
	public HsExchangeOrder selectByPrimaryKey(HsExchangeOrder order) {
		// TODO Auto-generated method stub
		return hsExchangeOrderDbService.selectByPrimaryKey(order.getId());
	}

}
