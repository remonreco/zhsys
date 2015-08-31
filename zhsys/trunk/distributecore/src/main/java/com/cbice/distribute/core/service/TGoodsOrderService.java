package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cbice.distribute.core.db.model.TGoodsOrder;


public interface TGoodsOrderService {
	List<Map<String, Object>> selectGoodsOrderBy(Map<String, Object> map);

	int countSelectOrderBy(Map<String,Object> map);
	
	int insertSelective(TGoodsOrder record);
	
	List<Map<String, Object>> selectReturnGoodsList(Map<String, Object> map);
	
	int selectReturnGoodsListCount(Map<String,Object> map);
	
	String selectDealerNameById(Long agencyId);
	
	String selectUserNameById(Long userId);
	
	Long selectUseridentyById(Long userId);
	
	List<TGoodsOrder> selectGoodsBatchById(Long batchId);
	
	TGoodsOrder selectTGoodsOrderById(Long id);
	
	int updateByPrimaryKeySelective(TGoodsOrder record);
	
	int deleteByPrimaryKey(Long id);

	List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map);
	
	int countSelectHistoryGoodsOrderBy(Map<String, Object> map);
	
	List<TGoodsOrder> selectToSendBuyUserid(Map<String, Object> paramMap);
	    
	int countSelectToSendBuyUserid(Map<String,Object> paramMap);
	
	TGoodsOrder selectByPrimaryKey(Long id);
	
	List<TGoodsOrder> outSaleCode(Map<String,Object> map);
	
	List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId);
	List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId);
	TGoodsOrder  selectOrderbySerial(Map<String,Object> paramMap);
	
List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map);
	
	int selectReturnGoodsHisCount(Map<String, Object> paramMap);
	/**
	 * 登陆线上登记系统
	 * @param map
	 * @return
	 */
	List<TGoodsOrder> selectUserMemberbyOrder(Map<String,Object> map);
	
	
	List<TGoodsOrder> selectOrderbyUserIdgoodsNameGoodsNum(Map<String,Object> map);
	
	//查询历史购买记录
	List<TGoodsOrder> selectHistoryGoodsOrder(Map<String, Object> map);
	
	Map<String,Object> selectHistoryGoodsOrderCount(Map<String,Object> map);
	
	List<TGoodsOrder> TranctionOut(Map<String, Object> paramMap);
	
List<TGoodsOrder> selectMemberHistoryGoodsOrderBy(Map<String, Object> map);
	
	int countSelectMemberHistoryGoodsOrderBy(Map<String, Object> paramMap);

	
}