package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.HsCommodity;

/**
 * 商品兑换service mgr
 * @author zj
 *
 */
public interface TCommodityService {
	/**
	 * 后台分页查询商品list
	 * @param map
	 * @return
	 */
	List<Map> commodityQueryList(Map<String, Object> map);
	/**
	 * 后台查询商品总数
	 * @param map
	 * @return
	 */
	public int countCommodityQueryList(Map<String, Object> map);
	
	/**
	 * 项目名称查询
	 * @return
	 */
	List<Map> queryLoadItemName(Map<String, Object> map);
	List<Map> queryLoadComName(Map<String, Object> map);
	
	HsCommodity selectByPrimaryKey(Long comIds,String sysId);
	HsCommodity selectBycomName(Map<String, Object> map);
	HsCommodity queryexchangeId(Map<String, Object> map);
	int inserAll(Map<String, Object> map, Map<String, Object> mapr0, Map<String, Object> mapr1, Map<String, Object> mapr2, Map<String, Object> mapr3, Map<String, Object> mapr4);
	/**
	 * 查询兑换规则
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> exchangeRuleQuery(Map<String, Object> map);
	/**
	 * 删除商品信息
	 * @param map
	 * @return
	 */
	int deleteByPrimaryKey(Map<String, Object> map);
	
	int updateByPrimaryKey(HsCommodity hsCommodity);
	
	int updateByPrimaryKeySelective(HsCommodity hsCommodity);

}
