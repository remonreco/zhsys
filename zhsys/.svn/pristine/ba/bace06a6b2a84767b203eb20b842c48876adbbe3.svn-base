package com.cbice.distribute.mgr.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TAccessGold;
import com.cbice.distribute.core.db.model.TFund;
import com.cbice.distribute.core.service.AccessGoldDbservice;
import com.cbice.distribute.mgr.service.AccessGoldSerice;

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
		return accessGoldDbservice.selectAccessGold(map);
	}

	@Override
	public List<TAccessGold> selectAccessGold(TAccessGold accessGold) {
		// TODO Auto-generated method stub
		return null;
	}

}
