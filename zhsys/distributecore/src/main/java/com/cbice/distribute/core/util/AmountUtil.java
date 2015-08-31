package com.cbice.distribute.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtil {
	/**
	 * 将元字符串转换为分
	 * 
	 * @param amountStr
	 * @return
	 */
	public static long parseAmountStr2Long(String amountStr) {
		if (amountStr == null || "".equals(amountStr)) {
			return 0L;
		}
		double amount = Double.parseDouble(amountStr);
		Double db = amount * 100;
		DecimalFormat df = new DecimalFormat("#");
		String s = df.format(db);
		return Long.parseLong(s);
	}

	/**
	 * 将分转换为元
	 * 
	 * @param amountStr
	 * @return
	 */
	public static String parseAmountLong2Str(Long amountLong) {
		if (amountLong == null) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double d = amountLong / 100d;
		String s = df.format(d);
		return s;
	}
	

	/**
	 * 将元转换为分
	 * 
	 * @param yuan
	 * @return
	 */
	public static Long yuan2Fen(Double yuan) {
		Double dFen = yuan * 100;
		Long lFen = dFen.longValue();
		return lFen;
	}

	/**
	 * 将分转换为元(四舍五入到分)
	 * 
	 * @param fen
	 * @return
	 */
	public static Double fen2Yuan(Long fen) {
		Double yuan = fen / 100.00;
		BigDecimal big = new BigDecimal(fen / 100.00);
		yuan = big.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return yuan;
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 去掉小数点
	 */
	public static String spitStr(String amountStr) {
		int index = amountStr.indexOf(".");
		if (index != -1) {
			amountStr = amountStr.substring(0, index);
		}
		return amountStr;
	}
	
	/**
	 * 将分转换为元
	 * 
	 * @param fen
	 * @return
	 */
	public static String fenToYuan(String fen ){
		
		if(fen==null || "".equals(fen)){
            return "0.00";			
		}
		double fen1 = Double.parseDouble(fen);
		double d = fen1 / 100d;
		DecimalFormat df = new DecimalFormat("0.00");
		String s = df.format(d);
		return s;
		
	}

	/**
	 * 分相减在换元，减法
	 * @param fen1,fen2
	 * @return
	 */
	public static String subtraction(String fen1,String fen2 ){
		
		double f2=0L; double subtract=0L;
		double f1=0L;
		if(fen1==null || "".equals(fen1)){
			 fen1="000";
		}
		if(fen2==null || "".equals(fen2)){
			 fen2="000";
		}
		f1= Double.parseDouble(fen1);
		f2= Double.parseDouble(fen2);
		if(f1>=f2){
			subtract=f1-f2;
			System.out.println("f1>=f2");
		}else{
			subtract=f2-f1;
		}
		DecimalFormat df = new DecimalFormat("0.00");
		double d1 = subtract / 100d;
		String s = df.format(d1);
		return s;
		
	}
	
	/**
	 * 将钱格式化，精确到小数点后多少位 四舍五入
	 * 
	 * @param money
	 * @return
	 */
	public static Double formatMoney(double money,int point) {
		Double yuan = money;
		BigDecimal big = new BigDecimal(money);
		yuan = big.setScale(point, BigDecimal.ROUND_HALF_UP).doubleValue();
		return yuan;
	}

	public static void main(String[] args) {
		String fen="20000";
		System.out.println(AmountUtil.fenToYuan(fen));
		//System.out.println(2000.0-1000.0);
	}

}
