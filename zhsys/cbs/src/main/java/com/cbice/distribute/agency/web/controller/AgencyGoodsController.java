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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.AgencyGoodService;
import com.cbice.distribute.agency.service.TAgencyService;
import com.cbice.distribute.core.db.model.HsAgencyGoods;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.model.GoodsDetail;
import com.cbice.distribute.core.service.THisOtcrealtimeService;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.DateUtils;

@Controller
public class AgencyGoodsController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TUserService tUserService;
	@Resource
	private TAgencyService tAgencyService;
	@Resource
	private AgencyGoodService agencyGoodsService;
	
	@Resource
	private THisOtcrealtimeService tHisOtcrealtimeService;

	@RequestMapping("/toAgencyGoods")
	public String AgencyGoods() {
		return "agencyGoods";
	}

	@RequestMapping("/queryAnencyGoods")
	public @ResponseBody
	List<HsAgencyGoods> queryAnencyGoods() {
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();
		TUser tu = tUserService.selectByPrimaryKey(user_id);
		TAgency ta= tAgencyService.selectByAgencyId(tu.getAgencyId());
		if(ta == null){
			return null;
		}
		List<HsAgencyGoods> list = agencyGoodsService.selectByAgencyId(ta.getDealerNum());
		return list;
	}

	@RequestMapping("/queryAnencyGoodsList")
	public @ResponseBody
	Map<String, Object> queryAnencyGoodsList(HttpServletResponse response,String page, String rows,String clientId,
				String initDateStart, String initDateEnd,String goodId,String data) {
		response.setContentType("text/html;charset=utf-8");
		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50"
				: rows);
		int end = intPage * no;
		int start = end - no + 1;
		int initDateStart1 = 0;
		int initDateEnd1 = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limit", no);
		map.put("offset", start - 1);
		//获取登录者Id
		UserDetailsImpl users = this.getUserInfo();
		Long user_id = users.gettUser().getId();
		TUser tu = tUserService.selectByPrimaryKey(user_id);
		TAgency ta= tAgencyService.selectByAgencyId(tu.getAgencyId());
		if(ta == null){
			return null;
		}
		map.put("agencyId", ta.getDealerNum());
		if(StringUtils.isEmpty(clientId)&&StringUtils.isEmpty(initDateEnd)&&StringUtils.isEmpty(initDateStart)&&StringUtils.isEmpty(goodId)){
			int maxDate=tHisOtcrealtimeService.queryMaxdate();
			initDateEnd1=maxDate;
			initDateStart1=maxDate;
			map.put("initDateStart", initDateStart1);
			map.put("initDateEnd",initDateEnd1 );
		}
		if (initDateStart != null && !initDateStart.equals("")) {
			initDateStart1 = StringToNum(initDateStart);
			map.put("initDateStart", initDateStart1);
		}
		if (initDateEnd != null && !initDateEnd.equals("")) {
			initDateEnd1 = StringToNum(initDateEnd);
			map.put("initDateEnd",initDateEnd1 );
		}
		if(clientId != null && !"".equals(clientId)){
			map.put("clientId",clientId );
		}
		if(goodId != null && !"".equals(goodId)){
			map.put("goodId",goodId );
		}
		if (data != null && data.equals("down")) {
			List<GoodsDetail> all =agencyGoodsService.selectAllGoodsListByAgencyId(map);
			downGoodsDetail(response, all);
		}
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		//进行查询
		List<Map<String, Object>> list =agencyGoodsService.selectGoodsListByAgencyId(map);
		int total = agencyGoodsService.countGoodsByAgencyId(map);
		jsonMap.put("rows", list);
		jsonMap.put("total", total);
		return jsonMap;
	}
	
	/**
	 * 导出产品持有数据
	 * @param response
	 * @param list
	 * @return
	 */
	private Object downGoodsDetail(HttpServletResponse response,List<GoodsDetail> list ) {
		response.setContentType("text/html;charset=utf-8");
		//写文件
		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		String fileName =  nowTime + ".xls";
		String titleName = "产品持有-下载";

		String[] title = new String[] { "权益代码", "权益名称", "客户账户", "客户姓名", "当前持仓", "日期" };
		String[] field = new String[] { "ocode", "gname", "cid", "cname", "camount", "ndate" };

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
					// 设置第八列单元格列宽
					sheet.setColumnView(8, 14);
					sheet.addCell(label);
				}
				// 循环获取数据
				int line = 2;// 行号
				if (list != null && list.size() > 0) {
					for (int i = (x*mus); i < m+1; i++) {
						int row = 0;//
						for (int k = 0; k < field.length; k++) {
							String ocd = field[k];
							ocd = ocd.substring(0, 1).toUpperCase()+ocd.substring(1);
							ocd = "get"+ocd;
							Method method = list.get(i).getClass().getMethod(ocd, null);
							Object ob = method.invoke(list.get(i), null);
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
			logger.error("产品持有下载", e1);
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
}
