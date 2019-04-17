package com.wxsoft.axis.core.util;

import org.springframework.context.ApplicationContext;

public class GlobalValues {
	/* ************************** begin 系统配置 ************************** */
	/**
	 * 应用程序是否初始化完成
	 */
	public static boolean enabled = false;
	/* ************************** end 系统配置 ************************** */
	
	
	/* ************************** begin 通道路由计算 ************************** */
	/**
	 * 计算路由后需添加个数
	 */
	public static Integer addCount = 0;
	/**
	 * 计算路由后需更新个数
	 */
	public static Integer updateCount = 0;
	/**
	 * 计算路由后需删除个数
	 */
	public static Integer delCount = 0;
	/* ************************** end 通道路由计算 ************************** */
	
	
	/* ************************** begin 电路路由计算 ************************** */
	/**
	 * 计算路由后需添加个数
	 */
	public static Integer addCount_circuit = 0;
	/**
	 * 计算路由后需更新个数
	 */
	public static Integer updateCount_circuit = 0;
	/**
	 * 计算路由后需删除个数
	 */
	public static Integer delCount_circuit = 0;
	/**
	 * 计算路由后电路与通道一对一个数
	 */
	public static Integer cp1v1Count_circuit = 0;
	/**
	 * 计算路由后电路与通道一对一个数(正向)
	 */
	public static Integer cp1v1Count_1_circuit = 0;
	/**
	 * 计算路由后电路与通道一对一个数(反向)
	 */
	public static Integer cp1v1Count_2_circuit = 0;
	/**
	 * 计算路由后电路与通道一对多个数
	 */
	public static Integer cp1vnCount_circuit = 0;
	/* ************************** end 电路路由计算 ************************** */
	
	
	/* ************************** begin 计算逻辑控制 ************************** */
	public static boolean calculateAll = false;
	public static boolean calculatePath = false;
	public static boolean calculatePathRoute = false;
	public static boolean calculateCircuitRoute = false;
	public static boolean calculateResBoard = false;
	public static boolean calculateResSlot = false;
	public static boolean calculateResShelf = false;
	public static boolean calculateResPort = false;
	public static boolean calculateResNe = false;
	/* ************************** end 计算逻辑控制 ************************** */
	
	
	
	/**
	 * 应用程序上下文
	 */
	private static ApplicationContext applicationContext;
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public static void setApplicationContext(ApplicationContext applicationContext) {
		GlobalValues.applicationContext = applicationContext;
	}
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
	public static <T> T getBean(Class<T> requiredType) {
		return getApplicationContext().getBean(requiredType);
	}
}
