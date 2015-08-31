package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoodsOrder;


public interface TgoodsOrderService {
	List<Map<String, Object>> selectGoodsOrderBy(Map<String, Object> map);
	
	int countSelectOrderBy(Map<String,Object> map);
	int insertSelective(TGoodsOrder record);
	int insertListSelective(List<TGoodsOrder> list);
	
	int countSelectHistoryGoodsOrderBy(Map<String, Object> map);
	List<Map<String, Object>> selectReturnGoodsList(Map<String, Object> map);
	int selectReturnGoodsListCount(Map<String,Object> map);
	String selectDealerNameById(Long agencyId);
	List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map);
	List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId);
	Long selectUseridentyById(Long userId);
	int cancelGoodsOrder(String id);
List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map);
	
	int selectReturnGoodsHisCount(Map<String, Object> paramMap);
	List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId);
	int updateByPrimaryKeySelective(Long id,Integer returnGoodsState,String returnOrderOptions,Long agency_id,Long higer_dealer_id,Long distributeNum,String goodsNum);
	//会员退货
	int updateByPrimaryKeyVIP(Long id,Integer returnGoodsState,String returnOrderOptions,Long agency_id,Long higer_dealer_id,Long distributeNum,String goodsNum);
	/**
	 * 销售码导出
	 * @param map
	 * @return
	 */
	List<TGoodsOrder> outSaleCode(Map<String,Object> map);
	
	int agreeReturnGoodsOrder(String id);
	
	
	List<TGoodsOrder> TranctionOut(Map<String, Object> paramMap);
}
