package com.cbice.distribute.buyer.service.impl;

import javax.annotation.Resource;

import com.cbice.distribute.buyer.service.HsTransactionRecordService;
import com.cbice.distribute.core.db.model.HsTransactionRecord;
import com.cbice.distribute.core.service.HsTransactionRecordDbService;

public class HsTransactionRecordServiceImpl implements HsTransactionRecordService{
	@Resource
	HsTransactionRecordDbService hsTransactionRecordDbService;
	
	public void setHsTransactionRecordDbService(
			HsTransactionRecordDbService hsTransactionRecordDbService) {
		this.hsTransactionRecordDbService = hsTransactionRecordDbService;
	}

	@Override
	public int insertSelective(HsTransactionRecord hsTransactionRecord) {
		// TODO Auto-generated method stub
		return hsTransactionRecordDbService.insertSelective(hsTransactionRecord);
	}

}
