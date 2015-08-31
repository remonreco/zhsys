package com.cbice.distribute.mgr.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.util.constantList;
import com.cbice.distribute.mgr.security.model.TlowerUser;
import com.cbice.distribute.mgr.service.TgoodsOrderService;


@Controller
public class ReturnGoodsController extends BaseController{
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	   @Resource
	    private TgoodsOrderService goodsOrderService;
	   
		/*
		 * 查询退货清单跳转页面
		 */
	    @RequestMapping("/returnGoodsOrderList")
	    public String returnGoods(){
	        return "returnGoodsOrderList";
	    }
	    /*
		 * 查询退货清单
		 */
		@RequestMapping("/queryReturnGoodsOrder")
		public @ResponseBody Map<String, Object> queryReturnGoodsOrder(String page,
				String rows, TGoodsOrder goodsorder,String dealerName) {

			   // 当前页
	        int intPage = Integer.parseInt((page == null) ? "1" : page);
	        // 每页显示行数
	        int no = Integer.parseInt((rows == null || "0".equals(rows)) ? "50" : rows);
	        int end = intPage * no;
	        int start = end - no + 1;

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("goodsname", goodsorder.getGoodsName());//产品名称
			map.put("goodsnum", goodsorder.getGoodsNum());//产品编号
			map.put("dealerName", dealerName);//所属经销商名称
			map.put("return_goods_state", goodsorder.getReturnGoodsState());//审核状态
			map.put("agencyId", constantList.IceId);
			map.put("limit", no);
			map.put("offset", start - 1);

			List<Map<String, Object>> list = goodsOrderService.selectReturnGoodsList(map);
			int count = goodsOrderService.selectReturnGoodsListCount(map);
			Map<String, Object> jsonmap = new HashMap<String, Object>();
			jsonmap.put("rows", list);
			jsonmap.put("total", count);
			return jsonmap;
		}
		/*
		 * 退货清单申请审核
		 * /**
		 * @param id
		 * @param return_goods_state 0，申请，1审核通过，2审核不通过
		 * @param 
		 * @return
		 */
		@RequestMapping("/checkGoodsOrder")
		@ResponseBody
		public String  checkGoodsOrder(HttpServletRequest request,HttpServletResponse response, 
				Long id, Integer returnGoodsState, String return_goods_options,
				Long agency_id,Long higer_dealer_id,Long distribute_num,String goods_num){
			                     
			try { 
				if (id!=null) {
					return goodsOrderService.updateByPrimaryKeySelective(id,returnGoodsState,return_goods_options,
							agency_id,higer_dealer_id,distribute_num,goods_num) + "";
				}
			} catch (Exception e) {
				logger.error("通过或拒绝审核错误",e);
			}
			return "0";
		}

}
