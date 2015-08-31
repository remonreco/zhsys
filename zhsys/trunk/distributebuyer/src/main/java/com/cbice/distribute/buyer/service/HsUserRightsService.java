package com.cbice.distribute.buyer.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsUserRights;

public interface HsUserRightsService {
	/**
	 * 新增用户权益信息
	 * @param hsUserRights
	 * @return
	 */
	int insertSelective(HsUserRights hsUserRights);
	/**
	 * 查询用户权益信息
	 * @param map
	 * @return
	 */
	List<HsUserRights> queryUserRights(Map<String, Object> map);
	/**
	 * 查询用户权益信息总数
	 * @param map
	 * @return
	 */
	long queryUserRightsCount(Map<String, Object> map);

}
