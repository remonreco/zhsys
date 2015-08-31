package com.cbice.distribute.mgr.web.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.model.TrancationReport;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.TAgencyService;
import com.cbice.distribute.mgr.service.TgoodsBatchService;
import com.cbice.distribute.mgr.service.TgoodsOrderService;

@Controller
public class GoodsBatchController extends BaseController {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	TgoodsBatchService goodsBatchService;
	
	@Resource
	private TgoodsOrderService goodsOrderService;
	
	@Resource
	private TAgencyService tAgencyServie;
		

	public void setGoodsBatchService(TgoodsBatchService goodsBatchService) {
		this.goodsBatchService = goodsBatchService;
	}
	   @RequestMapping("/showBatch")
	    public String modPwdInput(){
	        return "goodsBatch";
	    }
	    
		@RequestMapping("/queryBatch")
		public @ResponseBody Map<String,Object> queryTuser(String page, String rows,Map<String,Object> queryMap,String goodsName,String id){
			
			   // 当前页
	        int intPage = Integer.parseInt((page == null) ? "1" : page);
	        // 每页显示行数
	        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
	        int end = intPage * no;
	        int start = end - no + 1;
	        queryMap.put("limit", no);
	        queryMap.put("offset", start-1);
	        queryMap.put("userId", constantList.IceId);
	        if(goodsName!=null && !goodsName.isEmpty()){
	        queryMap.put("goodsName", goodsName);
	        }
	        if(id!=null && !id.isEmpty()){
	        queryMap.put("id", Long.parseLong(id));
	        }
	        List<TGoodsBatch>	 list=goodsBatchService.selectByUserId(queryMap);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        if(!list.isEmpty()){
	        	
	        
		        for (TGoodsBatch tGoodsBatch : list) {
		        TAgency agency=	tAgencyServie.selectByAgencyId(tGoodsBatch.gettUserId());
		        	//tGoodsBatch.setOperator("查看详细");
					if (tGoodsBatch.getCreatTime()!=null){
						tGoodsBatch.setShowTime(sdf.format(tGoodsBatch.getCreatTime()));
					}
					tGoodsBatch.setAgencyName(agency.getDealerName());
				}
	        }
			Map<String,Object> map = new HashMap<String, Object>();
			int count=goodsBatchService.countSelectDealer(queryMap);
			map.put("rows", list);
			map.put("total", count);
			return map;
		}
		
		/**
		 * 销售码查询
		 * @return
		 */
		@RequestMapping("/toSaleGoodsCodeList")
		public String saleGoodsCode() {
			return "saleGoodsCodeList";
		}
		
		/**
		 * 销售码导出查询
		 * @param page
		 * @param rows
		 * @param user
		 * @return
		 */
		@RequestMapping("/saleGoodsCode")
		public @ResponseBody Map<String,Object> saleGoodsCode(String page, String rows,String goodsNum,String goodsName){
			
			   // 当前页
	        int intPage = Integer.parseInt((page == null) ? "1" : page);
	        // 每页显示行数
	        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
	        int end = intPage * no;
	        int start = end - no + 1;
//	        goodsbatch.setLimit(no);
//	        goodsbatch.setStart(start-1);
	        long userid=constantList.IceId;
	        /*String goodsNum="";
	        String goodsName="";
	        if(goodsbatch ==null){
	        	 userid=goodsbatch.gettUserId();
	        	 goodsNum=goodsbatch.getGoodsNum();
	        	 goodsName=goodsbatch.getGoodsName();
	        }*/
	        Map<String,Object> map=new HashMap<String,Object>();
	        map.put("limit", no);
	        map.put("offset", start-1);
	        map.put("userId", userid);
	        if(StringUtils.isNotEmpty(goodsName)){
	        	map.put("goodsName", goodsName);
	        }
	        if(StringUtils.isNotEmpty(goodsNum)){
	        	map.put("goodsNum", goodsNum);
	        }
	        List<TrancationReport>  list= goodsBatchService.saleCodeOut(map);
	        int count =goodsBatchService.countSaleCodeOut(map);
			Map<String,Object> jsonmap = new HashMap<String, Object>();
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
		@RequestMapping("/loadoutSaleCode")
		@ResponseBody
		public Object outborrowReviewOperation(HttpServletResponse response,
				String id){
			String ids="";
			if(com.cbice.distribute.core.util.StringUtils.isNotEmpty(id)){
				String[] a=id.split(",");
				ids=a[0];
			}
			TGoodsBatch goodsbatch=goodsBatchService.selectByPrimaryKey(Long.parseLong(ids));
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("userId",goodsbatch.gettUserId());
			map.put("goodsNum", goodsbatch.getGoodsNum());
			map.put("goodsName", goodsbatch.getGoodsName());
			map.put("id", goodsbatch.getId());
			List<TGoodsOrder> list=goodsOrderService.outSaleCode(map);
	        List<TlowerUser> lowerList=new ArrayList<TlowerUser>();
	         for(TGoodsOrder order:list){
	        	 TlowerUser lowerUser=new TlowerUser();
	        	 lowerUser.setTel(order.getTel());
	        	 lowerUser.setGoodsNum(goodsbatch.getGoodsNum());
	        	 lowerUser.setLoweruserId(order.getUserId());
	        	 lowerUser.setGoodsName(goodsbatch.getGoodsName());
	        	 lowerUser.setGoodsCount(order.getDistributeNum());
	        	 lowerUser.setName(order.getUserName());
	        	 lowerUser.setAssetsAccount(order.getAssetsAccount());
	        	 if(order.getMemberReturnGoods()!=null&&order.getMemberReturnGoods()==0){
	        		 lowerUser.setSaleCode("已退货"); 
	        	 }else{
	        	 lowerUser.setSaleCode(order.getSalesCode());
	        	 }
	        	 lowerList.add(lowerUser);
	         }
			
	         if (list == null || list.size() == 0) {
	 			logger.info("查询到的数据为空");
	 			return null;
	 		}
	         
	         // 写文件
		 		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		 		String fileName = "发货信息"+nowTime + ".xls";
		 		String titleName = "分发产品数据-下载";

		 		String[] title = new String[] { "用户ID","资产账号","用户名称","电话号码","产品编号",
						"产品名称", "分发数量","销售码"};
				String[] field = new String[] { "getLoweruserId","getAssetsAccount","getName", "getTel",
						 "getGoodsNum", "getGoodsName", "getGoodsCount","getSaleCode"};
		
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
						if (lowerList != null && lowerList.size() > 0) {
							for (int i = 0; i < lowerList.size(); i++) {
								int row = 0;//
								for (int k = 0; k < field.length; k++) {
									Method method = lowerList.get(i).getClass().getMethod(field[k], null);
									Object ob = method.invoke(lowerList.get(i), null);

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
						logger.error("发货信息数据下载", e1);
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
