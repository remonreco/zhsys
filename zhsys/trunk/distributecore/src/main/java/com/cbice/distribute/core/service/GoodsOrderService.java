package com.cbice.distribute.core.service;

import java.util.List;

import com.cbice.distribute.core.db.data.VGoodOrderUserMapper;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TGoodsOrderExample;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;

public interface GoodsOrderService {
	
	
   	public List<VGoodOrderUser> selectDealer(VGoodOrderUser goods);
	
	public int countSelectDealer(VGoodOrderUser goods);

}
