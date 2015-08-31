package com.cbice.distribute.core.util;

import java.util.Random;

public class StringUtils {

	/**
	 * 生成15位长度的id（数字类型）
	 * 
	 * @param level
	 *            1表示代理商，2表示商户
	 * @return 代理商以90开头，商户以80开头
	 */
	public static String get15Id(int level) {
		String random = "" + System.currentTimeMillis();

		if (level == 1) {
			random = "90" + random;
		} else {
			random = "80" + random;
		}
		return random;
	}
	
	public static boolean isNotEmpty(String str) {
		return ((str != null) && (str.trim().length() > 0));
	}
	
	public static boolean isEmpty(String aStr) {
		return ((aStr == null) || (aStr.trim().length() == 0));
	}
	/**
	 * 获取文件名的后缀
	 * 
	 * @param fileName
	 *            abc.def
	 * @return def
	 */
	public static String getExtension(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			int i = fileName.lastIndexOf(".");
			if (i > -1 && i < (fileName.length() - 1)) {
				return fileName.substring(i + 1);
			}
		}
		return "";
	}

	/**
	 * 获取文件名，去掉文件后缀
	 * 
	 * @param fileName
	 *            全文件名
	 * @return
	 */
	public static String getFileName(String fileName) {
		if (fileName != null && fileName.length() > 0) {
			int i = fileName.lastIndexOf(".");
			if (i > -1 && i < (fileName.length() - 1)) {
				String s = fileName.substring(0, i);
				return s;
			}
		}
		return "";
	}


	/**
	 * 获取6位的随机数
	 * 
	 * @return
	 */
	public static String getRandom6() {
		StringBuffer str = new StringBuffer();
		int[] intRet = new int[6];
		int intRd = 0; // 存放随机数
		int count = 0; // 记录生成的随机数个数
		int flag = 0; // 是否已经生成过标志
		while (count < 6) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 9 + 1;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				str.append(intRd);
				count++;
			}
		}
		return str.toString();
	}
	
	/**
	 * 生成8位的数字和字母组合
	 * @return
	 */
	public static String getRandom8() {
		StringBuffer str = new StringBuffer();
		int count = 0; // 记录生成的随机数个数
		String[] strs = { "0", "1", "2", "3", "4", "5", "6", "7", "8","9", 
			  "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s","d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m",
			  "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N","M" };
		
		while (count < 8) {
			int a=(int)(Math.random()*62);
			str.append(strs[a]);
			count ++;
		}
		return str.toString();
	}
	
	public static String getByTag(String xml ,String tag){
	    if(org.apache.commons.lang.StringUtils.isEmpty(xml)||org.apache.commons.lang.StringUtils.isEmpty(tag))
	    	return "";
		int beg = xml.indexOf("<"+tag+">");
		if(beg<0)return "";
		int end = xml.indexOf("</"+tag+">");
		if(end<0) return "";
		return xml.substring(beg+2+tag.length(),end);
	}
	
	/**
	 * 生成30位的流水号，不重复
	 * <li>生成规则: "100" + 当前时间的毫秒值+14位随机数
	 * @return
	 */
	public static String get30SerialNum() {
		String random = "100" + System.currentTimeMillis();
		return random + random(9) + random(5);
	}
	
	/**
	 * 生成随机数（全部为数字）
	 * @param n 随机数长度，必须小于10
	 * @return
	 */
	public static String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while(true) {
                int k = ran.nextInt(10);
                if( (bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char)(k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }
}
