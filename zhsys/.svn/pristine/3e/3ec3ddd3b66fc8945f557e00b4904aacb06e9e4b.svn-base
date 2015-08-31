package com.cbice.distribute.core.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TsetInt {
	
	
	public static void main(String[] args) {
		
		String i="10000000000000000000000000";
		System.out.println(TsetInt.isInteger(i));
		
	}

	
	/***
	 * 判断 String 是否int
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInteger(String input){
		Matcher mer = Pattern.compile("^[0-9]+(\\.0+)$|^[0-9]+$").matcher(input);
		return mer.find();
	}
}
