package com.wxsoft.business.pojo;

import java.util.Date;

public class Transfer {
	private String id;
	private String jzrq;
	private String operator;
	private Date operatedate;
	private String barcode;
	private String drugsname;
	private String drugscode;
	private String stock;
	
	private String drugStoreShortName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJzrq() {
		return jzrq;
	}
	public void setJzrq(String jzrq) {
		this.jzrq = jzrq;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOperatedate() {
		return operatedate;
	}
	public void setOperatedate(Date operatedate) {
		this.operatedate = operatedate;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDrugsname() {
		return drugsname;
	}
	public void setDrugsname(String drugsname) {
		this.drugsname = drugsname;
	}
	public String getDrugscode() {
		return drugscode;
	}
	public void setDrugscode(String drugscode) {
		this.drugscode = drugscode;
	}
	public String getDrugStoreShortName() {
		return drugStoreShortName;
	}
	public void setDrugStoreShortName(String drugStoreShortName) {
		this.drugStoreShortName = drugStoreShortName;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

	
}
