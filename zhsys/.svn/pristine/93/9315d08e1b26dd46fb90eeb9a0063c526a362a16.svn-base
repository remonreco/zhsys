package com.cbice.distribute.buyer.service.impl;

import java.util.List;

import com.cbice.distribute.buyer.service.AccountActiveService;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TUserService;

public class AccountActiveServiceImpl implements AccountActiveService{
	
	private TUserService tUserService;
	
	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	@Override
	public List<TUser> selectAllToConvert(TUser tUser) {
		return tUserService.selectAllToConvert(tUser);
	}

	@Override
	public int updatePwdByAss(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserService.updatePwdByAss(tUser);
	}
	
	/**
	 * 兑换用户登录
	 */
	@Override
	public List<TUser> selectConvertLogin(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserService.selectConvertLogin(tUser);
	}
	
}
