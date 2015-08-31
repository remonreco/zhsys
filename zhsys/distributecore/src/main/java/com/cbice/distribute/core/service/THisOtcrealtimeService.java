package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.THisOtcrealtime;

/**
 * 
 * @author zj
 *his_otccrealtime表
 */
public interface THisOtcrealtimeService {
	/*
	 * 佣金查询
	 */
	List<Map<String, Object>> CommissionQuery(Map<String, Object> map);
	List<THisOtcrealtime> commissionQueryAll(Map<String, Object> map);
	List<THisOtcrealtime> commissionQueryAllSum(Map<String, Object> map);
	public int countSelectOrderBy(Map<String, Object> map);
	
	public int countSelectBySum(Map<String, Object> map);
	
	public List<Map<String,Object>> CommissionQuerySum(Map<String, Object> map);
	int queryMaxdate();
}
