package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Paylog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPaylogDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("paylog") Paylog paylog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Paylog> findAll(@Param("page") PageHelper page, @Param("paylog") Paylog paylog);
	
	public int insert(Paylog paylog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(Paylog paylog);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(Paylog paylog);
	
}
