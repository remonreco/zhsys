package com.cbice.distribute.buyer.web.controller.interfaces;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.buyer.service.HsCommodityRuleService;
import com.cbice.distribute.buyer.service.HsCommodityService;
import com.cbice.distribute.buyer.service.HsExchangeOrderService;
import com.cbice.distribute.buyer.util.JsonUtil;
import com.cbice.distribute.core.db.model.HsCommodity;
import com.cbice.distribute.core.db.model.HsCommodityRule;
import com.cbice.distribute.core.db.model.HsExchangeGift;
import com.cbice.distribute.core.db.model.HsExchangeOrder;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;

/**
 * 
 * @author zj
 * @date 2015年6月16日 上午10:31:32
 * @Description: 商品兑换接口
 */

@Controller
public class ConvertControllerI{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private HsCommodityService hsCommodityService;
	
	@Resource
	private HsCommodityRuleService hsCommodityRuleService;
	
	@Resource
	private HsExchangeOrderService hsExchangeOrderService;
	
	@Resource
	private SeqService seqService;
	
	
	private final static Map map = new HashMap();
	
	
	/**
	 * 商品列表
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exchangeInfoI", method = RequestMethod.POST)
	@ResponseBody
    public  Map<String, Object> exchangeInfo2(HttpServletRequest request,HttpServletResponse response
    		,String itemName,String rows,String pages) throws IOException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset='utf-8'");
		pages = request.getParameter("pages");
		//当前页
		int page=Integer.parseInt((pages.equals("0")) ? "1" : pages);
		//每页显示行数
		int no=Integer.parseInt((rows.equals("0")|| "0".equals(rows)) ? "50" : rows);
		int end = page * no;
		//从第几页开始读取数据
		int start = end - no ;
		
		
		if(itemName!=null&&!itemName.equals("")){
//			String gb = new String(itemName.getBytes("ISO-8859-1"),"UTF-8");
			map.put("itemName",itemName);	
			
		}	
		map.put("limit", no);
		map.put("offset", start );
		Map<String, Object> SumMap = new HashMap<String, Object>();
		Map<String, Object> mapItem = new HashMap<String, Object>();
		mapItem.put("exchangeState", constantList.EXCHANGE_STATE_1);      //查询兑换状态为开启的项目名称
		List<HsCommodity> itemList=hsCommodityService.queryProductType(mapItem);//得到查询项目名
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
		SumMap.put("total", total);
		SumMap.put("totalPage", totalPage);
			
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("list", list);
		maps.put("itemList", itemList);
		jsonMap.put("msg","操作成功，返回项目名称list和商品list！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps);
	    jsonMap.put("rows",total);				//商品数据总条数
	    jsonMap.put("itemRows",itemList.size());//总项目条数
		
		} catch (Exception e) {
			logger.error("数据查询出现异常！");
			jsonMap.put("msg","操作失败，数据查询出现异常！" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",map);
		    jsonMap.put("rows",0);
		    jsonMap.put("itemRows",0);
			return jsonMap;
		}
		return jsonMap;
	}
	
	/**
	 * 商品兑换页面
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exchangeGoodsI", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> exchangeGoods(HttpServletRequest request,HttpServletResponse response
    		,String comId,String userId,String sysId) throws IOException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
//		HttpSession session =request.getSession();
//		long userId=(Long) session.getAttribute("id");
		Map<String, Object> SumMap = new HashMap<String, Object>();
		long  comId1=Integer.parseInt(comId);
		long  userId1=Integer.parseInt(userId);
		try {
			
		HsCommodity commodity=hsCommodityService.selectByPrimaryKey(comId1,sysId);
		SumMap.put("comName", commodity.getComName());
		SumMap.put("currencyNum", commodity.getCurrencyNum());
		SumMap.put("introduction", commodity.getIntroduction());
		SumMap.put("freezeNum", commodity.getFreezeNum());
		SumMap.put("saleUnit", commodity.getSaleUnit());
		SumMap.put("comPicture", commodity.getComPicture());
		SumMap.put("minNum", commodity.getMinNum());
		map.put("comId", comId1);
		map.put("userId", userId1);
		List<HsCommodityRule> crList=hsCommodityRuleService.queryRulesById(map);
		maps.put("crList", crList);
		maps.put("SumMap", SumMap);
		jsonMap.put("msg","操作成功！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps);
	    jsonMap.put("rows",1);
		} catch (Exception e) {
			// TODO: handle exception
			jsonMap.put("msg","操作失败！" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}
		return jsonMap;
	}
	
	/**
	 *提交订单
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/submitOrderI", method = RequestMethod.POST)
	@ResponseBody
    public  Map<String, Object> submitOrder(HttpServletRequest request,HttpServletResponse response
    		) throws IOException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>(); //
		String comName=request.getParameter("comName");			//被兑换商品名
		String comId1 = request.getParameter("comId");			//被兑换商品ID
		String sysId = request.getParameter("sysId");			//系统ID
		String textVal = request.getParameter("comNum");		//被兑换商品数量
		String exchangeId1 = request.getParameter("exId");		//兑换使用物ID
		String exchangeName = request.getParameter("exName");	//兑换使用物名
		String exchangeNum1 = request.getParameter("exNum");	//兑换使用物数量
//		String exIds=request.getParameter("idFlag");			//
//		String[] splitId=exIds.split("=");						//
		String address=request.getParameter("address");			//发货地址
//		String comId1=request.getParameter("comId1");
//		String pageCurrencyNum1= request.getParameter("currencyNum1");//流通数量
//		String pageFreezeNum1 =request.getParameter("freezeNum1");//冻结数量
//		String textVal =request.getParameter("textVal");//被兑换数量
//			String exchangeId1=request.getParameter("exId1");
//			String exchangeName=request.getParameter("exName1");
//		String exchangeNum1=request.getParameter("costNum5");
//		String giftSums =request.getParameter("giveSum1");
//		String address=request.getParameter("address");
//		String version1=request.getParameter("version");		
	
//		long currencyNum1=Integer.parseInt(pageCurrencyNum1);
//		long FreezeNum1 =Integer.parseInt(pageFreezeNum1);
		long textVal1 =Integer.parseInt(textVal);
		long comId=Integer.parseInt(comId1);
		long id=seqService.getExchangeOrderId();
//		long version = Long.parseLong(version1);
	
		
//		int giftSum=0;
//		if(!giftSums.equals(""))	
//			giftSum=Integer.parseInt(giftSums);
		long exchangeNum=Integer.parseInt(exchangeNum1);
		long exchangeId=Integer.parseInt(exchangeId1);
		
		//验证被兑换商品数量是否小于流通数量
		map.put("comId", comId);
		HsCommodity commodity1=hsCommodityService.selectByPrimaryKey(comId,sysId);
		Long currencyNum=commodity1.getCurrencyNum();
		Integer mixNum=commodity1.getMinNum();
		Long version=commodity1.getVersion();
		if(currencyNum<textVal1){
			jsonMap.put("msg","操作失败！被兑换商品数大于当前流通数量！" );
		    jsonMap.put("value","0");
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}else if(mixNum>textVal1){
			jsonMap.put("msg","操作失败！被兑换商品数小于最小兑换数量！" );
		    jsonMap.put("value","0");
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}
		
		//检测该商品赠品数量是否足够用于赠送
		List<HsCommodityRule> crList = hsCommodityRuleService.queryRulesById(map);
		int sum = 0;
		for(HsCommodityRule hsCommodityRule:crList){
			int type = hsCommodityRule.getComType();
			if(type==2){
				sum++;
			}
		}
		String[] gifts = new String[sum] ; //赠品信息数组
		int giftSum = 0;
		for(int i=0;i<crList.size();i++){
			HsCommodityRule hsCommodityRule = crList.get(i);
			Integer comType = hsCommodityRule.getComType();	//赠品数量
			//当商品类型为赠品时继续操作
			if(comType==2){
				giftSum++;
				Long exchId = hsCommodityRule.getExchangeId();      //赠品id
				String exchName = hsCommodityRule.getExchangeName();      //赠品名
				Long exchNum = hsCommodityRule.getExchangeNum();	//赠品数量
				map1.put("comId", exchId);							
				HsCommodity commodity=hsCommodityService.selectByPrimaryKey(comId,sysId);	//查询赠品当前流通数量
				Long giftCurrencyNum = commodity.getCurrencyNum();  //
				if(giftCurrencyNum>0){
					if(giftCurrencyNum>(textVal1/exchNum)){
						gifts[giftSum-1] = exchId+"|"+(long)(textVal1/exchNum)+"|"+exchName;
					}else{
						gifts[giftSum-1] = exchId+"|"+giftCurrencyNum+"|"+exchName;
					}
				}else{
					giftSum--;
				}
			}
		}
		
		
		HsCommodity commodity=new HsCommodity();
		commodity.setComId(comId);
		commodity.setCurrencyNum(textVal1);
		commodity.setFreezeNum(textVal1);
		commodity.setVersion(version);
		
		long userId=Integer.parseInt(request.getParameter("userId"));
		String userName=request.getParameter("userName");
//		long userId=(Long) session.getAttribute("id");
//		String userName=(String) session.getAttribute("username");
		
		
		HsExchangeGift gife=new HsExchangeGift();
		gife.setOrderId(id);
		gife.setBeExchangeId(comId);
		gife.setBeExchangeName(comName);
		gife.setBeExchangeNum(textVal1);
		gife.setUserId(userId);
		gife.setUserName(userName);
		gife.setState(1);
		gife.setSplitId(gifts);
		
		
		HsExchangeOrder order=new HsExchangeOrder();
		order.setId(id);
		order.setOrderType(1);
		order.setUserId(userId);
		order.setUserName(userName);
		order.setBeExchangeId(comId);
		order.setBeExchangeName(comName);
		order.setExchangeId(exchangeId);
		order.setExchangeName(exchangeName);
		order.setOrderState(1);
		order.setOrderTime(new Date());
		order.setBeExchangeNum(textVal1);
		order.setExchangeNum(exchangeNum);
		order.setAddress(address);
		
		int result =hsCommodityService.updateAndInsert(commodity, order,gife);
		if(result==0){
			jsonMap.put("msg","操作成功！订单申请中" );
		    jsonMap.put("value","1");
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}
		jsonMap.put("msg","操作失败！订单申请失败，请重新申请！" );
	    jsonMap.put("value","0" );
	    jsonMap.put("data",maps);
	    jsonMap.put("rows",0);
	    return jsonMap;
	}
	
	
	/**
	 * 兑换查询，
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exQueryListI", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> exQueryList(HttpServletRequest request,HttpServletResponse response
    		,String pages,String userId,String rows) throws IOException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
		int page=Integer.parseInt((pages.equals("0")) ? "1" :pages);
		int no=Integer.parseInt((rows.equals("0")|| "0".equals(rows)) ? "50" : rows);
		int end = page * no;
		//从第几页开始读取数据
		int start = end -no;
		map.put("limit", no);
		map.put("offset", start );
//		HttpSession session=request.getSession();
//		long userId=(Long) session.getAttribute("id");
		map.put("userId",Integer.parseInt(userId));
//			if(map.get("flag").equals(1)){//为1表示该操作为撤单               根据订单Id更新订单状态
//				int orderId=Integer.parseInt((String) map.get("orderId"));
//				HsExchangeOrder order= new HsExchangeOrder();
//				order.setId((long)orderId);
//				order.setOrderState(4);//撤单
//				int result=hsCommodityService.selectGiftAndUpdateCommodity(order);
//				
//			}
		List<HsExchangeOrder> orderList=hsCommodityService.selectByUserId(map);
		int count=hsCommodityService.selectCountByUserId(map);
		float tPage= (float)count / no;
		int totalPage=(int) Math.ceil(tPage);
		
		maps.put("list", orderList);
		
		jsonMap.put("msg","操作成功，返回订单列表！" );
	    jsonMap.put("value","1" );
//	    jsonMap.put("data",JsonUtil.map2Json(maps));
	    jsonMap.put("data",maps);
	    jsonMap.put("rows",count);
		
		} catch (Exception e) {
			// TODO: handle exception
			jsonMap.put("msg","查询订单结果失败！" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",new HashMap());
		    jsonMap.put("rows",0);
		}
//	    return JsonUtil.map2Json(jsonMap);
	    return jsonMap;
	}
	
	/**
	 * 撤单操作
	 * @param request
	 * @param response
	 * @param orderId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/cancelOrderI", method = RequestMethod.POST)
	@ResponseBody
	   public Map<String, Object> cancelOrder(HttpServletRequest request,HttpServletResponse response
	    	,String orderId) throws IOException{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		int orderId1=Integer.parseInt(orderId);
		HsExchangeOrder order= new HsExchangeOrder();
		order.setId((long)orderId1);
		order.setOrderState(4);//撤单
		HsExchangeOrder hsExchangeOrder= hsExchangeOrderService.selectByPrimaryKey(order);
		if(hsExchangeOrder==null||hsExchangeOrder.equals("")||(hsExchangeOrder.getOrderState()!=1)){
			jsonMap.put("msg","撤单失败！该订单状态已不能被撤单" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}
		int result=hsCommodityService.selectGiftAndUpdateCommodity(order);
		if(result != 0){
			jsonMap.put("msg","撤单失败！" );
		    jsonMap.put("value","0" );
		    jsonMap.put("data",maps);
		    jsonMap.put("rows",0);
		    return jsonMap;
		}
		
		jsonMap.put("msg","撤单成功！" );
	    jsonMap.put("value","1" );
	    jsonMap.put("data",maps);
	    jsonMap.put("rows",0);
		return jsonMap;
	}
		
//	/**
//	 * 
//	 * @param request
//	 * @param response
//	 * @param map
//	 * @return
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "/showPicActionI", method = RequestMethod.POST)
//	@ResponseBody
//    public byte[] showPicAction(HttpServletRequest request,HttpServletResponse response
//    		,Map<String, Object> map) throws IOException{
//		String fileName=request.getParameter("picName");
//		
//		String picPath=constantList.UPLOAD_FILEPATH+fileName;
//		if(fileName.equals("")||fileName==null){
//			picPath=constantList.UPLOAD_FILEPATH+DateUtils.DateToString(new Date(), "yyyy-MM-dd H:m:s")+".png";
//		}
//		OutputStream output = null;
//		BufferedInputStream bis = null;
//		BufferedOutputStream bos = null;
//		InputStream imageIn = null;
//		try{
//			response.reset();
//			output = response.getOutputStream();// 得到输出流
//			String path = picPath.substring(picPath.indexOf(".") + 1, picPath.length());
//			
//			response.setContentType((String) map.get(path.toLowerCase()));
//			
//			File file=new File(picPath);
//			if (!file.exists()) {
//				file.mkdirs();
//				file.createNewFile();
//			}
//			imageIn = new FileInputStream(file);
//			bis = new BufferedInputStream(imageIn);// 输入缓冲流
//			bos = new BufferedOutputStream(output);// 输出缓冲流
//			byte data[] = new byte[4096];// 缓冲字节数
//			int size = 0;
//			size = bis.read(data);
//			while (size != -1) {
//				bos.write(data, 0, size);
//				size = bis.read(data);
//			}
//			bos.flush();// 清空输出缓冲流
//			return data;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if (imageIn != null) {
//				try {
//					imageIn.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (bis != null) {
//				try {
//					bis.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (bos != null) {
//				try {
//					bos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		return null;
//	}

//	public static void main(String[] args) {
//		
//		String json = "{\"data\":{\"list\":[{\"backup1\":\"\",\"beExchangeNum\":200,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":185,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":1,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1434353600795,\"minutes\":33,\"seconds\":20,\"hours\":15,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":1,\"date\":15},\"exchangeNum\":2000,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"中国北京\",\"orderTime1\":\"2015-06-15 03:33:20\",\"orderState1\":\"申请中\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":100,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":182,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":1,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1434353174434,\"minutes\":26,\"seconds\":14,\"hours\":15,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":1,\"date\":15},\"exchangeNum\":1000,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"中国北京\",\"orderTime1\":\"2015-06-15 03:26:14\",\"orderState1\":\"申请中\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":50,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":180,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":1,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1434340747731,\"minutes\":59,\"seconds\":7,\"hours\":11,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":1,\"date\":15},\"exchangeNum\":500,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"22\",\"orderTime1\":\"2015-06-15 11:59:07\",\"orderState1\":\"申请中\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":15,\"exchangeName\":\"茶叶1\",\"backTime\":null,\"id\":177,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100001,\"backup2\":\"\",\"beExchangeName\":\"茶杯1\",\"orderState\":4,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1434024337314,\"minutes\":5,\"seconds\":37,\"hours\":20,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":4,\"date\":11},\"exchangeNum\":75,\"issuedDate\":null,\"beExchangeId\":100006,\"address\":\"北京北京\",\"orderTime1\":\"2015-06-11 08:05:37\",\"orderState1\":\"撤单\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":30,\"exchangeName\":\"茶叶1\",\"backTime\":null,\"id\":176,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100001,\"backup2\":\"\",\"beExchangeName\":\"茶杯1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433991255203,\"minutes\":54,\"seconds\":15,\"hours\":10,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":4,\"date\":11},\"exchangeNum\":150,\"issuedDate\":null,\"beExchangeId\":100006,\"address\":\"北京通州梨园\",\"orderTime1\":\"2015-06-11 10:54:15\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":20,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":175,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433932036151,\"minutes\":27,\"seconds\":16,\"hours\":18,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":3,\"date\":10},\"exchangeNum\":200,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"22\",\"orderTime1\":\"2015-06-10 06:27:16\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":102,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":174,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433924106599,\"minutes\":15,\"seconds\":6,\"hours\":16,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":3,\"date\":10},\"exchangeNum\":1020,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"110\",\"orderTime1\":\"2015-06-10 04:15:06\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":12,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":173,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433923856358,\"minutes\":10,\"seconds\":56,\"hours\":16,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":3,\"date\":10},\"exchangeNum\":120,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"11111111111111\",\"orderTime1\":\"2015-06-10 04:10:56\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":22,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":171,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433923509414,\"minutes\":5,\"seconds\":9,\"hours\":16,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":3,\"date\":10},\"exchangeNum\":220,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"3333\",\"orderTime1\":\"2015-06-10 04:05:09\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"},{\"backup1\":\"\",\"beExchangeNum\":11,\"exchangeName\":\"天赐臻宝\",\"backTime\":null,\"id\":166,\"userId\":1089815,\"userName\":\"李权\",\"backup8\":\"\",\"giftName\":\"\",\"backup7\":\"\",\"giftId\":0,\"backup6\":\"\",\"backup5\":\"\",\"giftNum\":0,\"backup4\":\"\",\"backup3\":\"\",\"exchangeId\":100002,\"backup2\":\"\",\"beExchangeName\":\"茶叶1\",\"orderState\":2,\"rightsName\":\"\",\"orderType\":1,\"rightsCode\":0,\"orderTime\":{\"time\":1433923139786,\"minutes\":58,\"seconds\":59,\"hours\":15,\"month\":5,\"year\":115,\"timezoneOffset\":-480,\"day\":3,\"date\":10},\"exchangeNum\":110,\"issuedDate\":null,\"beExchangeId\":100001,\"address\":\"333333333\",\"orderTime1\":\"2015-06-10 03:58:59\",\"orderState1\":\"兑换成功\",\"availableNum\":0,\"rightsAccount\":\"\"}]},\"value\":\"1\",\"msg\":\"操作成功，返回订单列表！\",\"rows\":16}";
//		String json1 = "{\"data\":\"{\\\"list\\\":[{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":200,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":185,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":1,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1434353600795,\\\"minutes\\\":33,\\\"seconds\\\":20,\\\"hours\\\":15,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":1,\\\"date\\\":15},\\\"exchangeNum\\\":2000,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"中国北京\\\",\\\"orderTime1\\\":\\\"2015-06-15 03:33:20\\\",\\\"orderState1\\\":\\\"申请中\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":100,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":182,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":1,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1434353174434,\\\"minutes\\\":26,\\\"seconds\\\":14,\\\"hours\\\":15,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":1,\\\"date\\\":15},\\\"exchangeNum\\\":1000,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"中国北京\\\",\\\"orderTime1\\\":\\\"2015-06-15 03:26:14\\\",\\\"orderState1\\\":\\\"申请中\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":50,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":180,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":1,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1434340747731,\\\"minutes\\\":59,\\\"seconds\\\":7,\\\"hours\\\":11,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":1,\\\"date\\\":15},\\\"exchangeNum\\\":500,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"22\\\",\\\"orderTime1\\\":\\\"2015-06-15 11:59:07\\\",\\\"orderState1\\\":\\\"申请中\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":15,\\\"exchangeName\\\":\\\"茶叶1\\\",\\\"backTime\\\":null,\\\"id\\\":177,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100001,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶杯1\\\",\\\"orderState\\\":4,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1434024337314,\\\"minutes\\\":5,\\\"seconds\\\":37,\\\"hours\\\":20,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":4,\\\"date\\\":11},\\\"exchangeNum\\\":75,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100006,\\\"address\\\":\\\"北京北京\\\",\\\"orderTime1\\\":\\\"2015-06-11 08:05:37\\\",\\\"orderState1\\\":\\\"撤单\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":30,\\\"exchangeName\\\":\\\"茶叶1\\\",\\\"backTime\\\":null,\\\"id\\\":176,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100001,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶杯1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433991255203,\\\"minutes\\\":54,\\\"seconds\\\":15,\\\"hours\\\":10,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":4,\\\"date\\\":11},\\\"exchangeNum\\\":150,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100006,\\\"address\\\":\\\"北京通州梨园\\\",\\\"orderTime1\\\":\\\"2015-06-11 10:54:15\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":20,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":175,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433932036151,\\\"minutes\\\":27,\\\"seconds\\\":16,\\\"hours\\\":18,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":3,\\\"date\\\":10},\\\"exchangeNum\\\":200,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"22\\\",\\\"orderTime1\\\":\\\"2015-06-10 06:27:16\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":102,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":174,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433924106599,\\\"minutes\\\":15,\\\"seconds\\\":6,\\\"hours\\\":16,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":3,\\\"date\\\":10},\\\"exchangeNum\\\":1020,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"110\\\",\\\"orderTime1\\\":\\\"2015-06-10 04:15:06\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":12,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":173,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433923856358,\\\"minutes\\\":10,\\\"seconds\\\":56,\\\"hours\\\":16,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":3,\\\"date\\\":10},\\\"exchangeNum\\\":120,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"11111111111111\\\",\\\"orderTime1\\\":\\\"2015-06-10 04:10:56\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":22,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":171,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433923509414,\\\"minutes\\\":5,\\\"seconds\\\":9,\\\"hours\\\":16,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":3,\\\"date\\\":10},\\\"exchangeNum\\\":220,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"3333\\\",\\\"orderTime1\\\":\\\"2015-06-10 04:05:09\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"},{\\\"backup1\\\":\\\"\\\",\\\"beExchangeNum\\\":11,\\\"exchangeName\\\":\\\"天赐臻宝\\\",\\\"backTime\\\":null,\\\"id\\\":166,\\\"userId\\\":1089815,\\\"userName\\\":\\\"李权\\\",\\\"backup8\\\":\\\"\\\",\\\"giftName\\\":\\\"\\\",\\\"backup7\\\":\\\"\\\",\\\"giftId\\\":0,\\\"backup6\\\":\\\"\\\",\\\"backup5\\\":\\\"\\\",\\\"giftNum\\\":0,\\\"backup4\\\":\\\"\\\",\\\"backup3\\\":\\\"\\\",\\\"exchangeId\\\":100002,\\\"backup2\\\":\\\"\\\",\\\"beExchangeName\\\":\\\"茶叶1\\\",\\\"orderState\\\":2,\\\"rightsName\\\":\\\"\\\",\\\"orderType\\\":1,\\\"rightsCode\\\":0,\\\"orderTime\\\":{\\\"time\\\":1433923139786,\\\"minutes\\\":58,\\\"seconds\\\":59,\\\"hours\\\":15,\\\"month\\\":5,\\\"year\\\":115,\\\"timezoneOffset\\\":-480,\\\"day\\\":3,\\\"date\\\":10},\\\"exchangeNum\\\":110,\\\"issuedDate\\\":null,\\\"beExchangeId\\\":100001,\\\"address\\\":\\\"333333333\\\",\\\"orderTime1\\\":\\\"2015-06-10 03:58:59\\\",\\\"orderState1\\\":\\\"兑换成功\\\",\\\"availableNum\\\":0,\\\"rightsAccount\\\":\\\"\\\"}]}\",\"value\":\"1\",\"msg\":\"操作成功，返回订单列表！\",\"rows\":16}";
//		Map map = JsonUtil.jsonToMap(json1);
//		String str = (String) map.get("data");
//		Map map1 = JsonUtil.jsonToMap(str);
//		List<HsExchangeOrder> orderList = (List<HsExchangeOrder>) map1.get("list");
//		System.out.println(map.get("data"));
//		System.out.println(map.get("value"));
//		System.out.println(map.get("msg"));
//		System.out.println(map1.get("list"));
//		System.out.println(orderList);
//		for(HsExchangeOrder hsExchangeOrder:orderList){
//			String str1 = hsExchangeOrder.getAddress();
//			System.out.println(str1);
//		}
//	}
	
}
