package com.cbice.distribute.buyer.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsExchangeOrderMapper;
import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:21:21
 * @describe
 */
public interface HsCommodityService {
	
	public List<Map> commodityQuery(Map<String, Object> map);
	
	/**
	 * 查出商品总数
	 * @param map
	 * @return
	 */
	public int countSelectBy(Map<String,Object> map);
	
	/**
	 * 查出商品列表
	 * @param map
	 * @return
	 */
	public List<HsCommodity> commodityQueryBean(Map<String, Object> map);
	
	/**
	 * 查处所有项目名
	 * @return
	 */
	public List<HsCommodity> queryProductType(Map<String, Object> map);
	
	/**
	 * 根据id查询单条信息
	 * @param comId
	 * @return
	 */
	public  HsCommodity selectByPrimaryKey(Long comId,String sysId);
	
	/**
	 * 更新商品表流通数量和冻结数量
	 * 往订单表插入数据
	 * @param record
	 * @param order
	 * @return
	 */
	public int updateAndInsert(HsCommodity record,HsExchangeOrder order,HsExchangeGift gife);
	
	/**
	 * 根据用户id查出订单
	 * @param map
	 * @return
	 */
	public List<HsExchangeOrder> selectByUserId(Map<String, Object> map);
	
	/**
	 * 根据用户id查出有多少条订单
	 * @param map
	 * @return
	 */
	public int selectCountByUserId(Map<String, Object> map);
	
	/**
	 * 根据id更新订单表订单状态
	 * @param order
	 * @return
	 */
	public int updateByPrimaryKeySelective(HsExchangeOrder order);
	
	/**
	 * 根据id查出订单
	 * @param id
	 * @return
	 */
	public HsExchangeOrder selectOrderByPrimaryKey(long id);
	
	
	/**
	 * 撤单之后更新订单状态为撤单，并根据订单id查出赠品，并更新商品数据
	 * @param order
	 * @return
	 */
	public int selectGiftAndUpdateCommodity(HsExchangeOrder order);
	
}
