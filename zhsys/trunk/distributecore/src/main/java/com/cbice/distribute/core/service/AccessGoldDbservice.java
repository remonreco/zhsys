package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TAccessGold;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月12日 下午2:23:11
 * @describe
 */
public interface AccessGoldDbservice {
	
	List<Map> selectAccessGold(Map<String, Object> map);

	int selectAccessGoldCount(Map<String, Object> map);
	
	List<TAccessGold> selectAccessGoldAll(Map<String, Object> map);
	
	
	int selectAccessGoldCountSum(Map<String, Object> map);
	
	List<Map> selectAccessGoldSum(Map<String, Object> map);
	
	List<TAccessGold> selectAccessGoldAllSum(Map<String, Object> map);

	int queryMaxDate();

}
