package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsTransactionRecordMapper;
import com.cbice.distribute.core.db.model.HsTransactionRecord;
import com.cbice.distribute.core.service.HsTransactionRecordDbService;

public class HsTransactionRecordDbServiceImpl implements HsTransactionRecordDbService {
	HsTransactionRecordMapper hsTransactionRecordMapper;

	public void setHsTransactionRecordMapper(
			HsTransactionRecordMapper hsTransactionRecordMapper) {
		this.hsTransactionRecordMapper = hsTransactionRecordMapper;
	}

	@Override
	public int insertSelective(HsTransactionRecord hsTransactionRecord) {
		// TODO Auto-generated method stub
		return hsTransactionRecordMapper.insertSelective(hsTransactionRecord);
	}

	@Override
	public List<HsTransactionRecord> queryTodayTransactionRecord(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsTransactionRecordMapper.queryTodayTransactionRecord(map);
	}
	
}
