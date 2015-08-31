package com.cbice.distribute.agency.web.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
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

import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.THisOtcrealtime;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.THisOtcrealtimeService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.agency.security.model.TlowerUser;
import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.TgoodsOrderService;
import com.cbice.distribute.agency.service.TremainderGoodsService;
import com.cbice.distribute.agency.service.WebSysUserService;

@Controller
public class ReturnGoodsController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TgoodsOrderService goodsOrderService;

	@Resource
	private TRemainderGoodsService tRemainderGoodsService;

	@Resource
	private TremainderGoodsService tremainderGoodsService;

	@Resource
	private TUserService tUserService;

	@Resource
	private TAgencyDbService tAgencyDbService;
	@Resource
	private THisOtcrealtimeService tHisOtcrealtimeService;

	/*
	 * 跳转到本级退货申请管理页面
	 */
	@RequestMapping("/toReturnsGoods")
	public String toReturnsGoods() {
		return "remainderGoodsApplication";
	}

	/*
	 * 跳转到下级级退货申请管理页面
	 */
	@RequestMapping("/toLowReturnsGoods")
	public String toLowReturnsGoods() {
		return "returnGoodsOrderList";
	}

	/*
	 * 本级退货申请管理页面查询
	 */
	@RequestMapping("/queryRemainderGoodsApplication")
	public @ResponseBody
	Map<String, Object> queryReturnGoodsApplication(String page, String rows, String dealerName, String goodsName, String goodsId) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("goodsName", goodsName);// 产品名称
		map.put("goodsId", goodsId);// 产品编号
		map.put("dealerName", dealerName);// 所属经销商名称
		map.put("limit", no);
		map.put("offset", start - 1);

		// 获取当前登录人的ID
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();

		TUser tu = tUserService.selectByPrimaryKey(user_id);

		if (tu == null) {
			return null;
		}

		map.put("agency_id", tu.getAgencyId());
		TAgency ta = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		if (ta == null) {
			return null;
		}
		map.put("level", ta.getDealerLevel());

		List<Map<String, Object>> list1 = tRemainderGoodsService.selectRemainderGoodsApplcation(map);

		int count = tRemainderGoodsService.selectRemainderGoodsApplcationCount(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list1);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/*
	 * 本级退货申请
	 */
	@RequestMapping("/remainderGoodsApplication")
	@ResponseBody
	public String remainderGoodsApplication(HttpServletRequest request, HttpServletResponse response, String t_goods_num, Long user_id, Long goods_num, String returnGoodsReason, String goods_name) {

		try {
			if (user_id != null) {
				return tremainderGoodsService.updateByPrimaryKeySelective(t_goods_num, user_id, goods_num, returnGoodsReason, goods_name) + "";
			}
		} catch (Exception e) {
			logger.error("申请错误", e);
		}
		return "0";
	}

	/*
	 * 查询退货清单
	 */
	@RequestMapping("/queryReturnGoodsOrder")
	public @ResponseBody
	Map<String, Object> queryReturnGoodsOrder(String page, String rows, TGoodsOrder goodsorder, String dealerName) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("goodsname", goodsorder.getGoodsName());// 产品名称
		map.put("goodsnum", goodsorder.getGoodsNum());// 产品编号
		map.put("dealerName", dealerName);// 所属经销商名称
		map.put("return_goods_state", goodsorder.getReturnGoodsState());// 审核状态
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();

		TUser tu = tUserService.selectByPrimaryKey(user_id);
		map.put("agencyId", tu.getAgencyId());
		map.put("limit", no);
		map.put("offset", start - 1);

		List<Map<String, Object>> list = goodsOrderService.selectReturnGoodsList(map);
		int count = goodsOrderService.selectReturnGoodsListCount(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/*
	 * 退货清单申请审核 /**
	 * 
	 * @param id
	 * 
	 * @param return_goods_state 0，申请，1审核通过，2审核不通过
	 * 
	 * @param
	 * 
	 * @return
	 */
	@RequestMapping("/checkGoodsOrder")
	@ResponseBody
	public String checkGoodsOrder(HttpServletRequest request, HttpServletResponse response, Long id, Integer returnGoodsState, String return_goods_options, Long agency_id, Long higer_dealer_id, Long distribute_num, String goods_num) {

		try {
			if (id != null) {
				return goodsOrderService.updateByPrimaryKeySelective(id, returnGoodsState, return_goods_options, agency_id, higer_dealer_id, distribute_num, goods_num) + "";
			}
		} catch (Exception e) {
			logger.error("通过或拒绝审核错误", e);
		}
		return "0";
	}

	/*
	 * 跳转到剩余产品信息页
	 */
	@RequestMapping("/tolastGoods")
	public String toLastsGoods() {
		return "remainderGoods";
	}

	
	/*
	 * 跳转到每日佣金汇总查询页面
	 */
	@RequestMapping("/commisionSum")
	public String commisionSum() {
		return "commisionSum";
	}
	/*
	 * 跳转到每日佣金查询页面
	 */
	@RequestMapping("/commision")
	public String Commision() {
		return "commision";
	}

	/**
	 * 每日佣金查询
	 * 
	 * @param page
	 * @param rows
	 * @param dealerName
	 * @param goodsName
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/queryCommision")
	@ResponseBody
	public Map<String, Object> queryCommision(HttpServletResponse response, String page, String rows, String clientId, String otcCode, String developer, String initDateStart, String initDateEnd, String data ,boolean flag) {

		response.setContentType("text/html;charset=utf-8");

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		int initDateStart1 = 0;
		int initDateEnd1 = 0;
		if(StringUtils.isEmpty(clientId)&&StringUtils.isEmpty(otcCode)&&StringUtils.isEmpty(developer)&&StringUtils.isEmpty(initDateEnd)&&StringUtils.isEmpty(initDateStart)&&!flag){
			int maxDate=tHisOtcrealtimeService.queryMaxdate();
			initDateEnd1=maxDate;
			initDateStart1=maxDate;
		}
		
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
		}
		
		map.put("clientId", clientId);// 序列号
		map.put("otcCode", otcCode);// 产品编号
		map.put("initDateStart", initDateStart1);
		map.put("initDateEnd", initDateEnd1);
		map.put("limit", no);
		map.put("offset", start - 1);
		map.put("developer", developer);

		// 获取当前登录人的ID
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();

		TUser tu = tUserService.selectByPrimaryKey(user_id);

		if (tu == null) {
			return null;
		}
		TAgency agency = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		map.put("agency_id", tu.getAgencyId());
		String developer1 = agency.getDealerNum();
		if (developer1.equals("GDGJ-010") || developer1.equals("KYSJ-010")) {
			developer1 = developer1.substring(0, 5);
			map.put("developer1", developer1);
		} else if (developer1.equals("SCPT-2014-002")) {
			map.put("developer1", developer1);
			map.put("developer2", "JJ—2014—011");
			map.put("developer3", "JJZS-HXXY-2014-001");
		} else {
			String temp = developer1.substring(0, 5);
			if("SCPT-".equals(temp)){
				map.put("developer1", developer1.substring(developer1.length()-4));
			}else{
			map.put("developer1", developer1);
			}
		}
		TAgency ta = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		if (ta == null) {
			return null;
		}
		map.put("level", ta.getDealerLevel());

		if (data != null && data.equals("down")) {
			if (clientId != null && clientId.equals("")) {
				clientId = null;
				map.put("clientId", clientId);// 序列号
			}
			if (otcCode != null && otcCode.equals("")) {
				otcCode = null;
				map.put("otcCode", otcCode);// 产品编号
			}

			List<THisOtcrealtime> list2 = tHisOtcrealtimeService.commissionQueryAll(map);

			loadoutCommisionDetail(response, list2);
		}

		List<Map<String, Object>> list1 = tHisOtcrealtimeService.CommissionQuery(map);

		if (end > list1.size()) {
			end = list1.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = tHisOtcrealtimeService.countSelectOrderBy(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list1);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 导出佣金明细
	 * 
	 * @param response
	 * @param goodsName
	 * @param goodsNum
	 * @return
	 */
	// @RequestMapping("/loadoutCommisionDetail")
	// @ResponseBody
	public Object loadoutCommisionDetail(HttpServletResponse response, List<THisOtcrealtime> lowerList) {
		response.setContentType("text/html;charset=utf-8");

		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "佣金明细-下载";

		String[] title = new String[] { "经销商编号", "权益代码", "客户账号", "客户姓名", "总成交量", "总成交额", "佣金", "当前持仓", "佣金发生日" };
		String[] field = new String[] { "getDeveloper", "getOtc_code", "getClient_id", "getClient_name", "getCjsl", "getCjje", "getFare0", "getCurrent_amount", "getNature_date" };

		try {
			// 步骤2.根据条件生成文件
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(bos);

			int totle = lowerList.size();// 获取List集合的size
			int mus = 65534;// 每个工作表格最多存储2条数据（注：excel表格一个工作表可以存储65536条）
			int avg = (int)Math.ceil(totle / (mus*1.0));//页数
			int m=0;
			for (int x = 0; x < avg; x++) {
				
				if(totle<=mus){
					m=totle-1;
				}
				if(totle>mus){
					if((x+1)*mus<totle){
						m=(x+1)*mus-1;
					}
					else{
						m=totle-1;
					}
					
				}

				WritableSheet sheet = wwb.createSheet(titleName + (x + 1), x); // 创建一个可写入的工作表
				// 添加表头
				
				Label label;
	
				CellView cellView = new CellView();
				cellView.setAutosize(true);
				sheet.setColumnView(0, cellView);
	
				// 用于标题
				int charTitle = 15;// 标题字体大小
				WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), charTitle, WritableFont.BOLD);
				WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
				// 用于内容
				WritableCellFormat wcsH = new WritableCellFormat();
				wcsH.setWrap(true); // 是否换行
				wcsH.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 加边框
				wcsH.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居左
	
				DecimalFormat df = new DecimalFormat("0.00");
				jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00"); // 设置数字格式
				jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf); // 设置表单格式
				wcfN.setWrap(true); // 是否换行
				wcfN.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 加边框
				wcfN.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居左
	
				// 标题
				sheet.addCell(new Label(0, 0, titleName, titleFormat));
				// 准备设置excel工作表的标题
				for (int i = 0; i < title.length; i++) {
					// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是z
					// 在Label对象的子对象中指明单元格的位置和内容
					label = new Label(i, 1, title[i], wcsH);
					// 将定义好的单元格添加到工作表中
					// 设置第八列单元格列宽
					sheet.setColumnView(8, 14);
					sheet.addCell(label);
				}
				// 循环获取数据
				int line = 2;// 行号
				if (lowerList != null && lowerList.size() > 0) {
					for (int i = (x*mus); i < m+1; i++) {
						int row = 0;//
						for (int k = 0; k < field.length; k++) {
							Method method = lowerList.get(i).getClass().getMethod(field[k], null);
							Object ob = method.invoke(lowerList.get(i), null);
							if (k == 5 || k == 6) {
								jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format((Double) ob)), wcfN); // 格式化数值
								sheet.addCell(labelNF);
								row++;
								continue;
							}
							sheet.addCell(new Label(row, line, nullObjectFormat(ob, ""), wcsH));
							row++;
						}
						line++;
					}
				}

			}
			// 写入数据
			wwb.write();
			// 关闭文件
			wwb.close();
			bos.flush();
			bos.close();
		} catch (Exception e1) {
			logger.error("佣金明细下载", e1);
		}
		return null;
	}

	/*
	 * 剩余产品信息页面查询
	 */
	@RequestMapping("/queryRemainderGoods")
	public @ResponseBody
	Map<String, Object> queryReturnGoods(String page, String rows, String dealerName, String goodsName, String goodsId) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("goodsName", goodsName);// 产品名称
		map.put("goodsId", goodsId);// 产品编号
		map.put("dealerName", dealerName);// 所属经销商名称
		map.put("limit", no);
		map.put("start", start - 1);

		// 获取当前登录人的ID
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();

		TUser tu = tUserService.selectByPrimaryKey(user_id);

		if (tu == null) {
			return null;
		}

		map.put("agency_id", tu.getAgencyId());
		TAgency ta = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		if (ta == null) {
			return null;
		}
		map.put("level", ta.getDealerLevel());

		// List<Map<String, Object>> lists = new ArrayList<Map<String,
		// Object>>();
		// for (Map map2 : list1) {
		// map2.put("userId", user_id);
		// String goodsName2 = (String) map2.get("goods_name");
		// String goodsNum2 = (String) map2.get("t_goods_num");
		// List<TGoodsOrder> orderlist =
		// goodsOrderService.selectOrderbyUserIdgoodsNameGoodsNum(map2);
		// long goodsZcount = 0l;
		// for (TGoodsOrder order : orderlist) {
		// goodsZcount = goodsZcount + order.getDistributeNum();
		// }
		// map2.put("goods_count", goodsZcount);
		// lists.add(map2);
		// }

		List<Map<String, Object>> list1 = tHisOtcrealtimeService.CommissionQuery(map);
		int count = tHisOtcrealtimeService.countSelectOrderBy(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list1);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 剩余产品信息-补发货跳转controller
	 * */
	@RequestMapping("/toReplacementGoods")
	public String toReplacementGoods(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");

		String goodsNames = request.getParameter("goodsName");
		String goodsName = java.net.URLDecoder.decode(goodsNames, "UTF-8");
		String goodsNum = request.getParameter("goodsNum");
		request.setAttribute("goodsName", goodsName);
		request.setAttribute("goodsNum", goodsNum);
		return "sendGoodsInformation";
	}

	/**
	 * 将String型的标准数据转换成Integer
	 * 
	 * @param t
	 * @return
	 */
	public Integer StringToNum(String t) {
		return Integer.parseInt(t.replace("-", ""));
	}

	/**
	 * 将字符串转成时间
	 * 
	 * @param t
	 * @return
	 */
	public Date stringToDate(String t) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = sdf.parse(t);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	/** 如果返回为空对象则转换为自定义字符串 */
	private static String nullObjectFormat(Object object, String string) {
		String str;
		if (object == null) {
			str = string;
		} else if (object.equals("null")) {
			str = string;
		} else {
			str = object.toString();
		}
		return str;
	}
	
	/**
	 * 导出佣金汇总
	 * 
	 * @param response
	 * @param goodsName
	 * @param goodsNum
	 * @return
	 */
	// @RequestMapping("/loadoutCommisionDetail")
	// @ResponseBody
	public Object loadoutCommisionDetailSum(HttpServletResponse response, List<THisOtcrealtime> lowerList) {
		response.setContentType("text/html;charset=utf-8");
		
		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName = nowTime + ".xls";
		String titleName = "佣金汇总-下载";

		String[] title = new String[] { "经销商编号","权益代码", "总成交量", "总成交额", "佣金", "当前持仓","佣金发生日"};
		String[] field = new String[] { "getDeveloper", "getOtc_code","getCjsl", "getCjje", "getFare0", "getCurrent_amount","getNature_date" };

		try {
			// 步骤2.根据条件生成文件
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(bos);
			// 添加第一个工作表并设置第一个Sheet的名字
			WritableSheet sheet = wwb.createSheet(titleName, 0);
			Label label;
			
			CellView cellView = new CellView();
			cellView.setAutosize(true);
			sheet.setColumnView(0, cellView);
			
			

			// 用于标题
			int charTitle = 15;// 标题字体大小
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), charTitle, WritableFont.BOLD);
			WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
			// 用于内容
			WritableCellFormat wcsH = new WritableCellFormat();
			wcsH.setWrap(true); // 是否换行
			wcsH.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 加边框
			wcsH.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居左
			
			
			
			DecimalFormat df=new DecimalFormat("0.00");
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("0.00");    //设置数字格式
			jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf); //设置表单格式  
			wcfN.setWrap(true); // 是否换行
			wcfN.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 加边框
			wcfN.setAlignment(jxl.format.Alignment.CENTRE);// 把水平对齐方式指定为居左
			

			// 标题
			sheet.addCell(new Label(0, 0, titleName, titleFormat));
			// 准备设置excel工作表的标题
			for (int i = 0; i < title.length; i++) {
				// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是z
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i, 1, title[i], wcsH);
				// 将定义好的单元格添加到工作表中
				//设置第八列单元格列宽
				sheet.setColumnView(8, 14);
				sheet.addCell(label);
			}
			// 循环获取数据
			int line = 2;// 行号
			if (lowerList != null && lowerList.size() > 0) {
				for (int i = 0; i < lowerList.size(); i++) {
					int row = 0;//
					for (int k = 0; k < field.length; k++) {
						Method method = lowerList.get(i).getClass().getMethod(field[k], null);
						Object ob = method.invoke(lowerList.get(i), null);
						if(k==3||k==4){
							jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format((Double)ob)), wcfN); //格式化数值
							sheet.addCell(labelNF);
							row++;
							continue;
						}
						
						sheet.addCell(new Label(row, line, nullObjectFormat(ob, ""), wcsH));
						row++;
					}
					line++;
				}
			}

			// 写入数据
			wwb.write();
			// 关闭文件
			wwb.close();
			bos.flush();
			bos.close();
		} catch (Exception e1) {
			logger.error("佣金明细下载", e1);
		}
		return null;
	}
	
	/**
	 * 每日佣金汇总查询
	 * 
	 * @param page
	 * @param rows
	 * @param dealerName
	 * @param goodsName
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/queryCommisionSum")
	@ResponseBody
	public Map<String, Object> queryCommisionSum(HttpServletResponse response,String page, String rows, String clientId, String otcCode, String developer, String initDateStart, String initDateEnd ,String data,boolean flag) {

		response.setContentType("text/html;charset=utf-8");

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		int initDateStart1 =0 ;
		int initDateEnd1 =0;
		if(StringUtils.isEmpty(clientId)&&StringUtils.isEmpty(otcCode)&&StringUtils.isEmpty(developer)&&StringUtils.isEmpty(initDateEnd)&&StringUtils.isEmpty(initDateStart)&&!flag){
			int maxDate=tHisOtcrealtimeService.queryMaxdate();
			initDateEnd1=maxDate;
			initDateStart1=maxDate;
		}
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
		}

		map.put("clientId", clientId);// 序列号
		map.put("otcCode", otcCode);// 产品编号
		map.put("initDateStart", initDateStart1);
		map.put("initDateEnd", initDateEnd1);
		map.put("limit", no);
		map.put("offset", start - 1);
		map.put("developer", developer);

		// 获取当前登录人的ID
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();

		TUser tu = tUserService.selectByPrimaryKey(user_id);

		if (tu == null) {
			return null;
		}
		TAgency agency = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		map.put("agency_id", tu.getAgencyId());
		String developer1 = agency.getDealerNum();
		if (developer1.equals("GDGJ-010")||developer1.equals("KYSJ-010")) {
			developer1 = developer1.substring(0, 5);
			map.put("developer1", developer1);
		}else if(developer1.equals("SCPT-2014-002")){
			map.put("developer1", developer1);
			map.put("developer2", "JJ—2014—011");
			map.put("developer3", "JJZS-HXXY-2014-001");
		}
		else {
			String temp = developer1.substring(0, 5);
			if("SCPT-".equals(temp)){
				map.put("developer1", developer1.substring(developer1.length()-4));
			}else{
			map.put("developer1", developer1);
			}
		}
		TAgency ta = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		if (ta == null) {
			return null;
		}
		map.put("level", ta.getDealerLevel());
		

		if (data != null && data.equals("down")) {
			if(clientId!=null&&clientId.equals("")){
				clientId=null;
				map.put("clientId", clientId);// 序列号
			}
			if(otcCode!=null&&otcCode.equals("")){
				otcCode=null;
				map.put("otcCode", otcCode);// 产品编号
			}
			
			List<THisOtcrealtime> list2 = tHisOtcrealtimeService.commissionQueryAllSum(map);
			
			loadoutCommisionDetailSum(response,list2);
		}


		List<Map<String, Object>> list1 = tHisOtcrealtimeService.CommissionQuerySum(map);
		
		
		if (end > list1.size()) {
			end = list1.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = tHisOtcrealtimeService.countSelectBySum(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list1);
		jsonmap.put("total", count);
		return jsonmap;
	}

}
