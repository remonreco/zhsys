package com.cbice.distribute.mgr.service;

import java.util.Map;

/**
 * 实时交易记录service
 * @author zj
 * @date 2015年6月25日 下午5:29:08
 * @Description: TODO
 */
public interface TtransactionRecordService {
	/**
	 * 实时交易记录定时处理
	 * @return
	 */
	int doTransactionRecordApproval(Map<String, Object> map);
	
}
