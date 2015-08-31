package com.cbice.distribute.core.service.impl;

import com.cbice.distribute.core.db.data.TAgencyLevelRoleMapper;
import com.cbice.distribute.core.db.model.TAgencyLevelRole;
import com.cbice.distribute.core.service.TAgencyLevelRoleService;

public class TAgencyLevelRoleServiceDbImpl implements TAgencyLevelRoleService{
	
	private TAgencyLevelRoleMapper tAgencyLevelRoleMapper;

	public void settAgencyLevelRoleMapper(
			TAgencyLevelRoleMapper tAgencyLevelRoleMapper) {
		this.tAgencyLevelRoleMapper = tAgencyLevelRoleMapper;
	}

	@Override
	public int insertSelective(TAgencyLevelRole record) {
		return tAgencyLevelRoleMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TAgencyLevelRole record) {
		return tAgencyLevelRoleMapper.updateByPrimaryKeySelective(record);
	}

	

}
