/**
 * 日期帮助类
 */
package com.wxsoft.util.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author W Jian
 */
public class DateUtil {
	/**
	 * 默认分隔符.
	 */
	public static final String YEAR_MONTH_DAY_SEPARATOR = "-";

	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static int nextMonth() {
		return nextMonth(1);
	}

	public static int nextMonth(int value) {
		return nextMonth(
			GregorianCalendar.getInstance().get(Calendar.MONTH) + 1, value);
	}

	/*
	 * public static int nextDay(int value){ return }
	 * 
	 * public static int nextDay(int ){}
	 */
	public static int nextMonth(int month, int value) {
		if (month < 1 || month > 12)
			throw new IllegalArgumentException("month is invalid.");

		return (month + (value < 0 ? (value % 12 + 12) : value) - 1) % 12 + 1;
	}

	public static int previousMonth() {
		return nextMonth(-1);
	}

	public static int previousMonth(int value) {
		return nextMonth(value * -1);
	}

	public static int previousMonth(int month, int value) {
		return nextMonth(month, value * -1);
	}

	public static String complementMonth(int month) {
		return month < 10 ? ("0" + month) : ("" + month);
	}

	public static boolean isValid(String date) {
		return isValid(date, DateUtil.YEAR_MONTH_DAY_SEPARATOR);
	}

	public static boolean isValid(String date, String separator) {
		Pattern p = Pattern.compile(String.format(
			"^(\\d{2,4}%1$s\\d{1,2}%1$s\\d{1,2})$", separator));
		Matcher m = p.matcher(date);
		if (!m.matches()) {
			return false;
		}

		DateFormat format = new SimpleDateFormat(String.format(
			"yyyy%1$sMM%1$sdd", separator));
		format.setLenient(false);

		try {
			format.parse(date);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static String format(String date, String oldFormat) {
		return format(date, oldFormat, DateUtil.DATETIME_FORMAT);
	}

	public static String format(String date, String oldFormat, String newFormat) {
		if (date == null || "".equals(date)) {
			return "";
		}
		DateFormat of = new SimpleDateFormat(oldFormat);
		DateFormat nf = new SimpleDateFormat(newFormat);
		of.setLenient(false);

		try {
			Date d = of.parse(date);
			return nf.format(d);
		} catch (ParseException e) {
			throw new IllegalArgumentException(String.format(
				"无法使用参数[%s,%s]进行日期解析.", date, oldFormat));
		}
	}

	public static int betweenMonth(String beginYearMonth, String endYearMonth) {

		return betweenMonth(
			beginYearMonth, endYearMonth, DateUtil.YEAR_MONTH_DAY_SEPARATOR);
	}

	public static int betweenMonth(
			String beginYearMonth, String endYearMonth, String separator) {
		Pattern p = Pattern.compile(String.format(
			"^(\\d{4}%1$s\\d{1,2})$", separator));
		if (!(p.matcher(beginYearMonth).matches() && p
			.matcher(endYearMonth).matches())) {
			throw new IllegalArgumentException("date is invalid.");
		}

		// Calendar c = GregorianCalendar.getInstance();
		DateFormat format = new SimpleDateFormat(String.format(
			"yyyy%1$sMM", separator));

		try {
			Calendar begin = GregorianCalendar.getInstance();
			begin.setTime(format.parse(beginYearMonth));

			Calendar end = GregorianCalendar.getInstance();
			end.setTime(format.parse(endYearMonth));

			int i = 0;
			while (begin.compareTo(end) < 0) {
				begin.roll(1, false);
			}

			return i;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}
	/**
	 * 返回当前时间字符串格式"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String currentDatetime() {
		return currentDatetime(DateUtil.DATETIME_FORMAT);
	}
	/**
	 * 返回当前时间字符串格式自定义
	 * @param format
	 * @return
	 */
	public static String currentDatetime(String format) {
		DateFormat formator = new SimpleDateFormat(format);
		return formator.format(new Date());
	}

	public static String getDate(String str) {
		String time;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		java.sql.Date date = new java.sql.Date(d.getTime());

		time = date.toString();

		return time;
	}

	/**
	 * 任意月份的下一个月
	 */
	public static String getNextMomthly(String str) {
		String monthly = "";
		int year = Integer.parseInt(str.substring(0, 4));
		int mon = Integer.parseInt(str.substring(4));
		mon = mon + 1;
		if (mon <= 12) {
			monthly = (mon) < 10 ? ("0" + mon) : ("" + mon);
		} else {
			year = year + 1;
			monthly = "01";
		}
		return year + monthly;
	}

	/**
	 * 获取当前日期的前一天 日期格式：yyyy-MM-dd
	 */
	public static String getYestoday() {
		return getYestoday(currentDatetime());
	}

	/**
	 * 获取指定日期的前一天 日期格式：yyyy-MM-dd
	 */
	public static String getYestoday(String date) {
		int year, month, day;
		Calendar c;
		SimpleDateFormat sf;

		sf = new SimpleDateFormat("yyyy-MM-dd");
		year = Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(5, 7)) - 1;
		day = Integer.parseInt(date.substring(8, 10));
		c = new GregorianCalendar(year, month, day);
		c.add(GregorianCalendar.DATE, -1);
		return sf.format(c.getTime());
	}
	/**
	 * 获取第二天,日期格式：yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getNextDay(String date) {
		
		return getNextDay(date,"yyyy-MM-dd");
	}
	/**
	 * 获取第二天
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getNextDay(String date,String format) {
		int year, month, day;
		Calendar c;
		SimpleDateFormat sf;

		sf = new SimpleDateFormat(format);
		year = Integer.parseInt(date.substring(0, 4));
		month = Integer.parseInt(date.substring(5, 7)) - 1;
		day = Integer.parseInt(date.substring(8, 10));
		c = new GregorianCalendar(year, month, day);
		c.add(GregorianCalendar.DATE, 1);
		return sf.format(c.getTime());
	}

	/**
	 * 任意月份的上一个月
	 */
	public static String getTopMomthly(String str) {
		String monthly = "";
		int year = Integer.parseInt(str.substring(0, 4));
		int mon = Integer.parseInt(str.substring(4));
		mon = mon - 1;
		if (mon >= 1) {
			monthly = (mon) < 10 ? ("0" + mon) : ("" + mon);
		} else {
			year = year - 1;
			monthly = "12";
		}
		return year + monthly;
	}

	/**
	 * 任意月份的下一个月"yyyy-mm-dd"
	 * @param str
	 * @return
	 */
	public static String getNextMomthly_(String str) {
		String monthly = "";
		int year = Integer.parseInt(str.split("-")[0]);
		int mon = Integer.parseInt(str.split("-")[1]);
		int dd = Integer.parseInt(str.split("-")[2]);
		String DATE_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		c1.set(year, mon, dd);

		return sdf.format(c1.getTime());
	}
	/**
	 * 取得下一个月份
	 *
	 * @author 作者：chenliang
	 * @version 创建时间：Nov 19, 2012  11:01:23 PM
	 * @param format
	 * @return
	 */
	public static String getNextMonth(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
		
		return sdf.format(c.getTime());
	}
	/**
	 * 取得下一个年
	 *
	 * @author 作者：chenliang
	 * @version 创建时间：Nov 19, 2012  11:01:23 PM
	 * @param format
	 * @return
	 */
	public static String getNextYear(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)+12);
		
		return sdf.format(c.getTime());
	}
	/**
	 * 根据字符串返回日期Date类型
	 *
	 * @author 作者：chenliang
	 * @version 创建时间：Nov 19, 2012  11:10:54 PM
	 * @param dateStr
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(dateStr);
		return d ;
	}

	public static void main(String[] args) {
		try {
			System.out.println("----=="+getNextMonth("yyyyMMdd"));
			System.out.println(getDate("20120101","yyyyMMdd"));
			Calendar c = new GregorianCalendar(2009, 2, 1);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			System.out.println("加之前:" + sf.format(c.getTime()));
			// c.add(Calendar.DATE, 1);
			c.add(GregorianCalendar.DATE, -1);
			/*
			 * if(c.DAY_OF_YEAR == new Integer(1)){ c.roll(Calendar.DAY_OF_YEAR,
			 * 1); if(c.MONTH == 1){ c.roll(Calendar.MONTH, 1);
			 * c.roll(Calendar.YEAR, 1); } }else{ c.roll(Calendar.DAY_OF_YEAR,
			 * 1); }
			 */
			sf.format(c.getTime());
			System.out.println("加之后:" + sf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(DateUtil.nextMonth(8));
		// System.out.println(DateUtil.previousMonth());
		// System.out.println(DateUtil.currentDatetime("yyyy")+DateUtil.complementMonth(DateUtil.previousMonth(1)));
		// System.out.println(DateUtil.isValid("2007/4/20","/"));
		// System.out.println(DateUtil.currentDatetime().toString());
		// System.out.println(DateUtil.currentDatetime().toString());
		// System.out.println(DateUtil.currentDatetime("yyyy/MM/dd"));
		// System.out.println(DateUtil.currentDatetime("yyyyMMdd"));
		// System.out.println(DateUtil.format("2008-1-1","yyyy-M-d"));
		// System.out.println(DateUtil.format("2008-01-01","yyyy/MM-dd","yyyy-M-d"));
		// System.out.println(DateUtil.betweenMonth("2004-4","2004-06"));
		// System.out.println(DateUtil.getNextMomthly("200602"));
		// System.out.println(DateUtil.getTopMomthly("200601"));
		// System.out.println("2009-01-01".compareTo("2009-11-01"));
		// System.out.println("2007/4/20".replace("/","-"));
	}
}