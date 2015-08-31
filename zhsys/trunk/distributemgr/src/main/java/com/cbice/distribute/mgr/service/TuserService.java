package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.mgr.security.model.TlowerUser;
/**
 * 经销商会员管理
 * @author buyball
 *
 */
public interface TuserService {

	
	List<TUser> selectDealer(TUser user);
	int countSelectDealer(TUser user);
	
	List<TUser> selectDealer2(TUser user);
	int countSelectDealer2(TUser user);
	int insertSelective(TUser record);
	
	List<TUser> selectTuserBydealerNumAndName(TUser user);
	List<TUser> selectTuserBydUserNumAndName(TUser user);
	TUser selectById(Long id);
    int	UpdatebyId(TUser user);
	/**
	 * 查询经销商下的所有会员和经销商
	 * @param higerDealer
	 * @return
	 */
	List<TUser> selectLowerDealer(Map<String,Object> map);
	/**
	 * 统计下级经销商和会员
	 * @param higerDealer
	 * @return
	 */
	int countselectLowerDealer(Map<String,Object> map);
	
	int insertUserAgency(List<TlowerUser> list);
	

}
