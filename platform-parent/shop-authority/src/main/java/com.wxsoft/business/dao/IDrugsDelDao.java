package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugsDel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDrugsDelDao {


	long findCount(@Param("drugsDel") DrugsDel drugsDel);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<DrugsDel> findAll(@Param("page") PageHelper page, @Param("drugsDel") DrugsDel drugsDel);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<DrugsDel> findBy(@Param("drugsDel") DrugsDel drugsDel);

	public int insertDrugsDel(@Param("drugsDel") DrugsDel drugsDel);
	public int insertDrugs(@Param("drugsDel") DrugsDel drugsDel);
	
	public int insert(@Param("drugsDel") DrugsDel drugsDel);
	
	public int modify(@Param("drugsDel") DrugsDel drugsDel);
	
	public int delete(@Param("drugsDel") DrugsDel drugsDel);
	
}
