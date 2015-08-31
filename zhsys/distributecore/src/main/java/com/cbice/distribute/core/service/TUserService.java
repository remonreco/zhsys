package com.cbice.distribute.core.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TUser;

public interface TUserService {
	
	List<TUser> selectDealer(TUser user);
	
	List<TUser> selectDealerAgency(TUser user);
	/**
	 * 查询经销商的所有会员
	 * @param user
	 * @return
	 */
	List<TUser> selectDealerUser(TUser user);
	
	int countSelectDealerAgency(TUser user);
	
	
	int countSelectDealer(TUser user);
	
	
	/**
	 * 查询会员
	 * @param record
	 * @return
	 */
	List<TUser> selectDealer2(TUser user);
	
	int countSelectDealer2(TUser user);
	
	int insertSelective(TUser record);
	List<TUser>  selectTuserBydealerNumAndName(TUser user);
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
	 * 统计下级所有会员和经销商
	 * @param higerDealer
	 * @return
	 */
	int countselectLowerDealer(Map<String,Object> map);
	
	TUser selectByPrimaryKey(Long id);
	/**
	 * 查询本身和所有下级经销商用于选择所属经销商的select勿改
	 * @param map
	 * @return
	 */
	List<TUser> selectLowerList(Map<String,Object> map);
	
	/**
	 * 测试用的
	 * @return
	 */
	List<TUser> selectUserAndTest();
	
	/**
	 * 查询账户激活的用户是否存在
	 * @param tUser
	 * @return
	 */
	List<TUser> selectAllToConvert(TUser tUser);
	
	/**
	 * 更新激活账户的密码
	 * @param tUser
	 * @return
	 */
	int updatePwdByAss(TUser tUser);
	
	/**
	 * 兑换用户登录
	 * @param tUser
	 * @return
	 */
	List<TUser> selectConvertLogin(TUser tUser);

}
