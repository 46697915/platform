package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDrugsDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("drugs") Drugs drugs);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Drugs> findAll(@Param("page") PageHelper page, @Param("drugs") Drugs drugs);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Drugs> findBy(@Param("drugs") Drugs drugs);
	
	public int insert(@Param("drugs") Drugs drugs);
	
	public int modify(@Param("drugs") Drugs drugs);
	
	public int delete(@Param("drugs") Drugs drugs);
	
}
