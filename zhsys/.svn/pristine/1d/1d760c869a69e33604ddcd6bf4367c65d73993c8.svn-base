package com.cbice.distribute.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SequenceCreaterUtil {
	/**
	 * 生成带标志位的随机序列
	 * @param flag  flag为标志位如"XB"
	 * @return
	 * 
	 */
	public final static String []dic =SequenceCreaterUtil.initialiDic();
	public static String createSequence(String flag){
//		StringBuffer sequence =new StringBuffer(""+System.currentTimeMillis());
//		if(flag!=null && !flag.isEmpty()){
//			sequence.insert(0, flag);
//		}
//		Date date =new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		sequence.append(sdf.format(date));
//		sequence.append(SequenceCreaterUtil.getRandom2());
//		return sequence.toString();
		return SequenceCreaterUtil.getRandom8();
	}
	
	public static String createSerial(String flag){
		StringBuffer sequence =new StringBuffer(""+System.currentTimeMillis());
		if(flag!=null && !flag.isEmpty()){
		sequence.insert(0, flag);
	}
		
		sequence.append(SequenceCreaterUtil.getRandom8());
		sequence.append(SequenceCreaterUtil.getRandom8());
		sequence.append(SequenceCreaterUtil.getRandom8());
		
		return sequence.toString().substring(0,32);
	}
	
	/**
	 * 生成8位的数字和字母组合
	 * @return
	 */
	public static String getRandom8() {
		StringBuffer str = new StringBuffer();
		int count = 0; // 记录生成的随机数个数
		String[] strs = { "0", "1", "2", "3", "4", "5", "6", "7", "8","9", 
			  "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N","M" };
		
		while (count < 8) {
			int a=(int)(Math.random()*36);
			str.append(strs[a]);
			count ++;
		}
		return str.toString();
	}
   
	public static String[] initialiDic(){
		//定义随机种子
		int seed =100;
		Random  random=new Random(seed); 
		String[] strs = { "0", "1", "2", "3", "4", "5", "6", "7", "8","9", 
				  "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N","M" ,
				  "0", "1", "2", "3", "4", "5", "6", "7", "8","9",
				  "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N","M" ,
				  "0", "1", "2", "3", "4", "5", "6", "7", "8","9",
				  "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N","M" ,
				  "0", "1", "2", "3", "4", "5", "6", "7", "8","9"
		};
		//伪随机乱序
		String temp ="";
		for(int i=0;i<100;i++){
			int left=(int)(random.nextDouble()*100);
			int right=(int)(random.nextDouble()*100);
			temp =strs[left];
			strs[left]=strs[right];
			strs[right]=temp;
		}
		return strs;
	} 
}
