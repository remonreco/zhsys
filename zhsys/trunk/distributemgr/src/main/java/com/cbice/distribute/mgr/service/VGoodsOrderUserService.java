package com.cbice.distribute.mgr.service;

import java.util.List;

import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;

public interface VGoodsOrderUserService {
	
	
	public List<VGoodOrderUser> selectDealer(VGoodOrderUser goods);
	
	public int countSelectDealer(VGoodOrderUser goods);
	
	public TGoodsOrder  selectById(Long id);
	
	public int updateRecord(TGoodsOrder record);
	
	public TUser selectUserById(Long id);
}
