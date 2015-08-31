package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:24:31
 * @describe
 */
public interface HsCommodityDbservice {
	
	/**
	 * 商品列表
	 * @param map
	 * @return
	 */
	public List<Map> commodityQuery(Map<String, Object> map);
	
	/**
	 * 商品总数
	 * @param map
	 * @return
	 */
	public int countSelectBy(Map<String,Object> map);
	
	/**
	 * 商品列表
	 * @param map
	 * @return
	 */
	public List<HsCommodity> commodityQueryBean(Map<String, Object> map);
	
	/**
	 * 得到项目名
	 * @param map 
	 * @return
	 */
	public List<HsCommodity> queryProductType(Map<String, Object> map);
	
	/**
	 * 更新商品数量和冻结数量(审批成功时)
	 * @param hsCommodity
	 * @return
	 */
	public int updateCurrencyNumAndFreezeNum(HsCommodity hsCommodity);
	
	/**
	 * 根据id查询单条信息
	 * @param comId
	 * @return
	 */
	public  HsCommodity selectByPrimaryKey(Long comId,String sysId);
	
	/**
	 * 更新流通数量和冻结数量
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(HsCommodity record);
	
	/**
	 * 撤单后更新流通数量和冻结数量
	 * @param map
	 * @return
	 */
	public int updateCommodityById(Map<String ,Object> map);
	

	/**
	 * 购买后更新流通数量和冻结数量
	 * @param map
	 * @return
	 */
	public int deleteCommodityById(Map<String ,Object> map);
	
	/**
	 * 插入订单表
	 * @param order
	 * @return
	 */
	public int insertExchangeOrder(HsExchangeOrder order);
	
	
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

	
	public List<Map> queryLoadItemName(Map<String, Object> map);

	public List<Map> queryLoadComName(Map<String, Object> map);

	public List<Map> commodityQueryList(Map<String, Object> map);

	public int countCommodityQueryList(Map<String, Object> map);

	public HsCommodity selectBycomName(Map<String, Object> map);

	public HsCommodity queryexchangeId(Map<String, Object> map);

	public int insertNewComdity(Map<String, Object> map);

	public List<Map<String, Object>> exchangeRuleQuery(Map<String, Object> map);

	public int deleteByPrimaryKey(Map<String, Object> map);
	
	public int updateByPrimaryKey(HsCommodity hsCommodity);
	
	public int insertGift(HsExchangeGift gift);
	
	public HsExchangeOrder selectOrderByPrimaryKey(long id);
	
	public List<HsExchangeGift> selectGifeById(Map<String,Object> map);
	
	/**
	 * 通过主键和版本号更新商品信息
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeyAndVersion(HsCommodity record);
	
	/**
	 * 通过主键和SysID查询商品订单
	 * @param sumMap
	 * @return
	 */
	public List<HsExchangeOrder> selectByPrimaryKeyAndSysId(
			Map<String, Object> sumMap);
}
