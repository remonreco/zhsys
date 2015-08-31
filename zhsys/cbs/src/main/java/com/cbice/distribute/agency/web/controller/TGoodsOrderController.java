package com.cbice.distribute.agency.web.controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.agency.security.model.TlowerUser;
import com.cbice.distribute.agency.security.model.UserDetailsImpl;
import com.cbice.distribute.agency.service.TAgencyService;
import com.cbice.distribute.agency.service.TgoodsOrderService;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.DateUtils;
import com.cbice.distribute.core.util.StringUtils;
import com.cbice.distribute.core.util.constantList;


@Controller
public class TGoodsOrderController extends BaseController{
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	   @Resource
	    private TgoodsOrderService goodsOrderService;
	    @Resource
	    private TAgencyService tAgencyService;
	    
	    @Resource
	    private VUserAgencyDbService vUserAgencyDbService;
	    @RequestMapping("/goodsOrderList")
	    public String sentGoods(){
	        return "goodsOrderList";
	    }
	    
		@RequestMapping("/queryGoodsOrder")
		public @ResponseBody Map<String, Object> queryGoodsOrder(String page,
				String rows, TGoodsOrder goodsorder) {

			// 当前页
			int intPage = Integer.parseInt((page == null) ? "1" : page);
			// 每页显示行数
			int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
			int end = intPage * no;
			int start = end - no + 1;

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", goodsorder.getId());
			map.put("goodsname", goodsorder.getGoodsName());
			map.put("goodsnum", goodsorder.getGoodsNum());
			map.put("higherorderid", goodsorder.getHigherOrderId());
			map.put("agencyid", goodsorder.getAgencyId());
			map.put("limit", no);
			map.put("offset", start - 1);

			//获取当前登录人的ID
			UserDetailsImpl users = this.getUserInfo();
	        Long user_id=users.gettUser().getAgencyId();
	        map.put("user_id", user_id);
			
			List<Map<String, Object>> list1 = goodsOrderService.selectGoodsOrderBy(map);
			for (Map<String, Object> map2 : list1) {
				Long orderId =(Long)map2.get("user_id");
				VUserAgency  record=   vUserAgencyDbService.selectById(orderId);
				if(record.getUserIdenty()==0){
					map2.put("userName", record.getUserName());
					map2.put("userType", "会员");
				}
				else{
					map2.put("userName", record.getDealerName());
					map2.put("userType", "经销商");
				}
			}
			int count = goodsOrderService.countSelectOrderBy(map);
			Map<String, Object> jsonmap = new HashMap<String, Object>();
			jsonmap.put("rows", list1);
			jsonmap.put("total", count);
			return jsonmap;
		}
		
		
		 @RequestMapping("/toBeSendGoodsList")
		    public String toBeSendGoodsList(){
		        return "toBeSendGoodsList";
		    }
		
		/**
		 * 待发货 to be send
		 * */
		@RequestMapping("/toBeSendGoods")
		public @ResponseBody Object toBeSendGoods(String page,
				String rows,String goodsNum,String goodsName,String startDate,Date endDate) {
			UserDetailsImpl users = this.getUserInfo();
	        TUser sysuser=users.gettUser();
			long userid=sysuser.getId();
			long agencyId=sysuser.getAgencyId();
			// 当前页
			int intPage = Integer.parseInt((page == null) ? "1" : page);
			// 每页显示行数
			int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50"
								: rows);
			int end = intPage * no;
			int start = end - no + 1;
			
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("userId", userid);
			map.put("agencyId", agencyId);
			map.put("limit", no);
			map.put("offset", start - 1);
			map.put("goodsNum", goodsNum);
			map.put("goodsName", goodsName);
			if(endDate==null){
				endDate =new Date();
			}
			try {
				String endDate1 = DateUtils.addDays(endDate, 1);
				if(StringUtils.isNotEmpty(startDate)){
					map.put("startDate",new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
				}
				if(StringUtils.isNotEmpty(endDate1)){
					map.put("endDate", new SimpleDateFormat("yyyy-MM-dd").parse(endDate1));	
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			List<TGoodsOrder> toBeSendList=new ArrayList<TGoodsOrder>();
			try {
				toBeSendList = goodsOrderService.selectToSendBuyUserid(map);
			} catch (Exception e) {
				logger.error(null, e);
			}
			DecimalFormat df=new DecimalFormat("0.00");
			for(TGoodsOrder tGoodsOrder:toBeSendList){
				TAgency tAgency=tAgencyService.selectByAgencyId(tGoodsOrder.getAgencyId().longValue());
				tGoodsOrder.setAgencyName(tAgency.getDealerName());
				tGoodsOrder.setTotalAmt(df.format(tGoodsOrder.getBusinessMoney()/100.00));
				tGoodsOrder.setSendDate(DateUtils.DateToString(tGoodsOrder.getIssuedDate(), "yyyy-MM-dd HH:mm:ss"));
				if(tGoodsOrder.getOrderState()==4){
					tGoodsOrder.setOverdueState("已过期");
				}else if(tGoodsOrder.getOrderState()==3){
					tGoodsOrder.setOverdueState("成功领取");
				}else if(tGoodsOrder.getOrderState()==1){
					tGoodsOrder.setOverdueState("未领取");
				}
			}
			int count = goodsOrderService.countSelectToSendBuyUserid(map);
			Map<String, Object> jsonmap = new HashMap<String, Object>();
			jsonmap.put("rows", toBeSendList);
			jsonmap.put("total", count);
			return jsonmap;
		}
		
		/**
		 * 更新t_goods_order的订单状态并且增加库存数量
		 * @param id
		 * @author shangtengfei
		 * 2015-03-16
		 * @return
		 */
		@RequestMapping("/updateOrderStateById")
		public @ResponseBody int updateOrderStateById(int id) {
			int result = goodsOrderService.updateOrderStateById(id);
			return result;
		}
		
		
		/**
		 * 撤销产品操作
		 * 1.将订单的撤货状态改为已撤销
		 * 2.分到货的代理商或者会员减库存，上层经销商加库存
		 * @param id
		 * @return
		 */
		@RequestMapping("/cancelGoodsOrder")
		public @ResponseBody int cancelGoodsOrder(String id) {
			//撤销产品
			int result = goodsOrderService.cancelGoodsOrder(id);
			return result;
		}
}
