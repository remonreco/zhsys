package com.cbice.distribute.agency.web.controller;

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

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.AccessGoldSerice;
import com.cbice.distribute.core.db.model.TAccessGold;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;

/**
 * @author 冯志锋
 * @version 创建时间：2015年5月11日 下午2:08:14
 * @describe 出入金查询
 */

@Controller
public class AccessGoldController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private AccessGoldSerice accessGoldSerice;
	@Resource
	private TUserService tUserService;

	@Resource
	private TAgencyDbService tAgencyDbService;

	@RequestMapping("/toAccessGoldQuery")
	public String toAccessGoldQuery() {
		return "toAccessGoldQuery";
	}

	@RequestMapping("/toAccessGoldSumQuery")
	public String toAccessGoldSumQuery() {
		return "toAccessGoldSumQuery";
	}

	@RequestMapping("/queryAccessGold")
	public @ResponseBody
	Map<String, Object> queryAgencyBusiRoles(HttpServletResponse response, String page, String rows, String developer, String clientId, String initDateStart, String initDateEnd, String data ,boolean flag) {
		response.setContentType("text/html;charset=utf-8");
		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;
		int initDateStart1 = 0;
		int initDateEnd1 = 0;
		if(StringUtils.isEmpty(developer)&&StringUtils.isEmpty(clientId)&&StringUtils.isEmpty(initDateEnd)&&StringUtils.isEmpty(initDateStart)&&!flag){
			int maxDate=accessGoldSerice.queryMaxDate();
			initDateEnd1=maxDate;
			initDateStart1=maxDate;
		}
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("developer", developer);
		map.put("clientId", clientId);
		map.put("initDateStart", initDateStart1);
		map.put("initDateEnd", initDateEnd1);
		map.put("limit", no);
		map.put("offset", start - 1);

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

			map.put("developer1", developer1);
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
			if (developer != null && developer.equals("")) {
				developer = null;
				map.put("developer", developer);// 产品编号
			}

			List<TAccessGold> accessGoldList2 = accessGoldSerice.selectAccessGoldAll(map);
			downAccessGold(response, accessGoldList2);
		}

		List<Map> accessGoldList = accessGoldSerice.selectAccessGold(map);

		if (end > accessGoldList.size()) {
			end = accessGoldList.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = accessGoldSerice.selectAccessGoldCount(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", accessGoldList);
		jsonmap.put("total", count);

		return jsonmap;
	}

	/**
	 * 导出出入金明细
	 * 
	 * @param response
	 * @param accessGoldList
	 * @return
	 */
	private Object downAccessGold(HttpServletResponse response, List<TAccessGold> accessGoldList) {
		response.setContentType("text/html;charset=utf-8");

		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "出入金明细-下载";

		String[] title = new String[] { "经纪商", "客户账号", "客户姓名", "入金", "出金", "轧差", "持仓资金", "出入金日期" };
		String[] field = new String[] { "getDeveloper", "getClient_id", "getClient_name", "getIn_balance", "getOut_balance", "getDiff_balance", "getCurrent_balance", "getNature_date" };

		try {
			// 步骤2.根据条件生成文件
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(os);

			// 创建Excel工作薄
			WritableWorkbook wwb = Workbook.createWorkbook(bos);

			int totle = accessGoldList.size();
			int mus = 65534;// 每个工作表格最多存储2条数据（注：excel表格一个工作表可以存储65536条）
			int avg = (int) Math.ceil(totle / (mus * 1.0));// 页数
			int m = 0;
			for (int x = 0; x < avg; x++) {

				if (totle <= mus) {
					m = totle - 1;
				}
				if (totle > mus) {
					if ((x + 1) * mus < totle) {
						m = (x + 1) * mus - 1;
					} else {
						m = totle - 1;
					}

				}

				WritableSheet sheet = wwb.createSheet(titleName + (x + 1), x); // 创建一个可写入的工作表

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
					sheet.setColumnView(7, 14);
					sheet.addCell(label);
				}
				// 循环获取数据
				int line = 2;// 行号
				if (accessGoldList != null && accessGoldList.size() > 0) {
					for (int i = (x * mus); i < m + 1; i++) {
						int row = 0;//
						for (int k = 0; k < field.length; k++) {
							Method method = accessGoldList.get(i).getClass().getMethod(field[k], null);
							Object ob = method.invoke(accessGoldList.get(i), null);
							if (k >= 3 && k <= 6) {
								jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format(Double.parseDouble((String) ob))), wcfN); // 格式化数值
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
			logger.error("出入金明细下载", e1);
		}
		return null;

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

	@RequestMapping("/queryAccessGoldSum")
	public @ResponseBody
	Map<String, Object> queryAccessGoldSum(HttpServletResponse response, String page, String rows, String developer, String clientId, String initDateStart, String initDateEnd, String data ,boolean flag) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		int end = intPage * no;
		int start = end - no + 1;

		int initDateStart1 = 0;
		int initDateEnd1 = 0;
		if(StringUtils.isEmpty(developer)&&StringUtils.isEmpty(clientId)&&StringUtils.isEmpty(initDateEnd)&&StringUtils.isEmpty(initDateStart)&&!flag){
			int maxDate=accessGoldSerice.queryMaxDate();
			initDateEnd1=maxDate;
			initDateStart1=maxDate;
		}
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("developer", developer);
		map.put("clientId", clientId);
		map.put("initDateStart", initDateStart1);
		map.put("initDateEnd", initDateEnd1);
		map.put("limit", no);
		map.put("offset", start - 1);

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

			map.put("developer1", developer1);
		}
		TAgency ta = tAgencyDbService.selectByPrimaryKey(tu.getAgencyId());

		if (ta == null) {
			return null;
		}
		map.put("level", ta.getDealerLevel());

		if (data != null && data.equals("down")) {
			List<TAccessGold> accessGoldList = accessGoldSerice.selectAccessGoldAllSum(map);
			downAccessGoldSum(response, accessGoldList);
		}

		List<Map> accessGoldList = accessGoldSerice.selectAccessGoldSum(map);

		if (end > accessGoldList.size()) {
			end = accessGoldList.size();
			start = end - no + 1;
			map.put("offset", start - 1);
		}

		int count = accessGoldSerice.selectAccessGoldCountSum(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", accessGoldList);
		jsonmap.put("total", count);

		return jsonmap;
	}

	/**
	 * 导出出入金汇总
	 * 
	 * @param response
	 * @param accessGoldList
	 * @return
	 */
	private Object downAccessGoldSum(HttpServletResponse response, List<TAccessGold> accessGoldList) {
		response.setContentType("text/html;charset=utf-8");

		// 写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "出入金汇总-下载";

		String[] title = new String[] { "经纪商", "入金", "出金", "轧差", "持仓资金", "出入金日期" };
		String[] field = new String[] { "getDeveloper", "getIn_balance", "getOut_balance", "getDiff_balance", "getCurrent_balance", "getNature_date" };

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
				// sheet.setColumnView(7, 14);
				sheet.addCell(label);
			}
			// 循环获取数据
			int line = 2;// 行号
			if (accessGoldList != null && accessGoldList.size() > 0) {
				for (int i = 0; i < accessGoldList.size(); i++) {
					int row = 0;//
					for (int k = 0; k < field.length; k++) {
						Method method = accessGoldList.get(i).getClass().getMethod(field[k], null);
						Object ob = method.invoke(accessGoldList.get(i), null);
						if (k >= 1 && k <= 4) {
							jxl.write.Number labelNF = new jxl.write.Number(row, line, Double.parseDouble(df.format(Double.valueOf((String) ob))), wcfN); // 格式化数值
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
			logger.error("出入金汇总下载", e1);
		}
		return null;

	}

}
