package com.cbice.distribute.agency.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TUser;
/**
 * 经销商会员管理
 * @author buyball
 *
 */
public interface TuserService {

	
	List<TUser> selectDealer(TUser user);
	List<TUser> selectDealerAgency(TUser user);
	int countSelectDealer(TUser user);
	int countSelectDealerAgency(TUser user);
	int insertSelective(TUser record);
	
	TUser selectById(Long id);
	int	UpdatebyId(TUser user);
	List<TUser> selectTuserBydealerNumAndName(TUser user);
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
	/**
	 * 查询经销商下的所有会员
	 * @param user
	 * @return
	 */
	public List<TUser> selectDealerUser(TUser user);
	/**
	 * 查询经销商下的所有会员记录数
	 * */
	int countSelectDealer2(TUser user);
	
	/**
	 * 添加会员验证
	 * @param user
	 * @return
	 */
	List<TUser> selectTuserBydUserNumAndName(TUser user);
	/**
	 * 查询本身和所有下级经销商用于选择所属经销商的select勿改
	 * @param map
	 * @return
	 */
	List<TUser> selectLowerList(Map<String,Object> map);
	
}
