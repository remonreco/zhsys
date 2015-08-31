package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsTransactionRecord;

public interface HsTransactionRecordDbService {
	/**
	 * 新增实时交易记录
	 * @param hsTransactionRecord
	 * @return
	 */
	int insertSelective(HsTransactionRecord hsTransactionRecord);

	List<HsTransactionRecord> queryTodayTransactionRecord(Map<String, Object> map);

}
