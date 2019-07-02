package com.wxsoft.business.controller;

import com.wxsoft.business.model.easyui.Json;
import com.wxsoft.business.pojo.User;
import com.wxsoft.util.common.JsonTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {

	/**
	 * 返回前台结果
	 * 
	 * @param response
	 * @param m
	 */
	public void writeReturn(HttpServletRequest request,HttpServletResponse response, String m) {
		try {
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg(m);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			String Agent = request.getHeader("User-Agent");
			System.out.println(Agent);
			if(Agent.indexOf("IE")<0){
				response.setContentType("application/json;charset=UTF-8");
			}
			response.getWriter().write(JsonTools.createJsonString(j));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 输出json
	 * 
	 * @param response
	 * @param object
	 */
//	public void write(HttpServletResponse response, Object object) {
//		try {
//			// response.setContentType("text/json;charset=" + charset);
//			// PrintWriter out = response.getWriter();
//			// out.write(json);
//			// out.close();
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/json;charset=UTF-8");
//			//response.setContentType("application/json;charset=UTF-8");
//			response.getWriter().write(JsonTools.createJsonString(object));
//			response.getWriter().flush();
//			response.getWriter().close();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	public void write(HttpServletRequest request,HttpServletResponse response, Object object) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			String Agent = request.getHeader("User-Agent");
			System.out.println(Agent);
			if(!(Agent.indexOf("IE")<0)){
				response.setContentType("application/json;charset=UTF-8");
			}
			response.getWriter().write(JsonTools.createJsonString(object));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 输出json
	 * 
	 * @param response
	 * @param json
	 */
	public void write(HttpServletResponse response, String json) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 返回结果消息
	 * 
	 * @param response
	 * @param bool
	 */
	public void write(HttpServletResponse response, boolean bool) {
		try {
			String json = "{\"success\":\"" + bool + "\"}";
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getloginName(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("USER");
		return user.getUsername();
	}
	public static Integer getloginId(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("USER");
		return user.getId();
	}
}
