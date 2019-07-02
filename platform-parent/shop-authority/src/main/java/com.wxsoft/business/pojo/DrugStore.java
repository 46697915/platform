package com.wxsoft.business.pojo;

import java.util.HashMap;
import java.util.Map;

public class DrugStore {
	private int id;
	private String drugstorename;
	private String shortname;
	private Integer creator;

	private User creatorUser;
	private String creatorname;
	private String createtime;
	private Integer updater;
	private User updaterUser;
	private String updatername;
	private String updatetime;
	private String status;
	private String statusname;
	private String remark;
	private String linkman;
	private String telephone;
	private String address;
	private String postcode;
	private String linkmanqq;
	private String storecode;
	
	public String getStorecode() {
		return storecode;
	}
	public void setStorecode(String storecode) {
		this.storecode = storecode;
	}
	private static Map<String,String> statusMap = new HashMap<String,String>();
	static{
		statusMap.put("1","启用");
		statusMap.put("2","不启用");
	}
	public String getStatusname() {
		if(statusMap.containsKey(this.status)){
			return statusMap.get(status);
		}
		return "默认状态";
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getUpdater() {
		return updater;
	}
	public void setUpdater(Integer updater) {
		this.updater = updater;
	}
	public User getCreatorUser() {
		return creatorUser;
	}
	public void setCreatorUser(User creatorUser) {
		this.creatorUser = creatorUser;
	}
	public User getUpdaterUser() {
		return updaterUser;
	}
	public void setUpdaterUser(User updaterUser) {
		this.updaterUser = updaterUser;
	}
	public String getCreatorname() {
		return creatorname;
	}
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public String getUpdatername() {
		return updatername;
	}
	public void setUpdatername(String updatername) {
		this.updatername = updatername;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrugstorename() {
		return drugstorename;
	}
	public void setDrugstorename(String drugstorename) {
		this.drugstorename = drugstorename;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getLinkmanqq() {
		return linkmanqq;
	}
	public void setLinkmanqq(String linkmanqq) {
		this.linkmanqq = linkmanqq;
	}

	
}
