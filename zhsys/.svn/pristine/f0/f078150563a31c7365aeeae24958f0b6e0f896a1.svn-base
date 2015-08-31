package com.cbice.distribute.buyer.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.buyer.service.HsCommodityRuleService;
import com.cbice.distribute.core.db.model.HsCommodityRule;
import com.cbice.distribute.core.service.CommodityRuleDbservice;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:21:54
 * @describe
 */
public class CommodityRuleServiceImpl implements HsCommodityRuleService{

	private CommodityRuleDbservice commodityRuleDbService;
	
	
	
	public CommodityRuleDbservice getCommodityRuleDbService() {
		return commodityRuleDbService;
	}


	public void setCommodityRuleDbService(
			CommodityRuleDbservice commodityRuleDbService) {
		this.commodityRuleDbService = commodityRuleDbService;
	}



	@Override
	public List<HsCommodityRule> queryRulesById(Map<String, Object> map) {
		return commodityRuleDbService.queryRulesById(map);
	}

	

}
