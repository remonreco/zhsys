package com.cbice.distribute.buyer.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.buyer.service.HsCommodityRuleService;
import com.cbice.distribute.buyer.service.HsCommodityService;
import com.cbice.distribute.buyer.util.JsonUtil;
import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsCommodityRule;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月20日 下午7:56:57
 * @describe
 */

@Controller
public class ConvertController{
	
	@Resource
	private HsCommodityService hsCommodityService;
	
	@Resource
	private HsCommodityRuleService hsCommodityRuleService;
	
	
	@Resource
	private SeqService seqService;
	
	
	private final static Map map = new HashMap();
	static {
		map.put("gif", "image/gif;charset=UTF-8");
		map.put("jpg", "image/jpeg;charset=UTF-8");
		map.put("bmp", "image/bmp;charset=UTF-8");
		map.put("tiff", "image/tiff;charset=UTF-8");
		map.put("bng", "image/bng;charset=UTF-8");
	}

	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
    public  String exchange(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map,String page) throws IOException{
		
		return "exchange";
	}
	
	@RequestMapping(value = "/exchangeQuery", method = RequestMethod.POST)
    public  String exQuery(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map,String page) throws IOException{
		
		return "exQuery";
	}
	
	/**
	 * 商品列表
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exchangeInfo", method = RequestMethod.POST)
	@ResponseBody
    public  String exchangeInfo(HttpServletRequest request,HttpServletResponse response
    		,@RequestBody Map map) throws IOException{
		String itemName1 = (String) map.get("itemName");
		String rows1 = (String) map.get("rows");
		String page1 = (String) map.get("page");
		String picPath="file://D:"+constantList.UPLOAD_FILEPATH;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset='utf-8'");
		//当前页
		int page=Integer.parseInt((page1.equals("0")) ? "1" : page1);
		//每页显示行数
		int no=Integer.parseInt((rows1.equals("0")|| "0".equals(rows1)) ? "50" : rows1);
		int end = page * no;
		//从第几页开始读取数据
		int start = end - no ;
		
		
		if(itemName1!=null&&!itemName1.equals("")){
//			String gb = new String(itemName.getBytes("ISO-8859-1"),"UTF-8");
			map.put("itemName",itemName1);	
			
		}	
		map.put("limit", no);
		map.put("offset", start );
		map.put("sysId", constantList.SYSTEM_ID );
		map.put("exchangeState", constantList.EXCHANGE_STATE_1);      //查询兑换状态为开启的项目名称
		Map<String, Object> SumMap = new HashMap<String, Object>();
		Map<String, Object> mapItem = new HashMap<String, Object>();
		mapItem.put("exchangeState", constantList.EXCHANGE_STATE_1);      //查询兑换状态为开启的项目名称
		mapItem.put("sysId", constantList.SYSTEM_ID );
		List<HsCommodity> typeList=hsCommodityService.queryProductType(mapItem);//得到查询项目名
		List<HsCommodity> list= hsCommodityService.commodityQueryBean(map);//商品列表
		long total=hsCommodityService.countSelectBy(map);//共多少条
		float tPage= (float)total / no;
		int totalPage=(int) Math.ceil(tPage);
		if(total>0){
			if(page>totalPage && list!=null){
				
				page=totalPage;
				end=page*no;
				start=end-no;
				map.put("limit", no);
				map.put("offset", start );
				list= hsCommodityService.commodityQueryBean(map);
				
				
			}
		}
		SumMap.put("picPath", picPath);
		SumMap.put("total", total);
		SumMap.put("totalPage", totalPage);
			
			map.put("list", list);
		map.put("SumMap",SumMap);
		map.put("typeList", typeList);
		return JsonUtil.map2Json(map);
	}
	
	
	@RequestMapping(value = "/exchangeDetail", method = RequestMethod.POST)
    public @ResponseBody String exchangeDetail(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		
		Map<String, Object> SumMap = new HashMap<String, Object>();
		int test=1;
		SumMap.put("exchangeDetail", test);
		return JsonUtil.map2Json(SumMap);
	}
	
	/**
	 * 商品兑换页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exchangeGoods", method = RequestMethod.POST)
    public @ResponseBody String exchangeGoods(HttpServletRequest request,HttpServletResponse response
    		,@RequestBody Map<String, Object> map) throws IOException{
		HttpSession session =request.getSession();
		long userId=(Long) session.getAttribute("id");
		Map<String, Object> SumMap = new HashMap<String, Object>();
		String comId1=(String) map.get("comId");
		long  comId=Integer.parseInt(comId1);
		HsCommodity commodity=hsCommodityService.selectByPrimaryKey(comId,constantList.SYSTEM_ID);
		SumMap.put("comName", commodity.getComName());
		SumMap.put("currencyNum", commodity.getCurrencyNum());
		SumMap.put("introduction", commodity.getIntroduction());
		SumMap.put("freezeNum", commodity.getFreezeNum());
		SumMap.put("saleUnit", commodity.getSaleUnit());
		SumMap.put("comPicture", commodity.getComPicture());
		SumMap.put("minNum", commodity.getMinNum());
		map.put("comId", comId);
		map.put("sysId", constantList.SYSTEM_ID );
		map.put("userId", userId);
		map.put("comType1", constantList.COM_TYPE_1);
		map.put("comType2", constantList.COM_TYPE_2);
		List<HsCommodityRule> crList=hsCommodityRuleService.queryRulesById(map);
		map.put("crList", crList);
		map.put("SumMap", SumMap);
		return JsonUtil.map2Json(map);
	}
	
	
	/**
	 * 检测该商品流通数量是否足够用于兑换
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkNum", method = RequestMethod.POST)
    public @ResponseBody String checkNum(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		Map<String, Object> SumMap = new HashMap<String, Object>();
//		long id=seqService.getExchangeOrderId();
		String comId1=request.getParameter("comId");
		long  comId=Integer.parseInt(comId1);
		map.put("comId", comId);
		HsCommodity commodity=hsCommodityService.selectByPrimaryKey(comId,constantList.SYSTEM_ID);
		Long currencyNum=commodity.getCurrencyNum();
		Long version=commodity.getVersion();
		SumMap.put("currencyNum2", currencyNum);
		SumMap.put("version", version);
		map.put("SumMap", SumMap);
		return JsonUtil.map2Json(map);
	}
	
	
	

	/**
	 * 检测该商品赠品数量是否足够用于赠送
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkGiftNum", method = RequestMethod.POST)
    public @ResponseBody String checkGiftNum(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		Map<String, Object> SumMap = new HashMap<String, Object>();
//		long id=seqService.getExchangeOrderId();
		String comId1=request.getParameter("comId");
		long  comId=Integer.parseInt(comId1);
		map.put("comId", comId);
		HsCommodity commodity=hsCommodityService.selectByPrimaryKey(comId,constantList.SYSTEM_ID);
		Long giftNum=commodity.getCurrencyNum();
		SumMap.put("giftNum", giftNum);
		map.put("SumMap", SumMap);
		return JsonUtil.map2Json(map);
	}
	
	
	
	/**
	 *提交订单
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public  String submitOrder(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		HttpSession session=request.getSession();
		String exIds=request.getParameter("idFlag");
		String[] splitId=exIds.split("=");
		String comName=request.getParameter("comName1");
		String comId1=request.getParameter("comId1");
//		String pageCurrencyNum1= request.getParameter("currencyNum1");//流通数量
//		String pageFreezeNum1 =request.getParameter("freezeNum1");//冻结数量
		String textVal =request.getParameter("textVal");//被兑换数量
			String exchangeId1=request.getParameter("exId1");
			String exchangeName=request.getParameter("exName1");
		String exchangeNum1=request.getParameter("costNum5");
		String giftSums =request.getParameter("giveSum1");
		String address=request.getParameter("address");
		String version1=request.getParameter("version");
	
//		long currencyNum1=Integer.parseInt(pageCurrencyNum1);
//		long FreezeNum1 =Integer.parseInt(pageFreezeNum1);
		long textVal1 =Integer.parseInt(textVal);
		long comId=Integer.parseInt(comId1);
		long id=seqService.getExchangeOrderId();
		long version = Long.parseLong(version1);
	
		
		int giftSum=0;
		if(!giftSums.equals(""))	
			giftSum=Integer.parseInt(giftSums);
		long exchangeNum=Integer.parseInt(exchangeNum1);
		long exchangeId=Integer.parseInt(exchangeId1);
//		long currencyNum=currencyNum1-textVal1;
//		long freezeNum =FreezeNum1+textVal1;
		HsCommodity commodity=new HsCommodity();
		commodity.setComId(comId);
		commodity.setSysId(constantList.SYSTEM_ID);
		commodity.setCurrencyNum(textVal1);
		commodity.setFreezeNum(textVal1);
		commodity.setVersion(version);
		long userId=(Long) session.getAttribute("id");
		String userName=(String) session.getAttribute("username");
		
		
		HsExchangeGift gife=new HsExchangeGift();
		gife.setOrderId(id);
		gife.setSysId(constantList.SYSTEM_ID);
		gife.setBeExchangeId(comId);
		gife.setBeExchangeName(comName);
		gife.setBeExchangeNum(textVal1);
		gife.setUserId(userId);
		gife.setUserName(userName);
		gife.setState(1);
		gife.setSplitId(splitId);
		
		
		HsExchangeOrder order=new HsExchangeOrder();
		order.setId(id);
		order.setSysId(constantList.SYSTEM_ID);
		order.setOrderType(1);
		order.setUserId(userId);
		order.setUserName(userName);
		order.setBeExchangeId(comId);
		order.setBeExchangeName(comName);
		order.setExchangeId(exchangeId);
		order.setExchangeName(exchangeName);
//		order.setGiftId(exchangeId);
//		order.setGiftName(giveName);
		order.setOrderState(1);
		order.setOrderTime(new Date());
		order.setBeExchangeNum(textVal1);
		order.setExchangeNum(exchangeNum);
//		order.setGiftNum(giftSum);
		order.setAddress(address);
		
		int result =hsCommodityService.updateAndInsert(commodity, order,gife);
		if(result==0){
			return "orderSuccess";
		}
		return "orderFail";
	}
	
	
	/**
	 * 兑换查询，用于查看该用户下的订单，并对其进行订单状态进行操作
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exQueryList", method = RequestMethod.POST)
    public @ResponseBody String exQueryList(HttpServletRequest request,HttpServletResponse response
    		,@RequestBody Map<String, Object> map) throws IOException{
		Map<String, Object> SumMap = new HashMap<String, Object>();
		int page=Integer.parseInt((String) ((map.get("page").equals("0")) ? "1" : map.get("page")));
		int no =9;
		int end = page * no;
		//从第几页开始读取数据
		int start = end -no;
		map.put("limit", no);
		map.put("offset", start );
		HttpSession session=request.getSession();
		long userId=(Long) session.getAttribute("id");
		map.put("userId",(int)userId);
		map.put("sysId",constantList.SYSTEM_ID);
			if(map.get("flag").equals(1)){//为1表示该操作为撤单               根据订单Id更新订单状态
				int orderId=Integer.parseInt((String) map.get("orderId"));
				HsExchangeOrder order= new HsExchangeOrder();
				order.setId((long)orderId);
				order.setSysId(constantList.SYSTEM_ID);;
//				order.setOrderState(4);//撤单
				order.setOrderState(constantList.ORDER_STATE_4);//撤单
				int result=hsCommodityService.selectGiftAndUpdateCommodity(order);
				
			}
		List<HsExchangeOrder> orderList=hsCommodityService.selectByUserId(map);
		int count=hsCommodityService.selectCountByUserId(map);
		float tPage= (float)count / no;
		int totalPage=(int) Math.ceil(tPage);
		SumMap.put("totalPage", totalPage);
		SumMap.put("count", count);
		map.put("SumMap", SumMap);
		map.put("orderList", orderList);
		return JsonUtil.map2Json(map);
	}
		
	
	@RequestMapping(value = "/checkState", method = RequestMethod.POST)
    public @ResponseBody String checkState(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		Map<String, Object> SumMap = new HashMap<String, Object>();
		
		String id=request.getParameter("id");
		
		HsExchangeOrder order=hsCommodityService.selectOrderByPrimaryKey(Long.parseLong(id));
		
		SumMap.put("orderState", order.getOrderState());
		map.put("SumMap", SumMap);
		return JsonUtil.map2Json(map);
	}
	
	@RequestMapping(value = "/showPicAction", method = RequestMethod.GET)
    public  String showPicAction(HttpServletRequest request,HttpServletResponse response
    		,Map<String, Object> map) throws IOException{
		String fileName=request.getParameter("filePath");
		
		String picPath=constantList.UPLOAD_FILEPATH+fileName;
		if(fileName.equals("")||fileName==null){
			picPath=constantList.UPLOAD_FILEPATH+DateUtils.DateToString(new Date(), "yyyy-MM-dd H:m:s")+".png";
		}
		OutputStream output = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		InputStream imageIn = null;
		try{
			response.reset();
			output = response.getOutputStream();// 得到输出流
			String path = picPath.substring(picPath.indexOf(".") + 1, picPath.length());
			
			response.setContentType((String) map.get(path.toLowerCase()));
			
			File file=new File(picPath);
			if (!file.exists()) {
				file.mkdirs();
				file.createNewFile();
			}
			
			imageIn = new FileInputStream(file);
			
			bis = new BufferedInputStream(imageIn);// 输入缓冲流
			bos = new BufferedOutputStream(output);// 输出缓冲流
			byte data[] = new byte[4096];// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
			bos.flush();// 清空输出缓冲流
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (imageIn != null) {
				try {
					imageIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
}
