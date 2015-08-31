package com.cbice.distribute.buyer.service.impl;

import javax.annotation.Resource;

import com.cbice.distribute.buyer.service.TAgencyBuyerService;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.service.TAgencyDbService;

public class TAgencyBuyerServiceImpl implements TAgencyBuyerService {
	@Resource
	private TAgencyDbService tAgencyDbService;

	@Override
	public TAgency selectByPrimaryKey(long id) {
		return tAgencyDbService.selectByAgencyId(id);
	}
	
	
}
