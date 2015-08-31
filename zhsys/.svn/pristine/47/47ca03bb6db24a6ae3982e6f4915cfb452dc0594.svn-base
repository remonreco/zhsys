package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsCommodityRuleMapper;
import com.cbice.distribute.core.db.model.HsCommodityRule;
import com.cbice.distribute.core.service.CommodityRuleDbservice;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:25:06
 * @describe
 */
public class CommodityRuleDbServiceImpl implements CommodityRuleDbservice{

	private HsCommodityRuleMapper hsCommodityRuleMapper;
	
	
	public HsCommodityRuleMapper getHsCommodityRuleMapper() {
		return hsCommodityRuleMapper;
	}

	public void setHsCommodityRuleMapper(HsCommodityRuleMapper hsCommodityRuleMapper) {
		this.hsCommodityRuleMapper = hsCommodityRuleMapper;
	}

	@Override
	public List<HsCommodityRule> queryRulesById(Map<String, Object> map) {
		return hsCommodityRuleMapper.queryRulesById(map);
	}

	@Override
	public int insertNewComdityRole(Map<String, Object> map) {
		return hsCommodityRuleMapper.insertNewComdityRole(map);
	}
	
	@Override
	public int updateCommodityRule(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityRuleMapper.updateCommodityRule(map);
	}

	@Override
	public int deleteByPrimaryKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityRuleMapper.deleteByPrimaryKey((Long)map.get("comId"), (Long)map.get("exchangeId"),(String)map.get("sysId"));
	}


}
