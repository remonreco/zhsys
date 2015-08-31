package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TGoodsBatchMapper;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.model.TrancationReport;
import com.cbice.distribute.core.service.TGoodsBatchService;

public class TGoodsBatchServiceDbImpl implements TGoodsBatchService {

	private TGoodsBatchMapper tGoodsBatchMapper;
	
	public void settGoodsBatchMapper(TGoodsBatchMapper tGoodsBatchMapper) {
		this.tGoodsBatchMapper = tGoodsBatchMapper;
	}

	@Override
	public int insertSelective(TGoodsBatch record) {
		return tGoodsBatchMapper.insertSelective(record);
	}
	
	@Override
	public List<TGoodsBatch> selectByUserId(Map<String, Object> map) {
		
		return tGoodsBatchMapper.selectByUserId(map);
	}
	
	@Override
	public int countSelectDealer(Map<String,Object> map){
		
		return tGoodsBatchMapper.countSelectDealer(map);
	}

	/**
	 * 导出报表查询
	 */
	@Override
	public List<TrancationReport> selectTrancationReport(Map<String, Object> map) {
		return tGoodsBatchMapper.selectTrancationReport(map);
	}

	@Override
	public int countSelectTrancationReport(Map<String, Object> map) {
		return tGoodsBatchMapper.countSelectTrancationReport(map);
	}

	@Override
	public List<TrancationReport> outTrancationReport(Map<String, Object> map) {
		return tGoodsBatchMapper.outTrancationReport(map);
	}
	@Override
	public TGoodsBatch selectGoodsByGoodsnum(String goodsNum) {
		return tGoodsBatchMapper.selectGoodsByGoodsnum(goodsNum);
	}
	@Override
	public List<TrancationReport> saleCodeOut(Map<String, Object> map) {
		return tGoodsBatchMapper.saleCodeOut(map);
	}

	@Override
	public int countSaleCodeOut(Map<String, Object> map) {
		return tGoodsBatchMapper.countSaleCodeOut(map);
	}

	@Override
	public TGoodsBatch selectByPrimaryKey(Long id) {
		return tGoodsBatchMapper.selectByPrimaryKey(id);
	}
}
