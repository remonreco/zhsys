package com.cbice.distribute.agency.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.agency.service.AgencyGoodService;
import com.cbice.distribute.core.db.model.HsAgencyGoods;
import com.cbice.distribute.core.model.GoodsDetail;
import com.cbice.distribute.core.service.HsAgencyGoodsService;

/**
 * 
 * @author 贾阳
 *
 */
public class AgencyGoodsServiceImpl implements AgencyGoodService{
	
	private HsAgencyGoodsService hsAgencyGoodsService;
	
	public HsAgencyGoodsService getHsAgencyGoodsService() {
		return hsAgencyGoodsService;
	}
	public void setHsAgencyGoodsService(HsAgencyGoodsService hsAgencyGoodsService) {
		this.hsAgencyGoodsService = hsAgencyGoodsService;
	}
	@Override
	public List<HsAgencyGoods> selectByAgencyId(String agencyId) {
		return  hsAgencyGoodsService.selectByAgencyId(agencyId);
	}
	@Override
	public List<Map<String, Object>> selectGoodsListByAgencyId(
			Map<String, Object> map) {
		return hsAgencyGoodsService.selectGoodsListByAgencyId(map);
	}
	@Override
	public int countGoodsByAgencyId(Map<String, Object> map) {
		return hsAgencyGoodsService.countGoodsByAgencyId(map);
	}
	@Override
	public List<GoodsDetail> selectAllGoodsListByAgencyId(
			Map<String, Object> map) {
		return hsAgencyGoodsService.selectAllGoodsListByAgencyId(map);
	}


}
