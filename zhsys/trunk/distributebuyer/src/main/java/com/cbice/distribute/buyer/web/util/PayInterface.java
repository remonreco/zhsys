package com.cbice.distribute.buyer.web.util;

import java.util.HashMap;
import java.util.Map;

public class PayInterface {
	
	public static Map<String,String> pay(Map<String,String> map){
		
		Map<String,String> sendmap=new HashMap<String,String>();
		String url= PropertiseUtil.getDataFromPropertiseFile("site", "site.result");//前台通知地址
		String bgurl=PropertiseUtil.getDataFromPropertiseFile("site", "site.resultbg");//后台通知地址
		sendmap.put("pageurl", url);//支付完成后，跳转到商户方的页面
		sendmap.put("bgurl",bgurl);//后台地址
		sendmap.put("transactionid",map.get("orderSerial") );//商户交易号
		sendmap.put("subtransactionid",map.get("orderSerial") );//商户子订单号
		sendmap.put("taccountid","" );//主付款方账号
		sendmap.put("orderamount",map.get("totalAmt"));//orderAmount 商户订单金额
		sendmap.put("payeeamount",map.get("totalAmt") );//主收款方应收额
		sendmap.put("ordertime",map.get("orderTime"));//商户订单提交时间
		sendmap.put("productname",map.get("goodsName"));//商品名称
		sendmap.put("productnum",map.get("distributeNum"));//商品数量
		sendmap.put("productdesc","" );//商品描述
		sendmap.put("paytype","10" );//支付方式
		sendmap.put("bankid","" );//银行代码
		sendmap.put("pid","105290054110500" );//合作伙伴用户编号 merId
		sendmap.put("sharingfeecalctype","1" );//支付平台手续费计算方式
		sendmap.put("sharingfeetype","1" );//支付平台手续费承担方式
		sendmap.put("haspayeefee","1" );//是否分账方承担主收款方手续费
		sendmap.put("sharingdata","1" );//分账数据
		sendmap.put("sharingpayflag","1" );//分账标志
		sendmap.put("inputcharset","1" );//字符集
		sendmap.put("version","v2.0" );//网关版本
		sendmap.put("language","1" );//语言种类
		sendmap.put("signtype","1" );//签名类型
		sendmap.put("ext1","ext1" );
		sendmap.put("ext2","ext2" );
		return sendmap;
	}
	
	
}
