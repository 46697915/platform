package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.YbjsmxxxJz;

public interface IYbjsmxxxJzDao {


	long findCount(@Param("ybjsmxxxJz") YbjsmxxxJz ybjsmxxxJz);

	List<YbjsmxxxJz> findAll(@Param("page") PageHelper page, @Param("ybjsmxxxJz") YbjsmxxxJz ybjsmxxxJz);
	
	public int insert(@Param("ybjsmxxxJz") YbjsmxxxJz ybjsmxxxJz);
	
	public int modify(@Param("ybjsmxxxJz") YbjsmxxxJz ybjsmxxxJz);
	
	public int delete(@Param("ybjsmxxxJz") YbjsmxxxJz ybjsmxxxJz);
	
}
