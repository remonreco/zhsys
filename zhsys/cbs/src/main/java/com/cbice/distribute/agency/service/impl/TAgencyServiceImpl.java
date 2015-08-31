package com.cbice.distribute.agency.service.impl;



import java.util.Date;
import java.util.List;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.TAgencyService;
import com.cbice.distribute.agency.service.TAgencyUserBusiRoleService;
import com.cbice.distribute.agency.service.TuserService;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;

public class TAgencyServiceImpl implements TAgencyService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private TAgencyDbService tAgencyDbService;
	
	private SeqService seqService;
	
	private TuserService userService;
	
	private TAgencyService tAgencyService;
	
	private TAgencyUserBusiRoleService tAgencyUserBusiRoleService;
	
	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setUserService(TuserService userService) {
		this.userService = userService;
	}

	public void settAgencyService(TAgencyService tAgencyService) {
		this.tAgencyService = tAgencyService;
	}

	public void settAgencyUserBusiRoleService(
			TAgencyUserBusiRoleService tAgencyUserBusiRoleService) {
		this.tAgencyUserBusiRoleService = tAgencyUserBusiRoleService;
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
	public List<TAgency> selectDealerAgency(TAgency agency) {
		return tAgencyDbService.selectAgency(agency);
	}

	@Override
	public int countSelectAgency(TAgency agency) {
		return tAgencyDbService.countSelectAgency(agency);
	}

	@Override
	public List<TAgency> selectAgencyBydealerNumAndName(TAgency agency) {
		return tAgencyDbService.selectAgencyBydealerNumAndName(agency);
	}

	/**
	 * 查询当前登录的下级经销商名称
	 */
	@Override
	public List<TAgency> selectLowerDealerList(Map<String, Object> map) {
		return tAgencyDbService.selectLowerDealerList(map);
	}

	@Override
	public int UpdatebyId(TAgency agency) {
		return tAgencyDbService.UpdatebyId(agency);
	}

	@Transactional
	@Override
	public int insertAgencyTransactional(UserDetailsImpl users,String dealerNum2, String dealerName2) {
		int result=0;
	    int result1=0;
	    int result2=0;
	    int result3=0;
	    TUser tuser=new TUser();
	    TAgency tagency=new TAgency();
	    TAgencyUserBusiRole tagencyUserBusiRole=new TAgencyUserBusiRole();
	    if(StringUtils.isNotBlank(dealerNum2)){
	    	//String originPwdDigest = new Md5PasswordEncoder().encodePassword(dealerNum2, null);
	    	tagency.setDealerNum(dealerNum2);
	    	tuser.setDealerNum(new Md5PasswordEncoder().encodePassword("111111", null));
	    }
	    if(StringUtils.isNotBlank(dealerName2)){
	    	tagency.setDealerName(dealerName2);
	    	tuser.setDealerName(dealerName2);
	    	tuser.setUserName(dealerName2);
	    }
	    
	    //添加子级经销商的等级
        TUser sysuser=users.gettUser();
        TAgency tAgency = users.gettAgency();	
        //查询登陆用户所属经销商的级别
        int agencylevel=tAgencyService.levelOfAgency(sysuser.getId().intValue());
        //下级经销商级别 agencylevel+1
        tagency.setDealerLevel(agencylevel+1);
        //下级经销商的 '上级经销商的id'
        tagency.setHigerDealerId(sysuser.getAgencyId());
        
        tagency.setModifyTime(new Date());
	    //经销商标示
	    tuser.setUserIdenty(1);
	    tuser.setAccountStatus(1);
	    //设置默认为'0'
        tuser.setUserRoleId(0l);
      
	    try{
	    	//向t_agency表中添加数据
	        result1=insertSelective(tagency);
	        if(result1<1){
	        	return -1;
	        }
	        tuser.setAgencyId(tagency.getId());
	        result2=userService.insertSelective(tuser);
	        if(result2<1)
	        {
	        	return -1;
	        }
	        //得到tuser对象的id
	        tagencyUserBusiRole.setUserId(tuser.getId());
	        long busiRoleId=tAgency.getDealerLevel()+1;
	        tagencyUserBusiRole.setBusiRoleId(busiRoleId);
	        result3=tAgencyUserBusiRoleService.insertSelective(tagencyUserBusiRole);
	        boolean result4 = tAgencyDbService.genPreorder();
	        if(result3<-1){
	        	return -1;
	        }
	        return 1;
	    }catch(Exception e){
	        logger.error(null, e);
	        result = -1;
	    }
	    return result;
	}

	

}
