package com.cbice.distribute.buyer.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.epay.util.EncException;
import com.buybal.epay.util.PayEncrypt;
import com.buybal.epay.util.PaySign;
import com.buybal.epay.util.SharingPayMsg;
import com.cbice.distribute.buyer.service.TAgencyBuyerService;
import com.cbice.distribute.buyer.service.TgoodsOrderService;
import com.cbice.distribute.buyer.web.util.PayInterface;
import com.cbice.distribute.buyer.web.util.PropertiseUtil;
import com.cbice.distribute.core.db.model.TGoodsOrder;
import com.cbice.distribute.core.db.model.TUser;
import com.cbice.distribute.core.service.TUserService;
import com.cbice.distribute.core.util.StringUtils;

@Controller
public class BuyerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private TgoodsOrderService goodsOrderServie;
	 @Resource
    private TAgencyBuyerService tAgencyService;
	  @Resource
	    private TUserService tUserService;
	/**
	 * 前台相应
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/paymentOrder", method = RequestMethod.GET)
	public String paymentOrder(HttpServletRequest request, Map<String,Object> map)
			throws UnsupportedEncodingException {
		// 成功或失败 页面显示名称值
		// String name = new String(request.getParameter("goodsName"));
		// String agencyName = new String(request.getParameter("agencyName"));
		// request.setAttribute("name", name);
		// request.setAttribute("agencyName", agencyName);
		
		HttpSession session=request.getSession();
		// -----------------------------------------------
		String pid = new String(request.getParameter("pid"));
		String payresult = new String(request.getParameter("payresult"));
		String errcode = new String(request.getParameter("errcode"));
		String signmsg = new String(request.getParameter("signmsg"));

		StringBuffer sharingDataStr = new StringBuffer();
		if (request.getParameter("paytype") != null) {
			sharingDataStr.append("paytype=" + request.getParameter("paytype")
					+ "&");
		}
		if (request.getParameter("transactionid") != null) {
			sharingDataStr.append("transactionid="
					+ request.getParameter("transactionid") + "&");
		}
		if (request.getParameter("dealid") != null) {
			sharingDataStr.append("dealid=" + request.getParameter("dealid")
					+ "&");
		}
		if (request.getParameter("dealtime") != null) {
			sharingDataStr.append("dealtime="
					+ request.getParameter("dealtime") + "&");
		}
		if (request.getParameter("payamount") != null) {
			sharingDataStr.append("payamount="
					+ request.getParameter("payamount") + "&");
		}
		if (request.getParameter("payresult") != null) {
			sharingDataStr.append("payresult="
					+ request.getParameter("payresult") + "&");
		}
		String sharingresult = request.getParameter("sharingresult");
		if (sharingresult == null)
			sharingresult = "";
		sharingDataStr.append("sharingresult=" + sharingresult + "&");

		if (!sharingDataStr.toString().equals("")) {
			sharingDataStr.deleteCharAt(sharingDataStr.lastIndexOf("&"));
		}
		String backStr = sharingDataStr.toString();
		System.out.println("backStr=[" + backStr + "]");

		// 签名验证
		String merKey = PropertiseUtil.getDataFromPropertiseFile("site", pid);
		System.out.println(merKey);
		PaySign ps = new PaySign();
		System.out.println("signmsg=[" + signmsg + "]");
		
		//----------------------------------------------
				
				// 验证登录是否超时
				//查询订单给页面传值
				//-----------------------------------------------------------------------------------------------
				//获取相应参数
		        String transactionid=request.getParameter("transactionid");//订单流水号
		        Map<String,Object> map2=new HashMap<String,Object>();
		        map2.put("order_serial", transactionid);
		        TGoodsOrder tgoods=goodsOrderServie.selectOrderbySerial(map2);
		        DecimalFormat df=new DecimalFormat("0.00");
		        String username=tAgencyService.selectByPrimaryKey(tgoods.getAgencyId()).getDealerName();
		        tgoods.setAgencyName(username);//经销商名称
			   	tgoods.setTotalAmt(df.format(tgoods.getBusinessMoney()/100.00));//总价
			   	double price=tgoods.getBusinessMoney()/tgoods.getDistributeNum();
			   	tgoods.setSinglePrice(df.format(price/100.00));//单价
			   	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			   	tgoods.setSendDate(sdf.format(tgoods.getIssuedDate()));
			   	String time=sdf.format(tgoods.getIssuedDate());
				map.put("record", tgoods);
				
				TUser tUser=tUserService.selectByPrimaryKey(tgoods.getUserId());
            	session.setAttribute("username", tUser.getUserName());
		try {
			boolean flag = ps.verify(backStr, signmsg, merKey);
			if (!flag) {
				logger.info("验签失败,返回串为: "+backStr+"签名为："+signmsg);
				return "filed";
				
			}
			if (errcode.equals("") && !errcode.equals("0000")) {
				logger.info("支付失败，错误码 :"+errcode);
				return "filed";
			}
			if (payresult.equals("11")) {
//				if (payresult.equals("11")) {
//					// 支付失败 修改支付状态 失败
//					//tgoods.setId(list.get(0).getId());
//					//tgoods.setPayState(0);
//					//goodsOrderServie.updateByPrimaryKeySelective(tgoods);
//				}
				logger.info("支付失败，支付结果："+payresult);
				return "filed";
			}
			if (errcode.equals("0000") && payresult.equals("10")) {
				 request.setAttribute("name", "asdf");
				 request.setAttribute("agencyName", "asdfads");
				// 支付成功 修改支付状态 成功
					if (errcode.equals("0000") && payresult.equals("10")) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						request.setAttribute("payTime", formatter.format(new Date()));
						if(tgoods.getPayState()!=null && tgoods.getPayState()!=1){
						tgoods.setPayState(1);
						tgoods.setOrderState(3);
						tgoods.setIssuedDate(new Date());
						goodsOrderServie.updateByPrimaryKeySelective(tgoods);
						}
					}
				return "sucss";
			}
			
		} catch (EncException e) {
			e.printStackTrace();
		}
		return "filed";
		// ----------------------------------------------
	}
	/**
	 * 后台通知
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/houtaitongzhi", method = RequestMethod.GET)
	public void houtaitongzhi(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		HttpSession session=request.getSession();
		// -----------------------------------------------
		String pid = new String(request.getParameter("pid"));
		String payresult = new String(request.getParameter("payresult"));
		String errcode = new String(request.getParameter("errcode"));
		String signmsg = new String(request.getParameter("signmsg"));

		StringBuffer sharingDataStr = new StringBuffer();
		if (request.getParameter("paytype") != null) {
			sharingDataStr.append("paytype=" + request.getParameter("paytype")
					+ "&");
		}
		if (request.getParameter("transactionid") != null) {
			sharingDataStr.append("transactionid="
					+ request.getParameter("transactionid") + "&");
		}
		if (request.getParameter("dealid") != null) {
			sharingDataStr.append("dealid=" + request.getParameter("dealid")
					+ "&");
		}
		if (request.getParameter("dealtime") != null) {
			sharingDataStr.append("dealtime="
					+ request.getParameter("dealtime") + "&");
		}
		if (request.getParameter("payamount") != null) {
			sharingDataStr.append("payamount="
					+ request.getParameter("payamount") + "&");
		}
		if (request.getParameter("payresult") != null) {
			sharingDataStr.append("payresult="
					+ request.getParameter("payresult") + "&");
		}
		String sharingresult = request.getParameter("sharingresult");
		if (sharingresult == null)
			sharingresult = "";
		sharingDataStr.append("sharingresult=" + sharingresult + "&");

		if (!sharingDataStr.toString().equals("")) {
			sharingDataStr.deleteCharAt(sharingDataStr.lastIndexOf("&"));
		}
		String backStr = sharingDataStr.toString();
		System.out.println("backStr=[" + backStr + "]");

		// 签名验证
		String merKey = PropertiseUtil.getDataFromPropertiseFile("site", pid);
		System.out.println(merKey);
		PaySign ps = new PaySign();
		System.out.println("signmsg=[" + signmsg + "]");
		
		//----------------------------------------------
				
				// 验证登录是否超时
				//查询订单给页面传值
				//-----------------------------------------------------------------------------------------------
				//获取相应参数
		        String transactionid=request.getParameter("transactionid");//订单流水号
		        Map<String,Object> map2=new HashMap<String,Object>();
		        map2.put("order_serial", transactionid);
		        TGoodsOrder tgoods=goodsOrderServie.selectOrderbySerial(map2);
		        DecimalFormat df=new DecimalFormat("0.00");
		        String username=tAgencyService.selectByPrimaryKey(tgoods.getAgencyId()).getDealerName();
		        tgoods.setAgencyName(username);//经销商名称
			   	tgoods.setTotalAmt(df.format(tgoods.getBusinessMoney()/100.00));//总价
			   	double price=tgoods.getBusinessMoney()/tgoods.getDistributeNum();
			   	tgoods.setSinglePrice(df.format(price/100.00));//单价
			   	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			   	tgoods.setSendDate(sdf.format(tgoods.getIssuedDate()));
			   	String time=sdf.format(tgoods.getIssuedDate());
				//map.put("record", tgoods);
				
				TUser tUser=tUserService.selectByPrimaryKey(tgoods.getUserId());
            	session.setAttribute("username", tUser.getUserName());
		try {
			boolean flag = ps.verify(backStr, signmsg, merKey);
			if (!flag) {
				logger.info("验签失败,返回串为: "+backStr+"签名为："+signmsg);
				//return "filed";
			}
			if (errcode.equals("") && !errcode.equals("0000")) {
				//return "filed";
				logger.info("支付失败，错误码 :"+errcode);
			}
			if (payresult.equals("11")) {
//				if (payresult.equals("11")) {
//					// 支付失败 修改支付状态 失败
//					//tgoods.setId(list.get(0).getId());
//					//tgoods.setPayState(0);
//					//goodsOrderServie.updateByPrimaryKeySelective(tgoods);
//				}
				logger.info("支付失败，支付结果："+payresult);
				//return "filed";
			}
			if (errcode.equals("0000") && payresult.equals("10")) {
				 request.setAttribute("name", "asdf");
				 request.setAttribute("agencyName", "asdfads");
				// 支付成功 修改支付状态 成功
					if (errcode.equals("0000") && payresult.equals("10")) {
						SimpleDateFormat formatter = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						request.setAttribute("payTime", formatter.format(new Date()));
						if(tgoods.getPayState()!=null && tgoods.getPayState()!=1){
						tgoods.setPayState(1);
						tgoods.setOrderState(3);
						tgoods.setIssuedDate(new Date());
						goodsOrderServie.updateByPrimaryKeySelective(tgoods);
						}
					}
				//return "sucss";
			}
			
		} catch (EncException e) {
			e.printStackTrace();
		}
		
	}
//
//		String pid = new String(request.getParameter("pid"));
//		String payresult = new String(request.getParameter("payresult"));
//		String errcode = new String(request.getParameter("errcode"));
//		String signmsg = new String(request.getParameter("signmsg"));
//
//		StringBuffer sharingDataStr = new StringBuffer();
//		if (request.getParameter("paytype") != null) {
//			sharingDataStr.append("paytype=" + request.getParameter("paytype")
//					+ "&");
//		}
//		if (request.getParameter("transactionid") != null) {
//			sharingDataStr.append("transactionid="
//					+ request.getParameter("transactionid") + "&");
//		}
//		if (request.getParameter("dealid") != null) {
//			sharingDataStr.append("dealid=" + request.getParameter("dealid")
//					+ "&");
//		}
//		if (request.getParameter("dealtime") != null) {
//			sharingDataStr.append("dealtime="
//					+ request.getParameter("dealtime") + "&");
//		}
//		if (request.getParameter("payamount") != null) {
//			sharingDataStr.append("payamount="
//					+ request.getParameter("payamount") + "&");
//		}
//		if (request.getParameter("payresult") != null) {
//			sharingDataStr.append("payresult="
//					+ request.getParameter("payresult") + "&");
//		}
//		String sharingresult = request.getParameter("sharingresult");
//		if (sharingresult == null)
//			sharingresult = "";
//		sharingDataStr.append("sharingresult=" + sharingresult + "&");
//
//		if (!sharingDataStr.toString().equals("")) {
//			sharingDataStr.deleteCharAt(sharingDataStr.lastIndexOf("&"));
//		}
//		String backStr = sharingDataStr.toString();
//		System.out.println("backStr=[" + backStr + "]");
//
//		// 签名验证
//		String merKey = PropertiseUtil.getDataFromPropertiseFile("site", pid);
//		System.out.println(merKey);
//		PaySign ps = new PaySign();
//		System.out.println("signmsg=[" + signmsg + "]");
//		try {
//			boolean flag = ps.verify(backStr, signmsg, merKey);
//			String assetsAccount = (String) request.getSession().getAttribute(
//					"assetsAccount");
//			String salesCode = (String) request.getSession().getAttribute(
//					"salesCode");
//			// 根据资产账号和销售码 查询相关信息
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("assetsAccount", assetsAccount);
//			map.put("salesCode", salesCode);
//			List<TGoodsOrder> list = goodsOrderServie
//					.selectUserMemberbyOrder(map);
//
//			// 支付成功 修改支付状态 成功
//			TGoodsOrder tgoods = new TGoodsOrder();
//			if (payresult.equals("11")) {
//				// 支付失败 修改支付状态 失败
//				tgoods.setId(list.get(0).getId());
//				tgoods.setPayState(0);
//				goodsOrderServie.updateByPrimaryKeySelective(tgoods);
//			}
//			if (errcode.equals("0000") && payresult.equals("10")) {
//				SimpleDateFormat formatter = new SimpleDateFormat(
//						"yyyy-MM-dd HH:mm:ss");
//				request.setAttribute("payTime", formatter.format(new Date()));
//				tgoods.setId(list.get(0).getId());
//				request.setAttribute("id", list.get(0).getId());
//				tgoods.setPayState(1);
//				tgoods.setOrderState(3);
//				tgoods.setIssuedDate(new Date());
//				goodsOrderServie.updateByPrimaryKeySelective(tgoods);
//			}
//
//		} catch (EncException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@RequestMapping(value = "/choicePay", method = RequestMethod.POST)
	public String choicePay(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		// request.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF-8");
		String name = new String(request.getParameter("goodsName"));
		// name =java.net.URLDecoder.decode(name,"UTF-8");
		String orderId = new String(request.getParameter("orderId"));
		if (orderId != null && orderId != "") {
			TGoodsOrder tgoodsorder = goodsOrderServie.selectByPrimaryKey(Long
					.parseLong(orderId));
			if (tgoodsorder.getPayState() == 1) {
				request.getSession()
						.setAttribute("error", "错误操作，订单已生效！请尝试重新登录");
				return "redirect:land.jsp";
			}
			// 退货 由于退货生成了一条新数据,最开始的数据还是0
			if (tgoodsorder.getOrderIdenty() == 0
					&& tgoodsorder.getSalesCode().equals("XXX")) {
				request.getSession()
						.setAttribute("error", "错误操作，订单已生效！请尝试重新登录");
				return "redirect:land.jsp";
			}
		}
		String agencyName = new String(request.getParameter("agencyName")); // 货物名称

		// ----------------支付接口参数封装---------------
		String totalAmt = new String(request.getParameter("totalAmt"));// 总价
		//String singlePrice = new String(request.getParameter("singlePrice")); // 单间
		String orderSerial = new String(request.getParameter("orderSerial")); // 订单流水号
		//String orderSerial = orderSerial2.substring(0, 17);
		String distributeNum = new String(request.getParameter("distributeNum"));// 交易数量orderTime
		String orderTime = new String(request.getParameter("orderTime"));// 订单有效时间
		Map<String, String> map = new HashMap<String, String>();
		map.put("goodsName", name);
		map.put("totalAmt", totalAmt);
		map.put("orderSerial", orderSerial);
		map.put("distributeNum", distributeNum);
		map.put("orderTime", orderTime);

		Map<String, String> sendMap = PayInterface.pay(map);
		SharingPayMsg sharingPayMsg = SharingPayMsg.getSharingPayMsg(sendMap);
		// 对分账数据进行加密
		PayEncrypt pe = new PayEncrypt();
		String sharingData = "";
		String merKey = PropertiseUtil.getDataFromPropertiseFile("site",
				sharingPayMsg.getPid());
		// sharingContactType^ sharingContact^ sharingApplyAmount^
		// sharingFeeRate^ payeeFee^ sharingDesc<br>
		String sharingdata = "1^";
		sharingdata = sharingdata + sharingPayMsg.getPid() + "^"
				+ sharingPayMsg.getOrderamount() + "^0^0.00^desc";
		try {
			sharingData = pe.encryptMode(merKey, sharingdata);
			sharingPayMsg.setSharingdata(sharingData);
		} catch (EncException e) {
			e.printStackTrace();
		}
		// 签名
		PaySign ps = new PaySign();
		String signmsg;
		String sharingPayStr = sharingPayMsg.getSharingPayStr();
		try {
			signmsg = ps.sign(sharingPayStr, merKey);
			sharingPayMsg.setSignmsg(signmsg);
		} catch (EncException e) {
			e.printStackTrace();
		}

		request.setAttribute("version", sharingPayMsg.getVersion());
		request.setAttribute("sharingPayMsg", sharingPayMsg);
		return "merOrder2DovePay";
	}

	

}
