package com.cbice.distribute.core.service;

import com.cbice.distribute.core.db.model.TAgencyLevelRole;

/**
 * @author Lee
 *经销商等级与角色关联关系
 */
public interface TAgencyLevelRoleService {
	
	/**添加关联关系
	 * @param record
	 * @return
	 */
	int insertSelective(TAgencyLevelRole record);
	
	
	/**更新关联关系
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(TAgencyLevelRole record);
}
