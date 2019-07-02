package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;

public interface IOrderdetailForStoreDao {


	long findCount(@Param("orderdetail") Orderdetail orderdetail);
	public Integer getLastId(@Param("orderdetail") Orderdetail orderdetail);
	
	List<Orderdetail> findAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);
	
	public int insert(@Param("instorage") Orderdetail orderdetail);
	
	public int modify(@Param("instorage") Orderdetail orderdetail);
	
	public int delete(@Param("orderdetail") Orderdetail orderdetail);
	
	public long findMoneyCount(@Param("orderdetail") Orderdetail orderdetail);
}
