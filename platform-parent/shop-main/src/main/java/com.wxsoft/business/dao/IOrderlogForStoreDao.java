package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.Order;
import com.wxsoft.business.pojo.Orderlog;

public interface IOrderlogForStoreDao {


	long findCount(@Param("orderlog") Orderlog orderlog);
	public Integer getLastId(@Param("orderlog") Orderlog orderlog);
	
	List<Orderlog> findAll(@Param("page") PageHelper page, @Param("orderlog") Orderlog orderlog);
	
	public int insert(@Param("orderlog") Orderlog orderlog);
	
	public int modify(@Param("orderlog") Orderlog orderlog);
	
	public int delete(@Param("orderlog") Orderlog orderlog);
	
}
