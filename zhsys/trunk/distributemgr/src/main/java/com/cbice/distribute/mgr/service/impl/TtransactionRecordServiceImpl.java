package com.cbice.distribute.mgr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cbice.distribute.core.db.model.HsSystem;
import com.cbice.distribute.core.db.model.HsTransactionRecord;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.service.HsSystemService;
import com.cbice.distribute.core.service.HsTransactionRecordDbService;
import com.cbice.distribute.core.service.HsUserRightsDbService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.mgr.service.TtransactionRecordService;

/**
 * 实时交易记录service实现类
 * @author zj
 * @date 2015年6月25日 下午5:30:06
 * @Description: TODO
 */
public class TtransactionRecordServiceImpl implements TtransactionRecordService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	HsTransactionRecordDbService hsTransactionRecordDbService;
	@Resource
	HsUserRightsDbService hsUserRightsDbService;
	@Resource
	HsSystemService hsSystemService;
	
	public void setHsTransactionRecordDbService(
			HsTransactionRecordDbService hsTransactionRecordDbService) {
		this.hsTransactionRecordDbService = hsTransactionRecordDbService;
	}

	public void setHsUserRightsDbService(HsUserRightsDbService hsUserRightsDbService) {
		this.hsUserRightsDbService = hsUserRightsDbService;
	}

	public void setHsSystemService(HsSystemService hsSystemService) {
		this.hsSystemService = hsSystemService;
	}

//	@Override
//	public int doTransactionRecordApproval() {
//		// TODO Auto-generated method stub
//		Map<String,Object> map = new HashMap<String,Object>();
//		int result = 1;
//		List<HsSystem> systemList = hsSystemService.queryHsSystemList();
//		for(HsSystem hsSystem:systemList){
//			Date endDate = DateUtils.StringToDate(DateUtils.DateToString(new Date(), "yyyy-MM-dd")+" "+hsSystem.getScheduleTime()+":00","yyyy-MM-dd HH:mm:ss");
//			Date startDate = DateUtils.getAnyDayByNo(endDate, -1);
//			map.put("sysId", hsSystem.getSysId());
//			map.put("transTime1", startDate);
//			map.put("transTime2", endDate);
//			List<HsTransactionRecord> list = hsTransactionRecordDbService.queryTodayTransactionRecord(map);
//			if(null!=list&&list.size()>0){
//				result = parseList(list);
//			}
//			logger.info("系统ID为"+ hsSystem.getSysId()+"的实时交易记录与今日"+new Date()+"处理完成！");
//		}
//		return result;
//	}
	
	@Override
	public int doTransactionRecordApproval(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int result = 1;
//		Date endDate = DateUtils.StringToDate(DateUtils.DateToString(new Date(), "yyyy-MM-dd")+" "+hsSystem.getScheduleTime()+":00","yyyy-MM-dd HH:mm:ss");
//		Date startDate = DateUtils.getAnyDayByNo(endDate, -1);
		List<HsTransactionRecord> list = hsTransactionRecordDbService.queryTodayTransactionRecord(map);
		if(null!=list&&list.size()>0){
			result = parseList(list);
		}
		logger.info("系统ID为"+ map.get("sysId")+"的实时交易记录与今日"+new Date()+"处理完成！");
		return result;
	}
	
	/**
	 * 解析实时交易记录
	 * @param list
	 * @return
	 */
	int parseList(List<HsTransactionRecord> list){
		Map<String,Object> map = new HashMap<String,Object>();
		int result = 1;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result5 = 0;
		int result6 = 0;
		int result7 = 0;
		int result8 = 0;
		String sysId;			//系统ID
		long customerNum;		//客户编号
		long rightsCode;		//权益代码
		int transType;			//交易类型  1买  2卖
		String sysId1;			//系统ID
		long customerNum1;		//客户编号
		long rightsCode1;		//权益代码
		long useTotalNum = 0;	//同一系统下同一用户同一天操作的同一个产品所进出的总数
		boolean first = true;
		for(int i=0;i<list.size();i++){
			HsTransactionRecord hsTransactionRecord = list.get(i);
			sysId = hsTransactionRecord.getSysId();
			customerNum = hsTransactionRecord.getCustomerNum();
			rightsCode = hsTransactionRecord.getRightsCode();
			transType = hsTransactionRecord.getTransType();

			if(first){
//				map.put("sysId", sysId);
//				map.put("customerNum", customerNum);
//				map.put("rightsCode", constantList.DEF_RIGHT_CODE);
//				查询用户权益信息表，验证用户是否为第一次交易
//				HsUserRights hsUserRights1 = hsUserRightsDbService.queryBySpecialRightsCode(map);
				first = false;
				if(transType==1){
					useTotalNum =useTotalNum+hsTransactionRecord.getUseNum();
				}else{
					useTotalNum =useTotalNum-hsTransactionRecord.getUseNum();
				}
				if(list.size()==1){
					HsUserRights hsUserRights = new HsUserRights();
					hsUserRights.setSysId(sysId);
					hsUserRights.setCustomerNum(customerNum);
					hsUserRights.setCustomerName(hsTransactionRecord.getCustomerName());
					hsUserRights.setRightsCode(hsTransactionRecord.getRightsCode());
					hsUserRights.setRightsName(hsTransactionRecord.getRightsName());
					hsUserRights.setTransDate(DateUtils.StringToDate(DateUtils.DateToString(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
					hsUserRights.setAvailableNum(useTotalNum);
					result1 = hsUserRightsDbService.insertSelective(hsUserRights);
					if(result1!=1){
						result = 0;
						logger.error("系统ID为："+sysId+" 客户编号为："+customerNum+" 权益代码为："+hsTransactionRecord.getRightsCode()+"的实时交易记录插入用户权益表失败");
					}
				}
			}else{
				HsTransactionRecord hsTransactionRecord1 = list.get(i-1);
				sysId1 = hsTransactionRecord1.getSysId();
				customerNum1 = hsTransactionRecord1.getCustomerNum();
				rightsCode1 = hsTransactionRecord1.getRightsCode();
				
				
				HsUserRights hsUserRights = new HsUserRights();
				hsUserRights.setSysId(sysId1);
				hsUserRights.setCustomerNum(customerNum1);
				hsUserRights.setCustomerName(hsTransactionRecord1.getCustomerName());
				hsUserRights.setRightsCode(hsTransactionRecord1.getRightsCode());
				hsUserRights.setRightsName(hsTransactionRecord1.getRightsName());
				hsUserRights.setTransDate(DateUtils.StringToDate(DateUtils.DateToString(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
				
				if(sysId1.equals(sysId)){
					if(customerNum1==customerNum){
						if(rightsCode1==rightsCode){
							if(transType==1){
								useTotalNum =useTotalNum+hsTransactionRecord.getUseNum();
							}else{
								useTotalNum =useTotalNum-hsTransactionRecord.getUseNum();
							}
							if(i==(list.size()-1)){
								hsUserRights.setAvailableNum(useTotalNum);
								result2 = hsUserRightsDbService.insertSelective(hsUserRights);
								if(result2!=1){
									result = 0;
									logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
								}
							}
						}else{
							hsUserRights.setAvailableNum(useTotalNum);
							result3 = hsUserRightsDbService.insertSelective(hsUserRights);
							if(result3!=1){
								result = 0;
								logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
							}
							if(transType==1){
								useTotalNum = hsTransactionRecord.getUseNum();
							}else{
								useTotalNum = -hsTransactionRecord.getUseNum();
							}
							if(i==(list.size()-1)){
								hsUserRights.setAvailableNum(useTotalNum);
								result4 = hsUserRightsDbService.insertSelective(hsUserRights);
								if(result4!=1){
									result = 0;
									logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
								}
							}
						}
					}else{
						hsUserRights.setAvailableNum(useTotalNum);
						result5 = hsUserRightsDbService.insertSelective(hsUserRights);
						if(result5!=1){
							result = 0;
							logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
						}
						if(transType==1){
							useTotalNum = hsTransactionRecord.getUseNum();
						}else{
							useTotalNum = -hsTransactionRecord.getUseNum();
						}
						if(i==(list.size()-1)){
							hsUserRights.setAvailableNum(useTotalNum);
							result6 = hsUserRightsDbService.insertSelective(hsUserRights);
							if(result6!=1){
								result = 0;
								logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
							}
						}
					}
				}else{
					hsUserRights.setAvailableNum(useTotalNum);
					result7 = hsUserRightsDbService.insertSelective(hsUserRights);
					if(result7!=1){
						result = 0;
						logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
					}
					if(transType==1){
						useTotalNum = hsTransactionRecord.getUseNum();
					}else{
						useTotalNum = -hsTransactionRecord.getUseNum();
					}
					if(i==(list.size()-1)){
						hsUserRights.setAvailableNum(useTotalNum);
						result8 = hsUserRightsDbService.insertSelective(hsUserRights);
						if(result8!=1){
							result = 0;
							logger.error("系统ID为："+sysId1+" 客户编号为："+customerNum1+" 权益代码为："+hsTransactionRecord1.getRightsCode()+"的实时交易记录插入用户权益表失败");
						}
					}
				}
			}
		}
		return result;
	}
}
