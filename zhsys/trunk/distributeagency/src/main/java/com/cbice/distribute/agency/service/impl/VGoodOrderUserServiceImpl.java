package com.cbice.distribute.agency.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cbice.distribute.core.db.data.TGoodsOrderMapper;
import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.service.GoodsOrderService;
import com.cbice.distribute.agency.service.VGoodsOrderUserService;

public class VGoodOrderUserServiceImpl implements VGoodsOrderUserService {
	private GoodsOrderService goodsOrderService;
	@Resource
	private TGoodsOrderMapper tGoodsOrderMapper;
	@Resource
	private TUserMapper tUserMapper;
	
	
	
	public void settUserMapper(TUserMapper tUserMapper) {
		this.tUserMapper = tUserMapper;
	}


	public void setGoodsOrderService(GoodsOrderService goodsOrderService) {
		this.goodsOrderService = goodsOrderService;
	}
	

	public void settGoodsOrderMapper(TGoodsOrderMapper tGoodsOrderMapper) {
		this.tGoodsOrderMapper = tGoodsOrderMapper;
	}


	@Override
	public List<VGoodOrderUser> selectDealer(VGoodOrderUser goods) {
		return goodsOrderService.selectDealer(goods);
		
	}

	@Override
	public int countSelectDealer(VGoodOrderUser goods) {
		return goodsOrderService.countSelectDealer(goods);
	}

	@Override
	public TGoodsOrder selectById(Long id) {
		TGoodsOrder record = tGoodsOrderMapper.selectByPrimaryKey(id);
		return record;
	}

	@Override
	public int updateRecord(TGoodsOrder record) {
		int i=0;
		i =tGoodsOrderMapper.updateByPrimaryKey(record);
		return 0;
	}


	@Override
	public TUser selectUserById(Long id) {
		return tUserMapper.selectByPrimaryKey(id);
	}

}
