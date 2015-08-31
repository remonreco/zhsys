package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TAgency;
/**
 * 经销商表
 * @author shangtengfei
 *
 */
public interface TAgencyService {

	/**
	 * 查询经销商的级别
	 * @param higerDealer
	 * @return
	 */
	int levelOfAgency(long id);
	
	int insertSelective(TAgency agency);
	
	//添加经销商(事物)
	int insertAgencyTransactional(String dealerNum2, String dealerName2);
	
	TAgency selectByAgencyId(long id);
	
	List<TAgency> selectAgency(TAgency agency);
	
	int countSelectAgency(TAgency agency);
	
	//检查经销商是否存在
	List<TAgency> selectAgencyBydealerNumAndName(TAgency agency);
	
	TAgency selectById(Long id);
	
	int	UpdatebyId(TAgency agency);
	
	//查询当前登录的下级经销商列表
	List<TAgency> selectLowerDealerList(Map<String,Object> map);
}
