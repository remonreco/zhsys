package com.cbice.distribute.agency.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.model.TrancationReport;
import com.cbice.distribute.core.service.TGoodsBatchService;
import com.cbice.distribute.agency.service.TgoodsBatchService;

public class TgoodsBatchServiceImpl implements TgoodsBatchService {

	private TGoodsBatchService tGoodsBatchService;
	
	public void settGoodsBatchService(TGoodsBatchService tGoodsBatchService) {
		this.tGoodsBatchService = tGoodsBatchService;
	}

	
	
	@Override
	public int insertSelective(TGoodsBatch record) {
		return tGoodsBatchService.insertSelective(record);
	}
	public List<TGoodsBatch> selectByUserId(Map<String,Object> map) {
		return tGoodsBatchService.selectByUserId(map);
	}
	public int countSelectDealer(Map<String,Object> map) {
		return tGoodsBatchService.countSelectDealer(map);
	}



	@Override
	public List<TrancationReport> selectTrancationReport(Map<String, Object> map) {
		return tGoodsBatchService.selectTrancationReport(map);
	}



	@Override
	public int countSelectTrancationReport(Map<String, Object> map) {
		return tGoodsBatchService.countSelectTrancationReport(map);
	}



	@Override
	public List<TrancationReport> outTrancationReport(Map<String, Object> map) {
		return tGoodsBatchService.outTrancationReport(map);
	}



	@Override
	public List<TrancationReport> saleCodeOut(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tGoodsBatchService.saleCodeOut(map);
	}



	@Override
	public int countSaleCodeOut(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tGoodsBatchService.countSaleCodeOut(map);
	}



	@Override
	public TGoodsBatch selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return tGoodsBatchService.selectByPrimaryKey(id);
	}

}
