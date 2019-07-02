package com.wxsoft.util.wx;

import java.util.HashMap;
import java.util.Map;
/**
 * 保存缓存对象
 * @author chenliang
 *
 */
public class CacheUtil {

	private static Map<String, Object> cache = new HashMap<String, Object>();

	public static Map<String, Object> getCache() {
		return cache;
	}

	/**
	 * 获取缓存对象
	 * @Description:TODO
	 * @author: chenliang
	 * @time:2018-4-2 下午8:21:56
	 */
	public static Object getCache(String key) {
		return cache.get(key);
	}

	public static void putCache(String key, Object obj) {
		CacheUtil.cache.put(key, obj);
	}
	public static void removeCache(String key) {
		CacheUtil.cache.remove(key);
	}
	
	
	
	
}
