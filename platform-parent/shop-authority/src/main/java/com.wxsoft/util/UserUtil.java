package com.wxsoft.util;

import com.wxsoft.business.pojo.User;
import com.wxsoft.util.common.Const;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {

	/**
	 * 返回当前用户
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request){

		User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
		return user;
	}
	public static User getUser(){

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER); 
		return user;
	}
	public static String getUsername(){

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
		return user.getUsername();
	}
}
