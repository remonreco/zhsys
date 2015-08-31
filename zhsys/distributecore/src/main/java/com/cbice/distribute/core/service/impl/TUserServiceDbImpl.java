package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TAgencyBusiRoleRoleMapper;
import com.cbice.distribute.core.db.data.TAgencyUserBusiRoleMapper;
import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TUserService;

public class TUserServiceDbImpl implements TUserService {

	
  private TUserMapper tUserMapper;
  private SeqService seqService;
	
  private TAgencyBusiRoleRoleMapper tAgencyBusiRoleRoleMapper;
  
	public TAgencyBusiRoleRoleMapper gettAgencyBusiRoleRoleMapper() {
	return tAgencyBusiRoleRoleMapper;
}

public void settAgencyBusiRoleRoleMapper(
		TAgencyBusiRoleRoleMapper tAgencyBusiRoleRoleMapper) {
	this.tAgencyBusiRoleRoleMapper = tAgencyBusiRoleRoleMapper;
}

	public void settUserMapper(TUserMapper tUserMapper) {
	this.tUserMapper = tUserMapper;
      }

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}


	@Override
	public List<TUser> selectDealer(TUser user) {
		return tUserMapper.selectDealer(user);
	}
	
	@Override
	public List<TUser> selectDealerUser(TUser user) {
		return tUserMapper.selectDealerUser(user);
	}

	@Override
	public int countSelectDealer(TUser user) {
		
		return tUserMapper.countSelectDealer(user);
	}


	@Override
	public int insertSelective(TUser record) {
		Long id=seqService.getTUserId();
		record.setId(id);
		return tUserMapper.insertSelective(record);
	}


	@Override
	public List<TUser> selectTuserBydealerNumAndName(TUser user) {
		return tUserMapper.selectTuserBydealerNumAndName(user);
	}

	@Override
	public List<TUser> selectLowerDealer(Map<String,Object> map) {
		return tUserMapper.selectLowerDealer(map);
	}

	@Override
	public int countselectLowerDealer(Map<String,Object> map) {
		return tUserMapper.countselectLowerDealer(map);
	}

	@Override
	public List<TUser> selectDealer2(TUser user) {
		// TODO Auto-generated method stub
		return tUserMapper.selectDealer2(user);
	}

	@Override
	public int countSelectDealer2(TUser user) {
		// TODO Auto-generated method stub
		return tUserMapper.countSelectDealer2(user);
	}

	@Override
	public List<TUser> selectTuserBydUserNumAndName(TUser user) {
		// 
		return tUserMapper.selectTuserBydUserNumAndName(user);
	}

	@Override
	public TUser selectById(Long id) {
		return tUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public int UpdatebyId(TUser user) {
		return tUserMapper.updateByPrimaryKey(user);
	}


	@Override
	public List<TUser> selectDealerAgency(TUser user) {
		// TODO Auto-generated method stub
		return tUserMapper.selectDealerAgency(user);
	}

	@Override
	public int countSelectDealerAgency(TUser user) {
		// TODO Auto-generated method stub
		return tUserMapper.countSelectDealerAgency(user);
	}

	@Override
	public TUser selectByPrimaryKey(Long id) {
		return tUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TUser> selectLowerList(Map<String, Object> map) {
		return tUserMapper.selectLowerList(map);
	}

	@Override
	public List<TUser> selectUserAndTest() {
		return tUserMapper.selectUserAndTest();
	}

	
	/**
	 * 查询账户激活的用户是否存在
	 */
	@Override
	public List<TUser> selectAllToConvert(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserMapper.selectAllToConvert(tUser);
	}
	
	/**
	 * 更新激活账户的密码
	 */
	@Override
	public int updatePwdByAss(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserMapper.updatePwdByAss(tUser);
	}
	
	/**
	 * 兑换用户登录
	 */
	@Override
	public List<TUser> selectConvertLogin(TUser tUser) {
		// TODO Auto-generated method stub
		return tUserMapper.selectConvertLogin(tUser);
	}
	


}
