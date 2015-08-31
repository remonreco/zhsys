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
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cbice.distribute.buyer.service.TAgencyBuyerService;
import com.cbice.distribute.buyer.service.TgoodsOrderService;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.StringUtils;

@Controller
public class AdminController{

    @Resource
    private TgoodsOrderService goodsOrderServie;
    @Resource
    private TAgencyBuyerService tAgencyService;
    @Resource
    private TUserService tUserService;

    @RequestMapping(value = "/BuyerLogin", method = RequestMethod.POST)
    public String BuyerLogin(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
		HttpSession session = request.getSession();
    	//获取相应参数
    	String assetId=request.getParameter("assetsAccount").trim();//资产账号
    	String saleId=request.getParameter("salesCode").trim();//销售码
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("assetsAccount", assetId);
        map2.put("salesCode", saleId);
        List<TGoodsOrder> list=goodsOrderServie.selectUserMemberbyOrder(map2);
        if(list.size()<=0){
        	//查不到数据
        	session.setAttribute("error", "请检查你输入的信息是否正确");
        	return "redirect:land.jsp";
        }else{
        	//判断是否退货
        	if(list.get(0).getOrderIdenty()==1){
        		session.setAttribute("error", "已申请退货,销售码已过期！");
        		return "redirect:land.jsp";
        	}
        	if(list.get(0).getSalesCode().length()<6){
        		session.setAttribute("error", "该销售码已申请退货！");
        		return "redirect:land.jsp";
        	}
        	try {
        		TGoodsOrder tgoods = new TGoodsOrder();
        		session.setAttribute("test", "test");
            	session.setAttribute("assetsAccount", assetId);
            	//request.setAttribute("tGoodsOrder", list.get(0));
            	//将资产账号和销售码信息存入session
            	session.setAttribute("salesCode", saleId);
            	
            	//生成金额显示格式
            	DecimalFormat df=new DecimalFormat("0.00");
            	 tgoods = list.get(0);
            	tgoods.setAgencyName(tAgencyService.selectByPrimaryKey(tgoods.getAgencyId()).getDealerName());//经销商名称
            	tgoods.setTotalAmt(df.format(tgoods.getBusinessMoney()/100.00));//总价
            	double price=tgoods.getBusinessMoney()/tgoods.getDistributeNum();
            	tgoods.setSinglePrice(df.format(price/100.00));//单价
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
            	tgoods.setSendDate(sdf.format(tgoods.getIssuedDate()));
            	String time=sdf.format(tgoods.getIssuedDate());
            	//request.setAttribute("tGoodsOrder", tgoods);
            	map.put("goodRecord", tgoods);
            	
            	//根据userid获取会员名称
            	TUser tUser=tUserService.selectByPrimaryKey(list.get(0).getUserId());
            	session.setAttribute("username", tUser.getUserName());
			} catch (Exception e) {
				e.printStackTrace();
			}
        	return "goodsxiangqing";
    }
}
    /**
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/buyerLoginOut", method = RequestMethod.POST)
    public String BuyerLoginout(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
    	
		request.getSession().removeAttribute("assetsAccount");
		request.getSession().removeAttribute("salesCode");
		request.getSession().removeAttribute("username");
    	return "redirect:land.jsp";
    }
    
    
    
    
    @RequestMapping(value = "/ConvertLogin", method = RequestMethod.POST)
    public String ConvertLogin(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map) throws IOException{
//		HttpSession session = request.getSession();
//		String userName=request.getParameter("userName").trim();
//		String userPwd=request.getParameter("userPwd").trim();
		return "convertView";
    }
}
