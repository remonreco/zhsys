package com.cbice.distribute.core.service.impl;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.data.TGoodsMapper;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TGoodsService;

public class TGoodsServiceDbImpl implements TGoodsService {

	
	private TGoodsMapper tGoodsMapper;
	
	  private SeqService seqService;
	
	public void settGoodsMapper(TGoodsMapper tGoodsMapper) {
		this.tGoodsMapper = tGoodsMapper;
	}

	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}

	@Override
	public int insertSelective(TGoods record) {
		return tGoodsMapper.insertSelective(record);
	}

	@Override
	public List<TGoods> selectByExample(TGoodsExample example) {
		return tGoodsMapper.selectByExample(example);
	}

	@Override
	public List<TGoods> selectBygoodsNumOrGoodsName(Map<String, Object> map) {
		
		return tGoodsMapper.selectBygoodsNumOrGoodsName(map);
	}

	@Override
	public int countSlectgoodsNumorGoodsName(Map<String, Object> map) {
		return tGoodsMapper.countSlectgoodsNumorGoodsName(map);
	}

	@Override
	public TGoods selectOnlyGoodsNumOrGoodsName(Map<String, Object> map) {
		return tGoodsMapper.selectOnlyGoodsNumOrGoodsName(map);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return tGoodsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TGoods record) {
		return tGoodsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectGoodsCount(int userId) {
		// TODO Auto-generated method stub
		return tGoodsMapper.selectGoodsCount(userId);
	}

	@Override
	public TGoods selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return tGoodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int checkTgoodsNumEqualsTgoodsCount(Map<String, Object> map) {
		return tGoodsMapper.checkTgoodsNumEqualsTgoodsCount(map);
	}

	@Override
	public int selectCountGoodsNumOrGoodsName(Map<String, Object> map) {
		return tGoodsMapper.selectCountGoodsNumOrGoodsName(map);
	}

}
