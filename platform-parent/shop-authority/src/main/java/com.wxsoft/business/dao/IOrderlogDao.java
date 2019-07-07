package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderlogDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("orderlog") Orderlog orderlog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Orderlog> findAll(@Param("page") PageHelper page, @Param("orderlog") Orderlog orderlog);
	
	public int insert(Orderlog orderlog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(Orderlog orderlog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(Orderlog orderlog);
	
}
