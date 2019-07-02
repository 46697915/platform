package com.wxsoft.business.pojo;

import java.util.Date;

public class Keyvalue {
	private int id;
	private String type;
	private String code;
	private String name;
	private String operator;
	private Date operatetime;
	private String isvalidity;

	private String type_search;
	private String code_search;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public String getIsvalidity() {
		return isvalidity;
	}
	public void setIsvalidity(String isvalidity) {
		this.isvalidity = isvalidity;
	}
	public String getType_search() {
		return type_search;
	}
	public void setType_search(String type_search) {
		this.type_search = type_search;
	}
	public String getCode_search() {
		return code_search;
	}
	public void setCode_search(String code_search) {
		this.code_search = code_search;
	}

	
}
