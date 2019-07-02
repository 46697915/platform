package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.OrderlogJz;

public interface IOrderlogJzDao {


	long findCount(@Param("orderlogJz") OrderlogJz orderlogJz);

	List<OrderlogJz> findAll(@Param("page") PageHelper page, @Param("orderlogJz") OrderlogJz orderlogJz);
	
	public int insert(@Param("orderlogJz") OrderlogJz orderlogJz);
	
	public int modify(@Param("orderlogJz") OrderlogJz orderlogJz);
	
	public int delete(@Param("orderlogJz") OrderlogJz orderlogJz);
	
}
