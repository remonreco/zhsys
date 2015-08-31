package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.model.TrancationReport;

public interface TgoodsBatchService {
	
	int insertSelective(TGoodsBatch record);
	List<TGoodsBatch> selectByUserId(Map<String,Object> map);
	int countSelectDealer(Map<String,Object> map);
	List<TrancationReport> selectTrancationReport(Map<String,Object> map);
	
	int countSelectTrancationReport(Map<String,Object> map);
	
	List<TrancationReport> outTrancationReport(Map<String,Object> map);
	TGoodsBatch selectGoodsByGoodsnum(String goodsNum);
	List<TrancationReport> saleCodeOut(Map<String,Object> map);
	int countSaleCodeOut(Map<String,Object> map);
	TGoodsBatch selectByPrimaryKey(Long id);
}
