package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.OrderdetailJz;

public interface IOrderdetailJzDao {


	long findCount(@Param("orderdetailJz") OrderdetailJz orderdetailJz);

	List<OrderdetailJz> findAll(@Param("page") PageHelper page, @Param("orderdetailJz") OrderdetailJz orderdetailJz);
	
	public int insert(@Param("orderdetailJz") OrderdetailJz orderdetailJz);
	
	public int modify(@Param("orderdetailJz") OrderdetailJz orderdetailJz);
	
	public int delete(@Param("orderdetailJz") OrderdetailJz orderdetailJz);
	
}
