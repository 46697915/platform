package com.wxsoft.business.pojo;

public class PaylogJz {
	private int id;
	private String ordercode;
	private String paystate;
	private String paymoney;
	private String paydate;
	private String paytype;
	private String payee;
	private String getdate;
	private String remark;
	private String alipaynum;

	private String transfer_id;
	
	private String drugStoreShortName;
	
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
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String paymoney) {
		this.paymoney = paymoney;
	}
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public String getGetdate() {
		return getdate;
	}
	public void setGetdate(String getdate) {
		this.getdate = getdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAlipaynum() {
		return alipaynum;
	}
	public void setAlipaynum(String alipaynum) {
		this.alipaynum = alipaynum;
	}

	
	public String getDrugStoreShortName() {
		return drugStoreShortName;
	}
	public void setDrugStoreShortName(String drugStoreShortName) {
		this.drugStoreShortName = drugStoreShortName;
	}
	public String getTransfer_id() {
		return transfer_id;
	}
	public void setTransfer_id(String transfer_id) {
		this.transfer_id = transfer_id;
	}
}
