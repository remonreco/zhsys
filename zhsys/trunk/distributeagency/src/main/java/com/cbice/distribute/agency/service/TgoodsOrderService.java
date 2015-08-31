package com.cbice.distribute.agency.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoodsOrder;


public interface TgoodsOrderService {
	List<Map<String, Object>> selectGoodsOrderBy(Map<String, Object> map);
	
	int countSelectOrderBy(Map<String,Object> map);
	int insertSelective(TGoodsOrder record);
	int insertListSelective(List<TGoodsOrder> list);
	
	List<Map<String, Object>> selectReturnGoodsOrderBy(Map<String, Object> map);
	int countSelectReturnOrderBy(Map<String,Object> map);
	String selectDealerNameById(Long agencyId);
	String selectUserNameById(Long userId);
	Long selectUseridentyById(Long userId);
	List<TGoodsOrder> selectGoodsBatchById(Long batchId);
	List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map);
	int countSelectHistoryGoodsOrderBy(Map<String, Object> map);
	List<TGoodsOrder> selectToSendBuyUserid(Map<String,Object> paramMap);
	int countSelectToSendBuyUserid(Map<String,Object> paramMap);
	List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map);
	int selectReturnGoodsHisCount(Map<String, Object> paramMap);
	List<Map<String, Object>> selectReturnGoodsList(Map<String, Object> map);
	int selectReturnGoodsListCount(Map<String,Object> map);
	int updateByPrimaryKeySelective(Long id,Integer returnGoodsState,String returnOrderOptions,Long agency_id,Long higer_dealer_id,Long distributeNum,String goodsNum);
	/**
	 * 销售码导出
	 * @param map
	 * @return
	 */
	List<TGoodsOrder> outSaleCode(Map<String,Object> map);
	
	int agreeOrNotReturnGoodsOrder(Long id,Integer returnGoodsState,String goodsReason,Long user_id,Long agency_id,Long distributeNum,String goodsNum);
	
	List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId);
	List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId);
	
	/**
	 * 查询唯一订单
	 * @param map
	 * @return
	 */
	List<TGoodsOrder> selectOrderbyUserIdgoodsNameGoodsNum(Map<String, Object> map);
	
	int cancelGoodsOrder(String id);
	
	//更新t_goods_order的订单状态并且增加库存数量
	int updateOrderStateById(int id);
	

	List<TGoodsOrder> TranctionOut(Map<String, Object> paramMap);
}
