package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.THisOtcrealtimeMapper;
import com.cbice.distribute.core.db.model.THisOtcrealtime;
import com.cbice.distribute.core.service.THisOtcrealtimeService;
import com.cbice.distribute.core.util.AmountUtil;

public class THisOtcrealtimeServiceImpl implements THisOtcrealtimeService {
	THisOtcrealtimeMapper THisOtcrealtimeMapper;

	public void setTHisOtcrealtimeMapper(THisOtcrealtimeMapper tHisOtcrealtimeMapper) {
		THisOtcrealtimeMapper = tHisOtcrealtimeMapper;
	}

	@Override
	public List<Map<String, Object>> CommissionQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = THisOtcrealtimeMapper.CommissionQuery(map);
		for(Map<String, Object> map1:list){
			map1.put("cjsl",AmountUtil.formatMoney((Double)map1.get("cjsl"),2));
			map1.put("cjje",AmountUtil.formatMoney((Double)map1.get("cjje"),2));
			map1.put("fare0",AmountUtil.formatMoney((Double)map1.get("fare0"),2));
			map1.put("current_amount",AmountUtil.formatMoney((Double)map1.get("current_amount"),2));
		}
		return list;
	}

	@Override
	public int countSelectOrderBy(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return THisOtcrealtimeMapper.countSelectBy(map);
	}

	@Override
	public List<THisOtcrealtime> commissionQueryAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return THisOtcrealtimeMapper.commissionQueryAll(map);
	}

	@Override
	public int countSelectBySum(Map<String, Object> map) {
		return THisOtcrealtimeMapper.countSelectBySum(map);
	}

	@Override
	public List<Map<String, Object>> CommissionQuerySum(Map<String, Object> map) {
		List<Map<String, Object>> list = THisOtcrealtimeMapper.CommissionQuerySum(map);
		for(Map<String, Object> map1:list){
			map1.put("cjsl",AmountUtil.formatMoney((Double)map1.get("cjsl"),2));
			map1.put("cjje",AmountUtil.formatMoney((Double)map1.get("cjje"),2));
			map1.put("fare0",AmountUtil.formatMoney((Double)map1.get("fare0"),2));
			map1.put("current_amount",AmountUtil.formatMoney((Double)map1.get("current_amount"),2));
		}
		return list;
	}

	@Override
	public List<THisOtcrealtime> commissionQueryAllSum(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return THisOtcrealtimeMapper.commissionQueryAllSum(map);
	}

	@Override
	public int queryMaxdate() {
		// TODO Auto-generated method stub
		return THisOtcrealtimeMapper.queryMaxdate();
	}

}
