package com.cbice.distribute.mgr.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TGoodsService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.mgr.service.TgoodsService;
import com.cbice.distribute.mgr.service.TremainderGoodsService;

public class TgoodsServiceImpl implements TgoodsService {

	private TGoodsService tGoodsService;
	private TRemainderGoodsService tRemainderGoodsService;
	private SeqService seqService;

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}



	public void settRemainderGoodsService(
			TRemainderGoodsService tRemainderGoodsService) {
		this.tRemainderGoodsService = tRemainderGoodsService;
	}



	public void settGoodsService(TGoodsService tGoodsService) {
		this.tGoodsService = tGoodsService;
	}

	@Transactional
	@Override
	public int insertSelective(TGoods record) {
		return tGoodsService.insertSelective(record);
	}

	@Override
	public List<TGoods> selectByExample(TGoodsExample example) {
		return tGoodsService.selectByExample(example);
	}

	@Override
	public List<TGoods> selectBygoodsNumOrGoodsName(Map<String, Object> map) {

		return tGoodsService.selectBygoodsNumOrGoodsName(map);
	}

	@Override
	public int countSlectgoodsNumorGoodsName(Map<String, Object> map) {
		return tGoodsService.countSlectgoodsNumorGoodsName(map);
	}

	@Override
	public TGoods selectOnlyGoodsNumOrGoodsName(Map<String, Object> map) {
		return tGoodsService.selectOnlyGoodsNumOrGoodsName(map);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tGoodsService.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TGoods record) {
		return tGoodsService.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int insertGoodsAndRemainderGoods(Map<String, Object> map) {

		int relust = 0;

		try {
			TGoods goods = (TGoods) map.get("goods");
			long userid = (Long) map.get("userId");
			TRemainderGoods regoods = new TRemainderGoods();
			long id = seqService.getTRemainderGoods();
			long goodsid=seqService.getTGoodsId();
			goods.setId(goodsid);
			regoods.setGoodsId(goodsid);
			regoods.setGoodsNum(goods.getGoodsCount());
			regoods.setUserId(userid);
			regoods.setId(id);
			tGoodsService.insertSelective(goods);
			tRemainderGoodsService.insertSelective(regoods);
			relust = 1;
		} catch (Exception e) {
			throw new RuntimeException("插入产品信息入库失败");
		}
		return relust;

	}

	//根据主键查询商品信息
	@Override
	public TGoods selectByPrimaryKey(Long id) {
		return tGoodsService.selectByPrimaryKey(id);
	}

	@Override
	public int checkTgoodsNumEqualsTgoodsCount(Map<String, Object> map) {
		return tGoodsService.checkTgoodsNumEqualsTgoodsCount(map);
	}



	@Override
	public int selectCountGoodsNumOrGoodsName(Map<String, Object> map) {
		return tGoodsService.selectCountGoodsNumOrGoodsName(map);
	}

}
