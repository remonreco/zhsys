package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsUserRights;

public interface HsUserRightsDbService {

	int updateAvailableNum(HsUserRights hsUserRights);

	int insertSelective(HsUserRights hsUserRights);

	List<HsUserRights> queryUserRights(Map<String, Object> map);

	long queryUserRightsCount(Map<String, Object> map);

	HsUserRights queryBySpecialRightsCode(Map<String, Object> map);

}
