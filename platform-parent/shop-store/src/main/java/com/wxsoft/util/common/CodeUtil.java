package com.wxsoft.util.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CodeUtil {

	private static long counter = 0;

	/**
	 * 产生没后缀的编码
	 * @return 产生17位编码
	 */
	public synchronized static String getCode(){
		return getCode("");
	}
	/**
	 *  产生待后缀的编码
	 * @param type
	 * @return 产生17位编码
	 * @throws Exception
	 */
	public synchronized static String getCode(String suffix){
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) ;
		String sequ = new DecimalFormat("000").format(counter++);
		return date + sequ + suffix;
	}

	public static Class<String> getObjectType() {
		return String.class;
	}

	public static boolean isSingleton() {
		return false;
	}

	public static void reset() {
		CodeUtil.counter = 0;
	}

}
