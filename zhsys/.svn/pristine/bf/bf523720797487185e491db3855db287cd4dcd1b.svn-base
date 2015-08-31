package com.cbice.distribute.agency.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.data.TUserMapper;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TConfig;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.TConfigDbService;
import com.cbice.distribute.core.service.TGoodsBatchService;
import com.cbice.distribute.core.service.TGoodsOrderService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.SequenceCreaterUtil;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.agency.service.TgoodsOrderService;

public class TgoodsOrderServiceImpl implements TgoodsOrderService {
	private TGoodsOrderService tGoodsOrderService;

	private TGoodsBatchService tGoodsBatchService;

	private SeqService seqService;

	@Resource
	private TUserMapper tUserMapper;

	@Resource
	private TAgencyDbService tAgencyDbService;
	@Resource
   	private VUserAgencyDbService vUserAgencyDbService;
	
	private TConfigDbService tConfigDbService;

	public void settConfigDbService(TConfigDbService tConfigDbService) {
		this.tConfigDbService = tConfigDbService;
	}
	
	
	public void setvUserAgencyDbService(VUserAgencyDbService vUserAgencyDbService) {
		this.vUserAgencyDbService = vUserAgencyDbService;
	}

	public void settAgencyDbService(TAgencyDbService tAgencyDbService) {
		this.tAgencyDbService = tAgencyDbService;
	}

	private TRemainderGoodsService tRemainderGoodsService;

	public void settRemainderGoodsService(
			TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
	}

	public void settUserMapper(TUserMapper tUserMapper) {
		this.tUserMapper = tUserMapper;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	public void settGoodsBatchService(TGoodsBatchService tGoodsBatchService) {
		this.tGoodsBatchService = tGoodsBatchService;
	}

	public void settGoodsOrderService(TGoodsOrderService tGoodsOrderService) {
		this.tGoodsOrderService = tGoodsOrderService;
	}

	@Override
	public List<Map<String, Object>> selectGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.selectGoodsOrderBy(map);
	}

	@Override
	public int countSelectOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.countSelectOrderBy(map);
	}

	@Transactional
	@Override
	public int insertSelective(TGoodsOrder record) {
		return tGoodsOrderService.insertSelective(record);
	}

	@Transactional
	@Override
	public int insertListSelective(List<TGoodsOrder> list) {

		int relust = 0;

		try {
			String goodsname = "";
			String goodsnum = "";
			long agencyId = constantList.IceId;
			long batchid = seqService.getTGoodsBatch();
			long goodsCount = 0l;
			long userid = 0l;
			for (TGoodsOrder goodsorder : list) {
				long tgoodsOrderid = seqService.getTGoodsOrder();
   			 	//增加订单流水号
				goodsorder.setOrderSerial(SequenceCreaterUtil.createSerial(Long.toString(tgoodsOrderid)));
				goodsorder.setId(tgoodsOrderid);
				goodsname = goodsorder.getGoodsName();
				goodsnum = goodsorder.getGoodsNum();
				agencyId = goodsorder.getAgencyId();
				userid = goodsorder.getUserId();
				goodsorder.setBatchId(batchid);
				TConfig tconfig=tConfigDbService.selectByPrimaryKey("order.payRate");//获取码表订单费率问题
    			String payRate=tconfig.getValue();
    			Double rate=1d-Double.parseDouble(payRate);//1减费率
    		   long  biuessMoney=this.div(goodsorder.getBusinessMoney(), rate, 2);
    			goodsorder.setBusinessMoney(biuessMoney);
				tGoodsOrderService.insertSelective(goodsorder);
				goodsCount = goodsCount + goodsorder.getDistributeNum();
			}

			// tGoodsOrderService.s
			tRemainderGoodsService.lockedGoods(agencyId, goodsCount, goodsnum);
			TGoodsBatch batch = new TGoodsBatch();

			batch.setId(batchid);
			batch.setGoodsName(goodsname);
			batch.setGoodsNum(goodsnum);
			batch.settUserId(agencyId);
			batch.setStates(1);
			batch.setCreatTime(new Date());
			VUserAgency agency = vUserAgencyDbService.selectById(agencyId);
			batch.settHigerUserId(agency.getAgencyId());
			tGoodsBatchService.insertSelective(batch);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", agencyId);
			map.put("goodsName", goodsname);
			map.put("goodsNum", goodsnum);
			List<TGoodsOrder> orderList = tGoodsOrderService.selectOrderbyUserIdgoodsNameGoodsNum(map);
			for(TGoodsOrder order:orderList){
				order.setOrderState(5);//订单状态改成5
				tGoodsOrderService.updateByPrimaryKeySelective(order);
			}
			relust = 1;
		} catch (Exception e) {
			throw new RuntimeException("发货信息入库失败");
		}
		return relust;
	}

	@Override
	public List<Map<String, Object>> selectReturnGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsList(map);
	}

	@Override
	public int countSelectReturnOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.countSelectOrderBy(map);
	}

	@Override
	public String selectDealerNameById(Long agencyId) {
		return tGoodsOrderService.selectDealerNameById(agencyId);
	}

	@Override
	public String selectUserNameById(Long userId) {
		return tGoodsOrderService.selectUserNameById(userId);
	}

	@Override
	public Long selectUseridentyById(Long userId) {
		return tGoodsOrderService.selectUseridentyById(userId);
	}

	@Override
	public List<TGoodsOrder> selectGoodsBatchById(Long batchId) {
		return tGoodsOrderService.selectGoodsBatchById(batchId);
	}

	@Override
	public List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.selectHistoryGoodsOrderBy(map);
	}

	@Override
	public int countSelectHistoryGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.countSelectHistoryGoodsOrderBy(map);
	}

	// 查询待发货商品信息
	@Override
	public List<TGoodsOrder> selectToSendBuyUserid(Map<String, Object> paramMap) {
		return tGoodsOrderService.selectToSendBuyUserid(paramMap);
	}

	@Override
	public int countSelectToSendBuyUserid(Map<String, Object> paramMap) {
		return tGoodsOrderService.countSelectToSendBuyUserid(paramMap);
	}

	@Override
	public List<TGoodsOrder> outSaleCode(Map<String, Object> map) {
		return tGoodsOrderService.outSaleCode(map);
	}

	public TGoodsOrder selectTGoodsOrderById(Long id) {
		// TODO Auto-generated method stub
		return tGoodsOrderService.selectTGoodsOrderById(id);
	}

	@Transactional
	@Override
	public int agreeOrNotReturnGoodsOrder(Long id, Integer returnGoodsState,
			String goodsReason, Long user_id, Long agency_id,
			Long distributeNum, String goodsNum) {
		//从库中取出订单记录
		TGoodsOrder tGoodsOrder = tGoodsOrderService.selectByPrimaryKey(id);
		//从库中取出用户信息
		VUserAgency vUserAgency=vUserAgencyDbService.selectById(tGoodsOrder.getUserId());
		// 将订单退货审核状态 改为审核通过return_goods_state=1 审核不通过return_goods_state=2
		int rec = 0;
		//'0'会员      '1'经销商
				if(vUserAgency.getUserIdenty()==0){
					//查看订单状态     1'已支付'   0 '未支付'
					if(tGoodsOrder.getPayState()==1){
						try {
							if (id != null ) {
								tGoodsOrder.setId(id);
								tGoodsOrder.setReturnGoodsState(returnGoodsState);
									if(StringUtils.isNotEmpty(goodsReason)){
										tGoodsOrder.setReturnGoodsOptions(goodsReason);
									}
									tGoodsOrder.setIssuedDate(new Date());
									rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
									if (rec == 0) {
										throw new RuntimeException("退货审核失败！");
									}
									if(returnGoodsState==1){
										//如果审核通过，将上一级的库存剩余数量增加
										rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getAgencyId(), distributeNum, goodsNum);
									}else if(returnGoodsState==2){
										//如果审核不通过，将本级的库存剩余数量增加
										rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getUserId(), distributeNum, goodsNum);
									}
							}
						} catch (Exception e) {
							throw new RuntimeException("审核失败"); 
						}
					}
					else{
						try {
							if (id != null ) {
								tGoodsOrder.setId(id);
								tGoodsOrder.setReturnGoodsState(returnGoodsState);
									if(StringUtils.isNotEmpty(goodsReason)){
										tGoodsOrder.setReturnGoodsOptions(goodsReason);
									}
									tGoodsOrder.setIssuedDate(new Date());
									rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
									if (rec == 0) {
										throw new RuntimeException("退货审核失败！");
									}
									if(returnGoodsState==1){
										//只用解除锁定
										rec =tRemainderGoodsService.unlockGoods(tGoodsOrder.getAgencyId(), tGoodsOrder.getAgencyId(), tGoodsOrder.getDistributeNum(), tGoodsOrder.getGoodsNum());
									}else if(returnGoodsState==2){
										rec=1;
									}
							}
						} catch (Exception e) {
							throw new RuntimeException("审核失败"); 
						}
					}
				}
				//经销商
				else{
					try {
						if (id != null ) {
							tGoodsOrder.setId(id);
							tGoodsOrder.setReturnGoodsState(returnGoodsState);
								if(StringUtils.isNotEmpty(goodsReason)){
									tGoodsOrder.setReturnGoodsOptions(goodsReason);
								}
								tGoodsOrder.setIssuedDate(new Date());
								rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
								if (rec == 0) {
									throw new RuntimeException("退货审核失败！");
								}
								if(returnGoodsState==1){
									//如果审核通过，将上一级的库存剩余数量增加
									rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getAgencyId(), distributeNum, goodsNum);
								}else if(returnGoodsState==2){
									//如果审核不通过，将本级的库存剩余数量增加
									rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getUserId(), distributeNum, goodsNum);
								}
						}
					} catch (Exception e) {
						throw new RuntimeException("审核失败"); 
					}
				}
				return rec;
	}

	@Override
	public List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderService.selectGoodsOrderByBatchid(batchId);
	}

	@Override
	public List<TGoodsOrder> selectOrderbyUserIdgoodsNameGoodsNum(
			Map<String, Object> map) {
		return tGoodsOrderService.selectOrderbyUserIdgoodsNameGoodsNum(map);
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public int cancelGoodsOrder(String id) {
		int relust=0;
		//找到要撤销的订单
		TGoodsOrder goodsOrder = selectTGoodsOrderById(Long.parseLong(id));
    	try{
    		//审核不通过删除订单不做其他处理
    		if(goodsOrder!=null){
    			if(goodsOrder.getMemberReturnGoods()!=null &&goodsOrder.getMemberReturnGoods()==0){
    				throw new RuntimeException("已经提出退货申请不能撤货"); 
    			}
    			//撤销产品操作
    			//1.将订单的撤货状态改为已撤销
//    			goodsOrder.setWithdrawGoodsState(1);//已撤销
//    			int i = tGoodsOrderService.updateByPrimaryKeySelective(goodsOrder);
//    			if(i<1){
//    				throw new RuntimeException("更新订单撤销状态失败！");
//    			}
    			//2.分到货的代理商或者会员减库存，上层经销商加库存
    			Long upId = goodsOrder.getAgencyId();//上级代理商id
    			Long lowId = goodsOrder.getUserId();//下级代理商id
    			String goodsNo = goodsOrder.getGoodsNum();//产品编号
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("userId", upId);
    			map.put("goodsId", goodsNo);
    			map.put("goodsNum", goodsOrder.getDistributeNum());
    			//i=tRemainderGoodsService.insertOrUpdate(upId, goodsOrder.getDistributeNum(), goodsNo);
    			int i =tRemainderGoodsService.unlockGoods(upId, upId, goodsOrder.getDistributeNum(), goodsNo);
    			if(i<1){
    				throw new RuntimeException("更新上级代理商产品数量失败！");
    			}
    			
//    			map.put("userId", lowId);
//    			map.put("goodsId", goodsNo);
//    			map.put("goodsNum", -goodsOrder.getDistributeNum());
//    			i=tRemainderGoodsService.deleteOrUpdae(lowId, goodsOrder.getDistributeNum(), goodsNo);
    			
//    			if(i<1){
//    				throw new RuntimeException("更新下级代理商产品数量失败！");
//    			}
    			 //删除订单
    				 i =tGoodsOrderService.deleteByPrimaryKey(Long.parseLong(id));
    				if(i<1){
        				throw new RuntimeException("更新订单撤销状态失败！");
    			}
    			relust = 1;
    			}
    			
    	}catch(Exception e) {
    		throw new RuntimeException("撤货失败"); 
    	}finally{
    		return relust;
    	}
	}
	
	@Override
	public List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderService.selectReturnGoodsOrderByBatchid(batchId);
	}
	//更新t_goods_order的订单状态并且增加库存数量
	@Transactional
	@Override
	public int updateOrderStateById(int id) {
		//状态---->成功领取
		int result=0;
		try {
			TGoodsOrder goodsOrder=new TGoodsOrder();
			goodsOrder.setId((long)id);
			goodsOrder.setOrderState(3);
			int result1=tGoodsOrderService.updateByPrimaryKeySelective(goodsOrder);
			if(result1<1){
				throw new RuntimeException("更新订单状态失败！");
			}
			TGoodsOrder tGooderOrder=tGoodsOrderService.selectByPrimaryKey((long)id);
			//发货时要判断'是否领取成功'，领取操作是操作
			int i=tRemainderGoodsService.unlockGoods(tGooderOrder.getAgencyId(),tGooderOrder.getUserId(), tGooderOrder.getDistributeNum(), tGooderOrder.getGoodsNum());
			if(i!=1){
				throw new RuntimeException("更新订单产品数量失败！");
			}
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("领取产品失败");
		}
		
		return result;
	}

	@Override
	public List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsHis(map);
	}

	@Override
	public int selectReturnGoodsHisCount(Map<String, Object> paramMap) {
		return tGoodsOrderService.selectReturnGoodsHisCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectReturnGoodsList(
			Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsList(map);
	}

	@Override
	public int selectReturnGoodsListCount(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsListCount(map);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(Long id, Integer returnGoodsState,String returnGoodsOptions,
			Long agency_id,Long higer_dealer_id,Long distributeNum,String goodsNum) {
		TGoodsOrder tGoodsOrder = tGoodsOrderService.selectByPrimaryKey(id);
		int rec = 0;
		//判断是不是会员
		VUserAgency vUserAgency=vUserAgencyDbService.selectById(tGoodsOrder.getUserId());
		//'0'会员      '1'经销商
		if(vUserAgency.getUserIdenty()==0){
			//查看订单状态     1'已支付'   0 '未支付'
			if(tGoodsOrder.getPayState()==1){
				try {
					if (id != null ) {
						tGoodsOrder.setId(id);
						tGoodsOrder.setReturnGoodsState(returnGoodsState);
							if(StringUtils.isNotEmpty(returnGoodsOptions)){
								tGoodsOrder.setReturnGoodsOptions(returnGoodsOptions);
							}
							tGoodsOrder.setIssuedDate(new Date());
							rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
							if (rec == 0) {
								throw new RuntimeException("退货审核失败！");
							}
							if(returnGoodsState==1){
								//如果审核通过，将上一级的库存剩余数量增加
								rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getAgencyId(), distributeNum, goodsNum);
							}else if(returnGoodsState==2){
								//如果审核不通过，将本级的库存剩余数量增加
								rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getUserId(), distributeNum, goodsNum);
							}
					}
				} catch (Exception e) {
					throw new RuntimeException("审核失败"); 
				}
			}
			else{
				try {
					if (id != null ) {
						tGoodsOrder.setId(id);
						tGoodsOrder.setReturnGoodsState(returnGoodsState);
							if(StringUtils.isNotEmpty(returnGoodsOptions)){
								tGoodsOrder.setReturnGoodsOptions(returnGoodsOptions);
							}
							tGoodsOrder.setIssuedDate(new Date());
							rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
							if (rec == 0) {
								throw new RuntimeException("退货审核失败！");
							}
							if(returnGoodsState==1){
								//只用解除锁定
								rec =tRemainderGoodsService.unlockGoods(tGoodsOrder.getAgencyId(), tGoodsOrder.getAgencyId(), tGoodsOrder.getDistributeNum(), tGoodsOrder.getGoodsNum());
							}else if(returnGoodsState==2){
								rec=1;
							}
					}
				} catch (Exception e) {
					throw new RuntimeException("审核失败"); 
				}
			}
		}
		//经销商
		else{
			try {
				if (id != null ) {
					tGoodsOrder.setId(id);
					tGoodsOrder.setReturnGoodsState(returnGoodsState);
						if(StringUtils.isNotEmpty(returnGoodsOptions)){
							tGoodsOrder.setReturnGoodsOptions(returnGoodsOptions);
						}
						tGoodsOrder.setIssuedDate(new Date());
						rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
						if (rec == 0) {
							throw new RuntimeException("退货审核失败！");
						}
						if(returnGoodsState==1){
							//如果审核通过，将上一级的库存剩余数量增加
							rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getAgencyId(), distributeNum, goodsNum);
						}else if(returnGoodsState==2){
							//如果审核不通过，将本级的库存剩余数量增加
							rec = tRemainderGoodsService.unlockGoods(tGoodsOrder.getUserId(), tGoodsOrder.getUserId(), distributeNum, goodsNum);
						}
				}
			} catch (Exception e) {
				throw new RuntimeException("审核失败"); 
			}
		}
		return rec;
	}
	
    /**
     * 
     * @param v1被除数
     * @param v2除数
     * @param scale 精确到小数点后scale位
     * @return
     */
    public   static   long   div(long   v1,double   v2,int   scale){   
        if(scale<0){   
                throw   new   IllegalArgumentException(   
                        "The   scale   must   be   a   positive   integer   or   zero");   
        }   
        BigDecimal   b1   =   new   BigDecimal(Long.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));   
        return   b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).longValue();  
}


	@Override
	public List<TGoodsOrder> TranctionOut(Map<String, Object> paramMap) {
		return tGoodsOrderService.TranctionOut(paramMap);
	} 
}