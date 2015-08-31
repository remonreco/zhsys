package com.cbice.distribute.mgr.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.PayPluginException;
import com.cbice.distribute.core.util.SequenceCreaterUtil;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.ImportSendGoodsService;
import com.cbice.distribute.mgr.service.TgoodsBatchService;
import com.cbice.distribute.mgr.service.TgoodsService;
import com.cbice.distribute.mgr.service.TuserService;

@Controller
public class TGoodsController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TgoodsService tgoodsService;
	@Resource
	private TRemainderGoodsService tRemainderGoodsService;
	@Resource
	private TgoodsBatchService tgoodsBatchService;
	 @Resource
	 private ImportSendGoodsService importSendGoodsService;
	
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
		map.put("userId", constantList.IceId);
		map.put("limit", no);
		map.put("start", start-1);

		List<TGoods> list = tgoodsService.selectBygoodsNumOrGoodsName(map);
		int count = tgoodsService.countSlectgoodsNumorGoodsName(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 增加产品
	 * 
	 * @return
	 */
	@RequestMapping("/addgoods")
	public @ResponseBody int addSysBusiRole(String goodsNum2, String goodsName2,double goodsPrice2,String goodsCount2) {
		int result = 0;
		TGoods goods = new TGoods();
		if (StringUtils.isNotBlank(goodsNum2)) {
			goods.setGoodsNum(goodsNum2);
		}
		if (StringUtils.isNotBlank(goodsName2)) {
			goods.setGoodsName(goodsName2);
		}
		if (StringUtils.isNotBlank(String.valueOf(goodsPrice2))) {
			/*Double goodsprice=Double.valueOf(goodsPrice2);*/
			int goodsPrice = (int)(goodsPrice2*100);
			/*Double lastprice=this.mul3(goodsprice, 100d);*/
			long lastprice = Long.parseLong(String.valueOf(goodsPrice));
			goods.setGoodsPrice(lastprice);
		}
		if (StringUtils.isNotBlank(goodsCount2)) {
			long goodscount=Long.parseLong(goodsCount2);
			goods.setGoodsCount(goodscount);
		}
			//增加修改时间
			Date modify =new Date();
		    goods.setModifyTime(modify);
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("goodsNum", goodsNum2);
			//map.put("goodsName", goodsName2);
			List<TGoods> list = tgoodsService.selectBygoodsNumOrGoodsName(map);
			map.clear();
			if (list.size() >= 1) {
				result = 1;
			}
			else{
			
			map.put("userId", constantList.IceId);
			map.put("goods", goods);
			result = tgoodsService.insertGoodsAndRemainderGoods(map);
			}
		} catch (Exception e) {
			logger.error(null, e);
			result = -1;
		}
		return result;
	}

	
	//检查产品编号是否重复
	@RequestMapping("/checkTgoodsBygoodsNumAndName")
	public @ResponseBody String selectTuserBydealerNumAndName(String goodsNum2,
			String goodsName2) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsNum", goodsNum2);
		//map.put("goodsName", goodsName2);
		List<TGoods> list = tgoodsService.selectBygoodsNumOrGoodsName(map);
		String str = "0";
		if (list.size() >= 1) {
			str = "1";
		}
		return str;
	}
	
	//判断产品基本信息录入中发货数量是否和剩余产品数量相等
	//防止两用户同时操作同一条数据
	@RequestMapping("/checkTgoodsNumEqualsTgoodsCount")
	public @ResponseBody String checkTgoodsNumEqualsTgoodsCount(int id) {
		int count =0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("userId", constantList.IceId);
		count = tgoodsService.checkTgoodsNumEqualsTgoodsCount(map);
		String str = "0";
		if (count >= 1) {
			str = "1";
		}
		return str;
	}
	
	

	/**
	 * 删除产品
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
			//查询产品是否已下发
			TGoods tgoods=tgoodsService.selectByPrimaryKey(goods.getId());
			TGoodsBatch tgoodsBatch=tgoodsBatchService.selectGoodsByGoodsnum(tgoods.getGoodsNum());
			if(tgoodsBatch==null){
				result = tgoodsService.deleteByPrimaryKey(goods.getId());
			}else{
				result=0;
			}
		} catch (Exception e) {
			logger.error(null, e);
			result = -1;
		}
		return result;
	}
	
	
	@RequestMapping("/updateGoods")
    public @ResponseBody int updateUser(String id,String goodsNum, String goodsName, double goodsPrice, String goodsCount){
        int result = 0;
        int result1 = 0;
        Map<String,Object>  map=new HashMap<String, Object>();
        map.put("goodsNum", goodsNum);
        map.put("goodsName", goodsName);
        map.put("id", Long.parseLong(id));
        int result2= tgoodsService.selectCountGoodsNumOrGoodsName(map);
        if(result2>0){
        	return -2;
        }
        TGoods goods = new TGoods();
        TRemainderGoods tRemainderDoods=new TRemainderGoods();
        goods.setId(Long.parseLong(id));
        if(StringUtils.isNotBlank(goodsNum)){
        	
           goods.setGoodsNum(goodsNum);
        }
        if(StringUtils.isNotBlank(goodsName)){
           goods.setGoodsName(goodsName);
        }
        if(StringUtils.isNotEmpty(String.valueOf(goodsPrice))){
			int goodsPrice1 = (int)(goodsPrice*100);
			long lastprice = Long.parseLong(String.valueOf(goodsPrice1));
          goods.setGoodsPrice(lastprice);
        }
        if(StringUtils.isNotBlank(goodsCount)){
        	//如果修改过t_goods的发货数量，t_remainderDoods更新剩余产品数据
            tRemainderDoods.setGoodsNum(Long.parseLong(goodsCount));
        	goods.setGoodsCount(Long.parseLong(goodsCount));
         }
        //mgr系统的ICE的userId保存在静态类中constantList.IceId"
        tRemainderDoods.setUserId(constantList.IceId);
        tRemainderDoods.setGoodsId(goods.getId());
        try{
            result1=tRemainderGoodsService.updateTRemainderGoodsByUserIdAndGoodId(tRemainderDoods);
            if(result1<1)
            {
            	return -1;
            }
          //增加修改时间
			Date modify =new Date();
		    goods.setModifyTime(modify);
            result = tgoodsService.updateByPrimaryKeySelective(goods);
            if(result<1){
            	return -1;
            }
        }catch(Exception e){
            logger.error(null, e);
            return -1;
        }
        return result;
    }
	
	/**
	 * 产品基础信息录入-发货跳转controller
	 * */
	@RequestMapping("/toSendGoods")
    public String toSendGoodsPage(HttpServletRequest request) throws UnsupportedEncodingException{
		request.setCharacterEncoding("utf-8");
		
		String goodsNames=request.getParameter("goodsName");
		String goodsName = java.net.URLDecoder.decode(goodsNames,"UTF-8");
		String goodsNum=request.getParameter("goodsNum");
		request.setAttribute("goodsName", goodsName);
		request.setAttribute("goodsNum", goodsNum);
        return "sendGoodsInformation";
    }
	
	
	/**
	 * 检查货物id是否存在
	 * @return
	 */
	@RequestMapping("/checkGoodsByGoodsId")
	public @ResponseBody String checkGoodsByGoodsId(String id){
		TGoods goods= tgoodsService.selectByPrimaryKey(Long.parseLong(id));
		String str="0";
		if(goods!=null){
			str="1";
		}
	    return str;
	}
	
	
    public   static   long   mul2(long   v1,long   v2){   
        BigDecimal   b1   =   new   BigDecimal(Long.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Long.toString(v2));   
        return   b1.multiply(b2).longValue();   
    
}
}
