package com.cbice.distribute.mgr.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.service.TAgencyService;
import com.cbice.distribute.mgr.service.TAgencyUserBusiRoleService;
import com.cbice.distribute.mgr.service.TuserService;

public class TAgencyServiceImpl implements TAgencyService {

	private TAgencyDbService tAgencyDbService;
	
	private SeqService seqService;
	
	private TuserService userService;
	
	private TAgencyUserBusiRoleService tAgencyUserBusiRoleService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void settAgencyUserBusiRoleService(
			TAgencyUserBusiRoleService tAgencyUserBusiRoleService) {
		this.tAgencyUserBusiRoleService = tAgencyUserBusiRoleService;
	}

	public void setUserService(TuserService userService) {
		this.userService = userService;
	}

	public SeqService getSeqService() {
		return seqService;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	public void settAgencyDbService(TAgencyDbService tAgencyDbService) {
		this.tAgencyDbService = tAgencyDbService;
	}

	@Override
	public TAgency selectByAgencyId(long id) {
		return tAgencyDbService.selectByAgencyId(id);
	}

	@Override
	public int levelOfAgency(long id) {
		return tAgencyDbService.levelOfAgency(id);
	}

	@Override
	public int insertSelective(TAgency agency) {
		Long id=seqService.getTAgentId();
		agency.setId(id);
		return tAgencyDbService.insertSelective(agency);
	}


	@Override
	public List<TAgency> selectAgencyBydealerNumAndName(TAgency agency) {
		
		return tAgencyDbService.selectAgencyBydealerNumAndName(agency);
	}

	@Override
	public List<TAgency> selectAgency(TAgency agency) {
		return tAgencyDbService.selectAgency(agency);
	}

	@Override
	public int countSelectAgency(TAgency agency) {
		return tAgencyDbService.countSelectAgency(agency);
	}

	@Override
	public TAgency selectById(Long id) {
		return tAgencyDbService.selectByAgencyId(id);
	}

	@Override
	public int UpdatebyId(TAgency agency) {
		return tAgencyDbService.UpdatebyId(agency);
	}

	@Override
	public List<TAgency> selectLowerDealerList(Map<String, Object> map) {
		return tAgencyDbService.selectLowerDealerList(map);
	}
	@Transactional
	@Override
	public int insertAgencyTransactional(String dealerNum2, String dealerName2) {
		int result=0;
	    int result1=0;
	    int result2=0;
	    int result3=0;
	    TUser tuser=new TUser();
	    TAgency tagency=new TAgency();
	    TAgencyUserBusiRole tagencyUserBusiRole=new TAgencyUserBusiRole();
	    if(StringUtils.isNotBlank(dealerNum2)){
	    	String originPwdDigest = new Md5PasswordEncoder().encodePassword(dealerNum2, null);
	    	//经销商编码不加密  显示明文
	    	tagency.setDealerNum(dealerNum2);
	    	//设置一级经销商的默认密码为111111
	    	/*String originPwdDigest = new Md5PasswordEncoder().encodePassword(dealerNum2, null);
	    	tagency.setDealerNum(originPwdDigest);*/
	    	tuser.setDealerNum(new Md5PasswordEncoder().encodePassword("111111", null));//默认加密密码
	    }
	    if(StringUtils.isNotBlank(dealerName2)){
	    	tagency.setDealerName(dealerName2);
	    	tuser.setDealerName(dealerName2);
	    	tuser.setUserName(dealerName2);
	    }
	    
	    //mgr系统默认设置下级经销商等级为1
	    //添加子级经销商的等级
	    tagency.setDealerLevel(1);//一级经销商
	    
	    tagency.setHigerDealerId(constantList.IceId);//ICE下的一级经销商
	    tagency.setModifyTime(new Date());//修改时间
	    //设置经销商标示
	    tuser.setUserIdenty(1);//1经销商  0 会员 
	    tuser.setAccountStatus(1);//1有效
	    //设置默认为'0'
        tuser.setUserRoleId(0l); //用户角色  0经理
	    try{
	    	//添加到经销商表中
	    	result1=insertSelective(tagency);
	    	if(result1<1){
	    		return -1;
	    	}
	    	
	        tuser.setAgencyId(tagency.getId());
	        //添加到t_user表中
	        result2=userService.insertSelective(tuser);
	        if(result2<1){
	    		return -1;
	    	}
	        //得到tuser对象的id
	        tagencyUserBusiRole.setUserId(tuser.getId());
	        tagencyUserBusiRole.setBusiRoleId(1l);
	        //添加到t_agency_user_busi_role表中
	        result3=tAgencyUserBusiRoleService.insertSelective(tagencyUserBusiRole);
	        if(result3<1){
	        	return -1;
	        }
	        boolean result4 = tAgencyDbService.genPreorder();
	        result=1;
	    }catch(Exception e){
	        logger.error(null, e);
	        result = -1;
	    }
	    return result;
	}

}
