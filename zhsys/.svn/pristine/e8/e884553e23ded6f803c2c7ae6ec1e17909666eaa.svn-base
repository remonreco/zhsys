package com.cbice.distribute.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cbice.distribute.core.db.data.TGoodsMapper;
import com.cbice.distribute.core.db.data.TRemainderGoodsMapper;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TRemainderGoodsService;

public class TRemainderGoodsServiceDbImpl implements TRemainderGoodsService {

	
	private TRemainderGoodsMapper tRemainderGoodsMapper;

	private SeqService seqService;
	private TGoodsMapper  tGoodsMapper;
	
	public void settRemainderGoodsMapper(TRemainderGoodsMapper tRemainderGoodsMapper) {
		this.tRemainderGoodsMapper = tRemainderGoodsMapper;
	}
	public void setSeqService(SeqService seqService) {
		this.seqService = seqService;
	}
	public void settGoodsMapper(TGoodsMapper tGoodsMapper) {
		this.tGoodsMapper = tGoodsMapper;
	}

	@Override
	public int insertSelective(TRemainderGoods record) {
		return tRemainderGoodsMapper.insertSelective(record);
	}


	@Override
	public int updateByPrimaryKeySelective(TRemainderGoods record) {
		return tRemainderGoodsMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public List<TRemainderGoods> selectbuyUserid(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectbuyUserid(map);
	}
	
	@Override
	public int updateNumByUserAndGoods(Map<String, Object> map) {
		return tRemainderGoodsMapper.updateNumByUserAndGoods(map);
	}


	@Override
	public List<Map<String, Object>> selectRemainderGoodsApplcation(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectRemainderGoodsApplcation(map);
	}

	@Override
	public int selectRemainderGoodsApplcationCount(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectRemainderGoodsApplcationCount(map);
	}


	@Override
	public TRemainderGoods selectByPrimaryKey(Long id) {
		return tRemainderGoodsMapper.selectByPrimaryKey(id);
	}


	@Override
	public String selectAgencyId(Long user_id) {
		return tRemainderGoodsMapper.selectAgencyId(user_id);
	}


	@Override
	public TRemainderGoods selectbuyUseridAndGoodsid(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectbuyUseridAndGoodsid(map);
	}

	/**
	 * 直接在用户的的产品表里增加相应的产品（若记录存在则数量叠加，若记录不存在则新增记录）
	 * @param userId   用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @author zhang'lei
	 */
    @Transactional
	@Override
	@Deprecated  
	public int insertOrUpdate(Long userId,Long goodsNum, String goodsCode) {
		try {
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("goodsNum", goodsCode);
			TGoods goos =tGoodsMapper.selectBygoodsNum(map);
			Long goodsId =goos.getId();
			map.clear();
			map.put("userId", userId);
			map.put("goodsCode", goodsCode);
			TRemainderGoods record =tRemainderGoodsMapper.selecyBuyCondition(map);
			if(record ==null){
				TRemainderGoods good =new TRemainderGoods();
				Long newId =seqService.getTRemainderGoods();
				good.setId(newId);
				good.setGoodsNum(goodsNum);
				good.setGoodsId(goodsId);
				good.setUserId(userId);
				tRemainderGoodsMapper.insertSelective(good);
			}
			else{
				record.setGoodsNum(record.getGoodsNum()+goodsNum);
				tRemainderGoodsMapper.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
    /**
	 * 直接在用户的的产品表里减少相应的产品（数量递减，数量清零则删除记录）
	 * @param userId   用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @throws Exception  数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	@Override
	@Transactional
	@Deprecated  
	public int deleteOrUpdae(Long userId, Long goodsNum, String goodsCode)
			throws Exception {
		try {
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("goodsNum", goodsCode);
			TGoods goos =tGoodsMapper.selectBygoodsNum(map);
			Long goodsId =goos.getId();
			map.clear();
			map.put("userId", userId);
			map.put("goodsCode", goodsCode);
			TRemainderGoods record =tRemainderGoodsMapper.selecyBuyCondition(map);
			if(record==null){
				throw new Exception("查询不到该记录");
			}
			else if(record.getGoodsNum()<goodsNum){
				throw new Exception("产品数量超出存货数量");
			}
			else if(record.getGoodsNum().equals(goodsNum)) {
				tRemainderGoodsMapper.deleteByPrimaryKey(record.getId());
			}
			else{
				Long midGoodsNum =0L;
				if(record.getGoodsNum()!=null){
					 midGoodsNum =record.getGoodsNum();
				}
				record.setGoodsNum(midGoodsNum-goodsNum);
				tRemainderGoodsMapper.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			
			throw e;
		}
		return 1;
	}
	/**
	 * 锁定用户的的产品表里相应的产品（按goodsNum数量锁定）
	 * @param userId    用户ID	
	 * @param goodsNum  产品数量
	 * @param goodsCode  产品编码
	 * @return
	 * @throws Exception 数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	@Transactional
	@Override
	public int lockedGoods(Long userId, Long goodsNum, String goodsCode)
			throws Exception {
		int result = 0;
		try {
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("goodsNum", goodsCode);
			TGoods goos =tGoodsMapper.selectBygoodsNum(map);
			Long goodsId =goos.getId();
			map.clear();
			map.put("userId", userId);
			map.put("goodsCode", goodsCode);
			TRemainderGoods record =tRemainderGoodsMapper.selecyBuyCondition(map);
			if(record ==null){
				throw new Exception("查询不到该记录");
			}else if(record.getGoodsNum()<goodsNum){
				throw new Exception("产品数量超出存货数量");
			}else{
				Long midGoodsNum =0L;
				if(record.getGoodsNum()!=null){
					midGoodsNum =record.getGoodsNum();
				}
				Long midLockedNum =0L;
				if(record.getGoodsNumLocked()!=null){
					midLockedNum =record.getGoodsNumLocked();
				}
				record.setGoodsNum(midGoodsNum-goodsNum);
				record.setGoodsNumLocked(midLockedNum+goodsNum);//申请退货的产品数量
				result = tRemainderGoodsMapper.updateByPrimaryKeySelective(record);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	/**
	 * 从fromUserId中解锁产品存进toUserId的库存中（fromUserId可以等于toUserId，当库存表中不存在记录时则新增记录，当记录的库存和锁定产品数量均为0时则删除记录，其他情况则对产品数量做加减操作）
	 * @param fromUserId
	 * @param toUserId
	 * @param goodsNum
	 * @param goodsCode
	 * @return
	 * @throws Exception 数据库操作失败，需要减少的数量大于库存数量均会抛出异常
	 * @author zhang'lei
	 */
	@Transactional
	@Override
	public int unlockGoods(Long fromUserId, Long toUserId, Long goodsNum,
			String goodsCode) throws Exception {
		int result = 0;
		try {
			//-----------------处理fromuser库存--------------------------------------
			Map<String, Object> map =new HashMap<String, Object>();
			map.put("goodsNum", goodsCode);
			TGoods goos =tGoodsMapper.selectBygoodsNum(map);
			Long goodsId =goos.getId();
			map.clear();
			map.put("userId", fromUserId);
			map.put("goodsCode", goodsCode);
			TRemainderGoods record =tRemainderGoodsMapper.selecyBuyCondition(map);//查询本级的剩余产品
			if(record ==null){
				throw new Exception("查询不到该记录");
			}else if(record.getGoodsNumLocked()<goodsNum){
				throw new Exception("解锁数量超出存货数量");
			}else {
				Long midLockedNum =0L;
				if(record.getGoodsNumLocked()!=null){
					midLockedNum =record.getGoodsNumLocked();
				}
				record.setGoodsNumLocked(midLockedNum-goodsNum);
				//如果解锁后产品库存表中该记录的库存数量和锁定数量均为0则删除该记录
				if(record.getGoodsNum().equals(0L)&&record.getGoodsNumLocked().equals(0L)){
					result = tRemainderGoodsMapper.deleteByPrimaryKey(record.getId());
				}else{
					//如果解锁后产品库存表中该记录的库存数量和锁定数量不为0则修改锁定数量
					result = tRemainderGoodsMapper.updateByPrimaryKeySelective(record);
				}
			}
			
			//-----------------处理touser库存--------------------------------------
			Map<String, Object> map2 =new HashMap<String, Object>();
			map2.put("goodsNum", goodsCode);
			TGoods goos2 =tGoodsMapper.selectBygoodsNum(map2);
			Long goodsId2 =goos2.getId();
			map2.clear();
			map2.put("userId",toUserId );
			map2.put("goodsCode", goodsCode);
			TRemainderGoods record2 =tRemainderGoodsMapper.selecyBuyCondition(map2);
			//若查询不到该记录则新增记录
			if(record2 ==null){
				TRemainderGoods good =new TRemainderGoods();
				Long newId =seqService.getTRemainderGoods();
				good.setId(newId);
				good.setGoodsNum(goodsNum);
				good.setGoodsId(goodsId2);
				good.setUserId(toUserId);
				good.setGoodsNumLocked(0L);
				result = tRemainderGoodsMapper.insertSelective(good);
			}else {
				//若记录存在则修改产品数量
				Long midGoodsNum =0L;
				if(record2.getGoodsNum()!=null){
					midGoodsNum =record2.getGoodsNum();
				}
				record2.setGoodsNum(midGoodsNum+goodsNum);
				result = tRemainderGoodsMapper.updateByPrimaryKeySelective(record2);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> selectLowRemainderGoodsApplcation(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectLowRemainderGoodsApplcation(map);
	}
	@Override
	public int selectLowRemainderGoodsApplcationCount(Map<String, Object> map) {
		return tRemainderGoodsMapper.selectLowRemainderGoodsApplcationCount(map);
	}
	@Override
	public int updateTRemainderGoodsByUserIdAndGoodId(TRemainderGoods tRemainderGoods) {
		
		return tRemainderGoodsMapper.updateTRemainderGoodsByUserIdAndGoodId(tRemainderGoods);
	}

}
