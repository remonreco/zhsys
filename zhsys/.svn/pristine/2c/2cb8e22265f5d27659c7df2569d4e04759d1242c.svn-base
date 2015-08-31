package com.cbice.distribute.agency.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TUser;
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
	int insertAgencyTransactional(UserDetailsImpl users,String dealerNum2, String dealerName2);
	
	TAgency selectByAgencyId(long id);

	//检查经销商是否存在
		List<TAgency> selectAgencyBydealerNumAndName(TAgency agency);
	
	List<TAgency> selectDealerAgency(TAgency agency);
	
	int countSelectAgency(TAgency agency);

	/**
	 * 查询当前登录的下级经销商名称
	 * @param map
	 * @return
	 */
	List<TAgency> selectLowerDealerList(Map<String,Object> map);

	int	UpdatebyId(TAgency agency);
	
}
