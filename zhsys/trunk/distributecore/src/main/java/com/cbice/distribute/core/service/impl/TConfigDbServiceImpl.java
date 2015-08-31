package com.cbice.distribute.core.service.impl;

import com.cbice.distribute.core.db.data.TConfigMapper;
import com.cbice.distribute.core.db.model.TConfig;
import com.cbice.distribute.core.service.TConfigDbService;

public class TConfigDbServiceImpl implements TConfigDbService {

	
	private TConfigMapper tConfigMapper;
	
	public void settConfigMapper(TConfigMapper tConfigMapper) {
		this.tConfigMapper = tConfigMapper;
	}


	@Override
	public TConfig selectByPrimaryKey(String key) {
		return tConfigMapper.selectByPrimaryKey(key);
	}

}
