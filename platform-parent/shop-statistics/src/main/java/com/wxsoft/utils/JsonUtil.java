package com.wxsoft.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * 对象转json并写入response 无分页信息
	 * @param response
	 * @param object
	 */
	public static void toResponseNOPage(HttpServletResponse response,
			Object object) {
		response.setContentType("text/html;charset=utf-8");
		String jsonString = "";
		try {
			jsonString = objectToJsonNoPage(object);
			logger.info("无分页信息json串：" + jsonString);
			response.getWriter().print(jsonString);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对象转json并写入response
	 * 
	 * @param response
	 * @param o
	 */
	public static void toResponse(HttpServletResponse response, Object o) {

		response.setContentType("text/html;charset=utf-8");
		String r = objectToJson(o);
		logger.info("json串：" + r);
		try {
			response.getWriter().print(r);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将字符串转换成json对象
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToJsonNoPage(Object object) {
		String jsonString = "";
		try {
			jsonString = JSON.toJSONString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	/**
	 * 将对象转成json串
	 * 
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = null;
		String jsonStr = "";
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(
					writer);
			objectMapper.writeValue(jsonGenerator, object);
			jsonStr = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				jsonGenerator.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonStr;
	}
}
