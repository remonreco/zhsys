package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TAgencyMapper;
import com.cbice.distribute.core.db.data.TAgencyTreeMapper;
import com.cbice.distribute.core.db.data.TAgencyUserBusiRoleMapper;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyExample;
import com.cbice.distribute.core.db.model.TAgencyTree;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.TAgencyUserBusiRoleDbService;

public class TAgencyUserBusiRoleDbServiceImpl implements TAgencyUserBusiRoleDbService{

	private TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper;
	
	public void settAgencyUserBusiRoleMapper(
			TAgencyUserBusiRoleMapper tAgencyUserBusiRoleMapper) {
		this.tAgencyUserBusiRoleMapper = tAgencyUserBusiRoleMapper;
	}

	@Override
	public int insertSelective(TAgencyUserBusiRole tAgencyUserBusiRole) {
		return tAgencyUserBusiRoleMapper.insertSelective(tAgencyUserBusiRole);
	}
}
