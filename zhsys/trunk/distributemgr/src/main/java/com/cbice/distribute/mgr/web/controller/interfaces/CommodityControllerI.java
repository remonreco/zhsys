package com.cbice.distribute.mgr.web.controller.interfaces;

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
public class CommodityControllerI extends BaseController {
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
	@RequestMapping("/commodityListI")
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
	@RequestMapping("/queryCommodityI")
	@ResponseBody
	public Map<String, Object> queryCommodity(HttpServletResponse response, String page, String rows, String comId, String comName,String sysId) {

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
		map.put("sysId", sysId);//系统ID
		map.put("limit", no);
		map.put("offset", start - 1);

		List<Map> list = TCommodityService.commodityQueryList(map);

		if (end > list.size()) {
			end = list.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = TCommodityService.countCommodityQueryList(map);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("list", list);
		jsonMap.put("msg", "查询成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", maps);
		jsonMap.put("rows", count);
		return jsonMap;
	}

	/**
	 * 先查询出项目名称
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/loadItemNameI")
	public void loadItemName(HttpServletRequest request, HttpServletResponse response,String sysId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", sysId);// 系统ID
		List<Map> list = TCommodityService.queryLoadItemName(map);
		
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("list", list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("msg", "查询成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", maps);
		jsonMap.put("rows", 0);

		// 改改改
//		try {
//			response.getWriter().print(jsonArray);
//			response.getWriter().flush();
//			response.getWriter().close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@RequestMapping(value = "/loadComNameI", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadComName(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String sysId = request.getParameter("sysId");
		map.put("itemName", id);
		map.put("sysId", sysId);// 系统ID
		List<Map> list = TCommodityService.queryLoadComName(map);

		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("list", list);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("msg", "加载成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", maps);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	@RequestMapping(value = "/addCommodityGoodsI")
	@ResponseBody
	public Map<String, Object> addCommodityGoods(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr0 = new HashMap<String, Object>();
		Map<String, Object> mapr1 = new HashMap<String, Object>();
		Map<String, Object> mapr2 = new HashMap<String, Object>();
		Map<String, Object> mapr3 = new HashMap<String, Object>();
		Map<String, Object> mapr4 = new HashMap<String, Object>();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();
		String comName = request.getParameter("comName");
		String comId = request.getParameter("comId");
		String sysId = request.getParameter("sysId");
		Long comIdLong = Long.parseLong(comId);
		String itemName = request.getParameter("itemName");
		String listOrganization = request.getParameter("listOrganization");
		String saleUnit = request.getParameter("saleUnit");
		String salePrice = request.getParameter("salePrice");
		String saleNum = request.getParameter("saleNum");
		Long saleNumLong = Long.parseLong(saleNum);
		String saleMoney = request.getParameter("saleMoney");
		Double saleMDouble = Double.parseDouble(saleMoney);
		String currencyNum = request.getParameter("currencyNum");
		Long cuLong = Long.parseLong(currencyNum);
		String minNum = request.getParameter("minNum");
		int min = Integer.parseInt(minNum);
		String endDate = request.getParameter("endDate");
		String startDate = request.getParameter("startDate");
		String introduction = request.getParameter("introduction");
		String fileName = request.getParameter("fileName");

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

		String comNamecbb0 = request.getParameter("comNamecbb0");
		String amount0 = request.getParameter("amount0");
		long am0 = 0;
		if (amount0!=null&&!amount0.equals("")) {
			am0 = Long.parseLong(amount0);
		}
		String isGift0 = request.getParameter("isGift0");
		int isg0 = 0;
		if (isGift0!=null&&!isGift0.equals("")) {
			isg0 = Integer.parseInt(isGift0);
		}

		String comNamecbb1 = request.getParameter("comNamecbb1");
		String amount1 = request.getParameter("amount1");
		long am1 = 0;
		int isg1 = 0;
		if (amount1!=null&&!amount1.equals("")) {
			am1 = Long.parseLong(amount1);
		}
		String isGift1 = request.getParameter("isGift1");
		if (isGift1!=null&&!isGift1.equals("")) {
			isg1 = Integer.parseInt(isGift1);
		}

		String comNamecbb2 = request.getParameter("comNamecbb2");
		String amount2 = request.getParameter("amount2");
		long am2 = 0;
		int isg2 = 0;
		if (amount2!=null&&!amount2.equals("")) {
			am2 = Long.parseLong(amount2);
		}
		String isGift2 = request.getParameter("isGift2");
		if (isGift2!=null&&!isGift2.equals("")) {
			isg2 = Integer.parseInt(isGift2);
		}

		String comNamecbb3 = request.getParameter("comNamecbb3");
		String amount3 = request.getParameter("amount3");
		long am3 = 0;
		int isg3 = 0;
		if (amount3!=null&&!amount3.equals("")) {
			am3 = Long.parseLong(amount3);
		}
		String isGift3 = request.getParameter("isGift3");
		if (isGift3!=null&&!isGift3.equals("")) {
			isg3 = Integer.parseInt(isGift3);
		}

		String comNamecbb4 = request.getParameter("comNamecbb4");
		String amount4 = request.getParameter("amount4");
		long am4 = 0;
		int isg4 = 0;
		if (amount4!=null&&!amount4.equals("")) {
			am4 = Long.parseLong(amount4);
		}
		String isGift4 = request.getParameter("isGift4");
		if (isGift4!=null&&!isGift4.equals("")) {
			isg4 = Integer.parseInt(isGift4);
		}

		// ************开始组装commodity表的数据******************
		map.put("comName", comName);
		map.put("comId", comIdLong);
		map.put("sysId", sysId);// 系统ID
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
			map0.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map0);
			Long exchangeId = hsCommodity.getComId();
			mapr0.put("comId", comIdLong);
			mapr0.put("sysId", sysId);// 系统ID
			mapr0.put("comName", comName);
			mapr0.put("exchangeId", exchangeId);
			mapr0.put("exchangeName", comNamecbb0);
			mapr0.put("exchangeNum", am0);
			mapr0.put("comType", isg0);
		}

		if (comNamecbb1 != null && !comNamecbb1.equals("")) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("comName",comNamecbb1);
			map1.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map1);
			Long exchangeId = hsCommodity.getComId();
			mapr1.put("comId", comIdLong);
			mapr1.put("sysId", sysId);// 系统ID
			mapr1.put("comName", comName);
			mapr1.put("exchangeId", exchangeId);
			mapr1.put("exchangeName", comNamecbb1);
			mapr1.put("exchangeNum", am1);
			mapr1.put("comType", isg1);
		}
		if (comNamecbb2 != null && !comNamecbb2.equals("")) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("comName",comNamecbb2);
			map2.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map2);
			Long exchangeId = hsCommodity.getComId();
			mapr2.put("comId", comIdLong);
			mapr2.put("sysId", sysId);// 系统ID
			mapr2.put("comName", comName);
			mapr2.put("exchangeId", exchangeId);
			mapr2.put("exchangeName", comNamecbb2);
			mapr2.put("exchangeNum", am2);
			mapr2.put("comType", isg2);
		}
		if (comNamecbb3 != null && !comNamecbb3.equals("")) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("comName",comNamecbb3);
			map3.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map3);
			Long exchangeId = hsCommodity.getComId();
			mapr3.put("comId", comIdLong);
			mapr3.put("sysId", sysId);// 系统ID
			mapr3.put("comName", comName);
			mapr3.put("exchangeId", exchangeId);
			mapr3.put("exchangeName", comNamecbb3);
			mapr3.put("exchangeNum", am3);
			mapr3.put("comType", isg3);
		}
		if (comNamecbb4 != null && !comNamecbb4.equals("")) {
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("comName",comNamecbb4);
			map4.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map4);
			Long exchangeId = hsCommodity.getComId();
			mapr4.put("comId", comIdLong);
			mapr4.put("sysId", sysId);// 系统ID
			mapr4.put("comName", comName);
			mapr4.put("exchangeId", exchangeId);
			mapr4.put("exchangeName", comNamecbb4);
			mapr4.put("exchangeNum", am4);
			mapr4.put("comType", isg4);
		}
		// ************开始组装commodity_rule表的数据******************

		int flag = TCommodityService.inserAll(map, mapr0, mapr1, mapr2, mapr3, mapr4);

		Map<String, String> mapr = new HashMap<String, String>();

		if (flag == 0) {
			jsonMap.put("msg", "兑换商品添加成功");
			jsonMap.put("value", "1");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		
		jsonMap.put("msg", "兑换商品添加失败");
		jsonMap.put("value", "0");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	/**
	 * ajax判断comId是否可用
	 */
	@RequestMapping(value = "/isComIdUsefulI", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> isComIdUseful(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> mapr = new HashMap<String, String>();
		String comId = request.getParameter("comId");
		String sysId = request.getParameter("sysId");
		// ****************ajax判断输入的ID是否存在*********************
		HsCommodity hsCommodity = new HsCommodity();
		Long comIds = Long.parseLong(comId);
		hsCommodity = TCommodityService.selectByPrimaryKey(comIds,sysId);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		if (hsCommodity != null) {

			jsonMap.put("msg", "品种代码已存在，请更换");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		
		jsonMap.put("msg", "品种代码可以使用");
		jsonMap.put("value", "1");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	
	}

	/**
	 * ajax判断comName是否可用
	 */
	@RequestMapping(value = "/iscomNameUsefulI", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> iscomNameUseful(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> mapr = new HashMap<String, String>();
		String comName = request.getParameter("comName");
		String sysId = request.getParameter("sysId");
		// ****************ajax判断输入的comName是否存在*********************
		HsCommodity hsCommodity = new HsCommodity();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("comName", comName);
		maps.put("sysId", sysId);
		hsCommodity = TCommodityService.selectBycomName(maps);
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map = new HashMap<String, String>();
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (hsCommodity != null) {
			jsonMap.put("msg", "品种名称已存在，请更换");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		
		jsonMap.put("msg", "品种名称可以使用");
		jsonMap.put("value", "1");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/updateCommodityI", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateCommodity(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int comId = Integer.parseInt(request.getParameter("comId"));
		String sysId = request.getParameter("sysId");
		Long comIds = Long.parseLong(comId + "");
		HsCommodity hsCommodity = new HsCommodity();
		hsCommodity = TCommodityService.selectByPrimaryKey(comIds,sysId);
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr = new HashMap<String, Object>();
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
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("list", list);
			jsonMap.put("msg", "更新数据获取成功");
			jsonMap.put("value", "1");
			jsonMap.put("data", maps);
			jsonMap.put("rows", 0);
			
		} else {
			jsonMap.put("msg", "更新数据获取失败");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
		}
		
		return jsonMap;
	}

	@RequestMapping(value = "/updateCommodityGoodsI")
	@ResponseBody
	public Map<String, Object> updateCommodityGoods(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();
		String comName = request.getParameter("comName");
		String comId = request.getParameter("comId");
		String sysId = request.getParameter("sysId");
		Long comIdLong = Long.parseLong(comId);
		String itemName = request.getParameter("itemName");
		String listOrganization = request.getParameter("listOrganization");
		String saleUnit = request.getParameter("saleUnit");
		String salePrice = request.getParameter("salePrice");
		String saleNum = request.getParameter("saleNum");
		Long saleNumLong = Long.parseLong(saleNum);
		String saleMoney = request.getParameter("saleMoney");
		Double saleMDouble = Double.parseDouble(saleMoney);
		String currencyNum = request.getParameter("currencyNum");
		Long cuLong = Long.parseLong(currencyNum);
		String minNum = request.getParameter("minNum");
		int min = Integer.parseInt(minNum);
		String endDate = request.getParameter("endDate");
		String startDate = request.getParameter("startDate");
		String introduction = request.getParameter("introduction");
		String comPicture = request.getParameter("fileName");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date data1 = null;
		Date data2 = null;
		
		try {
			data1 = df.parse(endDate);
			data2 = df.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int exchangeState = 0;
		String state = request.getParameter("exchangeState");
		if (state.equals("暂停")) {
			exchangeState = 3;
		}
		if (state.equals("启动")) {
			exchangeState = 1;
		}
		if (state.equals("不启动")) {
			exchangeState = 2;
		}

		// ************开始组装commodity表的数据******************
		hsCommodity.setComName(comName);
		hsCommodity.setComId(comIdLong);
		hsCommodity.setSysId(sysId);
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
		hsCommodity.setComPicture(comPicture);

		int flag = TCommodityService.updateByPrimaryKeySelective(hsCommodity);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> mapr = new HashMap<String, Object>();
		if (flag == 1) {
			jsonMap.put("msg", "更新成功");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		
		jsonMap.put("msg", "更新失败");
		jsonMap.put("value", "1");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	/**
	 * 兑换规则查询
	 * @param comId
	 * @return
	 */
	@RequestMapping("/exchangeRuleQueryI")
	@ResponseBody
	public Map<String, Object> exchangeRuleQuery(String comId,String sysId) {

		Map<String, Object> map = new HashMap<String, Object>();

		if (comId != null && !"".equals(comId)) {
			map.put("comId", Integer.parseInt(comId));// 序列号
		}
		map.put("sysId", sysId);
		List<Map<String, Object>> list = TCommodityService.exchangeRuleQuery(map);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("list", list);
		jsonMap.put("msg", "查询成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", maps);
		jsonMap.put("rows", 0);
		return jsonMap;
	}
	
	/**
	 * 修改兑换规则
	 * @param comId
	 * @param exchangeId
	 * @param exchangeName
	 * @param exchangeNum
	 * @param comType
	 * @return
	 */
	@RequestMapping("/updateCommodityRuleI")
	public @ResponseBody
	Map<String, Object> updateCommodityRule(String comId, String exchangeId, String exchangeName, String exchangeNum, String comType,String sysId) {
		int result = 0;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> mapr = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comId", Long.parseLong(comId));
		map.put("exchangeId", Long.parseLong(exchangeId));
		map.put("exchangeName", exchangeName);
		map.put("exchangeNum", Long.parseLong(exchangeNum));
		map.put("sysId",sysId);
		if(comType!=null&&!comType.equals("")){
			map.put("comType", Integer.parseInt(comType));
		}
		
		try {
			// result=commodityRuleDbservice.updateExchangeRuleById(map);
			result = commodityRuleDbservice.updateCommodityRule(map);
			if (result < 1) {
				jsonMap.put("msg", "信息更新失败");
				jsonMap.put("value", "0");
				jsonMap.put("data", mapr);
				jsonMap.put("rows", 0);
				return jsonMap;
			}
		} catch (Exception e) {
			logger.error(null, e);
			jsonMap.put("msg", "信息更新失败");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		jsonMap.put("msg", "信息更新成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}
	
	/**
	 * 新增兑换规则
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCommodityRulesI")
	@ResponseBody
	public Map<String, Object> addCommodityRules(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr0 = new HashMap<String, Object>();
		Map<String, Object> mapr1 = new HashMap<String, Object>();
		Map<String, Object> mapr2 = new HashMap<String, Object>();
		Map<String, Object> mapr3 = new HashMap<String, Object>();
		Map<String, Object> mapr4 = new HashMap<String, Object>();
		HsCommodity hsCommodity = new HsCommodity();

		String comName = request.getParameter("comName");
		String comId = request.getParameter("comId");
		String sysId = request.getParameter("sysId");
		Long comIdLong = Long.parseLong(comId);

		String comNamecbb0 = request.getParameter("comNamecbb0");
		String amount0 = request.getParameter("amount0");
		long am0 = 0;
		if (amount0!=null&&!amount0.equals("")) {
			am0 = Long.parseLong(amount0);
		}
		String isGift0 = request.getParameter("isGift0");
		int isg0 = 0;
		if (isGift0!=null&&!isGift0.equals("")) {
			isg0 = Integer.parseInt(isGift0);
		}

		String comNamecbb1 = request.getParameter("comNamecbb1");
		String amount1 = request.getParameter("amount1");
		long am1 = 0;
		int isg1 = 0;
		if (amount1!=null&&!amount1.equals("")) {
			am1 = Long.parseLong(amount1);
		}
		String isGift1 = request.getParameter("isGift1");
		if (isGift1!=null&&!isGift1.equals("")) {
			isg1 = Integer.parseInt(isGift1);
		}

		String comNamecbb2 = request.getParameter("comNamecbb2");
		String amount2 = request.getParameter("amount2");
		long am2 = 0;
		int isg2 = 0;
		if (amount2!=null&&!amount2.equals("")) {
			am2 = Long.parseLong(amount2);
		}
		String isGift2 = request.getParameter("isGift2");
		if (isGift2!=null&&!isGift2.equals("")) {
			isg2 = Integer.parseInt(isGift2);
		}

		String comNamecbb3 = request.getParameter("comNamecbb3");
		String amount3 = request.getParameter("amount3");
		long am3 = 0;
		int isg3 = 0;
		if (amount3!=null&&!amount3.equals("")) {
			am3 = Long.parseLong(amount3);
		}
		String isGift3 = request.getParameter("isGift3");
		if (isGift3!=null&&!isGift3.equals("")) {
			isg3 = Integer.parseInt(isGift3);
		}

		String comNamecbb4 = request.getParameter("comNamecbb4");
		String amount4 = request.getParameter("amount4");
		long am4 = 0;
		int isg4 = 0;
		if (amount4!=null&&!amount4.equals("")) {
			am4 = Long.parseLong(amount4);
		}
		String isGift4 =request.getParameter("isGift4");
		if (isGift4!=null&&!isGift4.equals("")) {
			isg4 = Integer.parseInt(isGift4);
		}

		if (comNamecbb0 != null && !comNamecbb0.equals("")) {
			Map<String, Object> map0 = new HashMap<String, Object>();
			map0.put("comName",comNamecbb0);
			map0.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map0);
			Long exchangeId = hsCommodity.getComId();
			mapr0.put("comId", comIdLong);
			mapr0.put("sysId", sysId);
			mapr0.put("comName", comName);
			mapr0.put("exchangeId", exchangeId);
			mapr0.put("exchangeName", comNamecbb0);
			mapr0.put("exchangeNum", am0);
			mapr0.put("comType", isg0);
		}

		if (comNamecbb1 != null && !comNamecbb1.equals("")) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("comName",comNamecbb1);
			map1.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map1);
			Long exchangeId = hsCommodity.getComId();
			mapr1.put("comId", comIdLong);
			mapr1.put("sysId", sysId);
			mapr1.put("comName", comName);
			mapr1.put("exchangeId", exchangeId);
			mapr1.put("exchangeName", comNamecbb1);
			mapr1.put("exchangeNum", am1);
			mapr1.put("comType", isg1);
		}
		if (comNamecbb2 != null && !comNamecbb2.equals("")) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("comName",comNamecbb2);
			map2.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map2);
			Long exchangeId = hsCommodity.getComId();
			mapr2.put("comId", comIdLong);
			mapr2.put("sysId", sysId);
			mapr2.put("comName", comName);
			mapr2.put("exchangeId", exchangeId);
			mapr2.put("exchangeName", comNamecbb2);
			mapr2.put("exchangeNum", am2);
			mapr2.put("comType", isg2);
		}
		if (comNamecbb3 != null && !comNamecbb3.equals("")) {
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("comName",comNamecbb3);
			map3.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map3);
			Long exchangeId = hsCommodity.getComId();
			mapr3.put("comId", comIdLong);
			mapr3.put("sysId", sysId);
			mapr3.put("comName", comName);
			mapr3.put("exchangeId", exchangeId);
			mapr3.put("exchangeName", comNamecbb3);
			mapr3.put("exchangeNum", am3);
			mapr3.put("comType", isg3);
		}
		if (comNamecbb4 != null && !comNamecbb4.equals("")) {
			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("comName",comNamecbb4);
			map4.put("sysId",sysId);
			hsCommodity = TCommodityService.queryexchangeId(map4);
			Long exchangeId = hsCommodity.getComId();
			mapr4.put("comId", comIdLong);
			mapr4.put("sysId", sysId);
			mapr4.put("comName", comName);
			mapr4.put("exchangeId", exchangeId);
			mapr4.put("exchangeName", comNamecbb4);
			mapr4.put("exchangeNum", am4);
			mapr4.put("comType", isg4);
		}
		// ************开始组装commodity_rule表的数据******************

		int flag = TCommodityService.inserAll(map, mapr0, mapr1, mapr2, mapr3, mapr4);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> mapr = new HashMap<String, Object>();
		if (flag == 0) {
			jsonMap.put("msg", "兑换商品添加成功");
			jsonMap.put("value", "1");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		
		jsonMap.put("msg", "兑换商品添加失败");
		jsonMap.put("value", "0");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	// /**
	// * 删除产品
	// * @return
	// */
	// @RequestMapping("/deleteCommodity")
	// public @ResponseBody int deleteCommodity(String comId) {
	// int result = 0;
	// Map<String,Object> map=new HashMap<String, Object>();
	// if(comId==null||"".equals(comId)){
	// return result;
	// }
	// comId = comId.replace(",", "");
	// map.put("comId", Long.parseLong(comId));
	// try {
	// result = TCommodityService.deleteByPrimaryKey(map);
	// } catch (Exception e) {
	// logger.error(null, e);
	// result = -1;
	// }
	// return result;
	// }

	/**
	 * 删除产品
	 * 
	 * @return
	 */
	@RequestMapping("/deleteRuleI")
	public @ResponseBody
	Map<String, Object> deleteRule(String exchangeId, String comId,String sysId) {
		int result = 0;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapr = new HashMap<String, Object>();
		if (comId == null || "".equals(comId)) {
			jsonMap.put("msg", "兑换规则删除失败，品种代码为空");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		if (exchangeId == null || "".equals(exchangeId)) {
			jsonMap.put("msg", "兑换规则删除失败，品种编号为空");
			jsonMap.put("value", "0");
			jsonMap.put("data",mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		comId = comId.replace(",", "");
		exchangeId = exchangeId.replace(",", "");
		map.put("comId", Long.parseLong(comId));
		map.put("sysId",sysId);
		map.put("exchangeId", Long.parseLong(exchangeId));
		
		try {
			result = commodityRuleDbservice.deleteByPrimaryKey(map);
		} catch (Exception e) {
			logger.error(null, e);
			jsonMap.put("msg", "兑换规则删除失败");
			jsonMap.put("value", "0");
			jsonMap.put("data", mapr);
			jsonMap.put("rows", 0);
			return jsonMap;
		}
		jsonMap.put("msg", "兑换规则删除成功");
		jsonMap.put("value", "1");
		jsonMap.put("data", mapr);
		jsonMap.put("rows", 0);
		return jsonMap;
	}

	/**
	 * 上传图片
	 * 
	 * @return
	 */
	@RequestMapping("/uploadPictureI")
	public @ResponseBody
	String uploadPicture(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("imgFile");

		String ext = "";
		if (!(imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))) {
			String fileName = imgFile.getOriginalFilename();
			// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			// 对扩展名进行小写转换
			ext = ext.toLowerCase();
		}

		String fileName = "ex" + DateUtils.DateToString(new Date(), "yyyyMMddHHmmss") + "." + ext;
		String filePath = null;
		if ((imgFile.getOriginalFilename() == null || "".equals(imgFile.getOriginalFilename()))) {
			logger.info("上传图片失败");
			result = "uploadFail";
		} else if (!ext.equals("jpg")) {
			logger.info("上传图片不是jpg文件");
			result = "picTypeError";
		} else if (imgFile.getSize() / 1024 < 50 || imgFile.getSize() / 1024 > 1024) {
			System.out.println(imgFile.getSize());
			logger.info("上传图片大小不符合");
			result = "fileNotBig";
		} else {
			String upCheckFileDir = constantList.UPLOAD_FILEPATH;
			// String upCheckFileDir =
			// request.getSession().getServletContext().getRealPath(File.separator+"upload");
			filePath = upCheckFileDir + File.separator + fileName;
			boolean saveBool = this.saveFile(imgFile, upCheckFileDir, fileName);
			if (saveBool == true) {
				result = imgFile.getOriginalFilename() + "^" + filePath + "^" + fileName;
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
	 * 
	 * @return
	 */
	@RequestMapping("/deletePictureI")
	public @ResponseBody
	String deletePicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
		String upCheckFileDir = constantList.UPLOAD_FILEPATH;
		String filePath = upCheckFileDir + File.separator + request.getParameter("fileName");
		File file = new File(filePath);
		response.setContentType("text/html;charset=utf-8");
		logger.info("添加商品图片时,删除路径为" + filePath);
		if (file.exists()) {
			boolean bool = file.delete();
			logger.info("添加商品图片时，删除营业执照" + bool);
			result = "success";

		} else {
			logger.info("添加商品图片时，删除的文件不存在" + filePath);
			result = "fail";
		}
		return result;
	}
}
