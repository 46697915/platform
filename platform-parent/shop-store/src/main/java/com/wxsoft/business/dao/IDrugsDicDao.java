package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugsDic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDrugsDicDao {


	long findCount(@Param("drugsDic") DrugsDic drugsDic);

	List<DrugsDic> findAll(@Param("page") PageHelper page, @Param("drugsDic") DrugsDic drugsDic);

	List<DrugsDic> findBy(@Param("drugsDic") DrugsDic drugsDic);
	
	public int insert(@Param("drugsDic") DrugsDic drugsDic);
	
	public int modify(@Param("drugsDic") DrugsDic drugsDic);
	
	public int delete(@Param("drugsDic") DrugsDic drugsDic);
	
}
