package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsSystem;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;

public interface SonSysRoleService {

	List<Map> sysRoleQueryList(Map<String, Object> map);

	int countsysRoleQueryList(Map<String, Object> map);

	HsSystem selectBySysName(String sysName);

	int insertByselective(HsSystem hsSystem);

	int updateByselective(HsSystem hsSystem);


}
