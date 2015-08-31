package com.cbice.distribute.buyer.web.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.buyer.service.TAgencyBuyerService;
import com.cbice.distribute.buyer.service.TgoodsOrderService;
import com.cbice.distribute.buyer.util.JsonUtil;
import com.cbice.distribute.core.db.model.TGoodsOrder;

@Controller
public class TgoodsOrderController{

    @Resource
    private TgoodsOrderService goodsOrderServie;
    
    @Resource
    private TAgencyBuyerService tAgencyService;
    
	@RequestMapping(value = "/history", method = RequestMethod.POST)
    public String history(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map,String page) throws IOException{
    	//String assetId=request.getParameter("assetsAccount").trim();//权益账号
    	String assetId= (String) request.getSession().getAttribute("assetsAccount");
    	if(assetId==null||assetId.equals("")){
    		return "redirect:land.jsp";
    	}
    	String defaultDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
    	request.setAttribute("defaultDate", defaultDate);
    	 return "history";
    }
	
	
	@RequestMapping(value = "/historyInfo", method = RequestMethod.POST)
    public @ResponseBody String historyInfo(HttpServletRequest request,HttpServletResponse response,String starDate,String endDate,Map<String, Object> map,String page) throws IOException{
    	//String assetId=request.getParameter("assetsAccount").trim();//权益账号
    	String assetId= (String) request.getSession().getAttribute("assetsAccount");
    	int intPage = Integer.parseInt((page == null) ? "1" : page);
    	try {
			int size = 10;
			int end = intPage * size;
			int start = end - size ;
			//String saleId=request.getParameter("salesCode").trim();//资产账号
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("assetsAccount", assetId);
			map2.put("startDate", new SimpleDateFormat("yyyy-MM-dd").parse(starDate));
			map2.put("endDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endDate+" 23:59:59"));
			Map<String,Object> SumMap =new HashMap<String, Object>();
			long count =goodsOrderServie.countSelectMemberHistoryGoodsOrderBy(map2);
			SumMap.put("count", count);
			//long count = (Long)SumMap.get("count");
			long total = count%size==0?count/size:count/size +1;
			SumMap.put("total", total);
			map2.put("limit", size);
			map2.put("offset", start);
			//map2.put("salesCode", saleId);
			List<TGoodsOrder> list=goodsOrderServie.selectMemberHistoryGoodsOrderBy(map2);
			DecimalFormat df=new DecimalFormat("0.00");
			for (TGoodsOrder a : list) {
				if(a.getAgencyId()!=null){
				  a.setAgencyName( tAgencyService.selectByPrimaryKey(a.getAgencyId()).getDealerName());
				 
				}
				 a.setTotalAmt(df.format(a.getBusinessMoney()/100.00));
				 
				
			}
			map.put("list", list);
			map.put("SumMap", SumMap);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return JsonUtil.map2Json(map);
    }
	
	
    
    @RequestMapping(value = "/historyfm", method = RequestMethod.POST)
    public String historyfm(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map,Date begintime,Date endtime) throws IOException{
    	String assetId= (String) request.getSession().getAttribute("assetsAccount");
    	if(assetId==null||assetId.equals("")){
    		return "redirect:land.jsp";
    	}
        Map<String, Object> map2 = new HashMap<String, Object>();
        /*if(page>0){
        	map2.put("limit", 10);
        	map2.put("start", page);
        }*/
        map2.put("assetsAccount", assetId);
        map2.put("begintime", begintime);
        map2.put("endtime", endtime);
        List<TGoodsOrder> list=goodsOrderServie.selectHistoryGoodsOrder(map2);
        map.put("list", list);
        return "history";
    }
    
    @RequestMapping(value = "/returnOrderList", method = RequestMethod.POST)
    public  String returnOrderList(HttpServletRequest request,HttpServletResponse response,Map<String, Object> map) throws IOException{
    	
    	//String assetId=request.getParameter("assetsAccount").trim();//权益账号
    	String assetId= (String) request.getSession().getAttribute("assetsAccount");
    	if(assetId==null||assetId.equals("")){
    		return "redirect:land.jsp";
    	}
    	//String saleId=request.getParameter("salesCode").trim();//资产账号
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("assetsAccount", assetId);
        //map2.put("salesCode", saleId);
        List<TGoodsOrder> list=goodsOrderServie.selectHistoryGoodsOrder(map2);
        map.put("list", list);
    	 return "returnSuss";
    }
}
