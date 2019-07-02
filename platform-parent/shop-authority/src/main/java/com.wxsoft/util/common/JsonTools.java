package com.wxsoft.util.common;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.*;

public class JsonTools {
	private static Logger logger = Logger.getLogger(JsonTools.class);
	/**
	 * 将字符串转换成json对象.
	 * @param object
	 * @return
	 */
	public static String createJsonString(Object object){
	    String jsonString = "";
	    try {
	      jsonString = JSON.toJSONString(object);
	    } catch (Exception e) {
	    	logger.info(e.getMessage());
	    	e.printStackTrace();
	    }
	    return jsonString;
	}
	/**
	 * 解析json串为List
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String, String>> jsonToList(String jsonString)
    {
        JSONArray arry = JSONArray.fromObject(jsonString);
//        System.out.println("json字符串内容如下");
//        System.out.println(arry);
        List<Map<String, String>> rsList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < arry.size(); i++)
        {
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String, String> map = new HashMap<String, String>();
            for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();)
            {
                String key = (String) iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key, value);
            }
            rsList.add(map);
        }
        return rsList;
    }
	
	public static void main(String [] s){
		String json = "[{\"id\":2,\"name\":\"123\",\"sellprice\":\"123.00\",\"count\":1}]";
		jsonToList(json);
	}
	
}
