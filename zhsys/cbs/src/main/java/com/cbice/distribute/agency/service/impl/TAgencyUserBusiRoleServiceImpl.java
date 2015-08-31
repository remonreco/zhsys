package com.cbice.distribute.agency.service.impl;



import com.cbice.distribute.agency.service.TAgencyUserBusiRoleService;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.service.TAgencyUserBusiRoleDbService;

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
