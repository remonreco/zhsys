package com.cbice.distribute.mgr.web.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jxl.read.biff.Record;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TSysBusiRole;
import com.cbice.distribute.core.db.model.TSysRole;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.GoodsOrderService;
import com.cbice.distribute.core.service.SysBusiRoleService;
import com.cbice.distribute.core.service.SysRoleService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.SequenceCreaterUtil;
import com.cbice.distribute.mgr.service.VGoodsOrderUserService;
@Controller
public class GoodsOrderController extends BaseController {

	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 @Resource
	    private VGoodsOrderUserService vGoodsOrderUserService;
		@Resource
		private TRemainderGoodsService tRemainderGoodsService;
		@Resource
		private VUserAgencyDbService vUserAgencyDbService;
		

		public void setvGoodsOrderUserService(
				VGoodsOrderUserService vGoodsOrderUserService) {
			this.vGoodsOrderUserService = vGoodsOrderUserService;
		}

		public void settRemainderGoodsService(
				TRemainderGoodsService tRemainderGoodsService) {
			this.tRemainderGoodsService = tRemainderGoodsService;
		}


		@RequestMapping("/goodsMgr")
		
		    public  String goodsMgr(String id,Map<String,Object> map){
				map.put("id", id);
		        return "vgoodsOrderList";
		    }
			
		  @RequestMapping("/queryVGoodsOrder")
			public @ResponseBody Map<String,Object> querySysRoles(String page, String rows,VGoodOrderUser good){
			 
			   // 当前页
		        int intPage = Integer.parseInt((page == null) ? "1" : page);
		        // 每页显示行数
		        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
		        int end = intPage * no;
		        int start = end - no + 1;
		        //System.out.println(good.idForGto);
		        good.setBatchId(Long.parseLong(good.getIdForGto()));
		        good.setLimit(no);
		        good.setOffset(start-1);
		        DecimalFormat df=new DecimalFormat("0.00");
			  List<VGoodOrderUser> goods =this.vGoodsOrderUserService.selectDealer(good);
				for (VGoodOrderUser vGoodOrderUser : goods) {
					vGoodOrderUser.setBusinessMoneyDto(df.format(vGoodOrderUser.getBusinessMoney()/100.00));
					VUserAgency vUserAgency = vUserAgencyDbService.selectById(vGoodOrderUser.getUserId());
					if(vUserAgency!=null){
					if(vUserAgency.getUserIdenty()!=null &&vUserAgency.getUserIdenty()==2){
						vGoodOrderUser.setUserName(vUserAgency.getDealerName());
					}
					else if(vUserAgency.getUserIdenty()!=null &&vUserAgency.getUserIdenty()==0){
						vGoodOrderUser.setUserName(vUserAgency.getUserName());
					}
					}
				}
			  	Map<String,Object> map = new HashMap<String, Object>();
				int count=vGoodsOrderUserService.countSelectDealer(good);
				map.put("rows", goods);
				map.put("total", count);
				return map;
			}
		  
			 /**
			  * 批量同意
			  * @return
			  */
				@RequestMapping("/agreeOrder")
				public @ResponseBody int agreeOrder(String ids,String reason){
				    int result = 0;
				    String [] idList =ids.split(",");
				    try{
				    	  reason =java.net.URLDecoder.decode(reason,"UTF-8"); 
				    	  for (String recordId : idList) {
				    		  if(recordId!=null && !recordId.isEmpty()){
					    		  TGoodsOrder record =  vGoodsOrderUserService.selectById(Long.parseLong(recordId));
					    		  record.setOrderState(1);
					    		  //下发审核状态
					    		  record.setIssuedState(1);
					    		  //只对会员生成销售码
					    		  VUserAgency user =vUserAgencyDbService.selectById(record.getUserId());
					    		  if(user!=null){
					    			  if(user.getUserIdenty()!=null&&user.getUserIdenty()==0){
											record.setSalesCode(SequenceCreaterUtil.createSequence("DB"));
										} 
					    		  }
					    		  if(reason!=null &&!reason.isEmpty()){
					    			  record.setVerifyRejectionReason(reason);
					    		  }
					    		  //讲下发状态配置为0
					    		  record.setWithdrawGoodsState(0);
					    		  Date date =new Date();
					    		  record.setIssuedDate(date);
					    		  Date dateLimit = DateUtils.getAnyDayByNo(date, 7);
					    		  record.setOrderTime(dateLimit);
					    		  vGoodsOrderUserService.updateRecord(record);
					    		  //讲订单产品存至剩余产品表
					    		 // tRemainderGoodsService.unlockGoods(record.getAgencyId(), record.getUserId(), record.getDistributeNum(), record.getGoodsNum());
				    		  }
							}
				    	  result=1;
				    }catch(Exception e){
				        logger.error(null, e);
				        result = -1;
				    }
				    return result;
				}
				
				 /**
				  * 驳回
				  * @return
				  */
					@RequestMapping("/disAgreeOrder")
					public @ResponseBody int disAgreeOrder(String ids,String reason){
					    int result = 0;
					    String [] idList =ids.split(",");
					    try{
					    	  reason =java.net.URLDecoder.decode(reason,"UTF-8"); 
					    	  for (String recordId : idList) {
					    		  if(recordId!=null && !recordId.isEmpty()){
						    		  TGoodsOrder record =  vGoodsOrderUserService.selectById(Long.parseLong(recordId));
						    		  record.setOrderState(2);
						    		  //下发审核状态
						    		  record.setIssuedState(0);
						    		  if(reason!=null &&!reason.isEmpty()){
						    			  record.setVerifyRejectionReason(reason);
						    		  }
						    		  vGoodsOrderUserService.updateRecord(record);
						    		  //讲经销商被锁产品解锁回经销商产品表
						    		  tRemainderGoodsService.unlockGoods(record.getAgencyId(), record.getAgencyId(), record.getDistributeNum(), record.getGoodsNum());
					    		  }
								}
					    	  result=1;
					    }catch(Exception e){
					        logger.error(null, e);
					        result = -1;
					    }
					    return result;
					}
					
					
					/**
					 * 查看详细
					 */
					@RequestMapping("/goodOrderView")
					
				    public  String goodOrderView(String id,Map<String,Object> map){
						TGoodsOrder record =new TGoodsOrder();
						try {
							if(id!=null && !id.isEmpty()){
							record =vGoodsOrderUserService.selectById(Long.parseLong(id));
							}
							
							Field[] fields = TGoodsOrder.class.getDeclaredFields();
							for (Field field : fields) {
								field.setAccessible(true);
								map.put(field.getName(), field.get(record));
							}
							TUser agency =vGoodsOrderUserService.selectUserById(record.getAgencyId());
							VUserAgency user = vUserAgencyDbService.selectById(record.getUserId());
							//TUser user =vGoodsOrderUserService.selectUserById(record.getUserId());
							map.put("Agency", agency.getDealerName());
							if(user.getUserIdenty()!=null&&user.getUserIdenty()==0){
							map.put("flag", "会员名称");
							map.put("userName", user.getUserName());
							}
							else if(user.getUserIdenty()!=null&&user.getUserIdenty()==2){
								map.put("flag", "经销商名称");
								map.put("userName", user.getDealerName());	
							}
							BigDecimal businessMoney =BigDecimal.valueOf(record.getBusinessMoney());
							businessMoney =businessMoney.divide(BigDecimal.valueOf(100));
							map.put("businessMoney",businessMoney.toString());
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
				        
						return "goodsOrderView";
				    }
					
		
}
