package com.cbice.distribute.mgr.service;

import java.util.List;
import java.util.Map;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsExample;

public interface TgoodsService {


	   int insertSelective(TGoods record);
	   
	   List<TGoods> selectByExample(TGoodsExample example);
	   
	   List<TGoods> selectBygoodsNumOrGoodsName(Map<String,Object> map);
	   
	   int countSlectgoodsNumorGoodsName(Map<String,Object> map);
	   
	 	//判断产品基本信息录入中发货数量是否和剩余产品数量相等
		//防止两用户同时操作同一条数据
	   int checkTgoodsNumEqualsTgoodsCount(Map<String,Object> map);
	   
	   TGoods selectOnlyGoodsNumOrGoodsName(Map<String,Object> map);
	   
	   int deleteByPrimaryKey(Long id);
	   
	   int updateByPrimaryKeySelective(TGoods record);
	   
	   int insertGoodsAndRemainderGoods(Map<String,Object> map);
	   
	   TGoods selectByPrimaryKey(Long id);
	   
	   int selectCountGoodsNumOrGoodsName(Map<String,Object> map);
}
