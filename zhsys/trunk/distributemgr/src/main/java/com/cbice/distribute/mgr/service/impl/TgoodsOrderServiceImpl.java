package com.cbice.distribute.mgr.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TConfig;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TConfigDbService;
import com.cbice.distribute.core.service.TGoodsBatchService;
import com.cbice.distribute.core.service.TGoodsOrderService;
import com.cbice.distribute.core.service.TGoodsService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.SequenceCreaterUtil;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.service.TgoodsOrderService;

public class TgoodsOrderServiceImpl implements TgoodsOrderService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private  TGoodsOrderService tGoodsOrderService;
	
	private TGoodsBatchService tGoodsBatchService;
	
	private SeqService seqService;
	
	private VUserAgencyDbService vUserAgencyDbService;
	
	private TRemainderGoodsService tRemainderGoodsService;
	
	private TGoodsService tGoodsService;
	
	private TConfigDbService tConfigDbService;

	public void settConfigDbService(TConfigDbService tConfigDbService) {
		this.tConfigDbService = tConfigDbService;
	}

	public void setvUserAgencyDbService(VUserAgencyDbService vUserAgencyDbService) {
		this.vUserAgencyDbService = vUserAgencyDbService;
	}

	public void settGoodsService(TGoodsService tGoodsService) {
		this.tGoodsService = tGoodsService;
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

	public void settRemainderGoodsService(
			TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
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
		
    	int relust=0;
        
    	try{
    		String goodsname="";
    		String goodsnum="";
    		long agencyId=constantList.IceId;
    		long batchid= seqService.getTGoodsBatch();
    		for(TGoodsOrder goodsorder:list){
    			long tgoodsOrderid=seqService.getTGoodsOrder();
    			//增加订单流水号
				goodsorder.setOrderSerial(SequenceCreaterUtil.createSerial(Long.toString(tgoodsOrderid)));
    			goodsorder.setId(tgoodsOrderid);	
    			goodsname=goodsorder.getGoodsName();
    			goodsnum=goodsorder.getGoodsNum();
    			agencyId=goodsorder.getAgencyId();
    			goodsorder.setBatchId(batchid);
    			TConfig tconfig=tConfigDbService.selectByPrimaryKey("order.payRate");//获取码表订单费率问题
    			String payRate=tconfig.getValue();
    			Double rate=1d-Double.parseDouble(payRate);//1减费率
    		   long  biuessMoney=this.div(goodsorder.getBusinessMoney(), rate, 2);
    			goodsorder.setBusinessMoney(biuessMoney);
    			tGoodsOrderService.insertSelective(goodsorder);
    			//导入excel时不操作，发货时要判断'是否领取成功'，领取操作是操作
    			/*int i=tRemainderGoodsService.deleteOrUpdae(agencyId, goodsorder.getDistributeNum(), goodsnum);
    			tRemainderGoodsService.insertOrUpdate(goodsorder.getUserId(), goodsorder.getDistributeNum(), goodsnum);*/
	    		/*if(i!=1){
	    			return relust;
	    		}*/
    			tRemainderGoodsService.lockedGoods(agencyId, goodsorder.getDistributeNum(), goodsnum);
    		}
        	TGoodsBatch   batch=new TGoodsBatch();
        	batch.setId(batchid);
        	batch.setGoodsName(goodsname);
        	batch.setGoodsNum(goodsnum);
        	batch.settUserId(agencyId);
        	batch.setStates(1);
        	batch.setCreatTime(new Date());
        	tGoodsBatchService.insertSelective(batch);
        	relust=1;
    	}catch(Exception e) {
    		throw new RuntimeException("发货信息入库失败"); 
    	}
		return relust;
	}

	@Override
	public List<Map<String, Object>> selectReturnGoodsList(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsList(map);
	}

	@Override
	public int selectReturnGoodsListCount(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsListCount(map);
	}
	
	public String selectDealerNameById(Long agencyId) {
		return tGoodsOrderService.selectDealerNameById(agencyId);
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
	public TGoodsOrder selectTGoodsOrderById(Long id) {
		return tGoodsOrderService.selectTGoodsOrderById(id);
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public int cancelGoodsOrder(String id) {
		int result=0;
		//找到要撤销的订单
		TGoodsOrder goodsOrder = selectTGoodsOrderById(Long.parseLong(id));
    	try{
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
    			//i = tRemainderGoodsService.updateNumByUserAndGoods(map);
    			//i=tRemainderGoodsService.insertOrUpdate(upId, goodsOrder.getDistributeNum(), goodsNo);
    			int  i =tRemainderGoodsService.unlockGoods(upId, upId, goodsOrder.getDistributeNum(), goodsNo);
    			if(i<1){
    				throw new RuntimeException("更新上级代理商产品数量失败！");
    			}
//    			map.put("userId", lowId);
//    			map.put("goodsId", goodsNo);
//    			map.put("goodsNum", -goodsOrder.getDistributeNum());
//    			i=tRemainderGoodsService.deleteOrUpdae(lowId, goodsOrder.getDistributeNum(), goodsNo);
//    			//i = tRemainderGoodsService.updateNumByUserAndGoods(map);
//    			if(i<1){
//    				throw new RuntimeException("更新下级代理商产品数量失败！");
//    			}

    			//删除订单
   				 i =tGoodsOrderService.deleteByPrimaryKey(Long.parseLong(id));
   				if(i<1){
       				throw new RuntimeException("更新订单撤销状态失败！");
       			}
   			
    			result = 1;
    			}
    	}catch(Exception e) {
    		throw new RuntimeException("撤货失败"); 
    	}finally{
    		return result;
    	}
	}
	
	@Override
	public List<TGoodsOrder> selectHistoryGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.selectHistoryGoodsOrderBy(map);
	}
	@Override
	public List<TGoodsOrder> outSaleCode(Map<String, Object> map) {
		return tGoodsOrderService.outSaleCode(map);
	}

	@Override
	public int agreeReturnGoodsOrder(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countSelectHistoryGoodsOrderBy(Map<String, Object> map) {
		return tGoodsOrderService.countSelectHistoryGoodsOrderBy(map);
	}

	@Override
	public List<TGoodsOrder> selectGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderService.selectGoodsOrderByBatchid(batchId);
	}
	
	@Override
	public Long selectUseridentyById(Long userId) {
		return tGoodsOrderService.selectUseridentyById(userId);
	}
	
	@Override
	public List<TGoodsOrder> selectReturnGoodsOrderByBatchid(long batchId) {
		return tGoodsOrderService.selectReturnGoodsOrderByBatchid(batchId);
	}

	@Override
	public int updateByPrimaryKeyVIP(Long id, Integer returnGoodsState,
			String returnOrderOptions, Long agency_id, Long higer_dealer_id,
			Long distributeNum, String goodsNum) {
		int rec = 0;
		try {
			if (id != null ) {
				TGoodsOrder tGoodsOrder = new TGoodsOrder();
				tGoodsOrder.setId(id);
				tGoodsOrder.setReturnGoodsState(returnGoodsState);
					if(StringUtils.isNotEmpty(returnOrderOptions)){
						tGoodsOrder.setReturnGoodsOptions(returnOrderOptions);
					}
					tGoodsOrder.setIssuedDate(new Date());
	
					rec = tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
					
					if (rec == 0) {
						throw new RuntimeException("退货审核失败！");
					}
					if(returnGoodsState==1){
						//如果审核通过，将上一级的库存剩余数量增加
						//rec = tRemainderGoodsService.unlockGoods(agency_id, higer_dealer_id, distributeNum, goodsNum);
						//所属经销商库存增加
						tRemainderGoodsService.insertOrUpdate(agency_id, distributeNum, goodsNum);
						//会员对应的sales_code 的库存为"0"
						//tRemainderGoodsService.deleteOrUpdae(higer_dealer_id, distributeNum, goodsNum);
					}else if(returnGoodsState==2){
						//如果审核不通过，将本级的库存剩余数量增加
						rec = 1;
					}

			}
		} catch (Exception e) {
			throw new RuntimeException("审核失败"); 
		}
		return rec;
	}

	@Override
	public List<TGoodsOrder> selectReturnGoodsHis(Map<String, Object> map) {
		return tGoodsOrderService.selectReturnGoodsHis(map);
	}

	@Override
	public int selectReturnGoodsHisCount(Map<String, Object> paramMap) {
		return tGoodsOrderService.selectReturnGoodsHisCount(paramMap);
	}

    public   static   long   mul2(long   v1,Double   v2){   
        BigDecimal   b1   =   new   BigDecimal(Long.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Double.toString(v2));   
        return   b1.multiply(b2).longValue();   
    
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