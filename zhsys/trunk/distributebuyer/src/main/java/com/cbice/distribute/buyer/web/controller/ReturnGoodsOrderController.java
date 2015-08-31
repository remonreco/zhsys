package com.cbice.distribute.buyer.web.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbice.distribute.buyer.service.TAgencyBuyerService;
import com.cbice.distribute.buyer.service.TgoodsOrderService;
import com.cbice.distribute.core.db.model.TAgency;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TSysUser;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.AdmimService;
import com.cbice.distribute.core.service.TRemainderGoodsService;
import com.cbice.distribute.core.util.AmountUtil;

@Controller
public class ReturnGoodsOrderController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	 	public SimpleDateFormat getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}

		@Resource
	    private TgoodsOrderService goodsOrderServie;
	    @Resource
	    private TAgencyBuyerService tAgencyService;
    
    //跳转到退单页面
    
    @RequestMapping(value = "/returnOrderList2", method = RequestMethod.POST)
    public String returnOrderList2(HttpServletRequest request,HttpServletResponse response,String orderId,Map<String, Object> map) throws IOException{
    	
   		String saleId=(String)request.getSession().getAttribute("salesCode");//销售码 
		String assetsAccount= (String)request.getSession().getAttribute("assetsAccount");//资产账号
		if(assetsAccount==null||assetsAccount.equals("")){
    		return "redirect:land.jsp";
    	}
		
		Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("assetsAccount", assetsAccount);
        map2.put("salesCode", saleId);
        List<TGoodsOrder> list=goodsOrderServie.selectUserMemberbyOrder(map2);
        if(list.size()<=0){
        	//查不到数据
        	request.getSession().setAttribute("error", "请检查你输入的信息是否正确");
        	return "redirect:land.jsp";
        }else{
        	//防止下单后backspace两次后再次退货
        	if(list.get(0).getPayState()==1){
        		//查不到数据
            	request.getSession().setAttribute("error", "错误操作,已成功下单,请重新登陆！");
            	return "redirect:land.jsp";
        	}
        	
        	
        	TGoodsOrder returngoods = new TGoodsOrder();
        	//生成金额显示格式
        	DecimalFormat df=new DecimalFormat("0.00");
        	returngoods = list.get(0);
        	returngoods.setAgencyName(tAgencyService.selectByPrimaryKey(returngoods.getAgencyId()).getDealerName());//经销商名称
        	returngoods.setTotalAmt(df.format(returngoods.getBusinessMoney()));//总价
        	double price=returngoods.getBusinessMoney()/returngoods.getDistributeNum();
        	returngoods.setSinglePrice(df.format(price/100.00));//单价
        	returngoods.setReturnGoodsState(returngoods.getReturnGoodsState());//订单状态
        	returngoods.setId(returngoods.getId());//订单号
        	returngoods.setDistributeNum(returngoods.getDistributeNum());//产品总数
        	returngoods.setGoodsName(returngoods.getGoodsName());//产品名称
        	returngoods.setGoodsNum(returngoods.getGoodsNum());//产品代码
        	map.put("returnGoods", returngoods);
        	
        	//产品数量
    		String t_goods_num =  returngoods.getDistributeNum().toString(); 
    		Long user_id = returngoods.getUserId();
    		//产品编码
    		String goods_num = returngoods.getGoodsNum();
    		String returnGoodsReason = "ok";
    		String goods_name = returngoods.getGoodsName();
    		String i = "";
			try {
				if (returngoods.getUserId()!=null) {
					i = goodsOrderServie.fromASingle(returngoods.getId(),user_id,t_goods_num,goods_num,returnGoodsReason) + "";
				}
			} catch (Exception e) {
				logger.error("申请错误",e);
			}
			if(i != null && i != ""){
				request.setAttribute("id", returngoods.getId());
				request.setAttribute("issued_date",formatter.format( returngoods.getIssuedDate()));
				request.setAttribute("goodsName", returngoods.getGoodsName());
				request.setAttribute("goodsNum", returngoods.getGoodsNum());
				request.setAttribute("agencyName", returngoods.getAgencyName());
				request.setAttribute("totalAmt",AmountUtil.fenToYuan(returngoods.getTotalAmt()));
				request.setAttribute("distributeNum", returngoods.getDistributeNum());
				return "returnSuss";
			}
			return i;
        }
    }
}
