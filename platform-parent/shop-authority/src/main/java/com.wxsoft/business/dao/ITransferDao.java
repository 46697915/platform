package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Transfer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITransferDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("transfer") Transfer transfer);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Transfer> findAll(@Param("page") PageHelper page, @Param("transfer") Transfer transfer);
	
	public int insert(@Param("transfer") Transfer transfer);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(@Param("transfer") Transfer transfer);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(@Param("transfer") Transfer transfer);
	
}
