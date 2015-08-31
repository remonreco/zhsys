package com.cbice.distribute.buyer.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.buyer.service.TgoodsOrderService;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TGoodsOrderService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.VUserAgencyDbService;

public class TgoodsOrderServiceImpl implements TgoodsOrderService {

	private TGoodsOrderService tGoodsOrderService;
	
	private TRemainderGoodsService tRemainderGoodsService;
	
	private SeqService seqService;
	
	private VUserAgencyDbService vUserAgencyDbService;
	

	public void setvUserAgencyDbService(VUserAgencyDbService vUserAgencyDbService) {
		this.vUserAgencyDbService = vUserAgencyDbService;
	}

	public void settRemainderGoodsService(
			TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	public void settGoodsOrderService(TGoodsOrderService tGoodsOrderService) {
		this.tGoodsOrderService = tGoodsOrderService;
	}

	@Override
	public List<TGoodsOrder> selectUserMemberbyOrder(Map<String, Object> map) {
		return tGoodsOrderService.selectUserMemberbyOrder(map);
	}
	
	/**
	 * 查询历史购买记录
	 */
	@Override
	public List<TGoodsOrder> selectHistoryGoodsOrder(Map<String, Object> map) {
		return tGoodsOrderService.selectHistoryGoodsOrder(map);
	}

	@Override
	public TGoodsOrder selectByPrimaryKey(long id) {
		// TODO Auto-generated method stub
		return tGoodsOrderService.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TGoodsOrder tGoodsOrder) {
		// TODO Auto-generated method stub
		return tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrder);
	}

	@Transactional
	@Override
	public int fromASingle(long id,long user_id,String t_goods_num,String goods_num, String returnGoodsReason) {
		int result=0;
		//先拿到t_goods_order id对应的记录      克隆一条数据
		TGoodsOrder tGoodsOrder=tGoodsOrderService.selectByPrimaryKey(id);
		//更新克隆数据的Id
		tGoodsOrder.setId(seqService.getTGoodsOrder());
		//更新退货审核状态
		tGoodsOrder.setReturnGoodsState(0);//申请
		//更新退货原因
		tGoodsOrder.setReturnGoodsReason(returnGoodsReason);
		//更新退货数量
		tGoodsOrder.setReturnGoodsNum(Long.parseLong(t_goods_num));
		//更新为退货标示
		tGoodsOrder.setOrderIdenty(1);
		tGoodsOrder.setBatchId(null);
		tGoodsOrder.setOrderTime(new Date());
		//添加一条道t_goods_order表
		int result1=tGoodsOrderService.insertSelective(tGoodsOrder);
		if(result1<1){
			throw new RuntimeException("操作错误");
		}
		//原始数据的sales_code为空
		TGoodsOrder tGoodsOrderOld=tGoodsOrderService.selectByPrimaryKey(id);
		tGoodsOrderOld.setSalesCode("XXX");
		tGoodsOrderOld.setMemberReturnGoods(0);
		result1=tGoodsOrderService.updateByPrimaryKeySelective(tGoodsOrderOld);
		if(result1<1){
			throw new RuntimeException("操作错误");
		}
		//更新所属经销商的产品数量
		try {
			//判断是不是会员
			VUserAgency vUserAgency=vUserAgencyDbService.selectById(tGoodsOrder.getUserId());
			//'0'会员      '1'经销商
			if(vUserAgency.getUserIdenty()==0){
				//查看订单状态     1'已支付'   0 '未支付'
				if(tGoodsOrder.getPayState()==1){
					result=tRemainderGoodsService.lockedGoods(user_id, Long.parseLong(t_goods_num),goods_num);
					if(result<1){
						throw new RuntimeException("操作错误");
					}
				}
				else{
					//未支付  什么都不做
					result=1;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Map<String, Object> selectHistoryGoodsOrderCount(
			Map<String, Object> map) {
		return tGoodsOrderService.selectHistoryGoodsOrderCount(map);
	}

	@Override
	public TGoodsOrder selectOrderbySerial(Map<String, Object> paramMap) {
		return tGoodsOrderService.selectOrderbySerial(paramMap);
	}

	@Override
	public List<TGoodsOrder> selectMemberHistoryGoodsOrderBy(
			Map<String, Object> map) {
		
		return tGoodsOrderService.selectMemberHistoryGoodsOrderBy(map);
	}

	@Override
	public int countSelectMemberHistoryGoodsOrderBy(Map<String, Object> paramMap) {
		return tGoodsOrderService.countSelectMemberHistoryGoodsOrderBy(paramMap);
	}

}
