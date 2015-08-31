package com.cbice.distribute.agency.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.agency.service.TgoodsService;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.util.constantList;

@Controller
public class TGoodsController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TgoodsService tgoodsService;

	@RequestMapping("/goodsList")
	public String goodsList() {
		return "goodsList";
	}

	
	@RequestMapping("/queryGoods")
	public @ResponseBody Map<String, Object> queryTuser(String page,
			String rows, TGoods goods) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50"
				: rows);
		int end = intPage * no;
		int start = end - no + 1;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsNum", goods.getGoodsNum());
		map.put("goodsName", goods.getGoodsName());
		map.put("limit", no);
		map.put("start", start - 1);
        map.put("userId", constantList.IceId);
		List<TGoods> list = tgoodsService.selectBygoodsNumOrGoodsName(map);
		int count = tgoodsService.countSlectgoodsNumorGoodsName(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 增加经销商
	 * 
	 * @return
	 */
	@RequestMapping("/addgoods")
	public @ResponseBody int addSysBusiRole(String goodsNum2, String goodsName2,String goodsPrice2) {
		int result = 0;
		TGoods goods = new TGoods();
		if (StringUtils.isNotBlank(goodsNum2)) {
			goods.setGoodsNum(goodsNum2);
		}
		if (StringUtils.isNotBlank(goodsName2)) {
			goods.setGoodsName(goodsName2);
		}
		if (StringUtils.isNotBlank(goodsPrice2)) {
			long goodsprice=Long.parseLong(goodsPrice2);
			long lastprice=this.mul2(goodsprice, 100l);
			goods.setGoodsPrice(lastprice);
		}
		try {
			result = tgoodsService.insertSelective(goods);
		} catch (Exception e) {
			logger.error(null, e);
			result = -1;
		}
		return result;
	}

	@RequestMapping("/checkTgoodsBygoodsNumAndName")
	public @ResponseBody String selectTuserBydealerNumAndName(String goodsNum2,
			String goodsName2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsNum", goodsNum2);
		map.put("goodsName", goodsName2);
		List<TGoods> list = tgoodsService.selectBygoodsNumOrGoodsName(map);
		String str = "0";
		if (list.size() >= 1) {
			str = "1";
		}
		return str;
	}
	
	

	/**
	 * 增加经销商
	 * 
	 * @return
	 */
	@RequestMapping("/deleteGoods")
	public @ResponseBody int delGoods(String id) {
		int result = 0;
		TGoods goods = new TGoods();
		if (StringUtils.isNotBlank(id)) {
			String[] a=id.split(",");
			String goodid=a[0];
			long goodsid=Long.parseLong(goodid);
			goods.setId(goodsid);
		}
		try {
			result = tgoodsService.deleteByPrimaryKey(goods.getId());
		} catch (Exception e) {
			logger.error(null, e);
			result = -1;
		}
		return result;
	}
	
	
	@RequestMapping("/updateGoods")
    public @ResponseBody int updateUser(String id,String goodsNum, String goodsName, String goodsPrice){
        int result = 0;
        TGoods goods = new TGoods();
        goods.setId(Long.parseLong(id));
        if(StringUtils.isNotBlank(goodsNum)){
           goods.setGoodsNum(goodsNum);
        }
        if(StringUtils.isNotBlank(goodsName)){
           goods.setGoodsName(goodsName);
        }
        if(StringUtils.isNotEmpty(goodsPrice)){
        	Double paice=Double.parseDouble(goodsPrice);
          goods.setGoodsPrice(this.mul2(paice.longValue(), 100l));
        }
        try{
            result = tgoodsService.updateByPrimaryKeySelective(goods);
        }catch(Exception e){
            logger.error(null, e);
            return -1;
        }
        return result;
    }
	
	
    public   static   long   mul2(long   v1,long   v2){   
        BigDecimal   b1   =   new   BigDecimal(Long.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Long.toString(v2));   
        return   b1.multiply(b2).longValue();   
} 
}
