package com.wxsoft.axis.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 格式化时间
	 * @param date		时间
	 * @param string	格式
	 * @return
	 */
	public static String format(Date date, String string) {
		SimpleDateFormat f = new SimpleDateFormat(string);
		return f.format(date);
	}
	
	/**
	 * 格式化时间(默认格式:yyyy-MM-dd HH:mm:ss)
	 * @param date	时间
	 * @return
	 */
	public static String format(Date date) {
		return format(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 格式化时间(默认当前时间,默认格式:yyyy-MM-dd HH:mm:ss)
	 * @param date	时间
	 * @return
	 */
	public static String format() {
		return format(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 格式化时间
	 * @param dateStr	时间字符串
	 * @param string	格式
	 * @return
	 * @throws ParseException 
	 */
	public static Date parse(String dateStr, String string) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(string);
		return f.parse(dateStr);
	}
	
	/**
	 * 格式化时间(默认格式:yyyy-MM-dd HH:mm:ss)
	 * @param date	时间
	 * @return
	 * @throws ParseException 
	 */
	public static Date parse(String dateStr) throws ParseException {
		return parse(dateStr,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 格式化时间(默认格式:yyyy-MM-dd HH:mm:ss)
	 * @param date	时间
	 * @return
	 * @throws ParseException 
	 */
	public static Date currDate() {
		return new Date();
	}
	
}
