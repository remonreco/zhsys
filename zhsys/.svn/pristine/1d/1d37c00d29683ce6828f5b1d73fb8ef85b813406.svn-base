package com.cbice.distribute.mgr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.TAgencyUserBusiRoleDbService;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.TuserService;
/**
 *经销商会员管理
 * @author buyball
 *
 */
public class TuserServiceImpl implements TuserService{

	
	private TUserService tUserService;
	
	private SeqService seqService;
	
	private TAgencyDbService tAgencyDbService;
	
	private TAgencyUserBusiRoleDbService tAgencyUserBusiRoleDBService;
	
	
	public void settAgencyUserBusiRoleDBService(
			TAgencyUserBusiRoleDbService tAgencyUserBusiRoleDBService) {
		this.tAgencyUserBusiRoleDBService = tAgencyUserBusiRoleDBService;
	}

	public void settAgencyDbService(TAgencyDbService tAgencyDbService) {
		this.tAgencyDbService = tAgencyDbService;
	}

	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	/**
	 * 查询经销商
	 */
	@Override
	public List<TUser> selectDealer(TUser user) {

		return tUserService.selectDealer(user);
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
	public List<TUser> selectDealer2(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.selectDealer2(user);
	}

	@Override
	public int countSelectDealer2(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.countSelectDealer2(user);
	}

	@Override
	public List<TUser> selectTuserBydUserNumAndName(TUser user) {
		// TODO Auto-generated method stub
		return tUserService.selectTuserBydUserNumAndName(user);
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
	@Transactional
	@Override
	public int insertUserAgency(List<TlowerUser> list) {
        int  result=0;
		try {
			for(TlowerUser tloweruser:list){
				TUser tusers=new TUser();
				long userAgencyid=constantList.IceId;
				String dealerName=tloweruser.getDealerName();
				String dealerNum=tloweruser.getDealerNum();
				if(!dealerNum.equals("")&&!dealerName.equals("")){
					Map<String,Object> map=new HashMap<String,Object>();
					map.put("dealerName", tloweruser.getDealerName());
					map.put("dealerNum", tloweruser.getDealerNum());
					TAgency agen=tAgencyDbService.selectByAgencyNameAndAgnecyName(map);
					if(agen==null&&tloweruser.getDealerNum()!=null&&!tloweruser.getDealerNum().equals("")){
						userAgencyid=seqService.getTAgentId();
						TAgency agency=new TAgency();
						TUser tuser=new TUser();
						String dealerNamess=tloweruser.getDealerName().trim();
						String dealerNumss=tloweruser.getDealerNum().trim();
						TAgencyUserBusiRole tagencyUserBusiRole=new TAgencyUserBusiRole();
					
						agency.setId(userAgencyid);
						tusers.setAgencyId(userAgencyid);
						agency.setDealerName(tloweruser.getDealerName());
						agency.setDealerNum(tloweruser.getDealerNum());
						String a[]= tloweruser.getDealerNum().split("-");
//						if(a.length==2){
//							agency.setDealerLevel(1);
//							agency.setHigerDealerId(constantList.IceId);
//							tagencyUserBusiRole.setBusiRoleId(1l);
//						}else{
//							agency.setDealerLevel(2);
//							tagencyUserBusiRole.setBusiRoleId(2l);
//						}
						if(dealerNamess.equals("高德国金")&&dealerNumss.equals("GDGJ-010")){//一级经销商判断
							agency.setDealerLevel(1);
							agency.setHigerDealerId(constantList.IceId);
							tagencyUserBusiRole.setBusiRoleId(1l);
						}else if(dealerNamess.equals("克越世纪总公司")&&dealerNumss.equals("KYSJ-010")){//一级经销商判断
							agency.setDealerLevel(1);
							agency.setHigerDealerId(constantList.IceId);
							tagencyUserBusiRole.setBusiRoleId(1l);
						}else if(dealerNamess.equals("北京华信新业文化发展有限公司")&&dealerNumss.equals("SCPT-2014-002")){//一级经销商判断
							agency.setDealerLevel(1);
							agency.setHigerDealerId(constantList.IceId);
							tagencyUserBusiRole.setBusiRoleId(1l);
						}else{//二级经销商的判断
							    agency.setDealerLevel(2);
									if(a[0].equals("GDGJ")){
										Map<String,Object> map2=new HashMap<String,Object>();
										map2.put("dealerName", "高德国金");
										map2.put("dealerNum", "GDGJ-010");
										TAgency agens=tAgencyDbService.selectByAgencyNameAndAgnecyName(map2);//查找高德国金的经销商信息
										if(agens!=null){
											agency.setHigerDealerId(agens.getId());
										}
										tagencyUserBusiRole.setBusiRoleId(2l);
									}else if(a[0].equals("JJ")){
										Map<String,Object> map2=new HashMap<String,Object>();
										map2.put("dealerName", "北京华信新业文化发展有限公司");
										map2.put("dealerNum", "SCPT-2014-002");
										TAgency agens=tAgencyDbService.selectByAgencyNameAndAgnecyName(map2);//查找高德国金的经销商信息
										if(agens!=null){
											agency.setHigerDealerId(agens.getId());
										}
										agency.setAgencyId(agens.getId());
										tagencyUserBusiRole.setBusiRoleId(2l);
									}else{
										Map<String,Object> map2=new HashMap<String,Object>();
										map2.put("dealerName", "克越世纪总公司");
										map2.put("dealerNum", "KYSJ-010");
										TAgency agens=tAgencyDbService.selectByAgencyNameAndAgnecyName(map2);//查找高德国金的经销商信息
										if(agens!=null){
											agency.setHigerDealerId(agens.getId());
										}
										tagencyUserBusiRole.setBusiRoleId(2l);
								}
						}
						tAgencyDbService.insertSelective(agency);
						   tuser.setDealerNum(new Md5PasswordEncoder().encodePassword("111111", null));//默认加密密码
						   tuser.setDealerName(tloweruser.getDealerName());
					       tuser.setUserName(tloweruser.getDealerName());
					       tuser.setUserIdenty(1);//1经销商  0 会员 
						   tuser.setAccountStatus(1);//1有效
						   //设置默认为'0'
					       tuser.setUserRoleId(0l); //用户角色  0经理
					       tuser.setModifyTime(new Date());
					       tuser.setAgencyId(agency.getId());
					       tUserService.insertSelective(tuser);
					       tagencyUserBusiRole.setUserId(tuser.getId());
					       
					       tAgencyUserBusiRoleDBService.insertSelective(tagencyUserBusiRole);
						
					}else if(agen!=null){
						tusers.setAgencyId(agen.getId());
					}else{
						tusers.setAgencyId(userAgencyid);
					}
				}
				tusers.setAssetsAccount(tloweruser.getAssetsAccount());;;
				tusers.setRightsAccount(tloweruser.getRightAccount());
				tusers.setUserName(tloweruser.getName());
				tusers.setEmail(tloweruser.getEmail());
				tusers.setSex(tloweruser.getSex());
				tusers.setAddress(tloweruser.getAddress());
				tusers.setTel(tloweruser.getTel());
				tusers.setAccountStatus(1);
				tusers.setModifyTime(new Date());
				tusers.setUserIdenty(0);
				tusers.setCertificateNum(tloweruser.getIds());
				tUserService.insertSelective(tusers);
			}
			//boolean result4 = tAgencyDbService.genPreorder();
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	
		return result;
	}
	


}
