package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.OrderJz;

public interface IOrderJzDao {


	long findCount(@Param("orderJz") OrderJz orderJz);

	List<OrderJz> findAll(@Param("page") PageHelper page, @Param("orderJz") OrderJz orderJz);
	
	public int insert(@Param("orderJz") OrderJz orderJz);
	
	public int modify(@Param("orderJz") OrderJz orderJz);
	
	public int delete(@Param("orderJz") OrderJz orderJz);
	
}
