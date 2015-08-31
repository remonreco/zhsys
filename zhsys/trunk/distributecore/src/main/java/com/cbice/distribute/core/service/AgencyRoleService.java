package com.cbice.distribute.core.service;

import java.util.List;

import com.cbice.distribute.core.db.model.TAgencyRole;

/**
 * @author Richard Xiong
 * 系统角色表服务
 */
public interface AgencyRoleService {
	/**
	 * 通过系统用户标识查询角色列表
	 * @param userId 系统用户标识
	 * @return
	 */
	List<TAgencyRole> selectByUserId(Long userId);
	
	/**
	 * 获取所有系统角色
	 * @return
	 */
    List<TAgencyRole> selectAll();

}
