package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsUserRightsMapper;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.service.HsUserRightsDbService;

public class HsUserRightsDbServiceImpl implements HsUserRightsDbService {
	HsUserRightsMapper hsUserRightsMapper;

	public void setHsUserRightsMapper(HsUserRightsMapper hsUserRightsMapper) {
		this.hsUserRightsMapper = hsUserRightsMapper;
	}

	@Override
	public int updateAvailableNum(HsUserRights hsUserRights) {
		// TODO Auto-generated method stub
		return hsUserRightsMapper.updateAvailableNum(hsUserRights);
	}

	@Override
	public int insertSelective(HsUserRights hsUserRights) {
		// TODO Auto-generated method stub
		return hsUserRightsMapper.insertSelective(hsUserRights);
	}

	@Override
	public List<HsUserRights> queryUserRights(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsUserRightsMapper.queryUserRights(map);
	}

	@Override
	public long queryUserRightsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsUserRightsMapper.queryUserRightsCount(map);
	}

	@Override
	public HsUserRights queryBySpecialRightsCode(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsUserRightsMapper.queryBySpecialRightsCode(map);
	}
	
}
