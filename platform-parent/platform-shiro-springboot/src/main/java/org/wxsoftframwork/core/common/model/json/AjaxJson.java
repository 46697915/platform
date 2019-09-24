package org.wxsoftframwork.core.common.model.json;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 前台请求后需要接受的JSON
 * 
 * @author
 * 
 */
public class AjaxJson {

	private boolean success = true;// 是否成功
	private String msg = "操作成功";// 提示信息
	private Object obj = null;// 其他信息
	private Object obj1 = null;// 其他信息
	private Object obj2 = null;// 其他信息
	private Map<String, Object> attributes;// 其他参数
	
	
	public Object getObj1() {
		return obj1;
	}

	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getJsonStr(){
		JSONObject obj = new JSONObject();
		obj.put("success", this.isSuccess());
		obj.put("msg", this.getMsg());
		obj.put("obj", this.obj);
		obj.put("obj1", this.obj1);
		obj.put("obj2", this.obj2);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
}
