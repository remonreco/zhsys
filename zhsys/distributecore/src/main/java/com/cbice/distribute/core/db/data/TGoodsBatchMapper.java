package com.cbice.distribute.core.db.data;

import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsBatchExample;
import com.cbice.distribute.core.model.TrancationReport;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TGoodsBatchMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int countByExample(TGoodsBatchExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int deleteByExample(TGoodsBatchExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int insert(TGoodsBatch record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int insertSelective(TGoodsBatch record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	List<TGoodsBatch> selectByExample(TGoodsBatchExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	TGoodsBatch selectByPrimaryKey(Long id);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int updateByExampleSelective(@Param("record") TGoodsBatch record,
			@Param("example") TGoodsBatchExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int updateByExample(@Param("record") TGoodsBatch record,
			@Param("example") TGoodsBatchExample example);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int updateByPrimaryKeySelective(TGoodsBatch record);
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_batch
	 * @mbggenerated  Tue Mar 10 18:35:19 CST 2015
	 */
	int updateByPrimaryKey(TGoodsBatch record);
	List<TGoodsBatch> selectByUserId(Map<String,Object> map);
	int countSelectDealer(Map<String,Object> map);
	/**
	 * 报表导出查询
	 * @param map
	 * @return
	 */
	List<TrancationReport> selectTrancationReport(Map<String,Object> map);
	int countSelectTrancationReport(Map<String,Object> map);
	/**
	 * 导出报表
	 * @param map
	 * @return
	 */
	List<TrancationReport> outTrancationReport(Map<String,Object> map);
	
	/**
	 * 根据商品编号查询批次信息
	 * */
	TGoodsBatch selectGoodsByGoodsnum(String goodsNum);
	/**
	 * 导出�?��码查�?
	 * @param map
	 * @return
	 */
	List<TrancationReport> saleCodeOut(Map<String,Object> map);
	int countSaleCodeOut(Map<String,Object> map);
}