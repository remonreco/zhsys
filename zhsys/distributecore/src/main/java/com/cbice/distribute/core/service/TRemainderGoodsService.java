package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TRemainderGoods;

public interface TRemainderGoodsService {
	
	 int insertSelective(TRemainderGoods record);
	 
	 int updateByPrimaryKeySelective(TRemainderGoods record);
	 
	 List<TRemainderGoods> selectbuyUserid(Map<String,Object> map);

	 int updateNumByUserAndGoods(Map<String, Object> map);
	 TRemainderGoods selectbuyUseridAndGoodsid(Map<String, Object> map);
	 
	 List<Map<String, Object>> selectRemainderGoodsApplcation(Map<String, Object> map);
	int selectRemainderGoodsApplcationCount(Map<String, Object> map);
	TRemainderGoods selectByPrimaryKey(Long id);
	
	String selectAgencyId(Long user_id);
	
	List<Map<String, Object>> selectLowRemainderGoodsApplcation(Map<String, Object> map);
	int selectLowRemainderGoodsApplcationCount(Map<String, Object> map);
	
	/**
	 * 直接在用户的的产品表里增加相应的产品（若记录存在则数量叠加，若记录不存在则新增记录）
	 * @param userId   用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @author zhang'lei
	 */
	@Deprecated  
	int insertOrUpdate(Long userId,Long goodsNum, String goodsCode);
	/**
	 * 直接在用户的的产品表里减少相应的产品（数量递减，数量清零则删除记录）
	 * @param userId   用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @throws Exception  数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	@Deprecated  
	int  deleteOrUpdae  (Long userId,Long goodsNum, String goodsCode)  throws Exception  ;
	/**
	 * 锁定用户的的产品表里相应的产品（按goodsNum数量锁定）
	 * @param userId    用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @throws Exception 数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	int lockedGoods(Long userId,Long goodsNum, String goodsCode)  throws Exception ;
	/**
	 * 从fromUserId中解锁产品存进toUserId的库存中（fromUserId可以等于toUserId，当库存表中不存在记录时则新增记录，当记录的库存和锁定产品数量均为0时则删除记录，其他情况则对产品数量做加减操作）
	 * @param fromUserId
	 * @param toUserId
	 * @param goodsNum
	 * @param goodsCode
	 * @return
	 * @throws Exception 数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	int unlockGoods(Long fromUserId , Long toUserId,Long goodsNum, String goodsCode) throws Exception ;
	
	int updateTRemainderGoodsByUserIdAndGoodId(TRemainderGoods tRemainderGoods);
}
