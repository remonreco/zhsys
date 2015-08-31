package com.cbice.distribute.core.service;

import java.util.List;

import com.cbice.distribute.core.db.model.HsSystem;

/**
 * 系统分配表
 * @author zj
 * @date 2015年6月29日 上午10:07:38
 * @Description: TODO
 */
public interface HsSystemService {
	/**
	 * 查询系统列表
	 * @return
	 */
	List<HsSystem> queryHsSystemList();

}
