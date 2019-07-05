package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;
import com.wxsoft.business.pojo.InstorageSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IInstorageDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("instorage") Instorage instorage);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Instorage> findAll(@Param("page") PageHelper page, @Param("instorage") Instorage instorage);

	long instorageSummaryCount(@Param("instorage") Instorage instorage);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<InstorageSummary> instorageSummaryAll(@Param("page") PageHelper page, @Param("instorage") Instorage instorage);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<InstorageSummary> instorageSummaryByBarcode(@Param("instorage") Instorage instorage);
	
	public int insert(Instorage instorage);
	
	public int modify(Instorage instorage);
	
	public int delete(Instorage instorage);
	
}
