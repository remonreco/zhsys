package com.cbice.distribute.mgr.web.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;









import com.cbice.distribute.core.db.model.TGoodsBatch;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VGoodOrderUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.ThistoryGoodsOrder;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;
import com.cbice.distribute.mgr.service.TgoodsBatchService;
import com.cbice.distribute.mgr.service.TgoodsOrderService;
import com.cbice.distribute.mgr.service.VGoodsOrderUserService;

@Controller
public class HistoryGoodsOrderController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
    private TgoodsOrderService goodsOrderService;
	
	@Resource
   	private VUserAgencyDbService vUserAgencyDbService;
	
	
	 @Resource
	    private VGoodsOrderUserService vGoodsOrderUserService;
	 
	 @Resource
		TgoodsBatchService goodsBatchService;

	@RequestMapping("/historyGoodsOrderList")
	public String historyGoodsList() {
		return "historyGoodsOrderList";
	}
//	@RequestMapping("/historyGoodsOrderView")
//	public String historyGoodsOrderView(String id,Map<String,Object> map) {
//		
//		return "historyGoodsOrderView";
//	}
	
	@RequestMapping("/queryHistoryGoodsOrder")
	public @ResponseBody Map<String, Object> queryHistoryGoodsOrder(String page,
			String rows,String batchId,String goodsName, String orderTimeStart, Date orderTimeEnd ) {

		// 当前页
		int intPage = Integer.parseInt((page == null) ? "1" : page);
		// 每页显示行数
		int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50"
				: rows);
		int end = intPage * no;
		int start = end - no + 1;

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(orderTimeEnd!=null){
				String orderTimeEnd1 = DateUtils.addDays(orderTimeEnd, 1);
				if(StringUtils.isNotEmpty(orderTimeEnd1)){
					map.put("ordertimeend",new SimpleDateFormat("yyyy-MM-dd").parse(orderTimeEnd1));
				}
				}
			
			if(StringUtils.isNotEmpty(batchId)){
			    map.put("batchid", Long.parseLong(batchId));
			}
			if(StringUtils.isNotEmpty(goodsName)){
				map.put("goodsname", goodsName);
			}
			if(StringUtils.isNotEmpty(orderTimeStart)){
				map.put("ordertimestart",new SimpleDateFormat("yyyy-MM-dd").parse(orderTimeStart));
			}
			//UserDetailsImpl users = this.getUserInfo();
		   // TUser sysuser=users.gettUser();
		    map.put("userId", constantList.IceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("limit", no);
		map.put("offset", start - 1);

		List<TGoodsOrder> list1 = goodsOrderService.selectHistoryGoodsOrderBy(map);
		List<ThistoryGoodsOrder> list = new ArrayList<ThistoryGoodsOrder>();
		DecimalFormat df=new DecimalFormat("0.00");
		for(TGoodsOrder torder:list1){
			long agencyId = torder.gettUserId();
			
			ThistoryGoodsOrder thgorder=new ThistoryGoodsOrder();
			thgorder.setBatchId(torder.getBatchId());
			thgorder.setGoodsName(torder.getGoodsName());
			thgorder.setOrderTime(DateUtils.DateToString(torder.getCreatTime(), "yyyy-MM-dd HH:mm:ss"));
			//判断是会员还是经销商并放入相应的名称
			VUserAgency vUserAgency = vUserAgencyDbService.selectById(agencyId);
			if(vUserAgency!=null&&vUserAgency.getUserIdenty()==0L){
				if(vUserAgency.getUserName()!=null&& !vUserAgency.getUserName().isEmpty()){
					thgorder.setAgencyName(vUserAgency.getUserName());
				}
				
			}
			else if (vUserAgency!=null&&vUserAgency.getUserIdenty()==2L){
				if(vUserAgency.getDealerName()!=null&&!vUserAgency.getDealerName().isEmpty()){
				thgorder.setAgencyName(vUserAgency.getDealerName());
				}
			}
			
			thgorder.setSumd(torder.getSumd());
			thgorder.setSumm(df.format(torder.getSumm()/100.00));
			list.add(thgorder);
		}
		int count = goodsOrderService.countSelectHistoryGoodsOrderBy(map);
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("rows", list);
		jsonmap.put("total", count);
		return jsonmap;
	}
	
			
	
	    @RequestMapping("/historyOrderView")
	    public  String historyOrderView(String id,Map<String,Object> map){
	    	TGoodsBatch batch = goodsBatchService.selectByPrimaryKey(Long.parseLong(id));
	    	Map<String, Object> remap = new HashMap<String, Object>();
			Map<String, Object> jsonmap = new HashMap<String, Object>();
			DecimalFormat df=new DecimalFormat("0.00");

                if(StringUtils.isNotEmpty(id)){
        	    	long batchId = Long.valueOf(id).longValue();
        	    	System.out.println(batchId);
        	    	List<TGoodsOrder> list1 = goodsOrderService.selectGoodsOrderByBatchid(batchId);
        			List<ThistoryGoodsOrder> list = new ArrayList<ThistoryGoodsOrder>();
        			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        			for(TGoodsOrder tg:list1){
        				ThistoryGoodsOrder thg = new ThistoryGoodsOrder();
        				if(goodsOrderService.selectUseridentyById(tg.getUserId())!=null){
	        				if(goodsOrderService.selectUseridentyById(tg.getUserId())==0L){
	        					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getUserName());
	    				        tg.setUserName("会员");
	        				}else
	        				{
	        					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getDealerName());
	        					tg.setUserName("经销商");
	        				}
        				}
        				//tg.setBusinessMoney(tg.getBusinessMoney()/100);
        				if(tg.getBusinessMoney()!=null){
        				tg.setTotalAmt(df.format(tg.getBusinessMoney()/100.00));
        				}
        				tg.setPayStateDto();
        				tg.setOrderStateDto();
        				tg.setOrderReturnStateDto();
        				if(tg.getOrderTime()!=null){
        				tg.setSendDate(formatter.format(tg.getOrderTime()));
        				}
        				//thg.setDistributeNum(String.valueOf(tg.getDistributeNum()));
        				//thg.setSalesCode(tg.getSalesCode());
/*        				thg.setTel(goodsOrderService.selectTelById(tg.getUserId()));*/
        				list.add(thg);
        			}
        			
//        			List<TGoodsOrder> list2 = goodsOrderService.selectReturnGoodsOrderByBatchid(batchId);
//        			List<ThistoryGoodsOrder> listReturned = new ArrayList<ThistoryGoodsOrder>();
//        			for(TGoodsOrder tg:list2){
//        				if(goodsOrderService.selectUseridentyById(tg.getUserId())!=null){
//        				if(goodsOrderService.selectUseridentyById(tg.getUserId())==0L){
//        					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getUserName());
//    				        tg.setUserName("会员");
//        				}else
//        				{
//        					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getDealerName());
//        					tg.setUserName("经销商");
//        				}
//        				}
//        				//thg.setDistributeNum(String.valueOf(tg.getDistributeNum()));
//        				if(tg.getBusinessMoney()!=null){
//        				tg.setTotalAmt(df.format(tg.getBusinessMoney()/100.00));
//        				}
//        				//thg.setSalesCode(tg.getSalesCode());
//        				tg.setOrderStateDto();
//        				tg.setOrderReturnStateDto();
//        				if(tg.getOrderTime()!=null){
//        				tg.setSendDate(formatter.format(tg.getOrderTime()));
//        				}
//        			}
//        			List<TGoodsOrder> list2 = goodsOrderService.selectGoodsBatchById(batchId);
//        			List<ThistoryGoodsOrder> list3 = new ArrayList<ThistoryGoodsOrder>();
//        			for(TGoodsOrder tg2:list2){
//        				ThistoryGoodsOrder thg2 = new ThistoryGoodsOrder();
//        				thg2.setBatchId(batchId);
//        				thg2.setGoodsNum(tg2.getGoodsNum());
//        				thg2.setGoodsName(tg2.getGoodsName());
//        				thg2.setSumd(tg2.getSumd());
//        				thg2.setSumm(df.format(tg2.getSumm()/100.00));
//        				thg2.setAgencyName(goodsOrderService.selectDealerNameById(tg2.getAgencyId()));
//        				thg2.setOrderTime(DateUtils.DateToString(tg2.getCreatTime(), "yyyy-MM-dd HH:mm:ss"));
//        				list3.add(thg2);
//        				remap.put("batchId", batchId);
//        			}
        			if(list!=null && list.size()!=0){
        				map.put("rows", list1);
        			}
//        			if(listReturned !=null && listReturned.size()!=0){
//        				map.put("returned", list2);
//        			}
        				//batch.setShowTime(df.format(batch.getCreatTime()));
        			   map.put("batch", batch);
                }
               
          
	    	
				return "historyGoodsOrderView";
			}
	    
  
	    
	    //-----------------------------查询退货信息------------------------------------------------------
	    
	    @RequestMapping("/returnHistory")
		
	    public  String goodsMgr(String id,Map<String,Object> map){
			map.put("id", id);
	        return "returnHistoryList";
	    }
		
	  @RequestMapping("/returnHistoryList")
		public @ResponseBody Map<String,Object> querySysRoles(String page, String rows,String goodsName ,String goodsNum){
		 
		   // 当前页
	        int intPage = Integer.parseInt((page == null) ? "1" : page);
	        // 每页显示行数
	        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "10" : rows);
	        int end = intPage * no;
	        int start = end - no + 1;
	        Map<String,Object>  map =new HashMap<String, Object>();
	        map.put("limit", no);
			map.put("offset", start - 1);
	        DecimalFormat df=new DecimalFormat("0.00");
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	//List<TGoodsOrder> list2 = goodsOrderService.selectReturnGoodsOrderByBatchid(c);
	       if(goodsName!=null && !goodsName.isEmpty()){
	    	   map.put("goods_name", goodsName);
	       }
	       if(goodsNum!=null && !goodsNum.isEmpty()){
	    	   map.put("goods_num", goodsNum);
	       }
	        map.put("id", constantList.IceId);
	        List<TGoodsOrder> list2 =goodsOrderService.selectReturnGoodsHis(map);
	        int count=goodsOrderService.selectReturnGoodsHisCount(map);
	        //map.clear();
	        
			//List<ThistoryGoodsOrder> listReturned = new ArrayList<ThistoryGoodsOrder>();
			for(TGoodsOrder tg:list2){
				if(goodsOrderService.selectUseridentyById(tg.getUserId())!=null){
				if(goodsOrderService.selectUseridentyById(tg.getUserId())==0L){
					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getUserName());
			        tg.setUserName("会员");
				}else
				{
					tg.setAgencyName(vUserAgencyDbService.selectById(tg.getUserId()).getDealerName());
					tg.setUserName("经销商");
				}
				}
				//thg.setDistributeNum(String.valueOf(tg.getDistributeNum()));
				if(tg.getBusinessMoney()!=null){
				tg.setTotalAmt(df.format(tg.getBusinessMoney()/100.00));
				}
				//thg.setSalesCode(tg.getSalesCode());
				tg.setOrderStateDto();
				tg.setOrderReturnStateDto();
				if(tg.getOrderTime()!=null){
				tg.setSendDate(formatter.format(tg.getOrderTime()));
				}
			}
			map.put("rows", list2);
			map.put("total", count);
			return map;
		}
	  
	  
//	  /**
//		 * 查看详细
//		 */
//		@RequestMapping("/goodOrderView")
//		
//	    public  String goodOrderView(String id,Map<String,Object> map){
//			TGoodsOrder record =new TGoodsOrder();
//			try {
//				if(id!=null && !id.isEmpty()){
//				record =vGoodsOrderUserService.selectById(Long.parseLong(id));
//				}
//				
//				Field[] fields = TGoodsOrder.class.getDeclaredFields();
//				for (Field field : fields) {
//					field.setAccessible(true);
//					map.put(field.getName(), field.get(record));
//				}
//				TUser agency =vGoodsOrderUserService.selectUserById(record.getAgencyId());
//				VUserAgency user = vUserAgencyDbService.selectById(record.getUserId());
//				map.put("Agency", agency.getDealerName());
//				if(user.getUserIdenty()!=null&&user.getUserIdenty()==0){
//				map.put("flag", "会员名称");
//				map.put("userName", user.getUserName());
//				}
//				else if(user.getUserIdenty()!=null&&user.getUserIdenty()==2){
//					map.put("flag", "经销商名称");
//					map.put("userName", user.getDealerName());	
//				}
//				BigDecimal businessMoney =BigDecimal.valueOf(record.getBusinessMoney());
//				businessMoney =businessMoney.divide(BigDecimal.valueOf(100));
//				map.put("businessMoney",businessMoney.toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			return "goodsOrderView";
//	    }
}