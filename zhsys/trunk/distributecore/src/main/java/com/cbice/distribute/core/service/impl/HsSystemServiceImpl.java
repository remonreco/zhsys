package com.cbice.distribute.core.service.impl;

import java.util.List;

import com.cbice.distribute.core.db.data.HsSystemMapper;
import com.cbice.distribute.core.db.model.HsSystem;
import com.cbice.distribute.core.service.HsSystemService;

public class HsSystemServiceImpl implements HsSystemService{
	HsSystemMapper hsSystemMapper;
	
	public void setHsSystemMapper(HsSystemMapper hsSystemMapper) {
		this.hsSystemMapper = hsSystemMapper;
	}

	@Override
	public List<HsSystem> queryHsSystemList() {
		// TODO Auto-generated method stub
		return hsSystemMapper.queryHsSystemList();
	}

}
