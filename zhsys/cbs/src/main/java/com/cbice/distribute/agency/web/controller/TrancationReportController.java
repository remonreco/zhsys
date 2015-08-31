package com.cbice.distribute.agency.web.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsExample;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.model.TrancationReport;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.agency.service.TAgencyService;
import com.cbice.distribute.agency.service.TgoodsBatchService;
import com.cbice.distribute.agency.service.TgoodsOrderService;
import com.cbice.distribute.agency.service.TgoodsService;
import com.cbice.distribute.agency.service.TuserService;

@Controller
public class TrancationReportController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TgoodsBatchService goodsBatchService;
	@Resource
	private TuserService userService;
	@Resource
	private TAgencyService tAgencyService;
	 @Resource
	 private TgoodsOrderService goodsOrderService;

	@RequestMapping("/trancationReportList")
	public String goodsList() {
		return "trancationReportList";
	}

	@RequestMapping("/querytrancationReport")
	public @ResponseBody Map<String, Object> queryTuser(String page,
			String rows, TGoodsBatch goods) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50"
				: rows);
		int end = intPage * no;
		int start = end - no + 1;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("tUserId", this.getUserInfo().gettUser().getAgencyId());//ICE会员ID
		map.put("goodsNum", goods.getGoodsNum());
		map.put("goodsName", goods.getGoodsName());
		map.put("id", goods.getId());
		map.put("limit", no);
		map.put("start", start - 1);

		List<TrancationReport> list=goodsBatchService.selectTrancationReport(map);
		int count =goodsBatchService.countSelectTrancationReport(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}

	/**
	 * 导出产品分发
	 * @param response
	 * @param goodsName
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping("/loadoutTrancationReport")
	@ResponseBody
	public Object loadoutTrancationReport(HttpServletResponse response,
			String id,String userId){
		
		TAgency agency=tAgencyService.selectByAgencyId(Long.parseLong(userId));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("agenylft", agency.getRgt());
		map.put("agenyrgt", agency.getLft());
		map.put("t_user_id", Long.parseLong(userId));
		TGoodsBatch goodbatch=goodsBatchService.selectByPrimaryKey(Long.parseLong(id));
		map.put("goodsNum", goodbatch.getGoodsNum());
		map.put("id", goodbatch.getId());
		List<TGoodsOrder> list=goodsOrderService.TranctionOut(map);
		List<TGoodsOrder>  newlist=new ArrayList<TGoodsOrder>();
		for(TGoodsOrder report:list){
			TUser user=userService.selectById(report.getUserId());
			report.setAssetsAccount(user.getAssetsAccount());
			report.setNonTradable("0");
			report.setProcessType("0");
			report.setStackMarke("01");
			if(report.getPayState()==0){
				report.setPayStateTostr("未支付");
			}else{
				report.setPayStateTostr("已支付");
			}
			newlist.add(report);
		}
		  if (list == null || list.size() == 0) {
	 			logger.info("查询到的数据为空");
	 			return null;
	 		}
	         
	         // 写文件
		 		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		 		String fileName = "发货信息"+nowTime + ".xls";
		 		String titleName = "交易报表数据-下载";

		 		String[] title = new String[] { "股权市场 ", "产品代码","权益账号","流通股数","非流通股数",
						"处理类别","所属经销商","是否支付"};
				String[] field = new String[] { "getStackMarke", "getGoodsNum", "getAssetsAccount",
						"getDistributeNum", "getNonTradable", "getProcessType","getDealerName","getPayStateTostr"};
		 			try {
						// 步骤2.根据条件生成文件
						OutputStream os = response.getOutputStream();
						response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition",
								"attachment; filename=".concat(fileName));
						BufferedOutputStream bos = new BufferedOutputStream(os);

						// 创建Excel工作薄
						WritableWorkbook wwb = Workbook.createWorkbook(bos);
						// 添加第一个工作表并设置第一个Sheet的名字
						WritableSheet sheet = wwb.createSheet(titleName, 0);
						Label label;

						// 用于标题
						int charTitle = 15;// 标题字体大小
						WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), charTitle, WritableFont.BOLD);
						WritableCellFormat titleFormat = new WritableCellFormat(titleFont);
						// 用于内容
						WritableCellFormat wcsH = new WritableCellFormat();
						wcsH.setWrap(true); // 是否换行
						wcsH.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN); // 加边框
						wcsH.setAlignment(jxl.format.Alignment.LEFT);// 把水平对齐方式指定为居左

						// 标题
						sheet.addCell(new Label(0, 0, titleName, titleFormat));
						// 准备设置excel工作表的标题
						for (int i = 0; i < title.length; i++) {
							// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是z
							// 在Label对象的子对象中指明单元格的位置和内容
							label = new Label(i, 1, title[i], wcsH);
							// 将定义好的单元格添加到工作表中
							sheet.addCell(label);
						}
						// 循环获取数据
						int line = 2;// 行号
						if (newlist != null && newlist.size() > 0) {
							for (int i = 0; i < newlist.size(); i++) {
								int row = 0;//
								for (int k = 0; k < field.length; k++) {
									Method method = newlist.get(i).getClass().getMethod(field[k], null);
									Object ob = method.invoke(newlist.get(i), null);

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
						logger.error("交易报表数据下载", e1);
					}
		return null;
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
