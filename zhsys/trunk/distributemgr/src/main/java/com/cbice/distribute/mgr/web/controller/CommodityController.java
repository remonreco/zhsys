
package com.cbice.distribute.mgr.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.service.CommodityRuleDbservice;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.service.TCommodityService;

/**
 * 商品兑换管理
 * 
 * @author zj
 * 
 */
@Controller
public class CommodityController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	TCommodityService TCommodityService;
	
	@Resource
	CommodityRuleDbservice commodityRuleDbservice;
	/**
	 * 跳转至商品列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/commodityList")
	public String commodityList() {
		return "commodityList";
	}

	/**
	 * 查询商品List
	 * 
	 * @param response
	 * @param page
	 * @param rows
	 * @param comId
	 * @param comName
	 * @return
	 */
	@RequestMapping("/queryCommodity")
	@ResponseBody
	public Map<String, Object> queryCommodity(HttpServletResponse response, String page, String rows, String comId, String comName) {

		response.setContentType("text/html;charset=utf-8");
		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		if (comId != null && !"".equals(comId)) {
			map.put("comId", Integer.parseInt(comId));// 序列号
		}
		map.put("comName", comName);// 产品编号
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		map.put("limit", no);
		map.put("offset", start - 1);

		List<Map> list = TCommodityService.commodityQueryList(map);

		if (end > list.size()) {
			end = list.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = TCommodityService.countCommodityQueryList(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 先查询出项目名称
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loadItemName")
	public void loadItemName(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		List<Map> list = TCommodityService.queryLoadItemName(map);

		JSONArray jsonArray = new JSONArray().fromObject(list);

		try {
			response.getWriter().print(jsonArray);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载商品名
	 * @param request
	 * @param response
	 * @param mapx
	 * @return
	 */
	@RequestMapping(value = "/loadComName", method = RequestMethod.POST)
	@ResponseBody
	public String loadComName(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		String id = (String) mapx.get("id");
		map.put("itemName", id);

		List<Map> list = TCommodityService.queryLoadComName(map);

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @param mapx
	 * @return
	 */
	@RequestMapping(value = "/addCommodityGoods", method = RequestMethod.POST)
	@ResponseBody
	public String addCommodityGoods(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr0 = new HashMap<String, Object>();
		Map<String, Object> mapr1 = new HashMap<String, Object>();
		Map<String, Object> mapr2 = new HashMap<String, Object>();
		Map<String, Object> mapr3 = new HashMap<String, Object>();
		Map<String, Object> mapr4 = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();
		String comName = (String) mapx.get("comName");
		String comId = (String) mapx.get("comId");
		Long comIdLong = Long.parseLong(comId);
		String itemName = (String) mapx.get("itemName");
		String listOrganization = (String) mapx.get("listOrganization");
		String saleUnit = (String) mapx.get("saleUnit");
		String salePrice=(String) mapx.get("salePrice");
		String saleNum = (String) mapx.get("saleNum");
		Long saleNumLong = Long.parseLong(saleNum);
		String saleMoney = (String) mapx.get("saleMoney");
		Double saleMDouble = Double.parseDouble(saleMoney);
		String currencyNum = (String) mapx.get("currencyNum");
		Long cuLong = Long.parseLong(currencyNum);
		String minNum = (String) mapx.get("minNum");
		int min = Integer.parseInt(minNum);
		String endDate = (String) mapx.get("endDate");
		String startDate = (String) mapx.get("startDate");
		String introduction = (String) mapx.get("introduction");
		String fileName = (String) mapx.get("fileName");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date data1 = null;
		Date data2 = null;
		Date data3 = new Date();

		try {
			data1 = df.parse(startDate);
			data2 = df.parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int exchangeState = 0;
		if (data3.after(data1) && data2.after(data3)) {
			exchangeState = 1;
		} else {
			exchangeState = 3;
		}

		String comNamecbb0 = (String) mapx.get("comNamecbb0");
		String amount0 = (String) mapx.get("amount0");
		long am0 = 0;
		if (!amount0.equals("")) {
			am0 = Long.parseLong(amount0);
		}
		String isGift0 = (String) mapx.get("isGift0");
		int isg0 = 0;
		if (!isGift0.equals("")) {
			isg0 = Integer.parseInt(isGift0);
		}

		String comNamecbb1 = (String) mapx.get("comNamecbb1");
		String amount1 = (String) mapx.get("amount1");
		long am1 = 0;
		int isg1 = 0;
		if (!amount1.equals("")) {
			am1 = Long.parseLong(amount1);
		}
		String isGift1 = (String) mapx.get("isGift1");
		if (!isGift1.equals("")) {
			isg1 = Integer.parseInt(isGift1);
		}

		String comNamecbb2 = (String) mapx.get("comNamecbb2");
		String amount2 = (String) mapx.get("amount2");
		long am2 = 0;
		int isg2 = 0;
		if (!amount2.equals("")) {
			am2 = Long.parseLong(amount2);
		}
		String isGift2 = (String) mapx.get("isGift2");
		if (!isGift2.equals("")) {
			isg2 = Integer.parseInt(isGift2);
		}

		String comNamecbb3 = (String) mapx.get("comNamecbb3");
		String amount3 = (String) mapx.get("amount3");
		long am3 = 0;
		int isg3 = 0;
		if (!amount3.equals("")) {
			am3 = Long.parseLong(amount3);
		}
		String isGift3 = (String) mapx.get("isGift3");
		if (!isGift3.equals("")) {
			isg3 = Integer.parseInt(isGift3);
		}

		String comNamecbb4 = (String) mapx.get("comNamecbb4");
		String amount4 = (String) mapx.get("amount4");
		long am4 = 0;
		int isg4 = 0;
		if (!amount4.equals("")) {
			am4 = Long.parseLong(amount4);
		}
		String isGift4 = (String) mapx.get("isGift4");
		if (!isGift4.equals("")) {
			isg4 = Integer.parseInt(isGift4);
		}

		// ************开始组装commodity表的数据******************
		map.put("comName", comName);
		map.put("comId", comIdLong);
		map.put("sysId", constantList.SYSTEM_ID);
		map.put("itemName", itemName);
		map.put("listOrganization", listOrganization);
		map.put("saleUnit", saleUnit);
		map.put("salePrice", Double.parseDouble(salePrice));
		map.put("saleNum", saleNumLong);
		map.put("saleMoney", saleMDouble);
		map.put("currencyNum", cuLong);
		map.put("minNum", min);
		map.put("startDate", data1);
		map.put("endDate", data2);
		map.put("introduction", introduction);
		map.put("exchangeState", exchangeState);
		map.put("freezeNum", 0);
		map.put("comPicture", fileName);
		
		if (comNamecbb0 != null && !comNamecbb0.equals("")) {
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("comName",comNamecbb0);
			map0.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map0);
			Long exchangeId = hsCommodity.getComId();
			mapr0.put("comId", comIdLong);
			mapr0.put("comName", comName);
			mapr0.put("sysId", constantList.SYSTEM_ID);
			mapr0.put("exchangeId", exchangeId);
			mapr0.put("exchangeName", comNamecbb0);
			mapr0.put("exchangeNum", am0);
			mapr0.put("comType", isg0);
		}

		if (comNamecbb1 != null && !comNamecbb1.equals("")) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("comName",comNamecbb1);
			map1.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map1);
			Long exchangeId = hsCommodity.getComId();
			mapr1.put("comId", comIdLong);
			mapr1.put("comName", comName);
			mapr1.put("sysId", constantList.SYSTEM_ID);
			mapr1.put("exchangeId", exchangeId);
			mapr1.put("exchangeName", comNamecbb1);
			mapr1.put("exchangeNum", am1);
			mapr1.put("comType", isg1);
		}
		if (comNamecbb2 != null && !comNamecbb2.equals("")) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("comName",comNamecbb2);
			map2.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map2);
			Long exchangeId = hsCommodity.getComId();
			mapr2.put("comId", comIdLong);
			mapr2.put("comName", comName);
			mapr2.put("sysId", constantList.SYSTEM_ID);
			mapr2.put("exchangeId", exchangeId);
			mapr2.put("exchangeName", comNamecbb2);
			mapr2.put("exchangeNum", am2);
			mapr2.put("comType", isg2);
		}
		if (comNamecbb3 != null && !comNamecbb3.equals("")) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("comName",comNamecbb3);
			map3.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map3);
			Long exchangeId = hsCommodity.getComId();
			mapr3.put("comId", comIdLong);
			mapr3.put("comName", comName);
			mapr3.put("sysId", constantList.SYSTEM_ID);
			mapr3.put("exchangeId", exchangeId);
			mapr3.put("exchangeName", comNamecbb3);
			mapr3.put("exchangeNum", am3);
			mapr3.put("comType", isg3);
		}
		if (comNamecbb4 != null && !comNamecbb4.equals("")) {
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("comName",comNamecbb4);
			map4.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map4);
			Long exchangeId = hsCommodity.getComId();
			mapr4.put("comId", comIdLong);
			mapr4.put("comName", comName);
			mapr4.put("sysId", constantList.SYSTEM_ID);
			mapr4.put("exchangeId", exchangeId);
			mapr4.put("exchangeName", comNamecbb4);
			mapr4.put("exchangeNum", am4);
			mapr4.put("comType", isg4);
		}
		// ************开始组装commodity_rule表的数据******************

		int flag = TCommodityService.inserAll(map, mapr0, mapr1, mapr2, mapr3, mapr4);

		List<Map> list = new ArrayList<Map>();
		Map<String, String> mapr = new HashMap<String, String>();

		if (flag == 0) {
			mapr.put("success", "0");
			list.add(0, mapr);
		}
		if (flag == -1) {
			mapr.put("error", "0");
			list.add(0, mapr);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * ajax判断comId是否可用
	 */
	@RequestMapping(value = "/isComIdUseful", method = RequestMethod.POST)
	@ResponseBody
	public String isComIdUseful(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {

		String comId = (String) mapx.get("comId");
		// ****************ajax判断输入的ID是否存在*********************
		HsCommodity hsCommodity = new HsCommodity();
		Long comIds = Long.parseLong(comId);
		hsCommodity = TCommodityService.selectByPrimaryKey(comIds,constantList.SYSTEM_ID);
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		if (hsCommodity != null) {
			map.put("error", "0");
			list.add(0, map);
		} else {
			map.put("success", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * ajax判断comName是否可用
	 */
	@RequestMapping(value = "/iscomNameUseful", method = RequestMethod.POST)
	@ResponseBody
	public String iscomNameUseful(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		String comName = (String) mapx.get("comName");
		// ****************ajax判断输入的comName是否存在*********************
		HsCommodity hsCommodity = new HsCommodity();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("comName", comName);
		maps.put("sysId", constantList.SYSTEM_ID);
		hsCommodity = TCommodityService.selectBycomName(maps);
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		if (hsCommodity != null) {
			map.put("error", "0");
			list.add(0, map);
		} else {
			map.put("success", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/updateCommodity", method = RequestMethod.POST)
	@ResponseBody
	public String updateCommodity(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int comId = (Integer) mapx.get("comId");
		Long comIds = Long.parseLong(comId + "");
		HsCommodity hsCommodity = new HsCommodity();
		hsCommodity = TCommodityService.selectByPrimaryKey(comIds,constantList.SYSTEM_ID);
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		if (hsCommodity != null) {
			int hs = hsCommodity.getExchangeState();
			String exchangeState = "";
			if (hs == 0) {
				exchangeState = "其他";
			}
			if (hs == 1) {
				exchangeState = "启动";
			}
			if (hs == 2) {
				exchangeState = "不启动";
			}
			if (hs == 3) {
				exchangeState = "暂停";
			}
			map.put("success", "0");
			map.put("comName", hsCommodity.getComName());
			map.put("comId", hsCommodity.getComId());
			map.put("itemName", hsCommodity.getItemName());
			map.put("listOrganization", hsCommodity.getListOrganization());
			map.put("saleUnit", hsCommodity.getSaleUnit());
			map.put("salePrice", hsCommodity.getSalePrice());
			map.put("saleNum", hsCommodity.getSaleNum());
			map.put("saleMoney", hsCommodity.getSaleMoney());
			map.put("currencyNum", hsCommodity.getCurrencyNum());
			map.put("minNum", hsCommodity.getMinNum());
			map.put("startDate", df.format(hsCommodity.getStartDate()));
			map.put("endDate", df.format(hsCommodity.getEndDate()));
			map.put("introduction", hsCommodity.getIntroduction());
			map.put("exchangeState", exchangeState);
			map.put("freezeNum", hsCommodity.getFreezeNum());
			map.put("fileName", hsCommodity.getComPicture());
			map.put("version", hsCommodity.getVersion());
			list.add(0, map);
		} else {
			map.put("error", "0");
			list.add(0, map);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}

	@RequestMapping(value = "/updateCommodityGoods", method = RequestMethod.POST)
	@ResponseBody
	public String updateCommodityGoods(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		Map<String, Object> map = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();
		String comName = (String) mapx.get("comName");
		String comId = (String) mapx.get("comId");
		Long comIdLong = Long.parseLong(comId);
		String itemName = (String) mapx.get("itemName");
		String listOrganization = (String) mapx.get("listOrganization");
		String saleUnit = (String) mapx.get("saleUnit");
		String salePrice = (String) mapx.get("salePrice");
		String saleNum = (String) mapx.get("saleNum");
		Long saleNumLong = Long.parseLong(saleNum);
		String saleMoney = (String) mapx.get("saleMoney");
		Double saleMDouble = Double.parseDouble(saleMoney);
		String currencyNum = (String) mapx.get("currencyNum");
		Long cuLong = Long.parseLong(currencyNum);
		String minNum = (String) mapx.get("minNum");
		int min = Integer.parseInt(minNum);
		String endDate = (String) mapx.get("endDate");
		String startDate = (String) mapx.get("startDate");
		String introduction = (String) mapx.get("introduction");
		String freezeNum = (String) mapx.get("freezeNum");
		String version = (String) mapx.get("version");
		String comPicture = (String) mapx.get("fileName");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date data1 = null;
		Date data2 = null;
		Date data3 = new Date();

		try {
			data1 = df.parse(endDate);
			data2 = df.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int exchangeState = 0;
		String state=(String) mapx.get("exchangeState");
		if(state.equals("暂停")){
			exchangeState=3;
		}
		if(state.equals("启动")){
			exchangeState=1;
		}
		if(state.equals("不启动")){
			exchangeState=2;
		}

		// ************开始组装commodity表的数据******************
		hsCommodity.setComName(comName);
		hsCommodity.setComId(comIdLong);
		hsCommodity.setSysId(constantList.SYSTEM_ID);;
		hsCommodity.setItemName(itemName);
		hsCommodity.setListOrganization(listOrganization);
		hsCommodity.setSaleUnit(saleUnit);
		hsCommodity.setSalePrice(Double.parseDouble(salePrice));
		hsCommodity.setSaleNum(saleNumLong);
		hsCommodity.setSaleMoney(saleMDouble);
		hsCommodity.setCurrencyNum(cuLong);
		hsCommodity.setMinNum(min);
		hsCommodity.setStartDate(data2);
		hsCommodity.setEndDate(data1);
		hsCommodity.setIntroduction(introduction);
		hsCommodity.setExchangeState(exchangeState);
//		hsCommodity.setFreezeNum(Long.parseLong(freezeNum));
//		hsCommodity.setVersion(Long.parseLong(version));
		hsCommodity.setComPicture(comPicture);

		int flag = TCommodityService.updateByPrimaryKeySelective(hsCommodity);

		List<Map> list = new ArrayList<Map>();
		Map<String, String> mapr = new HashMap<String, String>();

		if (flag == 1) {
			mapr.put("success", "0");
			list.add(0, mapr);
		} else {
			mapr.put("error", "0");
			list.add(0, mapr);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
	
	
	
	/**
	 * 兑换规则查询
	 * @param comId
	 * @return
	 */
	@RequestMapping("/exchangeRuleQuery")
	@ResponseBody
	public Map<String, Object> exchangeRuleQuery(String comId) {

		Map<String, Object> map = new HashMap<String, Object>();
		
		if (comId != null && !"".equals(comId)) {
			map.put("comId", Integer.parseInt(comId));// 序列号
		}
		map.put("sysId", constantList.SYSTEM_ID);//系统ID
		List<Map<String, Object>> list = TCommodityService.exchangeRuleQuery(map);

		Map<String, Object> jsonmap = new HashMap<String, Object>();
		
		jsonmap.put("rows", list);

		return jsonmap;
	}
	
	/**
	 * 更新兑换规则
	 * @param comId
	 * @param exchangeId
	 * @param exchangeName
	 * @param exchangeNum
	 * @param comType1
	 * @return
	 */
	@RequestMapping("/updateCommodityRule")
    public @ResponseBody int updateCommodityRule(String comId,String exchangeId, String exchangeName, String exchangeNum, int comType1){
        int result = 0;
        Map<String,Object>  map=new HashMap<String, Object>();
        map.put("comId", Long.parseLong(comId));
        map.put("sysId", constantList.SYSTEM_ID);//系统ID
        map.put("exchangeId", Long.parseLong(exchangeId));
        map.put("exchangeName", exchangeName);
        map.put("exchangeNum",Long.parseLong(exchangeNum));
        map.put("comType",comType1);
        try{
        //    result=commodityRuleDbservice.updateExchangeRuleById(map);
        	result = commodityRuleDbservice.updateCommodityRule(map);
            if(result<1)
            {
            	return -1;
            }
        }catch(Exception e){
            logger.error(null, e);
            return -1;
        }
        return result;
    }
	
	/**
	 * 新增商品
	 * @param request
	 * @param response
	 * @param mapx
	 * @return
	 */
	@RequestMapping(value = "/addCommodityRules", method = RequestMethod.POST)
	@ResponseBody
	public String addCommodityRules(HttpServletRequest request, HttpServletResponse response, @RequestBody Map mapx) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr0 = new HashMap<String, Object>();
		Map<String, Object> mapr1 = new HashMap<String, Object>();
		Map<String, Object> mapr2 = new HashMap<String, Object>();
		Map<String, Object> mapr3 = new HashMap<String, Object>();
		Map<String, Object> mapr4 = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();
		
		String comName = (String) mapx.get("comName");
		String comId = (String) mapx.get("comId");
		Long comIdLong = Long.parseLong(comId);
		
		String comNamecbb0 = (String) mapx.get("comNamecbb0");
		String amount0 = (String) mapx.get("amount0");
		long am0=0;
		if (!amount0.equals("")) {
			am0 = Long.parseLong(amount0);
		}
		String isGift0 = (String) mapx.get("isGift0");
		int isg0=0;
		if (!isGift0.equals("")) {
			isg0 = Integer.parseInt(isGift0);
		}

		String comNamecbb1 = (String) mapx.get("comNamecbb1");
		String amount1 = (String) mapx.get("amount1");
		long am1=0;
		int isg1=0;
		if (!amount1.equals("")) {
			am1 = Long.parseLong(amount1);
		}
		String isGift1 = (String) mapx.get("isGift1");
		if (!isGift1.equals("")) {
			isg1 = Integer.parseInt(isGift1);
		}

		String comNamecbb2 = (String) mapx.get("comNamecbb2");
		String amount2 = (String) mapx.get("amount2");
		long am2=0;
		int isg2=0;
		if (!amount2.equals("")) {
			am2 = Long.parseLong(amount2);
		}
		String isGift2 = (String) mapx.get("isGift2");
		if (!isGift2.equals("")) {
			isg2 = Integer.parseInt(isGift2);
		}

		String comNamecbb3 = (String) mapx.get("comNamecbb3");
		String amount3 = (String) mapx.get("amount3");
		long am3=0;
		int isg3=0;
		if (!amount3.equals("")) {
			 am3 = Long.parseLong(amount3);
		}
		String isGift3 = (String) mapx.get("isGift3");
		if (!isGift3.equals("")) {
			 isg3 = Integer.parseInt(isGift3);
		}

		String comNamecbb4 = (String) mapx.get("comNamecbb4");
		String amount4 = (String) mapx.get("amount4");
		long am4=0;
		int isg4=0;
		if (!amount4.equals("")) {
			 am4 = Long.parseLong(amount4);
		}
		String isGift4 = (String) mapx.get("isGift4");
		if (!isGift4.equals("")) {
			isg4 = Integer.parseInt(isGift4);
		}


		if (comNamecbb0 != null && !comNamecbb0.equals("")) {
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("comName",comNamecbb0);
			map0.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map0);
			Long exchangeId = hsCommodity.getComId();
			mapr0.put("comId", comIdLong);
			mapr0.put("comName", comName);
			mapr0.put("sysId", constantList.SYSTEM_ID);
			mapr0.put("exchangeId", exchangeId);
			mapr0.put("exchangeName", comNamecbb0);
			mapr0.put("exchangeNum", am0);
			mapr0.put("comType", isg0);
		}

		if (comNamecbb1 != null && !comNamecbb1.equals("")) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("comName",comNamecbb1);
			map1.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map1);
			Long exchangeId = hsCommodity.getComId();
			mapr1.put("comId", comIdLong);
			mapr1.put("comName", comName);
			mapr1.put("sysId", constantList.SYSTEM_ID);
			mapr1.put("exchangeId", exchangeId);
			mapr1.put("exchangeName", comNamecbb1);
			mapr1.put("exchangeNum", am1);
			mapr1.put("comType", isg1);
		}
		if (comNamecbb2 != null && !comNamecbb2.equals("")) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("comName",comNamecbb2);
			map2.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map2);
			Long exchangeId = hsCommodity.getComId();
			mapr2.put("comId", comIdLong);
			mapr2.put("comName", comName);
			mapr2.put("sysId", constantList.SYSTEM_ID);
			mapr2.put("exchangeId", exchangeId);
			mapr2.put("exchangeName", comNamecbb2);
			mapr2.put("exchangeNum", am2);
			mapr2.put("comType", isg2);
		}
		if (comNamecbb3 != null && !comNamecbb3.equals("")) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("comName",comNamecbb3);
			map3.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map3);
			Long exchangeId = hsCommodity.getComId();
			mapr3.put("comId", comIdLong);
			mapr3.put("comName", comName);
			mapr3.put("sysId", constantList.SYSTEM_ID);
			mapr3.put("exchangeId", exchangeId);
			mapr3.put("exchangeName", comNamecbb3);
			mapr3.put("exchangeNum", am3);
			mapr3.put("comType", isg3);
		}
		if (comNamecbb4 != null && !comNamecbb4.equals("")) {
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("comName",comNamecbb4);
			map4.put("sysId",constantList.SYSTEM_ID);
			hsCommodity = TCommodityService.queryexchangeId(map4);
			Long exchangeId = hsCommodity.getComId();
			mapr4.put("comId", comIdLong);
			mapr4.put("comName", comName);
			mapr4.put("sysId", constantList.SYSTEM_ID);
			mapr4.put("exchangeId", exchangeId);
			mapr4.put("exchangeName", comNamecbb4);
			mapr4.put("exchangeNum", am4);
			mapr4.put("comType", isg4);
		}
		// ************开始组装commodity_rule表的数据******************

		int flag = TCommodityService.inserAll( map, mapr0, mapr1, mapr2, mapr3, mapr4);

		List<Map> list = new ArrayList<Map>();
		Map<String, String> mapr = new HashMap<String, String>();

		if (flag == 0) {
			mapr.put("success", "0");
			list.add(0, mapr);
		}
		if (flag == -1) {
			mapr.put("error", "0");
			list.add(0, mapr);
		}

		JSONArray jsonArray = new JSONArray().fromObject(list);
		return jsonArray.toString();
	}
//    /**
//	 * 删除产品
//	 * @return
//	 */
//	@RequestMapping("/deleteCommodity")
//	public @ResponseBody int deleteCommodity(String comId) {
//		int result = 0;
//		Map<String,Object>  map=new HashMap<String, Object>();
//		if(comId==null||"".equals(comId)){
//			return result;
//		}
//		comId = comId.replace(",", "");
//		map.put("comId", Long.parseLong(comId));
//		try {
//			result = TCommodityService.deleteByPrimaryKey(map);
//		} catch (Exception e) {
//			logger.error(null, e);
//			result = -1;
//		}
//		return result;
//	}

	/**
	* 删除规则
	* @return
	*/
	@RequestMapping("/deleteRule")
	public @ResponseBody int deleteRule(String exchangeId,String comId) {
		int result = 0;
		Map<String,Object>  map=new HashMap<String, Object>();
		if(comId==null||"".equals(comId)){
			return result;
		}
		if(exchangeId==null||"".equals(exchangeId)){
			return result;
		}
		comId = comId.replace(",", "");
		exchangeId = exchangeId.replace(",", "");
		map.put("comId", Long.parseLong(comId));
		map.put("exchangeId", Long.parseLong(exchangeId));
		map.put("sysId", constantList.SYSTEM_ID);	//系统ID
		try {
			result = commodityRuleDbservice.deleteByPrimaryKey(map);
		} catch (Exception e) {
			logger.error(null, e);
			result = -1;
		}
		return result;
	}

	/**
	* 上传图片
	* @return
	*/
	@RequestMapping("/uploadPicture")
	public @ResponseBody String uploadPicture(HttpServletRequest request,HttpServletResponse response) {
		String result = "";
		MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
		MultipartFile imgFile  =  multipartRequest.getFile("imgFile"); 
		
		String ext ="";
		if(!(imgFile.getOriginalFilename() ==null || "".equals(imgFile.getOriginalFilename()))) {  
			String fileName = imgFile.getOriginalFilename();
			//获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
			ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			//对扩展名进行小写转换  
			ext = ext.toLowerCase();
		}
		
		String fileName = "ex"+ DateUtils.DateToString(new Date(), "yyyyMMddHHmmss") +"."+ ext;
		String filePath = null;
		if ((imgFile.getOriginalFilename() ==null || "".equals(imgFile.getOriginalFilename()))) {
			logger.info("上传图片失败");
			result= "uploadFail";
		} else if(!ext.equals("jpg")){
			logger.info("上传图片不是jpg文件");
			result = "picTypeError";
		}else if (imgFile.getSize() / 1024 < 50||imgFile.getSize() / 1024 > 1024) {
			System.out.println(imgFile.getSize());
			logger.info("上传图片大小不符合");
			result ="fileNotBig";
		} else {
			String upCheckFileDir = constantList.UPLOAD_FILEPATH;
//			String upCheckFileDir = request.getSession().getServletContext().getRealPath(File.separator+"upload");
			filePath = upCheckFileDir +File.separator+ fileName;
			boolean saveBool = this.saveFile(imgFile, upCheckFileDir, fileName);
			if (saveBool == true) {
				result = imgFile.getOriginalFilename() + "^" + filePath + "^"
						+ fileName;
				logger.info("resultMsg" + result);
			} else {
				result = "fail";
			}
		}
	
		return result;
	}
	
	/**
	 * 保存文件到指定位置
	 * 
	 * @param formFile
	 * @param savepath
	 * @return
	 */
	public boolean saveFile(MultipartFile formFile, String saveDir, String saveName) {
		boolean bool = true;
		InputStream in = null;
		OutputStream out = null;
		try {
			logger.info("开始保存文件");
			new File(saveDir).mkdirs();
			in = formFile.getInputStream();
			out = new FileOutputStream(saveDir + File.separator + saveName);
			byte[] buff = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(buff, 0, buff.length)) != -1) {
				out.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
			logger.error("保存文件有异常", e);
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("保存文件结果：" + bool);
		return bool;
	}
	
	/**
	* 删除图片
	* @return
	*/
	@RequestMapping("/deletePicture")
	public @ResponseBody String deletePicture(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String result = "";
		String upCheckFileDir = constantList.UPLOAD_FILEPATH;
		String filePath = upCheckFileDir+File.separator+request.getParameter("fileName");
		File file = new File(filePath);
		response.setContentType("text/html;charset=utf-8");
		logger.info("添加商品图片时,删除路径为" + filePath);
		if (file.exists()) {
			boolean bool = file.delete();
			logger.info("添加商品图片时，删除营业执照" + bool);
			result =  "success";

		} else {
			logger.info("添加商品图片时，删除的文件不存在" + filePath);
			result = "fail";
		}
		return result;
	}
}





