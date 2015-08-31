package com.cbice.distribute.agency.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.agency.service.TremainderGoodsService;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TGoodsOrderService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.service.VUserAgencyDbService;

public class TremainderGoodsServiceImpl implements TremainderGoodsService{
	private TRemainderGoodsService tRemainderGoodsService;
	
	private  TGoodsOrderService tGoodsOrderService;
	
	private SeqService seqService;
	
	
	private TUserService tUserService;
	
	@Resource
	private VUserAgencyDbService vUserAgencyDbService;
	
	
	
	public void setvUserAgencyDbService(VUserAgencyDbService vUserAgencyDbService) {
		this.vUserAgencyDbService = vUserAgencyDbService;
	}

	public void settRemainderGoodsService(TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
	}
	
	public void settGoodsOrderService(TGoodsOrderService tGoodsOrderService) {
		this.tGoodsOrderService = tGoodsOrderService;
	}

	public void settUserService(TUserService tUserService) {
		this.tUserService = tUserService;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	@Override
	public List<TRemainderGoods> selectbuyUserid(Map<String, Object> map) {
		return tRemainderGoodsService.selectbuyUserid(map);
	}
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(String t_goods_num ,Long user_id, 
			Long goods_num, String returnGoodsReason,String goods_name) {
		int rec = 0;
		try{
			if (user_id != null ) {
				
				//1.对本级的产品剩余表进行操作
				rec = tRemainderGoodsService.lockedGoods(user_id,goods_num,t_goods_num);
				if (rec < 0) {
					System.out.println("更新本级剩余库存表失败！");
					return rec;
				}
				
				//2.对产品订单表进行操作
				TGoodsOrder tGoodsOrder = new TGoodsOrder();
				Long tGoodsOrderId = seqService.getTGoodsOrder();
				if(tGoodsOrderId == null){
					System.out.println("更新订单流水失败！");
					return 0;
				}
				tGoodsOrder.setId(tGoodsOrderId);//主键自动生成
				if(StringUtils.isNotEmpty(returnGoodsReason)){
					tGoodsOrder.setReturnGoodsReason(returnGoodsReason);//将退货原因存入
				}
				tGoodsOrder.setOrderIdenty(1);//状态改为 1退货
				tGoodsOrder.setReturnGoodsState(0);//退货申请审核状态 0待审核
				tGoodsOrder.setIssuedDate(new Date());//操作时间进行更新
				tGoodsOrder.setUserId(user_id);//本级用户ID
				tGoodsOrder.setGoodsName(goods_name);//产品名称
				
				VUserAgency agency = vUserAgencyDbService.selectById(user_id);
				if(agency != null){
					tGoodsOrder.setAgencyId(agency.getAgencyId());//上级ID
				}else{
					tGoodsOrder.setAgencyId(user_id);//上级ID
				}
				tGoodsOrder.setGoodsNum(t_goods_num);//产品标识
				tGoodsOrder.setDistributeNum(goods_num);//剩余产品数量
				rec = tGoodsOrderService.insertSelective(tGoodsOrder);
				if (rec < 0) {
					System.out.println("更新库存订单表失败！");
					return rec;
				}
			}
		}catch(Exception e) {
			throw new RuntimeException("申请失败"); 
		}
		return rec;
	}

	@Override
	public TRemainderGoods selectbuyUseridAndGoodsid(Map<String, Object> map) {
		return tRemainderGoodsService.selectbuyUseridAndGoodsid(map);
	}

}
