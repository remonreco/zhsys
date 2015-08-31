package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TAccessGoldMapper;
import com.cbice.distribute.core.db.model.TAccessGold;
import com.cbice.distribute.core.service.AccessGoldDbservice;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月12日 下午2:39:23
 * @describe
 */
public class AccessGoldDbServeiceImpl implements AccessGoldDbservice{

	private TAccessGoldMapper accessGoldMapper;
	

	public void setAccessGoldMapper(TAccessGoldMapper accessGoldMapper) {
		this.accessGoldMapper = accessGoldMapper;
	}


	@Override
	public List<Map> selectAccessGold(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGold(map);
	}


	@Override
	public int selectAccessGoldCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGoldCount(map);
	}


	@Override
	public List<TAccessGold> selectAccessGoldAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGoldAll(map);
	}


	@Override
	public int selectAccessGoldCountSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGoldCountSum(map);
	}


	@Override
	public List<Map> selectAccessGoldSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGoldSum(map);
	}


	@Override
	public List<TAccessGold> selectAccessGoldAllSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return accessGoldMapper.selectAccessGoldAllSum(map);
	}


	@Override
	public int queryMaxDate() {
		// TODO Auto-generated method stub
		return accessGoldMapper.queryMaxDate();
	}


}
