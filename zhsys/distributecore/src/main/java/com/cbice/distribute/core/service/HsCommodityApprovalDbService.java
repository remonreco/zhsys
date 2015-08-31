package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsCommodityApproval;

public interface HsCommodityApprovalDbService {
	/**
	 * 分页查询商品list
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> commodityApprovalQuery(Map<String, Object> map);
	/**
	 * 查询商品总数
	 * @param map
	 * @return
	 */
	public int countCommodityApprovalQuery(Map<String, Object> map);
	/**
	 * 添加商品审批字段
	 * @param record
	 * @return
	 */
	int insertSelective(HsCommodityApproval record);
	/**
	 * 当日审批成功数据查询
	 * @param map
	 * @return
	 */
	List<HsCommodityApproval> queryApprovalSuccess(Map<String, Object> map);
	/**
	 * 当日审批成功兑换详情数据查询
	 * @param map
	 * @return
	 */
	List<HsCommodityApproval> queryApprovalSuccessEx(Map<String, Object> map);
}
