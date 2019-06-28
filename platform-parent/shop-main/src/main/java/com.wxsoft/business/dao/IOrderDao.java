package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.Order;

public interface IOrderDao {


	long findCount(@Param("order") Order order);

	List<Order> findAll(@Param("page") PageHelper page, @Param("order") Order order);
	
	public int insert(Order order);
	
	public int modify(Order order);
	
	public int delete(Order order);
	
}
