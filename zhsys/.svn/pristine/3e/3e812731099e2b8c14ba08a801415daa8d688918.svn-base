package com.cbice.distribute.mgr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.ImportAllUserService;
import com.cbice.distribute.mgr.service.TuserService;

public class ImportAllUserServiceImpl implements ImportAllUserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private TuserService userService;
	 
	 
	public void setUserService(TuserService userService) {
		this.userService = userService;
	}
	private static String[] TITLE1 = { "客户编号", "权益账号", "客户姓名",
		"性别", "证件号码", "手机号码","邮箱","地址","代理商编号","代理商名称"};
	
	@Override
	public JSONArray importAllUser(MultipartFile messageFile) {
		String error = "";
		String flag = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		Row row = null;
		try {
			Workbook workbook =  WorkbookFactory.create(messageFile.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);

			// 判断是否为空
			if (sheet.getLastRowNum() < 1) {
				json1.put("msg","请输入要导入的数据");
				json1.put("code","1001");
				jsonArray.add(json1);
				return jsonArray;
			}
			// 判断是否是模板
			row = sheet.getRow(0);
			for (int i = 0; i < TITLE1.length; i++) {
				if (!TITLE1[i].equals(this.getStringValue(row.getCell(i)).trim())) {
					json1.put("msg","请使用模板导入");
					json1.put("code","1002");
					jsonArray.add(json1);
					return jsonArray;
					
				}
			}
			//判断 是否重发
			Row row1 = sheet.getRow(1);//获取第一行有效数据
			
			
//			for (int i = 1; i <= sheet.getLastRowNum()+1; i++) {
//				row = sheet.getRow(i);
//				if(row == null) continue;
//				error = "第" + (i + 1) + "行,";
//				flag = error;
//				//分发数量
//				String val = this.getStringValue(row.getCell(6));
//				if(StringUtils.isEmpty(val)){
//					error+=TITLE1[6]+"分发数量不能为空,";
//				}
//				
//				if(!error.equals(flag)){
//					logger.error(error);
//					json1.put("msg",error);
//					json1.put("code","1003");
//					jsonArray.add(json1);
//					return jsonArray;
//				}
//			}
			
			json1.put("msg","导入成功");
			json1.put("code","0000");
			jsonArray.add(json1);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if(row == null) continue;
				TlowerUser lower=new TlowerUser();
				String loweruserAccount=this.getStringValue(row.getCell(0)).toString();
				lower.setAssetsAccount(loweruserAccount);
				
				String rightAccount=this.getStringValue(row.getCell(1)).toString();
				lower.setRightAccount(rightAccount);
				
				String name=this.getStringValue(row.getCell(2)).toString();
				lower.setName(name);
				
				String sex=this.getStringValue(row.getCell(3)).toString();
				if(sex.equals("男")){
					lower.setSex(1);
				}else{
					lower.setSex(0);;
				}
				
				String ids=this.getStringValue(row.getCell(4)).toString();
				lower.setIds(ids);
				
				String tel=this.getStringValue(row.getCell(5)).toString();
				lower.setTel(tel);
				
				String email=this.getStringValue(row.getCell(6)).toString();
				lower.setEmail(email);
				
				String address=this.getStringValue(row.getCell(7)).toString();
				lower.setAddress(address);
				
				String dealerNum=this.getStringValue(row.getCell(8)).toString();
				if(dealerNum==null){
					dealerNum="";
				}
				lower.setDealerNum(dealerNum);
				String dealerName=this.getStringValue(row.getCell(9)).toString();
				if(dealerName==null){
					dealerName="";
				}
				lower.setDealerName(dealerName);
				
			    int k=i;
			    List<TlowerUser> tlowerlist=new ArrayList<TlowerUser>();
			    tlowerlist.add(lower);
			   int m=	userService.insertUserAgency(tlowerlist);
			   System.out.println("............."+i);
			   if(m==1){
				   json1.put("msg","导入成功");
					json1.put("code","0000");
					jsonArray.add(json1);
			   }
				
			}
		} catch (Exception e2) {
			logger.error("导入分发数量失败", e2);
			json1.put("msg","系统内部错误");
			json1.put("code","1004");
			jsonArray.add(json1);
			return jsonArray;
		}
		
		return jsonArray;
	}
	public String getStringValue(Cell cell){
		if(null==cell){
			return "";
		}
		int cellType = cell.getCellType();
		String val = null;
		if(cellType == HSSFCell.CELL_TYPE_NUMERIC){
			double doubleval = cell.getNumericCellValue();
			val = new BigDecimal(doubleval+"").toPlainString();
		}
		if(cellType == HSSFCell.CELL_TYPE_STRING){
			val = cell.getStringCellValue();
		}
		if(cellType == HSSFCell.CELL_TYPE_FORMULA){
			val =cell.getCellFormula();
		}
		if(cellType == HSSFCell.CELL_TYPE_BLANK){
			val = null;
		}
		if(cellType == HSSFCell.CELL_TYPE_BOOLEAN){
			boolean bool =cell.getBooleanCellValue();
			if(bool == true){val = "1";}
			if(bool == false){val = "0";}
		}
		if(cellType == HSSFCell.CELL_TYPE_ERROR){
			val = null;
		}
		return val;
	}
	
}
