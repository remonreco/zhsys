package com.cbice.distribute.agency.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;

public interface TgoodsService {


	   int insertSelective(TGoods record);
	   
	   List<TGoods> selectByExample(TGoodsExample example);
	   
	   List<TGoods> selectBygoodsNumOrGoodsName(Map<String,Object> map);
	   
	   int countSlectgoodsNumorGoodsName(Map<String,Object> map);
	   
	   TGoods selectOnlyGoodsNumOrGoodsName(Map<String,Object> map);
	   
	   int deleteByPrimaryKey(Long id);
	   int updateByPrimaryKeySelective(TGoods record);
	   
	   int selectGoodsCount(int userId);
}
