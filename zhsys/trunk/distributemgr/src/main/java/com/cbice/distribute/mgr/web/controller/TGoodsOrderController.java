package com.cbice.distribute.mgr.web.controller;

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

import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.db.model.VUserAgency;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.service.VUserAgencyDbService;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.security.model.UserDetailsImpl;
import com.cbice.distribute.mgr.service.TgoodsOrderService;


@Controller
public class TGoodsOrderController extends BaseController{
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	   @Resource
	    private TgoodsOrderService goodsOrderService;
	   @Resource
	    private VUserAgencyDbService vUserAgencyDbService;
	    
	   //跳转到撤销产品管理
	    @RequestMapping("/goodsOrderList")
	    public String sentGoods(){
	        return "goodsOrderList";
	    }
	    //撤销产品管理查询
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
			map.put("agencyname", goodsorder.getAgencyName());
			map.put("limit", no);
			map.put("offset", start - 1);

			//获取ICE的Id
	        
	        map.put("user_id", constantList.IceId);//获取上级经销商的ID
			
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
