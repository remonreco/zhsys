package com.cbice.distribute.core.service;

import java.util.Map;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TUser;

public interface TAgencyUserBusiRoleDbService {

	 int insertSelective(TAgencyUserBusiRole tAgencyUserBusiRole);
}
