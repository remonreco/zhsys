package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.HsCommodityApproval;

/**
 * 商品审批service
 * @author zj
 *
 */
public interface TCommodityApprovalService {
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
	int countCommodityApprovalQuery(Map<String, Object> map);
	
	/**
	 * 执行商品审批service
	 * @param map
	 * @return
	 */
	int doCommodityApproval(Map<String, Object> map);
	/**
	 * 当日审批成功数据查询service
	 * @param map
	 * @return
	 */
	List<HsCommodityApproval> queryApprovalSuccess(Map<String, Object> map);
	/**
	 * 当日审批成功兑换详情数据查询service
	 * @param map
	 * @return
	 */
	List<HsCommodityApproval> queryApprovalSuccessEx(Map<String, Object> map);
}
