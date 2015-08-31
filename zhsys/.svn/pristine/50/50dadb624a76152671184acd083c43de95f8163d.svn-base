package com.cbice.distribute.mgr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.cbice.distribute.core.db.data.TRemainderGoodsMapper;
import com.cbice.distribute.core.db.model.TGoods;
import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TRemainderGoods;
import com.cbice.distribute.core.service.TGoodsBatchService;
import com.cbice.distribute.core.service.TGoodsService;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.ImportSendGoodsService;

/**
 * 导入Service
 * @author buyball
 *
 */
public class ImportSendGoodsServiceImpl implements ImportSendGoodsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private TGoodsService   tGoodsService;
	
	private TGoodsBatchService tGoodsBatchService;
	
	private TRemainderGoodsMapper tRemainderGoodsMapper;
	
	public void settRemainderGoodsMapper(TRemainderGoodsMapper tRemainderGoodsMapper) {
		this.tRemainderGoodsMapper = tRemainderGoodsMapper;
	}


	public void settGoodsBatchService(TGoodsBatchService tGoodsBatchService) {
		this.tGoodsBatchService = tGoodsBatchService;
	}


	public void settGoodsService(TGoodsService tGoodsService) {
		this.tGoodsService = tGoodsService;
	}


	private static String[] TITLE1 = { "用户ID","资产账号","用户名称", "电话号码",
		"用户标识", "产品编号", "产品名称","分发数量"};
	
	
	@Override
	public JSONArray importSendGoods(MultipartFile messageFile) {
		
		String error = "";
		String flag = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		
		List<TlowerUser> list=new ArrayList<TlowerUser>();
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
			row = sheet.getRow(1);
			for (int i = 0; i < TITLE1.length; i++) {
				if (!TITLE1[i].equals(this.getStringValue(row.getCell(i)))) {
					
					json1.put("msg","请使用模板导入");
					json1.put("code","1002");
					jsonArray.add(json1);
					return jsonArray;
					
				}
			}
			//判断 是否重发
			Row row1 = sheet.getRow(2);//获取第一行有效数据
			
			//判断商品信息是否正确
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("goodsCode", this.getStringValue(row1.getCell(5)).trim());
			map.put("goodsName", this.getStringValue(row1.getCell(6)).trim());
			map.put("goodsNum", this.getStringValue(row1.getCell(5)).trim());
			map.put("userId",constantList.IceId);
			TGoods goods=tGoodsService.selectOnlyGoodsNumOrGoodsName(map);
			TRemainderGoods record =tRemainderGoodsMapper.selecyBuyCondition(map);
			if(record==null){
				json1.put("msg","请核对产品信息是否有误！");
				json1.put("code","1003");
				jsonArray.add(json1);
				return jsonArray;
			}
			
			Long totalCount=0l;//累加货品数量
			
			for (int i = 2; i <= sheet.getLastRowNum()+1; i++) {
				row = sheet.getRow(i);
				if(row == null) continue;
				error = "第" + (i + 1) + "行,";
				flag = error;
				//分发数量
				String val = this.getStringValue(row.getCell(7)).trim();
				if(StringUtils.isEmpty(val)){
					error+=TITLE1[7]+"分发数量不能为空";
					json1.put("msg",error);
					json1.put("code","1004");
					jsonArray.add(json1);
					return jsonArray;
				}
				if(val.equals("0")||val.equals("0.0" )||val.equals("0.00")){
					error+=TITLE1[7]+"分发数量不能为0";
					json1.put("msg",error);
					json1.put("code","1004");
					jsonArray.add(json1);
					return jsonArray;
				}
				if(val.contains("-")){
					error+=TITLE1[7]+"分发数量不能为负数,";
					json1.put("msg",error);
					json1.put("code","1004");
					jsonArray.add(json1);
					return jsonArray;
				}
				if(!this.isInteger(val)){
					error+=TITLE1[7]+"分发数量不能为小数,";
					json1.put("msg",error);
					json1.put("code","1004");
					jsonArray.add(json1);
					return jsonArray;
				}
					Double ahah=Double.parseDouble(val);
					totalCount=totalCount+ahah.longValue();
			}
			long noUseCount=record.getGoodsNum();
			//判断产品总量是否充足
			if(totalCount > noUseCount){
				json1.put("msg","抱歉，货品总量不足！");
				json1.put("code","1005");
				jsonArray.add(json1);
				return jsonArray;
			}
			
			Long price=goods.getGoodsPrice();
			json1.put("msg","导入成功");
			json1.put("code","0000");
			jsonArray.add(json1);
			
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				if(row == null) continue;
				TlowerUser lower=new TlowerUser();
				lower.setGoodsPrice(price);//产品单价
				String loweruserId=this.getStringValue(row.getCell(0)).toString();
				lower.setLoweruserId(Long.parseLong(loweruserId));
				
				String name=this.getStringValue(row.getCell(2)).toString();
				lower.setName(name);
				
				String tel=this.getStringValue(row.getCell(3)).toString();
				lower.setTel(tel);
				
				String userIdenty=this.getStringValue(row.getCell(4)).toString();
				lower.setUserIdenty(userIdenty);
				
				String goodsNum=this.getStringValue(row.getCell(5)).toString();
				lower.setGoodsNum(goodsNum);
				
				String goodsName=this.getStringValue(row.getCell(6)).toString();
				lower.setGoodsName(goodsName);
				
				String goodsCount=this.getStringValue(row.getCell(7)).toString();
				double bidMoneys=Double.parseDouble(goodsCount);
				long count=this.mul(bidMoneys, 1l);
				lower.setGoodsCount(count);
				
			    int k=i-2;
				json2.put("TlowerUser"+k, lower);
				jsonArray.add(json2);
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
			return null;
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
    public   static   long   mul(double   v1,long   v2){   
        BigDecimal   b1   =   new   BigDecimal(Double.toString(v1));   
        BigDecimal   b2   =   new   BigDecimal(Long.toString(v2));   
        return   b1.multiply(b2).longValue();   
}  
    
	/***
	 * 判断 String 是否int
	 * 
	 * @param input
	 * @return
	 */
    public static boolean isInteger(String input){
		Matcher mer = Pattern.compile("^[0-9]+(\\.0+)$|^[0-9]+$").matcher(input);
		return mer.find();
	}
}
