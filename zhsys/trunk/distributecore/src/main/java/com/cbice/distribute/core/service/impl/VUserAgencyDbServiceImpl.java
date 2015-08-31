package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.VUserAgencyMapper;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.VUserAgencyDbService;

public class VUserAgencyDbServiceImpl implements VUserAgencyDbService {
	
	private VUserAgencyMapper vUserAgencyMapper;
	
	
	public void setvUserAgencyMapper(VUserAgencyMapper vUserAgencyMapper) {
		this.vUserAgencyMapper = vUserAgencyMapper;
	}
	/**
	 * 根据userId查找记录
	 * @param id
	 * @return
	 */
	@Override
	public VUserAgency selectById(Long id) {
		return vUserAgencyMapper.selectById(id);
	}
	/**
	 * 根据上级经销商Id查找所有的会员和经销商
	 * @param id
	 * @return
	 */
	@Override
	public List<VUserAgency> selectAllByHigerAgencyId(Long id) {
		return vUserAgencyMapper.selectAllByHigerAgencyId(id);
	}
	/**
	 * 根据上级经销商Id查找所有的会员
	 * @param id
	 * @return
	 */
	@Override
	public List<VUserAgency> selectUsersByHigerAgencyId(Long id) {
		return vUserAgencyMapper.selectUsersByHigerAgencyId(id);
	}
	/**
	 * 根据上级经销商Id查找所有的经销商
	 * @param id
	 * @return
	 */
	
	@Override
	public List<VUserAgency> selectAgenciesByHigerAgencyId(Long id) {
		return vUserAgencyMapper.selectAgenciesByHigerAgencyId(id);
	}
	@Override
	public List<VUserAgency> selectLowerDealer(Map<String, Object> map) {
		return vUserAgencyMapper.selectLowerDealer(map);
	}
	@Override
	public int countselectLowerDealer(Map<String, Object> map) {
		return vUserAgencyMapper.countselectLowerDealer(map);
	}

}
