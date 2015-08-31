package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.VUserAgency;

public interface VUserAgencyDbService {
	/**
	 * 根据userId查找记录
	 * @param id
	 * @return
	 */
	public VUserAgency selectById(Long id);
	/**
	 * 根据上级经销商Id查找所有的会员和经销商
	 * @param id
	 * @return
	 */
	public List<VUserAgency> selectAllByHigerAgencyId(Long id);
	/**
	 * 根据上级经销商Id查找所有的会员
	 * @param id
	 * @return
	 */
	
	public List<VUserAgency> selectUsersByHigerAgencyId(Long id);
	/**
	 * 根据上级经销商Id查找所有的经销商
	 * @param id
	 * @return
	 */
	
	public List<VUserAgency> selectAgenciesByHigerAgencyId(Long id);
	
	
	public List<VUserAgency> selectLowerDealer(Map<String,Object> map);
	public int countselectLowerDealer(Map<String,Object> map);

}
