package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsAgencyGoodsMapper;
import com.cbice.distribute.core.db.model.HsAgencyGoods;
import com.cbice.distribute.core.db.model.HsAgencyGoodsExample;
import com.cbice.distribute.core.model.GoodsDetail;
import com.cbice.distribute.core.service.HsAgencyGoodsService;

/**
 * 
 * @author 贾阳
 *
 */
public class HsAgencyGoodsServiceImpl implements HsAgencyGoodsService{
	
	private HsAgencyGoodsMapper hsAgencyGoodsMapper;

	public void setHsAgencyGoodsMapper(HsAgencyGoodsMapper hsAgencyGoodsMapper) {
		this.hsAgencyGoodsMapper = hsAgencyGoodsMapper;
	}

	@Override
	public List<HsAgencyGoods> selectByAgencyId(String agencyId) {
		HsAgencyGoodsExample he = new HsAgencyGoodsExample();
		he.createCriteria().andAgencyIdEqualTo(agencyId);
		List<HsAgencyGoods> reslut = hsAgencyGoodsMapper.selectByExample(he);
		return reslut;
	}

	@Override
	public List<Map<String, Object>> selectGoodsListByAgencyId(
			Map<String, Object> map) {
		List<Map<String, Object>> reslut = hsAgencyGoodsMapper.selectGoodsListByAgencyId(map);
		return reslut;
	}

	@Override
	public int countGoodsByAgencyId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int count = hsAgencyGoodsMapper.countGoodsByAgencyId(map);
		return count;
	}

	@Override
	public List<GoodsDetail> selectAllGoodsListByAgencyId(
			Map<String, Object> map) {
		List<GoodsDetail> reslut = hsAgencyGoodsMapper.selectAllGoodsListByAgencyId(map);
		return reslut;
	}


}
