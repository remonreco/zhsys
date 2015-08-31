package com.cbice.distribute.mgr.web.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

import com.cbice.distribute.core.db.model.HsCommodityApproval;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.service.TCommodityApprovalService;

/**
 * 商品兑换审核管理
 * @author zj
 *
 */
@Controller
public class CommodityApprovalController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	TCommodityApprovalService tCommodityApprovalService;
	/**
	 * 跳转至商品审核列表页面
	 * @return
	 */
	@RequestMapping("/commodityApproval")
	public String commodityApprovalList() {
		return "commodityApprovalList";
	}
	
	/**
	 * 跳转至商品审核列表页面（不显示数据）
	 * @return
	 */
	@RequestMapping("/queryCommodityApprovalFirst")
	public Map<String, Object> toCommodityApproval() {
		
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		List<Map<String, Object>> list = null;
		jsonmap.put("rows", list);
		jsonmap.put("total", 0);
		return jsonmap;
	}
	

	/**
	 * 跳转至商品审核列表页面（显示数据）
	 * @param response
	 * @param page
	 * @param rows
	 * @param customerName
	 * @param exchangeName
	 * @param approvalTimeStart
	 * @param approvalTimeEnd
	 * @return
	 */
	@RequestMapping("/queryCommodityApproval")
	@ResponseBody
	public  Map<String, Object> queryCommodity(HttpServletResponse response,String page,
			String rows, String customerName, String exchangeName, String approvalTimeStart, String approvalTimeEnd,String approvalresult) {
		
		response.setContentType("text/html;charset=utf-8");
		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		map.put("customerName", customerName);//  兑请者
		map.put("exchangeName", exchangeName);// 兑换物名
		Date approvalTimeStart1 = null;
		Date approvalTimeEnd1 = null;
		if(approvalTimeStart!=null&&!"".equals(approvalTimeStart)){
			approvalTimeStart1 = DateUtils.StringToDate(approvalTimeStart,"yyyy-MM-dd");
			map.put("approvalTimeStart", approvalTimeStart1);
		}
		if(approvalTimeEnd!=null&&!"".equals(approvalTimeEnd)){
			approvalTimeEnd1 = DateUtils.StringToDate(approvalTimeEnd,"yyyy-MM-dd");
			map.put("approvalTimeEnd", DateUtils.getAnyDayByNo(approvalTimeEnd1, 1));
		}
		map.put("limit", no);
		map.put("offset", start - 1);
		
		if(approvalresult!=null&&approvalresult.equals("1")){ //当审批完成后查询当日审批数据；
			Date approvalTime = DateUtils.StringToDate((DateUtils.DateToString(new Date(), "yyyy-MM-dd")), "yyyy-MM-dd");
			map.put("approvalTimeStart",approvalTime);
			map.put("approvalTimeEnd",DateUtils.getAnyDayByNo(approvalTime, 1));
		}
		
		List<Map<String, Object>> list = tCommodityApprovalService.commodityApprovalQuery(map);
		
		if (end > list.size()) {
			end = list.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = tCommodityApprovalService.countCommodityApprovalQuery(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}
	
	/**
	 * 执行商品审批
	 * @return
	 */
	@RequestMapping("/doCommodityApproval")
	public @ResponseBody int doCommodityApproval() {
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		map.put("orderType",constantList.ORDER_TYPE_1);
		map.put("orderState", constantList.ORDER_STATE_1);
//		Date orderTimeStart = DateUtils.StringToDate((DateUtils.DateToString(new Date(), "yyyy-MM-dd")), "yyyy-MM-dd");
//		map.put("orderTimeStart",orderTimeStart);
//		map.put("orderTimeEnd",DateUtils.getAnyDayByNo(orderTimeStart, 1));
		try {
			result = tCommodityApprovalService.doCommodityApproval(map);
		} catch (Exception e) {
			// TODO: handle exception
			result = -1;
		}
		return result;
	}
	
	/**
	 * 下载今日审批成功数据
	 * 
	 * @param response
	 * @param goodsName
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping("/downLoadApprovalSuccess")
	@ResponseBody
	public int downLoadApprovalSuccess(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Date approvalTime = DateUtils.StringToDate((DateUtils.DateToString(new Date(), "yyyy-MM-dd")), "yyyy-MM-dd");
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		map.put("approvalTimeStart",approvalTime);
		map.put("approvalTimeEnd",DateUtils.getAnyDayByNo(approvalTime, 1));
		map.put("approvalResult",constantList.APPROVAL_RESULT_1);
		List<HsCommodityApproval> list = tCommodityApprovalService.queryApprovalSuccess(map);
		if(null==list||list.size()==0){
			return 0;
		}
		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "今日审批-下载";

		String[] title = new String[] { "股权市场", "产品代码", "权益账号", "流通股数", "非流通股数", "处理级别" };
		String[] field = new String[] { "getStockMarket", "getProductCode", "getRightsAccount", "getUseNum", "getNoCurrencyNum", "getHandleClass"};

		try {
			// 步骤2.根据条件生成文件
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(bos);

			int totle = list.size();// 获取List集合的size
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
					// 设置第五列单元格列宽
					sheet.setColumnView(4, 12);
					sheet.addCell(label);
				}
				// 循环获取数据
				int line = 2;// 行号
				if (list != null && list.size() > 0) {
					for (int i = (x*mus); i < m+1; i++) {
						int row = 0;//
						for (int k = 0; k < field.length; k++) {
							Method method = list.get(i).getClass().getMethod(field[k], null);
							Object ob = method.invoke(list.get(i), null);
//							if (k == 1 || k == 3) {
//								jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format((Double) ob)), wcfN); // 格式化数值
//								sheet.addCell(labelNF);
//								row++;
//								continue;
//							}
							if (k == 3) {
								sheet.addCell(new Label(row, line, nullObjectFormat((Long)ob*(-1), ""), wcsH));
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
			logger.error("今日审批下载", e1);
		}
		return 1;
	}
	
	/**
	 * 下载今日审批成功的兑换数据
	 * 
	 * @param response
	 * @param goodsName
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping("/downLoadApprovalSuccessEx")
	@ResponseBody
	public String downLoadApprovalSuccessEx(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Date approvalTime = DateUtils.StringToDate((DateUtils.DateToString(new Date(), "yyyy-MM-dd")), "yyyy-MM-dd");
		map.put("sysId", constantList.SYSTEM_ID);// 系统ID
		map.put("approvalTimeStart",approvalTime);
		map.put("approvalTimeEnd",DateUtils.getAnyDayByNo(approvalTime, 1));
		map.put("approvalResult",constantList.APPROVAL_RESULT_1);
		List<HsCommodityApproval> list = tCommodityApprovalService.queryApprovalSuccessEx(map);
		if(null==list||list.size()==0){
			return "0";
		}
		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "今日兑换信息-下载";

		String[] title = new String[] { "客户姓名", "兑换物名称", "兑换物数量", "赠品及数量", "地址", "联系电话" };
		String[] field = new String[] { "getCustomerName", "getExchangeName", "getExchangeNum", "getGifts", "getAddress", "getTel"};

		try {
			// 步骤2.根据条件生成文件
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(bos);

			int totle = list.size();// 获取List集合的size
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
					// 设置第五列单元格列宽
					sheet.setColumnView(0, 9);
					sheet.setColumnView(1, 12);
					sheet.setColumnView(2, 12);
					sheet.setColumnView(3, 30);
					sheet.setColumnView(4, 25);
					sheet.setColumnView(5, 12);
					sheet.addCell(label);
				}
				// 循环获取数据
				int line = 2;// 行号
				if (list != null && list.size() > 0) {
					for (int i = (x*mus); i < m+1; i++) {
						int row = 0;//
						for (int k = 0; k < field.length; k++) {
							Method method = list.get(i).getClass().getMethod(field[k], null);
							Object ob = method.invoke(list.get(i), null);
//							if (k == 1 || k == 3) {
//								jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format((Double) ob)), wcfN); // 格式化数值
//								sheet.addCell(labelNF);
//								row++;
//								continue;
//							}
							if (k == 2) {
								sheet.addCell(new Label(row, line, nullObjectFormat((Long)ob, ""), wcsH));
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
			logger.error("今日审批兑换信息下载", e1);
		}
		return "1";
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

}
