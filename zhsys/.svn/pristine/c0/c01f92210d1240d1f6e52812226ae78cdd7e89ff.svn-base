package com.cbice.distribute.buyer.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cbice.distribute.buyer.service.HsUserRightsService;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.service.HsUserRightsDbService;

public class HsUserRightsServiceImpl implements HsUserRightsService {
	@Resource
	HsUserRightsDbService HsUserRightsDbService;
	
	public void setHsUserRightsDbService(HsUserRightsDbService hsUserRightsDbService) {
		HsUserRightsDbService = hsUserRightsDbService;
	}

	@Override
	public int insertSelective(HsUserRights hsUserRights) {
		// TODO Auto-generated method stub
		return HsUserRightsDbService.insertSelective(hsUserRights);
	}

	@Override
	public List<HsUserRights> queryUserRights(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return HsUserRightsDbService.queryUserRights(map);
	}

	@Override
	public long queryUserRightsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return HsUserRightsDbService.queryUserRightsCount(map);
	}
	
}
