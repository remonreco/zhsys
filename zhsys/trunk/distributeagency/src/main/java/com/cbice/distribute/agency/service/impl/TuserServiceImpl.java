package com.cbice.distribute.agency.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.agency.service.TuserService;
/**
 *经销商会员管理
 * @author buyball
 *
 */
public class TuserServiceImpl implements TuserService{

	
	private TUserService tUserService;

	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	/**
	 * 查询经销商
	 */
	@Override
	public List<TUser> selectDealer(TUser user) {

		return tUserService.selectDealer(user);
	}
	/**
	 * 查询经销商的会员信息
	 */
	@Override
	public List<TUser> selectDealerUser(TUser user) {

		return tUserService.selectDealerUser(user);
		
	}
	/**
	 * 会员重复添加验证
	 */
	@Override
	public List<TUser> selectTuserBydUserNumAndName(TUser user) {
		return tUserService.selectTuserBydUserNumAndName(user);
	}
	
	@Override
	public int countSelectDealer(TUser user) {
		return tUserService.countSelectDealer(user);
	}

	@Override
	public int insertSelective(TUser record) {
		return tUserService.insertSelective(record);
	}

	@Override
	public List<TUser> selectTuserBydealerNumAndName(TUser user) {
		return tUserService.selectTuserBydealerNumAndName(user);
	}

	@Override
	public List<TUser> selectLowerDealer(Map<String,Object> map) {
		return tUserService.selectLowerDealer(map);
	}

	@Override
	public int countselectLowerDealer(Map<String,Object> map) {
		return tUserService.countselectLowerDealer(map);
	}
	
	@Override
	public TUser selectById(Long id) {
		// TODO Auto-generated method stub
		return tUserService.selectById(id);
	}
	
	@Override
	public int UpdatebyId(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.UpdatebyId(user);
	}

	@Override
	public List<TUser> selectDealerAgency(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.selectDealerAgency(user);
	}

	@Override
	public int countSelectDealerAgency(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.countSelectDealerAgency(user);
	}

	@Override
	public int countSelectDealer2(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.countSelectDealer2(user);
	}

	@Override
	public List<TUser> selectLowerList(Map<String, Object> map) {
		
		return tUserService.selectLowerList(map);
	}

}
