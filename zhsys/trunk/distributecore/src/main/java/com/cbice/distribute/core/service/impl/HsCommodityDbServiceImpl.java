package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.HsCommodityMapper;
import com.cbice.distribute.core.db.data.HsCommodityRuleMapper;
import com.cbice.distribute.core.db.data.HsExchangeGiftMapper;
import com.cbice.distribute.core.db.data.HsExchangeOrderMapper;
import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.HsCommodityDbservice;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月21日 下午5:25:06
 * @describe
 */
public class HsCommodityDbServiceImpl implements HsCommodityDbservice {

	private HsCommodityMapper hsCommodityMapper;

	private HsCommodityRuleMapper hsCommodityRuleMapper;

	private HsExchangeOrderMapper hsExchangeOrderMapper;
	
	private HsExchangeGiftMapper giftMapper;

	public void setGiftMapper(HsExchangeGiftMapper giftMapper) {
		this.giftMapper = giftMapper;
	}

	@Override
	public List<Map> commodityQuery(Map<String, Object> map) {
		return hsCommodityMapper.commodityQuery(map);
	}

	@Override
	public int countSelectBy(Map<String, Object> map) {
		return hsCommodityMapper.countSelectBy(map);
	}

	@Override
	public List<HsCommodity> commodityQueryBean(Map<String, Object> map) {
		return hsCommodityMapper.commodityQueryBean(map);
	}

	@Override
	public int updateCurrencyNumAndFreezeNum(HsCommodity hsCommodity) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.updateCurrencyNumAndFreezeNum(hsCommodity);
	}

	public HsCommodityRuleMapper getHsCommodityRuleMapper() {
		return hsCommodityRuleMapper;
	}

	public void setHsCommodityRuleMapper(HsCommodityRuleMapper hsCommodityRuleMapper) {
		this.hsCommodityRuleMapper = hsCommodityRuleMapper;
	}

	public HsExchangeOrderMapper getHsExchangeOrderMapper() {
		return hsExchangeOrderMapper;
	}

	public void setHsExchangeOrderMapper(HsExchangeOrderMapper hsExchangeOrderMapper) {
		this.hsExchangeOrderMapper = hsExchangeOrderMapper;
	}

	public HsCommodityMapper getHsCommodityMapper() {
		return hsCommodityMapper;
	}

	public void setHsCommodityMapper(HsCommodityMapper hsCommodityMapper) {
		this.hsCommodityMapper = hsCommodityMapper;
	}

	public HsCommodityRuleMapper getHsCommoditRuleyMapper() {
		return hsCommodityRuleMapper;
	}

	public void setHsCommoditRuleyMapper(HsCommodityRuleMapper hsCommoditRuleyMapper) {
		this.hsCommodityRuleMapper = hsCommoditRuleyMapper;
	}

	@Override
	public List<HsCommodity> queryProductType(Map<String, Object> map) {
		return hsCommodityMapper.queryProductType(map);
	}

	@Override
	public HsCommodity selectByPrimaryKey(Long comId,String sysId) {
		return hsCommodityMapper.selectByPrimaryKey(comId,sysId);
	}

	@Override
	public int updateByPrimaryKeySelective(HsCommodity record) {
		return hsCommodityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertExchangeOrder(HsExchangeOrder order) {
		return hsExchangeOrderMapper.insert(order);
	}

	@Override
	public List<Map> queryLoadItemName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.queryLoadItemName(map);
	}

	@Override
	public List<Map> queryLoadComName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.queryLoadComName(map);
	}

	@Override
	public List<Map> commodityQueryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.commodityQueryList(map);
	}

	@Override
	public int countCommodityQueryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.countCommodityQueryList(map);
	}

	@Override
	public HsCommodity selectBycomName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.selectBycomName(map);
	}

	@Override
	public HsCommodity queryexchangeId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.queryexchangeId(map);
	}

	@Override
	public int insertNewComdity(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.insertNewComdity(map);
	}


	@Override
	public List<Map<String, Object>> exchangeRuleQuery(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.exchangeRuleQuery(map);
	}

	@Override
	public int deleteByPrimaryKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.deleteByPrimaryKey((Long)map.get("comId"),(String)map.get("sysId"));
	}
	
	@Override
	public List<HsExchangeOrder> selectByUserId(Map<String, Object> map) {
		return hsExchangeOrderMapper.selectByUserId(map)
				;
	}

	@Override
	public int selectCountByUserId(Map<String, Object> map) {
		return hsExchangeOrderMapper.selectCountByUserId(map);
	}

	@Override
	public int updateByPrimaryKeySelective(HsExchangeOrder order) {
		return hsExchangeOrderMapper.updateByPrimaryKeySelective(order);
	}
	
	@Override
	public int updateByPrimaryKey(HsCommodity hsCommodity) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.updateByPrimaryKey(hsCommodity);
	}

	@Override
	public int insertGift(HsExchangeGift gift) {
		return giftMapper.insert(gift);
	}

	@Override
	public HsExchangeOrder selectOrderByPrimaryKey(long id) {
		return hsExchangeOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<HsExchangeGift> selectGifeById(Map<String, Object> map) {
		return giftMapper.selectGifeById(map);
	}

	@Override
	public int updateCommodityById(Map<String, Object> map) {
		return hsCommodityMapper.updateCommodityById(map);
	}

	@Override
	public int deleteCommodityById(Map<String, Object> map) {
		return hsCommodityMapper.deleteCommodityById(map);
	}

	@Override
	public int updateByPrimaryKeyAndVersion(HsCommodity record) {
		// TODO Auto-generated method stub
		return hsCommodityMapper.updateByPrimaryKeyAndVersion(record);
	}

	@Override
	public List<HsExchangeOrder> selectByPrimaryKeyAndSysId(
			Map<String, Object> sumMap) {
		// TODO Auto-generated method stub
		return hsExchangeOrderMapper.selectByPrimaryKeyAndSysId(sumMap);
	}
	
	
}

