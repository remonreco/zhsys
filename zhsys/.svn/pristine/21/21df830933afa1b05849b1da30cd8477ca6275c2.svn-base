package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsCommodityApprovalMapper;
import com.cbice.distribute.core.db.data.HsCommodityRuleMapper;
import com.cbice.distribute.core.db.model.HsCommodityApproval;
import com.cbice.distribute.core.service.HsCommodityApprovalDbService;

public class HsCommodityApprovalDbServiceImpl implements HsCommodityApprovalDbService{
	HsCommodityApprovalMapper hsCommodityApprovalMapper;
	public void setHsCommodityApprovalMapper(
			HsCommodityApprovalMapper hsCommodityApprovalMapper) {
		this.hsCommodityApprovalMapper = hsCommodityApprovalMapper;
	}

	@Override
	public List<Map<String, Object>> commodityApprovalQuery(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalMapper.commodityApprovalQuery(map);
	}

	@Override
	public int countCommodityApprovalQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalMapper.countCommodityApprovalQuery(map);
	}

	@Override
	public int insertSelective(HsCommodityApproval record) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalMapper.insertSelective(record);
	}

	@Override
	public List<HsCommodityApproval> queryApprovalSuccess(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalMapper.queryApprovalSuccess(map);
	}

	@Override
	public List<HsCommodityApproval> queryApprovalSuccessEx(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalMapper.queryApprovalSuccessEx(map);
	}

}
