package com.cbice.distribute.mgr.web.controller;


import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TAgencyBusiRoleRole;
import com.cbice.distribute.core.db.model.TAgencyUserBusiRole;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.SeqService;
import com.cbice.distribute.core.service.TAgencyDbService;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.ConversionChineseCharacters;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.SequenceCreaterUtil;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;
import com.cbice.distribute.mgr.service.ImportAllUserService;
import com.cbice.distribute.mgr.service.ImportSendGoodsService;
import com.cbice.distribute.mgr.service.TAgencyService;
import com.cbice.distribute.mgr.service.TAgencyUserBusiRoleService;
import com.cbice.distribute.mgr.service.TgoodsOrderService;
import com.cbice.distribute.mgr.service.TuserService;
import com.cbice.distribute.mgr.service.VGoodsOrderUserService;

@Controller
public class TUserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
    private TuserService userService;
    
	@Resource
	private TAgencyService tAgencyServie;
	
	@Resource
	private TAgencyUserBusiRoleService tAgencyUserBusiRoleService;
	
    @Resource
    private SeqService seqService;
    
    @Resource
    private VGoodsOrderUserService vGoodsOrderUserService;

    @Resource
    private ImportSendGoodsService importSendGoodsService;
   
    @Resource
    private TgoodsOrderService goodsOrderService;
    
    @Resource
    private ImportAllUserService importAllUserService;
    
    @Resource
	private VUserAgencyDbService vUserAgencyDbService;
    @Resource
    private TAgencyDbService tAgencyDbService;
    
    @RequestMapping("/dealerList")
    public String modPwdInput(){
        return "dealerList";
    }
    
    @RequestMapping("/tUserList")
    public String tUserList(Map<String, Object> map){
    	map.put("agencyId", String.valueOf(constantList.IceId));//ICE的ID
        return "tuserList";
    }
    
	@RequestMapping("/queryTuser")
	public @ResponseBody Map<String,Object> queryTuser(String page, String rows,TAgency tAgency){
		
		   // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
        int end = intPage * no;
        int start = end - no + 1;
        tAgency.setLimit(no);
        tAgency.setStart(start-1);
        tAgency.setDealerLevel(1);
        tAgency.setAgencyId(constantList.IceId);
        ConversionChineseCharacters ccc=new ConversionChineseCharacters();
        List<TAgency> list1=tAgencyServie.selectAgency(tAgency);
        List<TAgency> list=new ArrayList<TAgency>();
        for (int i = 0; i < list1.size(); i++) {
			TAgency tA=new TAgency();
			//获取经销商等级
			String dealer_level=list1.get(i).getDealerLevel().toString();
			tA.setId(list1.get(i).getId());
			tA.setDealerName(list1.get(i).getDealerName());
			tA.setDealerNum(list1.get(i).getDealerNum());
			tA.setStringDealerLevel(ccc.transition(dealer_level)+"级经销商");
			tA.setDealerLevel(list1.get(i).getDealerLevel());
			tA.setHigerDealerId(list1.get(i).getHigerDealerId());
			tA.setHigerDealer(list1.get(i).getHigerDealer());
			tA.setLft(list1.get(i).getLft());
			tA.setRgt(list1.get(i).getRgt());
			tA.setUserName(list1.get(i).getUserName());
			list.add(tA);
		}
        Map<String,Object> map = new HashMap<String, Object>();
		int count=tAgencyServie.countSelectAgency(tAgency);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	/**
     * 查询当前登录的下级经销商列表
     * @return
     */
    @RequestMapping("/selectLowerDealerList")
   	public @ResponseBody Object selectLowerDealerList(){
   		 UserDetailsImpl users = this.getUserInfo();
   	     /*TUser sysuser=users.gettUser();
   	     Map<String,Object> map = new HashMap<String, Object>();
   	     map.put("agencyId", sysuser.getAgencyId());*/
   		 Map<String,Object> map = new HashMap<String, Object>();
   		 map.put("agencyId", constantList.IceId);//ICE的ID
	     List<TAgency> data =tAgencyServie.selectLowerDealerList(map);
	     return data;
   	}
	/**
	 * 会员管理的查询会员
	 * @param page
	 * @param rows
	 * @param user
	 * @return
	 */
	@RequestMapping("/queryTuser2")
	public @ResponseBody Map<String,Object> queryTuser2(String page, String rows,TUser user){
		
		   // 当前页
        int intPage = Integer.parseInt((page == null) ? "1" : page);
        // 每页显示行数
        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
        int end = intPage * no;
        int start = end - no + 1;
        user.setLimit(no);
        user.setStart(start-1);
        //user.setAgencyId(constantList.IceId);//ice的ID
        user.setUserIdenty(0);
        List<TUser>	 list=userService.selectDealer2(user);
		Map<String,Object> map = new HashMap<String, Object>();
		int count=userService.countSelectDealer2(user);
		map.put("rows", list);
		map.put("total", count);
		return map;
	}
	 /**
	  * 增加经销商
	  * @return
	  */
		@RequestMapping("/addDealer")
		public @ResponseBody int addSysBusiRole(String dealerNum2, String dealerName2){
			TAgency   tagency =new TAgency();
			tagency.setDealerNum(dealerNum2);
			List<TAgency> list= tAgencyServie.selectAgencyBydealerNumAndName(tagency);
			
			if(list.size()>=1){
			 return  1;
			}
			else{
			return tAgencyServie.insertAgencyTransactional(dealerNum2, dealerName2);
			}
		}
		
		/**
		  * 编辑经销商
		  * @return
		  */
			@RequestMapping("/editDealer")
			public @ResponseBody int editDealer(String id,String dealerNum3, String dealerName3){
			    int result = 0;
			    int result1 = 0;
			    try{
			    		TAgency tAgency=tAgencyServie.selectByAgencyId(Long.parseLong(id));
					    if(StringUtils.isNotBlank(dealerNum3)){
					    	String originPwdDigest = new Md5PasswordEncoder().encodePassword(dealerNum3, null);
					    	//t_agency经销商编码 明文
					    	tAgency.setDealerNum(dealerNum3);
					    }
					    if(StringUtils.isNotBlank(dealerName3)){
					    	tAgency.setDealerName(dealerName3);
					    }
					    tAgency.setModifyTime(new Date());
					    result1=tAgencyServie.UpdatebyId(tAgency);
					    if(result1<1){
					    	return -1;
					    }
					    result=1;
			    }catch(Exception e){
			        logger.error(null, e);
			        result = -1;
			    }
			    return result;
			}
			
		
		/**
		  * 增加会员
		  * @return
		  */
			@RequestMapping("/addTuser")
			public @ResponseBody int addSysBusiRole(String assetsAccount2, String rightsAccount2, String userName2,String tel2,String address2,String email2,String sex2,String certificateNum2,String lowerDealer2){
			    int result = 0;
			    TUser tuser=new TUser();
			    if(StringUtils.isNotBlank(assetsAccount2)){
			    	tuser.setAssetsAccount(assetsAccount2);
			    }
			    if(StringUtils.isNotBlank(rightsAccount2)){
			    	tuser.setRightsAccount(rightsAccount2);
			    }
			    if(StringUtils.isNotBlank(userName2)){
			    	tuser.setUserName(userName2);
			    }
			    if(StringUtils.isNotBlank(tel2)){
			    	tuser.setTel(tel2);
			    }
			    if(StringUtils.isNotBlank(address2)){
			    	tuser.setAddress(address2);;
			    }
			    if(StringUtils.isNotBlank(sex2)){
			    	tuser.setSex(Integer.valueOf(sex2));
			    }
			    if(StringUtils.isNotBlank(certificateNum2)){
			    	tuser.setCertificateNum(certificateNum2);
			    }
			    if(StringUtils.isNotBlank(email2)){
			    	tuser.setEmail(email2);
			    }if(StringUtils.isNotBlank(lowerDealer2)){
			    	tuser.setAgencyId(Long.parseLong(lowerDealer2));
			    }
			    tuser.setUserIdenty(0);
		        tuser.setAgencyId(Long.parseLong(lowerDealer2));
		        tuser.setModifyTime(new Date());
			    try{
			    	String checkString = checkTuserByUserNumAndName(assetsAccount2,rightsAccount2,"");
			    	if("1".equals(checkString)){
			    		result = 2;
			    	}else{
			    		result=userService.insertSelective(tuser);
			    	}
			    }catch(Exception e){
			        logger.error(null, e);
			        result = -1;
			    }
			    return result;
			}
			
			/**
			  * 编辑会员
			  * @return
			  */
				@RequestMapping("/editTuser")
				public @ResponseBody int editSysBusiRole(String id,String assetsAccount3, String rightsAccount3, String userName3,String tel3,String address3,String sex3,String email3,String certificateNum3,String lowerDealer3){
				    int result = 0;
				    
				    try{
				    	if(id!=null){
					    	TUser tuser=userService.selectById(Long.parseLong(id));
						    if(assetsAccount3!=null){
						    	tuser.setAssetsAccount(assetsAccount3);
						    }
						    if(rightsAccount3!=null){
						    	tuser.setRightsAccount(rightsAccount3);;
						    }
						    if(userName3!=null){
						    	tuser.setUserName(userName3);
						    }
						    if(tel3!=null){
						    	tuser.setTel(tel3);
						    }
						    if(address3!=null){
						    	tuser.setAddress(address3);;
						    }
						    if(sex3!=null){
						    	tuser.setSex(Integer.valueOf(sex3));
						    }
						    if(certificateNum3!=null){
						    	tuser.setCertificateNum(certificateNum3);;
						    }
						    if(email3!=null){
						    	tuser.setEmail(email3);;
						    }
						    if(StringUtils.isNotBlank(lowerDealer3)){
						    	tuser.setAgencyId(Long.parseLong(lowerDealer3));
						    }
						    tuser.setModifyTime(new Date());
					        result=userService.UpdatebyId(tuser);
				    	}
				    }catch(Exception e){
				        logger.error(null, e);
				        result = -1;
				    }
				    return result;
				}
		
		/**
		 * 检查经销商是否存在
		 * @return
		 */
		@RequestMapping("/checkTAgencyBydealerNumAndName")
		public @ResponseBody String selectTuserBydealerNumAndName(String dealerNum2, String dealerName2,String id){
			 TAgency tagency =new TAgency();
				//经销商编码显示明文检查t_agency是否有重复的经销商编码
				 //tagency.setDealerNum(dealerNum2);
				 //只用判断经销商编码是否重复即可
				// tagency.setDealerName(dealerName2);
				 if(id!=null && !id.isEmpty()){
					 tagency.setId(Long.parseLong(id));
				 }
				 
				 if(dealerNum2!=null && !dealerNum2.isEmpty()){
					 tagency.setDealerNum(dealerNum2);
				 }
				 else{
					 tagency.setDealerNum("");
				 }
				 if(dealerName2!=null && !dealerName2.isEmpty()){
					 tagency.setDealerName(dealerName2);
				 }
				 else{
					 tagency.setDealerName("");
				 }
				List<TAgency> list= tAgencyServie.selectAgencyBydealerNumAndName(tagency);
				String str="0";
				if(list.size()>=1){
					str="1";
				}
			    return str;
		}
		
		/**
		 * 检查会员是否存在
		 * @return
		 */
		@RequestMapping("/checkTuserByUserNumAndName")
		public @ResponseBody String checkTuserByUserNumAndName(String assetsAccount, String rightsAccount,String id){
			TUser tuser=new TUser();
			tuser.setAssetsAccount(assetsAccount);
			tuser.setRightsAccount(rightsAccount);
			if(id!=null && !id.isEmpty()){
				tuser.setId(Long.parseLong(id));
			}
			List<TUser> list = null;
			String str="0";
			if(StringUtils.isNotBlank(assetsAccount)){
				tuser.setRightsAccount("");
				list= userService.selectTuserBydUserNumAndName(tuser);
				if(list.size()>=1){
					str="1";
					return str;
				}
			}
			if(StringUtils.isNotBlank(rightsAccount)){
				tuser.setAssetsAccount("");
				tuser.setRightsAccount(rightsAccount);
				list= userService.selectTuserBydUserNumAndName(tuser);
				if(list.size()>=1){
					str="1";
					return str;
				}
			}
		    return str;
		}
		
		
		 @RequestMapping("/toQueryLowerUser")
		    public String toQueryLowerUser(){
		        return "sendGoodsInformation";
		    }
		
		/**
		 * 查询下级经销商和会员
		 * @param page
		 * @param rows
		 * @return
		 */
		@RequestMapping("/queryLowerUser")
		public @ResponseBody Map<String,Object> queryLowerUser(String page, String rows){
			
			   // 当前页
	        int intPage = Integer.parseInt((page == null) ? "1" : page);
	        // 每页显示行数
	        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
	        int end = intPage * no;
	        int start = end - no + 1;
	        Map<String,Object> sqlmap=new HashMap<String,Object>();
	        sqlmap.put("limit", no);
	        sqlmap.put("start", start - 1);
	        sqlmap.put("higerDealer", constantList.IceId);
	        List<VUserAgency> list=vUserAgencyDbService.selectLowerDealer(sqlmap);
	        List<TlowerUser> lowerList=new ArrayList<TlowerUser>();
	         for(VUserAgency user:list){
	        	 TlowerUser lowerUser=new TlowerUser();
	        	 if(user.getUserIdenty()==2){//经销商
	        		 lowerUser.setName(user.getDealerName());
	        		 lowerUser.setUserIdenty("经销商");
	        	 }else if(user.getUserIdenty()==0){
	        		 lowerUser.setName(user.getUserName());
	        		 lowerUser.setUserIdenty("会员");
	        	 }
	        	 lowerUser.setId(user.getId());
	        	 lowerUser.setTel(user.getTel());
	        	 lowerUser.setHigerDealer(user.getAgencyId());
	        	 lowerUser.setHigerAgency(user.getHigerAgency());
	        	 lowerList.add(lowerUser);
	         }
	            
			Map<String,Object> map = new HashMap<String, Object>();
			int count=vUserAgencyDbService.countselectLowerDealer(sqlmap);
			map.put("rows", lowerList);
			map.put("total", count);
			return map;
		}
		
		/**
		 * 导出产品分发
		 * @param response
		 * @param goodsName
		 * @param goodsNum
		 * @return
		 */
		@RequestMapping("/loadoutTuserAndGoods")
		@ResponseBody
		public Object outborrowReviewOperation(HttpServletResponse response,
				String goodsName,String goodsNum,String ids){
			String goodsName2="";
			String goodsNum2="";
			if(com.cbice.distribute.core.util.StringUtils.isNotEmpty(goodsName)){
				String[] a=goodsName.split(",");
				goodsName2=a[0];
			}
			
			if(com.cbice.distribute.core.util.StringUtils.isNotEmpty(goodsNum)){
				String[] b=goodsNum.split(",");
				goodsNum2=b[0];
			}
			 List<VUserAgency> list2 =new ArrayList<VUserAgency>();
				if(com.cbice.distribute.core.util.StringUtils.isNotEmpty(ids)){
					String[] c=ids.split(",");
					for(int i=0;i<c.length;i++){
						    Map<String,Object> sqlmap=new HashMap<String,Object>();
					        sqlmap.put("higerDealer", constantList.IceId);
					        sqlmap.put("id", Long.parseLong(c[i]));
					        List<VUserAgency>   list=vUserAgencyDbService.selectLowerDealer(sqlmap);
					        list2.add(list.get(0));
					}
			       
				}else{
					Map<String,Object> sqlmap=new HashMap<String,Object>();
			        sqlmap.put("higerDealer", constantList.IceId);
			        list2=vUserAgencyDbService.selectLowerDealer(sqlmap);
				}
	        List<TlowerUser> lowerList=new ArrayList<TlowerUser>();
	         for(VUserAgency user:list2){
	        	 TlowerUser lowerUser=new TlowerUser();
	        	 if(user.getUserIdenty()==2){//经销商
	        		 lowerUser.setName(user.getDealerName());
	        		 lowerUser.setUserIdenty("经销商");
	        	 }else if(user.getUserIdenty()==0){
	        		 lowerUser.setName(user.getUserName());
	        		 lowerUser.setUserIdenty("会员");
	        	 }
	        	 lowerUser.setAssetsAccount(user.getAssetsAccount());
	        	 lowerUser.setTel(user.getTel());
	        	 lowerUser.setHigerDealer(user.getAgencyId());
	        	 lowerUser.setGoodsNum(goodsNum2);
	        	 lowerUser.setGoodsName(goodsName2);
	        	 lowerUser.setLoweruserId(user.getId());
	        	 lowerList.add(lowerUser);
	        	 
	         }
			
	         if (list2 == null || list2.size() == 0) {
	 			logger.info("查询到的数据为空");
	 			return null;
	 		}
	         
	         // 写文件
		 		String nowTime = DateUtils.parseDate(new Date(), "yyyyMMddHHmmss");
		 		String fileName = "发货信息"+nowTime + ".xls";
		 		String titleName = "分发产品数据-下载";

		 		String[] title = new String[] { "用户ID","资产账号", "用户名称","电话号码","用户标识","产品编号",
						"产品名称", "分发数量"};
				String[] field = new String[] { "getLoweruserId","getAssetsAccount","getName", "getTel",
						"getUserIdenty", "getGoodsNum", "getGoodsName", "getGoodsCount"};
		
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
		
		
		@RequestMapping("/toUpSendGoodsByUsers")
		public String toUprepayment(){
			return "upSendGoodsByUsers";
		}
		/**
		 * 导入发货信息
		 * @param messageFile
		 * @param request
		 * @return
		 */
		@RequestMapping("/importSendGoodsByUser")
		public String importMessage(@RequestParam MultipartFile messageFile,HttpServletRequest request){
			JSONArray result = null;
			String memo = "";
			try {
				result=importSendGoodsService.importSendGoods(messageFile);
				
			
			request.setAttribute("urlName", "导入分发产品");
			request.setAttribute("urlAddr","toMessageEmail.html");
			
		
	    	if(result == null||result.size()<2){
	    		if(result.size() == 1){
	    			JSONObject object = (JSONObject) result.get(0);
	    	    	String code = object.getString("code");
	    	    	if(code.equals("1002")){
	    	    		memo = "请使用模板导入";
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    	    	if(code.equals("1003")){
	    	    		memo = "请核对产品信息无误！";
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    	    	if(code.equals("1004")){
	    	    		memo = object.getString("msg");
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    	    	if(code.equals("1005")){
	    	    		memo = object.getString("msg");
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    		}
	    		request.setAttribute("memo", "导入分发产品信息错误，请稍候重试！");
	    		return "upSendGoodsError";
			}
	    	
	    	JSONObject object = (JSONObject) result.get(0);
	    	String code = object.getString("code");
	    	if(StringUtils.isEmpty(code)||!code.equals("0000")){
	    		memo = "导入分发产品出错";
	    		return "upSendGoodsError";
	    	}
	    	List<TGoodsOrder> goodslist=new ArrayList<TGoodsOrder>();
	    	if(result != null&&result.size()>1){
	    		result.remove(0);
	    		for(int k=0;k<result.size();k++){	
	    			 JSONObject   json= (JSONObject) result.get(k);
	    			 TlowerUser lowerUser=(TlowerUser) JSONObject.toBean(json.getJSONObject("TlowerUser"+k), TlowerUser.class);
	    			 TGoodsOrder goodsorder=new TGoodsOrder();
	    			 goodsorder.setAgencyId(constantList.IceId);//iCE的ID
	    			 long content=lowerUser.getGoodsCount();
	    			 long price=lowerUser.getGoodsPrice();
	    			 long money=this.mul2(content, price);
	    			 goodsorder.setBusinessMoney(money);//交易金额
	    			 goodsorder.setOrderIdenty(0);//订单标识
	    			 goodsorder.setGoodsName(lowerUser.getGoodsName());//产品名称
	    			 goodsorder.setGoodsNum(lowerUser.getGoodsNum());//产品编码
	    			 goodsorder.setUserId(lowerUser.getLoweruserId());//下级经销商会员ID
	    			 goodsorder.setDistributeNum(lowerUser.getGoodsCount());//下发数量
	    			 goodsorder.setIssuedDate(new Date());//下发时间
	    			 goodsorder.setPayState(0);//支付状态0，未支付；1支付
	    			 goodsorder.setOrderState(1);//订单状态
	    			 goodsorder.setWithdrawGoodsState(0);
		    		 Date date =new Date();
		    		 goodsorder.setIssuedState(1);//下发审核状态
		    		 Date dateLimit = DateUtils.getAnyDayByNo(date, 7);
		    		 goodsorder.setOrderTime(dateLimit);//订单有效时间
		    		 TUser user =vGoodsOrderUserService.selectUserById(goodsorder.getUserId());
		    		 if(user!=null){
		    			 if(user.getUserIdenty()!=null&&user.getUserIdenty()==0){
								goodsorder.setSalesCode(SequenceCreaterUtil.createSequence("DB"));
							}
		    		 }
	    			 goodslist.add(goodsorder);
	    		}
	    		int results= goodsOrderService.insertListSelective(goodslist);
    			if(results!=1){
    				memo = "导入分发产品失败";
    	    		return "upSendGoodsError";
    			}
	    	}
			} catch (Exception e) {
				e.printStackTrace();
				memo = "导入数据失败";
	    		request.setAttribute("memo", memo);
	    		return "upSendGoodsError";
			}
	    	request.setAttribute("memo", "分发产品导入成功!");
			return "success";
		}
		
	    public   static   long   mul2(long   v1,long   v2){   
	        BigDecimal   b1   =   new   BigDecimal(Long.toString(v1));   
	        BigDecimal   b2   =   new   BigDecimal(Long.toString(v2));   
	        return   b1.multiply(b2).longValue();   
	} 
	    
	    @RequestMapping("/upAllUser")
		public String upAllUser() {
			return "upAllUsers";
		}
	    /**
	     * 导入所有人员
	     * @param messageFile
	     * @param request
	     * @return
	     */
	    @RequestMapping("/importAllUser")
		public String importAllUser(@RequestParam MultipartFile messageFile,HttpServletRequest request){
			JSONArray result = null;
			String memo = "";
			try {
			result=importAllUserService.importAllUser(messageFile);
			request.setAttribute("urlName", "导入所有人员");
			request.setAttribute("urlAddr","toMessageEmail.html");
			
	    	if(result == null||result.size()<2){
	    		if(result.size() == 1){
	    			JSONObject object = (JSONObject) result.get(0);
	    	    	String code = object.getString("code");
	    	    	if(code.equals("1002")){
	    	    		memo = "请使用模板导入";
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    	    	if(code.equals("1003")){
	    	    		memo = "请核对产品信息无误！";
	    	    		request.setAttribute("memo", memo);
	    	    		return "upSendGoodsError";
	    	    	}
	    	   
	    		}
	    		request.setAttribute("memo", "导入所有人员信息错误，请稍候重试！");
	    		return "upSendGoodsError";
			}
	    	
	    	JSONObject object = (JSONObject) result.get(0);
	    	String code = object.getString("code");
	    	if(StringUtils.isEmpty(code)||!code.equals("0000")){
	    		memo = "导入分发产品出错";
	    		return "upSendGoodsError";
	    	}
			} catch (Exception e) {
				memo = "导入分发产品出错";
	    		return "upSendGoodsError";
			}
	    	request.setAttribute("memo", "所有用户导入成功!");
			return "success";
		}
		
}
