package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("order") Order order);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Order> findAll(@Param("page") PageHelper page, @Param("order") Order order);
	
	public int insert(Order order);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(Order order);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(Order order);
	
}
