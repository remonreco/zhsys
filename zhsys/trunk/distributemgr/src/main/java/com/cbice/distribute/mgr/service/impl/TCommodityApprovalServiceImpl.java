package com.cbice.distribute.mgr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsCommodityApproval;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.db.model.HsUserRights;
import com.cbice.distribute.core.service.HsCommodityApprovalDbService;
import com.cbice.distribute.core.service.HsCommodityDbservice;
import com.cbice.distribute.core.service.HsExchangeOrderDbService;
import com.cbice.distribute.core.service.HsUserRightsDbService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.service.TCommodityApprovalService;

public class TCommodityApprovalServiceImpl implements TCommodityApprovalService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	HsCommodityApprovalDbService hsCommodityApprovalDbService;
	@Resource
	HsExchangeOrderDbService hsExchangeOrderDbService;
	@Resource
	HsCommodityDbservice hsCommodityDbservice;
	@Resource
	HsUserRightsDbService hsUserRightsDbService;
	public void setHsCommodityApprovalDbService(
			HsCommodityApprovalDbService hsCommodityApprovalDbService) {
		this.hsCommodityApprovalDbService = hsCommodityApprovalDbService;
	}

	public void setHsExchangeOrderDbService(
			HsExchangeOrderDbService hsExchangeOrderDbService) {
		this.hsExchangeOrderDbService = hsExchangeOrderDbService;
	}

	public void setHsCommodityDbservice(HsCommodityDbservice hsCommodityDbservice) {
		this.hsCommodityDbservice = hsCommodityDbservice;
	}

	public void setHsUserRightsDbService(HsUserRightsDbService hsUserRightsDbService) {
		this.hsUserRightsDbService = hsUserRightsDbService;
	}

	@Override
	public List<Map<String, Object>> commodityApprovalQuery(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = hsCommodityApprovalDbService.commodityApprovalQuery(map);
		for(Map<String, Object> map1:list){
			map1.put("approval_time",DateUtils.DateToString((Date)map1.get("approval_time"), "yyyy-MM-dd hh:mm:ss"));
			if((Integer) map1.get("approval_result")==1){
				map1.put("approval_result", "成功");
			}else if((Integer) map1.get("approval_result")==2){
				map1.put("approval_result", "失败");
			}else{
				map1.put("approval_result", "其他");
			}
			
		}
		return list;
	}

	@Override
	public int countCommodityApprovalQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalDbService.countCommodityApprovalQuery(map);
	}
	
	@Transactional
	@Override
	public int doCommodityApproval(Map<String, Object> map) {
		// TODO Auto-generated method stub
		int a = 1;
		List<HsExchangeOrder> list = hsExchangeOrderDbService.queryExchangeOrder(map);
		if(list!=null&&list.size()!=0){
			a = parseList(list);
		}
		return a;
	}
	/**
	 * 商品审批处理
	 * @param list
	 * @return
	 */
	public int parseList(List<HsExchangeOrder> list){
		logger.info("今日商品审批处理开始");
		HsExchangeOrder hsExchangeOrder;
		HsExchangeOrder hsExchangeOrder1;
		HsExchangeOrder hsExchangeOrder2 = new HsExchangeOrder();
		HsExchangeOrder hsExchangeOrder3 = new HsExchangeOrder();
		HsCommodity hsCommodity1 = new HsCommodity();
		HsCommodity hsCommodity2 = new HsCommodity();
		HsCommodity hsCommodity3 = new HsCommodity();//修改赠品使用
		HsUserRights hsUserRights = new HsUserRights();
		HsCommodityApproval hsCommodityApproval = new HsCommodityApproval();
		HsCommodityApproval hsCommodityApproval2 = new HsCommodityApproval();
		
		int a = 1;
		long id;
		String sysId = null;
		long userId;
		long beExchangeId;
		long exchangeId;
		long exchangeNum;
		long availableNum;
		long userId1;
		long exchangeId1;
		long exchangeNum1;
		long availableNum1;
		long globalavailableNum = 0L;
		boolean availableNum_is_zero = false;
		
		Map<String, Object> map1;
		for(int i=0;i<list.size();i++){
			hsExchangeOrder = list.get(i);
			id = hsExchangeOrder.getId();
			sysId = hsExchangeOrder.getSysId();
			userId = hsExchangeOrder.getUserId();
			exchangeId = hsExchangeOrder.getExchangeId();
			beExchangeId = hsExchangeOrder.getBeExchangeId();
			exchangeNum = hsExchangeOrder.getExchangeNum();
			availableNum = hsExchangeOrder.getAvailableNum();
			
			//根据订单id和状态查询赠品数据，修改商品表赠品流通数量和冻结数量；
			map1 = new HashMap<String, Object>();
			map1.put("id", id);
			map1.put("sysId", sysId);
			map1.put("orderType", constantList.ORDER_TYPE_1);
			//修改订单状态为申请成功
			hsExchangeOrder2.setId(id);
			hsExchangeOrder2.setSysId(sysId);
			hsExchangeOrder2.setOrderState(constantList.ORDER_STATE_2);
			//修改订单状态为申请失败
			hsExchangeOrder3.setId(id);
			hsExchangeOrder3.setSysId(sysId);
			hsExchangeOrder3.setOrderState(constantList.ORDER_STATE_3);
			//修改商品表冻结金额
			hsCommodity1.setComId(hsExchangeOrder.getBeExchangeId());
			hsCommodity1.setSysId(sysId);
			hsCommodity1.setFreezeNum(hsExchangeOrder.getBeExchangeNum());
			//修改商品表流通数量和冻结金额
			hsCommodity2.setComId(hsExchangeOrder.getBeExchangeId());
			hsCommodity2.setSysId(sysId);
			hsCommodity2.setCurrencyNum(hsExchangeOrder.getBeExchangeNum());
			hsCommodity2.setFreezeNum(hsExchangeOrder.getBeExchangeNum());
			//修改用户权益表可用数量
			hsUserRights.setSysId(sysId);
			hsUserRights.setCustomerNum(hsExchangeOrder.getUserId());
			hsUserRights.setRightsCode(hsExchangeOrder.getExchangeId());
			hsUserRights.setAvailableNum(hsExchangeOrder.getExchangeNum());
			//新增商品审批成功结果
			hsCommodityApproval.setOrderId(id);
			hsCommodityApproval.setSysId(sysId);
			hsCommodityApproval.setCustomerId(userId);
			hsCommodityApproval.setExchangeId(beExchangeId);
			hsCommodityApproval.setExchangeUseId(exchangeId);
			hsCommodityApproval.setCustomerName(hsExchangeOrder.getUserName());
			hsCommodityApproval.setExchangeName(hsExchangeOrder.getBeExchangeName());
			hsCommodityApproval.setExchangeNum(hsExchangeOrder.getBeExchangeNum());
			hsCommodityApproval.setExchangeUseName(hsExchangeOrder.getExchangeName());
			hsCommodityApproval.setUseNum(hsExchangeOrder.getExchangeNum());
			hsCommodityApproval.setHoldNum(hsExchangeOrder.getAvailableNum());
			hsCommodityApproval.setApprovalResult(constantList.APPROVAL_RESULT_1);
			hsCommodityApproval.setRightsAccount(hsExchangeOrder.getRightsAccount());
			hsCommodityApproval.setProductCode(hsExchangeOrder.getExchangeId());
			hsCommodityApproval.setStockMarket("01");
			hsCommodityApproval.setNoCurrencyNum("0");
			hsCommodityApproval.setHandleClass("0");
			hsCommodityApproval.setApprovalTime(new Date());
			//新增商品审批失败结果
			hsCommodityApproval2.setOrderId(id);
			hsCommodityApproval2.setSysId(sysId);
			hsCommodityApproval2.setCustomerId(userId);
			hsCommodityApproval2.setExchangeId(beExchangeId);
			hsCommodityApproval2.setExchangeUseId(exchangeId);
			hsCommodityApproval2.setCustomerName(hsExchangeOrder.getUserName());
			hsCommodityApproval2.setExchangeName(hsExchangeOrder.getBeExchangeName());
			hsCommodityApproval2.setExchangeNum(hsExchangeOrder.getBeExchangeNum());
			hsCommodityApproval2.setExchangeUseName(hsExchangeOrder.getExchangeName());
			hsCommodityApproval2.setUseNum(hsExchangeOrder.getExchangeNum());
			hsCommodityApproval2.setHoldNum(hsExchangeOrder.getAvailableNum());
			hsCommodityApproval2.setApprovalResult(constantList.APPROVAL_RESULT_2);
			hsCommodityApproval2.setRightsAccount(hsExchangeOrder.getRightsAccount());
			hsCommodityApproval2.setProductCode(hsExchangeOrder.getExchangeId());
			hsCommodityApproval2.setStockMarket("01");
			hsCommodityApproval2.setNoCurrencyNum("0");
			hsCommodityApproval2.setHandleClass("0");
			hsCommodityApproval2.setApprovalTime(new Date());
			
//			System.out.println(hsExchangeOrder.getUserId()+"|"+hsExchangeOrder.getExchangeNum()+"|"+hsExchangeOrder.getExchangeName()+"|"+hsExchangeOrder.getRightsCode()+"|"+hsExchangeOrder.getAvailableNum());
			if(globalavailableNum==0){
				if(exchangeNum<availableNum){
					globalavailableNum = availableNum - exchangeNum;
					try {
						a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder2);
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
						return a;
					}
					try {
						a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity1);		//修改商品表字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
						return a;
					}
					try {
						a = hsUserRightsDbService.updateAvailableNum(hsUserRights);					//修改用户权益表
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsUserRightsDbService.updateAvailableNum修改用户权益表失败"+a);
						return a;
					}
					try {
						a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval);		//添加商品审批字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
						return a;
					}
					//修改赠品商品表信息
					List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
					if(list1!=null&&list1.size()>0){
						for(HsExchangeOrder hs:list1){
							hsCommodity3.setComId(hs.getGiftId());
							hsCommodity3.setSysId(sysId);
							hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
							a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
							if(a!=1){
								return a;
							}
						}
					}
				}else if(exchangeNum == availableNum){
					globalavailableNum = 1L;
					availableNum_is_zero = true;
					try {
						a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder2);
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
						return a;
					}
					try {
						a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity1);		//修改商品表字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
						return a;
					}
					try {
						a = hsUserRightsDbService.updateAvailableNum(hsUserRights);					//修改用户权益表
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsUserRightsDbService.updateAvailableNum修改用户权益表失败"+a);
						return a;
					}
					try {
						a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval);		//添加商品审批字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
						return a;
					}
					//修改赠品商品表信息
					List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
					if(list1!=null&&list1.size()>0){
						for(HsExchangeOrder hs:list1){
							hsCommodity3.setComId(hs.getGiftId());
							hsCommodity3.setSysId(sysId);
							hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
							a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
							if(a!=1){
								return a;
							}
						}
					}
				}else{
					try {
						a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder3);	//修改订单状态为失败
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
						return a;
					}
					try {
						a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity2);		//修改商品表字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
						return a;
					}
					try {
						a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval2);		//添加商品审批字段
					} catch (Exception e) {
						// TODO: handle exception
						logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
						return a;
					}
					//修改赠品商品表信息(订单失败时)
					List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
					if(list1!=null&&list1.size()>0){
						for(HsExchangeOrder hs:list1){
							hsCommodity3.setComId(hs.getGiftId());
							hsCommodity3.setSysId(sysId);
							hsCommodity3.setCurrencyNum(hs.getGiftNum()+0L);
							hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
							a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
							if(a!=1){
								return a;
							}
						}
					}
				}
			}else{
				hsExchangeOrder1 = list.get(i-1);
				userId1 = hsExchangeOrder1.getUserId();
				exchangeId1 = hsExchangeOrder1.getExchangeId();
				exchangeNum1 = hsExchangeOrder1.getExchangeNum();
				availableNum1 = hsExchangeOrder1.getAvailableNum();
				if(userId==userId1){
					if(exchangeId==exchangeId1){
						if(exchangeNum<globalavailableNum&&!availableNum_is_zero){
							globalavailableNum = globalavailableNum - exchangeNum1;
							try {
								a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder2);
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
								return a;
							}
							try {
								a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity1);		//修改商品表字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
								return a;
							}
							try {
								a = hsUserRightsDbService.updateAvailableNum(hsUserRights);					//修改用户权益表
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsUserRightsDbService.updateAvailableNum修改用户权益表失败"+a);
								return a;
							}
							try {
								a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval);		//添加商品审批字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
								return a;
							}
							//修改赠品商品表信息
							List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
							if(list1!=null&&list1.size()>0){
								for(HsExchangeOrder hs:list1){
									hsCommodity3.setComId(hs.getGiftId());
									hsCommodity3.setSysId(sysId);
									hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
									a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
									if(a!=1){
										return a;
									}
								}
							}
						}else if(exchangeNum==globalavailableNum&&!availableNum_is_zero){
							globalavailableNum = 1L;
							availableNum_is_zero = true;
							try {
								a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder2);
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
								return a;
							}
							try {
								a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity1);		//修改商品表字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
								return a;
							}
							try {
								a = hsUserRightsDbService.updateAvailableNum(hsUserRights);					//修改用户权益表
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsUserRightsDbService.updateAvailableNum修改用户权益表失败"+a);
								return a;
							}
							try {
								a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval);		//添加商品审批字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
								return a;
							}
							//修改赠品商品表信息
							List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
							if(list1!=null&&list1.size()>0){
								for(HsExchangeOrder hs:list1){
									hsCommodity3.setComId(hs.getGiftId());
									hsCommodity3.setSysId(sysId);
									hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
									a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
									if(a!=1){
										return a;
									}
								}
							}
						}else{
							try {
								a = hsExchangeOrderDbService.updateByPrimaryKeySelective(hsExchangeOrder3);	//修改订单状态为失败
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsExchangeOrderDbService.updateByPrimaryKeySelective修改兑换订单表订单状态为成功失败"+a);
								return a;
							}
							try {
								a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity2);		//修改商品表字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityDbservice.updateCurrencyNumAndFreezeNum修改商品表字段流通数量or冻结数量失败"+a);
								return a;
							}
							try {
								a = hsCommodityApprovalDbService.insertSelective(hsCommodityApproval2);		//添加商品审批字段
							} catch (Exception e) {
								// TODO: handle exception
								logger.error("hsCommodityApprovalDbService.insertSelective添加商品审批字段失败"+a);
								return a;
							}
							//修改赠品商品表信息(订单失败时)
							List<HsExchangeOrder> list1 = hsExchangeOrderDbService.queryExchangeOrderAndGift(map1);
							if(list1!=null&&list1.size()>0){
								for(HsExchangeOrder hs:list1){
									hsCommodity3.setComId(hs.getGiftId());
									hsCommodity3.setSysId(sysId);
									hsCommodity3.setCurrencyNum(hs.getGiftNum()+0L);
									hsCommodity3.setFreezeNum(hs.getGiftNum()+0L);
									a = hsCommodityDbservice.updateCurrencyNumAndFreezeNum(hsCommodity3);		//修改商品表字段
									if(a!=1){
										return a;
									}
								}
							}
						}
					}else{
						globalavailableNum=0L;
						i=i-1;
					}
				}else{
					globalavailableNum=0L;
					i=i-1;
				}
			}
		}
		
		logger.info("今日商品审批处理完成");
		return a;
	}

	@Override
	public List<HsCommodityApproval> queryApprovalSuccess(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityApprovalDbService.queryApprovalSuccess(map);
	}

	@Override
	public List<HsCommodityApproval> queryApprovalSuccessEx(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<HsCommodityApproval> list = hsCommodityApprovalDbService.queryApprovalSuccessEx(map);
		String gifts;
		String giftss[];
		for(HsCommodityApproval hsCommodityApproval:list){
			gifts = hsCommodityApproval.getGifts();
			giftss = gifts.split(":");
			if(giftss.length==0){
				hsCommodityApproval.setGifts("");
			}
		}
		return list;
	}

}
