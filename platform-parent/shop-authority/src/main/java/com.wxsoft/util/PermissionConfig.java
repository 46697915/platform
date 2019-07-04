package com.wxsoft.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置
 * 对应 permission.properties
 * @author GaoYuan
 */
public class PermissionConfig {
	private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<>(16);

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader(
			"permission.properties");

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		if(loader == null){
			logger.info("缺失配置文件 - permission.properties");
			return null;
		}
		String value = map.get(key);
		if (value == null) {
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

}