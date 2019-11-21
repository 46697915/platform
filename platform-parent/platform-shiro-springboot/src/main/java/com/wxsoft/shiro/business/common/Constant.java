/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.wxsoft.shiro.business.common;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Constant {

    /** 数据权限过滤 */
	public static final String SQL_FILTER = "sql_filter";

	/**
	 * 菜单类型
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG("catalog"),
        /**
         * 菜单
         */
        MENU("menu"),
        /**
         * 按钮
         */
        BUTTON("button");

        private String value;

        MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
