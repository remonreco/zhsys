package com.cbice.distribute.core.service.impl;

import java.util.List;

import com.cbice.distribute.core.db.data.TGoodsOrderMapper;
import com.cbice.distribute.core.db.data.VGoodOrderUserMapper;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TGoodsOrderExample;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.db.model.VGoodOrderUserExample;
import com.cbice.distribute.core.service.GoodsOrderService;

public class GoodsOrderServiceImpl implements GoodsOrderService {

	private VGoodOrderUserMapper vGoodOrderUserMapper;

	

	public void setvGoodOrderUserMapper(VGoodOrderUserMapper vGoodOrderUserMapper) {
		this.vGoodOrderUserMapper = vGoodOrderUserMapper;
	}

	@Override
   	public List<VGoodOrderUser> selectDealer(VGoodOrderUser goods){
		
		return vGoodOrderUserMapper.selectDealer(goods);
	}
	@Override
	public int countSelectDealer(VGoodOrderUser goods){
		
		return vGoodOrderUserMapper.countSelectDealer(goods);
	}

}
