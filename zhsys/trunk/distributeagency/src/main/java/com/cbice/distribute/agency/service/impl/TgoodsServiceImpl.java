package com.cbice.distribute.agency.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.service.TGoodsService;
import com.cbice.distribute.agency.service.TgoodsService;

public class TgoodsServiceImpl implements TgoodsService {

	private TGoodsService   tGoodsService;
	
	
	public void settGoodsService(TGoodsService tGoodsService) {
		this.tGoodsService = tGoodsService;
	}

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

	@Override
	public int selectGoodsCount(int userId) {
		// TODO Auto-generated method stub
		return tGoodsService.selectGoodsCount(userId);
	}

}
