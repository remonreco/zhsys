package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TGoodsOrderMapper;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TGoodsOrderService;

public class TGoodsOrderServiceDbImpl implements TGoodsOrderService{
	private  TGoodsOrderMapper tGoodsOrderMapper;
	 private SeqService seqService;

	public void settGoodsOrderMapper(TGoodsOrderMapper tGoodsOrderMapper) {
		this.tGoodsOrderMapper = tGoodsOrderMapper;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	@Override
	public List<Map<String, Object>> selectGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderMapper.selectBy(map);
	}

	@Override
	public int countSelectOrderBy(Map<String, Object> map) {
		return tGoodsOrderMapper.countSelectBy(map);
	}

	@Override
	public int insertSelective(TGoodsOrder record) {
		return tGoodsOrderMapper.insertSelective(record);
	}

	@Override
	public List<Map<String, Object>> selectReturnGoodsList(Map<String, Object> map) {
		return tGoodsOrderMapper.selectReturnGoodsList(map);
	}

	@Override
	public int selectReturnGoodsListCount(Map<String, Object> map) {
		return tGoodsOrderMapper.selectReturnGoodsListCount(map);
	}

	@Override
	public String selectDealerNameById(Long agencyId) {
		return tGoodsOrderMapper.selectDealerNameById(agencyId);
	}
	
	@Override
	public String selectUserNameById(Long userId) {
		return tGoodsOrderMapper.selectUserNameById(userId);
	}
	
	@Override
	public Long selectUseridentyById(Long userId) {
		return tGoodsOrderMapper.selectUseridentyById(userId);
	}
	
	@Override
	public List<TGoodsOrder> selectGoodsBatchById(Long batchId) {
		return tGoodsOrderMapper.selectGoodsBatchById(batchId);
	}

	@Override
	public int updateByPrimaryKeySelective(TGoodsOrder record) {
		return tGoodsOrderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public TGoodsOrder selectTGoodsOrderById(Long id) {
		return tGoodsOrderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map){
		return tGoodsOrderMapper.selectHistoryGoodsOrderBy(map);
	}
	
	@Override
	public int countSelectHistoryGoodsOrderBy(Map<String, Object> map){
		return tGoodsOrderMapper.countSelectHistoryGoodsOrderBy(map);
	}
	
	@Override
	public List<TGoodsOrder> selectToSendBuyUserid(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.selectToSendBuyUserid(paramMap);
	}

	@Override
	public int countSelectToSendBuyUserid(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.countSelectToSendBuyUserid(paramMap);
	}

	@Override
	public TGoodsOrder selectByPrimaryKey(Long id) {
		return tGoodsOrderMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<TGoodsOrder> outSaleCode(Map<String, Object> map) {
		return tGoodsOrderMapper.outSaleCode(map);
	}

	@Override
	public List<TGoodsOrder> selectUserMemberbyOrder(Map<String, Object> map) {
		return tGoodsOrderMapper.selectUserMemberbyOrder(map);
	}

	@Override
	public List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderMapper.selectGoodsOrderByBatchid(batchId);
	}

	@Override
	public List<TGoodsOrder> selectOrderbyUserIdgoodsNameGoodsNum(
			Map<String, Object> map) {
		return tGoodsOrderMapper.selectOrderbyUserIdgoodsNameGoodsNum(map);
	}

	@Override
	public List<TGoodsOrder> selectHistoryGoodsOrder(Map<String, Object> map) {
		return tGoodsOrderMapper.selectHistoryGoodsOrder(map);
	}
	
	public Map<String,Object> selectHistoryGoodsOrderCount(Map<String, Object> map){
		return tGoodsOrderMapper.selectHistoryGoodsOrderCount(map);
	}

	@Override
	public List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderMapper.selectReturnGoodsOrderByBatchid(batchId);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tGoodsOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map) {
		return tGoodsOrderMapper.selectReturnGoodsHis(map);
	}

	@Override
	public int selectReturnGoodsHisCount(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.selectReturnGoodsHisCount(paramMap);
	}

	@Override
	public TGoodsOrder selectOrderbySerial(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.selectOrderbySerial(paramMap);
	}

	@Override
	public List<TGoodsOrder> TranctionOut(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.TranctionOut(paramMap);
	}

	@Override
	public List<TGoodsOrder> selectMemberHistoryGoodsOrderBy(
			Map<String, Object> map) {
		
		return tGoodsOrderMapper.selectMemberHistoryGoodsOrderBy(map);
	}

	@Override
	public int countSelectMemberHistoryGoodsOrderBy(Map<String, Object> paramMap) {
		return tGoodsOrderMapper.countSelectMemberHistoryGoodsOrderBy(paramMap);
	}


}
