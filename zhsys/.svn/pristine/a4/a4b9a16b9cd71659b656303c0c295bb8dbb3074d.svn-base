package com.cbice.distribute.buyer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import sun.net.www.content.image.gif;

import com.cbice.distribute.buyer.service.HsCommodityService;
import com.cbice.distribute.core.db.data.HsExchangeOrderMapper;
import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.HsCommodityDbservice;
import com.cbice.distribute.core.util.DateUtils;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:21:54
 * @describe
 */
public class HsCommodityServiceImpl implements HsCommodityService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private HsCommodityDbservice  hsCommodityDbservice;
	
	public HsCommodityDbservice getHsCommodityDbservice() {
		return hsCommodityDbservice;
	}

	public void setHsCommodityDbservice(HsCommodityDbservice hsCommodityDbservice) {
		this.hsCommodityDbservice = hsCommodityDbservice;
	}

	@Override
	public List<Map> commodityQuery(Map<String, Object> map) {
		return hsCommodityDbservice.commodityQuery(map);
	}

	@Override
	public int countSelectBy(Map<String, Object> map) {
		return hsCommodityDbservice.countSelectBy(map);
	}

	@Override
	public List<HsCommodity> commodityQueryBean(Map<String, Object> map) {
		List<HsCommodity> list=hsCommodityDbservice.commodityQueryBean(map);
		
		for(HsCommodity hsCommodity:list){
			hsCommodity.setStartDate1(DateUtils.DateToString(hsCommodity.getStartDate(), "yyyy-MM-dd")); 
			hsCommodity.setEndDate1(DateUtils.DateToString(hsCommodity.getEndDate(), "yyyy-MM-dd"));
		}
		
		return list;
	}

	@Override
	public List<HsCommodity> queryProductType(Map<String, Object> map) {
		return hsCommodityDbservice.queryProductType(map);
	}

	@Override
	public HsCommodity selectByPrimaryKey(Long comId,String sysId) {
		return hsCommodityDbservice.selectByPrimaryKey(comId,sysId);
	}

	@Transactional
	@Override
	public int updateAndInsert(HsCommodity record, HsExchangeOrder order,HsExchangeGift gife) {
		int result=0;
		int result1=0;
		int result2=0;
		int result3=0;
		int result4=0;
		Map<String, Object> SumMap = new HashMap<String, Object>();
		try{
			
			result1 =hsCommodityDbservice.updateByPrimaryKeyAndVersion(record);
			if(result1<1){
				return -1;
			}
			
			result2 =hsCommodityDbservice.insertExchangeOrder(order);
			if(result2<1){
				return -1;
			}
			String test[]={""};
			//gift_id gift_num gift_name freezeNum currencyNum
			for(int i=0;i<gife.getSplitId().length;i++){
				test=gife.getSplitId()[i].split("\\|");
				if(test[0]!=null&&!"".equals(test[0])){
					gife.setGiftId(Long.parseLong(test[0]));
					gife.setGiftNum(Long.parseLong(test[1]));
					gife.setGiftName(test[2]);
					if(gife.getGiftNum()>0){
						result3=hsCommodityDbservice.insertGift(gife);//插入赠品表
						if(result3<1){
							return -1;
						}
					}
					
					long giveSum=Integer.parseInt(test[1]);
					if(giveSum>0){
					
						SumMap.put("comId", Long.parseLong(test[0]));
						SumMap.put("giftNum", giveSum);
						SumMap.put("sysId", gife.getSysId());
						result4 =hsCommodityDbservice.updateCommodityById(SumMap);//兑换后更新商品表中对应的赠品信息
						
	//					long freezeNum=Integer.parseInt(test[3])+Integer.parseInt(test[1]);//赠品冻结数量
	//					long  currencyNum=Integer.parseInt(test[4])-Integer.parseInt(test[1]);//赠品流通数量
	//					
	//					record.setComId(Long.parseLong(test[0]));
	//					record.setFreezeNum(freezeNum);
	//					record.setCurrencyNum(currencyNum);
						if(result4<1){
							return -1;
						}
					}
				}else{
					break;
				}
				
			}
			
		}catch(Exception e){
			logger.error(null, e);
			result=-1;
		}
		
		return result;
	}

	@Override
	public List<HsExchangeOrder> selectByUserId(Map<String, Object> map) {
		
		 List<HsExchangeOrder> orderList=hsCommodityDbservice.selectByUserId(map);
		 for(HsExchangeOrder order:orderList){
			 order.setOrderTime1(DateUtils.DateToString((Date)order.getOrderTime(), "yyyy-MM-dd hh:mm:ss"));
			 if(order.getOrderState()==1){
				 order.setOrderState1("申请中");
			 }else if(order.getOrderState()==2){
				 order.setOrderState1("兑换成功");
			 }else if(order.getOrderState()==3){
				 order.setOrderState1("兑换失败");
			 }else if(order.getOrderState()==4){
				 order.setOrderState1("撤单");
			 }else{
				 order.setOrderState1("其它");
			 }
		 }
		return orderList;
	}

	@Override
	public int selectCountByUserId(Map<String, Object> map) {
		return hsCommodityDbservice.selectCountByUserId(map);
	}

	@Override
	public int updateByPrimaryKeySelective(HsExchangeOrder order) {
		return hsCommodityDbservice.updateByPrimaryKeySelective(order);
	}

	@Override
	public HsExchangeOrder selectOrderByPrimaryKey(long id) {
		return hsCommodityDbservice.selectOrderByPrimaryKey(id);
	}


	@Transactional
	@Override
	public int selectGiftAndUpdateCommodity(HsExchangeOrder order) {
		int result=0;
		int result1=0;
		int result2=0;
		int result3=0;
		Map<String, Object> SumMap = new HashMap<String, Object>();
		try{
			result1=hsCommodityDbservice.updateByPrimaryKeySelective(order);
			
			if(result1<1){
				return -1;
			}
			SumMap.put("id", order.getId());
			SumMap.put("sysId", order.getSysId());
			List<HsExchangeOrder> beExList=hsCommodityDbservice.selectByPrimaryKeyAndSysId(SumMap);
			if(beExList!=null &&beExList.size()>0){
				HsExchangeOrder hsExchangeOrder=beExList.get(0);
				long beExchangeId=hsExchangeOrder.getBeExchangeId();
				long beExchangeNum=hsExchangeOrder.getBeExchangeNum();
				SumMap.put("comId", beExchangeId);
				SumMap.put("sysId", order.getSysId());
				SumMap.put("giftNum",beExchangeNum);
				result3=hsCommodityDbservice.deleteCommodityById(SumMap);//赠品
			}
			SumMap.put("orderId", order.getId());
			SumMap.put("sysId", order.getSysId());
			List<HsExchangeGift> giftList=hsCommodityDbservice.selectGifeById(SumMap);
			if(giftList!=null &&giftList.size()>0){
				for(HsExchangeGift gift:giftList){
					long giftId=gift.getGiftId();
					long giftNum=gift.getGiftNum();//selectByPrimaryKey
	//				
	//				HsCommodity commodity =hsCommodityDbservice.selectByPrimaryKey(giftId);
	//				long currencyNum=commodity.getCurrencyNum();
	//				long freezeNum=commodity.getFreezeNum();
	//				
	//				long  currencyNumResult=currencyNum+giftNum;
	//				long freezeNumResult=freezeNum-giftNum;
	//				
	//				commodity.setComId(giftId);
	//				commodity.setCurrencyNum(currencyNumResult);
	//				commodity.setFreezeNum(freezeNumResult);
					SumMap.put("comId", giftId);
					SumMap.put("sysId", order.getSysId());
					SumMap.put("giftNum",giftNum);
					result2=hsCommodityDbservice.deleteCommodityById(SumMap);//撤单后更新商品表中对应的赠品信息
					if(result2<1){
						return -1;
					}
					
			}
		}		
		}catch(Exception e){
			logger.error(null, e);
			result=-1;
		}
		return result;
	}


}
