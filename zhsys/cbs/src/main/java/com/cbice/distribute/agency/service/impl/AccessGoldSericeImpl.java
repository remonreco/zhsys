package com.cbice.distribute.agency.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.agency.service.AccessGoldSerice;
import com.cbice.distribute.core.db.model.TAccessGold;
import com.cbice.distribute.core.service.AccessGoldDbservice;
import com.cbice.distribute.core.util.AmountUtil;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月11日 下午5:16:49
 * @describe
 */
public class AccessGoldSericeImpl implements AccessGoldSerice{
	private AccessGoldDbservice accessGoldDbservice;

	public AccessGoldDbservice getAccessGoldDbservice() {
		return accessGoldDbservice;
	}

	public void setAccessGoldDbservice(AccessGoldDbservice accessGoldDbservice) {
		this.accessGoldDbservice = accessGoldDbservice;
	}

	@Override
	public List<Map> selectAccessGold(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map> list = accessGoldDbservice.selectAccessGold(map);
		for(Map<String, Object> map1:list){
			map1.put("in_balance",AmountUtil.formatMoney((Double)map1.get("in_balance"),2));
			map1.put("out_balance",AmountUtil.formatMoney((Double)map1.get("out_balance"),2));
			map1.put("diff_balance",AmountUtil.formatMoney((Double)map1.get("diff_balance"),2));
			map1.put("current_balance",AmountUtil.formatMoney((Double)map1.get("current_balance"),2));
		}
		return list;
	}

	@Override
	public int selectAccessGoldCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldDbservice.selectAccessGoldCount(map);
	}

	@Override
	public List<TAccessGold> selectAccessGoldAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldDbservice.selectAccessGoldAll(map);
	}

	@Override
	public List<Map> selectAccessGoldSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map> list = accessGoldDbservice.selectAccessGoldSum(map);
		for(Map<String, Object> map1:list){
			map1.put("in_balance",AmountUtil.formatMoney((Double)map1.get("in_balance"),2));
			map1.put("out_balance",AmountUtil.formatMoney((Double)map1.get("out_balance"),2));
			map1.put("diff_balance",AmountUtil.formatMoney((Double)map1.get("diff_balance"),2));
			map1.put("current_balance",AmountUtil.formatMoney((Double)map1.get("current_balance"),2));
		}
		return list;
	}

	@Override
	public int selectAccessGoldCountSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldDbservice.selectAccessGoldCountSum(map);
	}

	@Override
	public List<TAccessGold> selectAccessGoldAllSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldDbservice.selectAccessGoldAllSum(map);
	}

	@Override
	public int queryMaxDate() {
		// TODO Auto-generated method stub
		return accessGoldDbservice.queryMaxDate();
	}


}
