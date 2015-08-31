package com.cbice.distribute.core.service.impl;

import com.cbice.distribute.core.db.data.AdminDao;
import com.cbice.distribute.core.service.AdmimService;



public class AdmimServiceImpl implements AdmimService 
{

	private AdminDao adminDao;
	
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	//查询出用户信息
	@Override
	public String loginService() 
	{
		
		return null;
	}

}
