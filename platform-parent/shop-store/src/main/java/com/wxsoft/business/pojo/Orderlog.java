package com.wxsoft.business.pojo;

public class Orderlog {
	private int id;
	private String ordercode;
	private String operator;
	private String operatedate;
	private String operate;
	private String operatetype;
	private String drugStoreShortName;

	public String getDrugStoreShortName() {
		return drugStoreShortName;
	}
	public void setDrugStoreShortName(String drugStoreShortName) {
		this.drugStoreShortName = drugStoreShortName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(String operatedate) {
		this.operatedate = operatedate;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getOperatetype() {
		return operatetype;
	}
	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype;
	}

	
}
