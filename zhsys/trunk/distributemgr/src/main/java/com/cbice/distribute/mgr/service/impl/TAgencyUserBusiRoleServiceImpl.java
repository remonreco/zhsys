package com.cbice.distribute.mgr.service.impl;


import java.util.List;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.TAgencyUserBusiRoleDbService;
import com.cbice.distribute.mgr.service.TAgencyService;
import com.cbice.distribute.mgr.service.TAgencyUserBusiRoleService;

public class TAgencyUserBusiRoleServiceImpl implements TAgencyUserBusiRoleService {

	private TAgencyUserBusiRoleDbService tAgencyUserBusiRoleDBService;
	
	

	public void settAgencyUserBusiRoleDBService(
			TAgencyUserBusiRoleDbService tAgencyUserBusiRoleDBService) {
		this.tAgencyUserBusiRoleDBService = tAgencyUserBusiRoleDBService;
	}


	@Override
	public int insertSelective(TAgencyUserBusiRole tAgencyUserBusiRole) {
		
		return tAgencyUserBusiRoleDBService.insertSelective(tAgencyUserBusiRole);
	}


}
