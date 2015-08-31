package com.cbice.distribute.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

public class DateUtils {
	 private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 
	 /**
	  * 监控查询时间使用
	  * @param time
	  * @return
	  */
	 public static String getMonitorStartTime(Calendar time){
		 int currentMM = DateUtils.getTimeMM(time);
			int endMM = 0;
			for(int i=0; i<5; i++){
				if((currentMM + i) % 5 == 0){
					endMM = currentMM + i;
					break;
				}
			}
			int startMM = endMM - 5;
			return DateUtils.getSpecifiedStartTime(DateUtils.getTimeYear(time), DateUtils.getTimeMonth(time), DateUtils.getTimeDate(time), DateUtils.getTimeHH24(time), startMM);
	 }
	 
	 /**
	  * 监控查询时间使用
	  * @param time
	  * @return
	  */
	 public static String getMonitorEndTime(Calendar time){
			int currentMM = DateUtils.getTimeMM(time);
			int endMM = 0;
			for(int i=0; i<5; i++){
				if((currentMM + i) % 5 == 0){
					endMM = currentMM + i;
					break;
				}
			}
			///int startMM = endMM - 5;
			return DateUtils.getSpecifiedEndTime(DateUtils.getTimeYear(time), DateUtils.getTimeMonth(time), DateUtils.getTimeDate(time), DateUtils.getTimeHH24(time), endMM-1);
	 }
	 
	 
	 
	 public static String getShowTime(Calendar time){
		 int currentMM = DateUtils.getTimeMM(time);
			int endMM = 0;
			for(int i=0; i<5; i++){
				if((currentMM + i) % 5 == 0){
					endMM = currentMM + i;
					break;
				}
			}
			return DateUtils.getSpecifiedStartTime(DateUtils.getTimeYear(time), DateUtils.getTimeMonth(time), DateUtils.getTimeDate(time), DateUtils.getTimeHH24(time), endMM);
	 }
	 
	 public static Date getCurrentDateForDB(){
		 return new Date();
	 }
	 
	 public static Date formatDateYYYYMMDDHHMMSS(String time){
		 return formatDate(time, "yyyy-MM-dd H:m:s");
	 }
	 
	 public static Calendar addMMDate(Date time, int n){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(org.apache.commons.lang.time.DateUtils.addMinutes(time, n));
		 return cal;
	 }
	 
	 public static String addHours(Date time, int n){
		return format.format(org.apache.commons.lang.time.DateUtils.addHours(time, n));
	 }
	 
	 public static String addDays(Date time, int n){
		 return format.format(org.apache.commons.lang.time.DateUtils.addDays(time, n));
	 }
	 
	public static String getCurrentDate(){
		return formateTime(new Date().getTime());
	}
	
	public static Calendar getCalendar(){
		return Calendar.getInstance();
	}
	
	public static int getTimeYear(Calendar time){
		return time.get(Calendar.YEAR);
	}
	
	public static int getTimeMonth(Calendar time){
		return time.get(Calendar.MONTH);
	}
	
	public static int getTimeDate(Calendar time){
		return time.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getTimeHH24(Calendar time){
		return time.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getTimeMM(Calendar time){
		return time.get(Calendar.MINUTE);
	}
	
	
	public static Calendar getSpecifieldCalendar(int year, int month, int date, int hourOfDay, int minute){
		 Calendar c = Calendar.getInstance();
		 c.set(year, month, date, hourOfDay, minute, 00);
		 return c;
	}
	
	public static String getSpecifiedStartTime(int year, int month, int date, int hourOfDay, int minute){
		 Calendar c = Calendar.getInstance();
		 c.set(year, month, date, hourOfDay, minute, 00);
		return format.format(c.getTime());
	}
	
	public static Date getSpecifiedStartDate(int year, int month, int date, int hourOfDay, int minute){
		 Calendar c = Calendar.getInstance();
		 c.set(year, month, date, hourOfDay, minute, 00);
		 return c.getTime();
	}
	
	
	public static String getSpecifiedEndTime(int year, int month, int date, int hourOfDay, int minute){
		Calendar c = Calendar.getInstance();
		c.set(year, month, date, hourOfDay, minute, 59);
		return format.format(c.getTime());
	}
	
	public static String getSpecifiedTime(Calendar time){
		return format.format(time.getTime())+0;
	}
	
	public static String getYesterday(){
		return formateTime( org.apache.commons.lang.time.DateUtils.addDays(new Date(), -1).getTime());
	}
	
	public static Date createDate(String dateString, String pattern)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(dateString);
	}

	public static String parseDate(Date date, String pattern) {
		if (date == null)
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	public static String formateTime(Long time) {
		return DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date formatDate(String dateStr, String pattern) {
		if (!StringUtils.isNotBlank(dateStr))
			throw new IllegalArgumentException("date is null");
		if (pattern == null)
			throw new IllegalArgumentException("pattern is null");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("格式化日期异常");
		}
		return date;
	}
	
	public static Long dateToLong(String dateTime, String pattern){
		return formatDate(dateTime, pattern ).getTime();
		
	}
	
	/** 日期转换为自定义格式输出 */
	public static String DateToString(Date date, String formatType) {
		if (date == null) {
			return null;
		}
		if (formatType == null || "".equals(formatType)) {
			return null;
		}
		String dateStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatType);
			dateStr = sdf.format(date);
			return dateStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 根据传入的日期，获取相隔天数日期
	 * 
	 * @param date
	 * @param anyDay
	 *            可正可负
	 * @return
	 */
	public static Date getAnyDayByNo(Date date, int anyDay) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_WEEK, anyDay);
		return c.getTime();
	}
	
	/**
	 * 根据传入的日期，获取相隔分钟的时间
	 * 
	 * @param date
	 * @param anyTime
	 *            可正可负
	 * @return
	 */
	public static Date getAnyTimeByNow(Date date, int anyTime) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, anyTime);
		return c.getTime();
	}
	/**
	 * 传入日期，去除时分秒（置零 00:00:00）
	 * @param date
	 * @return
	 */
	public static Date removeTime(Date date){
		if(date==null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		
	}
	/**
	 * 传入日期，去除分秒
	 * @param date
	 * @return
	 */
	public static Date removeMinute(Date date){
		if(date==null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		
	}
	
	/**
	 * 获取今日日期固定格式的字符串
	 * @return
	 */
	 public static String formatDateYYYYMMDD(){
		 Date date=new Date();
	     SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		 return sdf.format(date);
	 }
	 
	 /**
	  * 将日期类型字符串转换为long
	 * @throws Exception 
	  */
	 public static Long StringToLong(String dateStr){
		 SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		 try {
			return sd.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	 }
	 
	 /**
	  * 将String类型字符串转换为Date
	 * @throws Exception 
	  */
	 public static Date StringToDate(String dateStr,String format){
		if (dateStr == null) {
			return null;
		}
		if (format == null || "".equals(format)) {
			return null;
		}
		 SimpleDateFormat sd = new SimpleDateFormat(format);
		 try {
			return sd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	 }
}
