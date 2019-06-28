package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.pojo.DrugsDel;

public interface IDrugsDelDao {


	long findCount(@Param("drugsDel") DrugsDel drugsDel);

	List<DrugsDel> findAll(@Param("page") PageHelper page, @Param("drugsDel") DrugsDel drugsDel);
	List<DrugsDel> findBy(@Param("drugsDel") DrugsDel drugsDel);

	public int insertDrugsDel(@Param("drugsDel") DrugsDel drugsDel);
	public int insertDrugs(@Param("drugsDel") DrugsDel drugsDel);
	
	public int insert(@Param("drugsDel") DrugsDel drugsDel);
	
	public int modify(@Param("drugsDel") DrugsDel drugsDel);
	
	public int delete(@Param("drugsDel") DrugsDel drugsDel);
	
}
