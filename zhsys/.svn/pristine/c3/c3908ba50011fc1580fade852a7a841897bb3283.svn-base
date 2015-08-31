package com.cbice.distribute.mgr.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsSystemMapper;
import com.cbice.distribute.core.db.data.TRemainderGoodsMapper;
import com.cbice.distribute.core.db.model.HsSystem;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.mgr.service.SonSysRoleService;

public class SonSysRoleServiceImpl implements SonSysRoleService{
	
	private HsSystemMapper hsSystemMapper;
	
	
	public HsSystemMapper getHsSystemMapper() {
		return hsSystemMapper;
	}

	public void setHsSystemMapper(HsSystemMapper hsSystemMapper) {
		this.hsSystemMapper = hsSystemMapper;
	}

	public List<Map> sysRoleQueryList(Map<String, Object> map) {
		List<Map> list=hsSystemMapper.sysRoleQueryList(map);
		for(Map<String, Object> map1:list){
			map1.put("edit_time",DateUtils.DateToString((Date)map1.get("edit_time"), "yyyy-MM-dd HH:mm:ss"));
		}
		return	list;
	}
	
	public int countsysRoleQueryList(Map<String, Object> map) {
		return	hsSystemMapper.countsysRoleQueryList(map);
	}

	@Override
	public HsSystem selectBySysName(String sysName) {
		// TODO Auto-generated method stub
		return hsSystemMapper.selectBySysName(sysName);
	}

	@Override
	public int insertByselective(HsSystem hsSystem) {
		// TODO Auto-generated method stub
		return hsSystemMapper.insertSelective(hsSystem);
	}

	@Override
	public int updateByselective(HsSystem hsSystem) {
		// TODO Auto-generated method stub
		return hsSystemMapper.updateByPrimaryKeySelective(hsSystem);
	}


}
