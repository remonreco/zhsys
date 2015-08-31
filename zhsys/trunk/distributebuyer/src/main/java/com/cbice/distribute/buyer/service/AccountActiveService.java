package com.cbice.distribute.buyer.service;

import java.util.List;

import com.cbice.distribute.core.db.model.TUser;

public interface AccountActiveService {

	/**
	 * 根据用户信息查询Tuser 判断用户是否存在
	 * @param tUser
	 * @return
	 */
	List<TUser> selectAllToConvert(TUser tUser);

	int updatePwdByAss(TUser tUser);
	
	/**
	 * 兑换用户登录
	 * @param tUser
	 * @return
	 */
	List<TUser> selectConvertLogin(TUser tUser);

}
