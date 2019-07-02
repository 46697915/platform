package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;

import com.wxsoft.business.pojo.YbjszxxJz;

public interface IYbjszxxJzDao {


	long findCount(@Param("ybjszxxJz") YbjszxxJz ybjszxxJz);

	List<YbjszxxJz> findAll(@Param("page") PageHelper page, @Param("ybjszxxJz") YbjszxxJz ybjszxxJz);
	
	public int insert(@Param("ybjszxxJz") YbjszxxJz ybjszxxJz);
	
	public int modify(@Param("ybjszxxJz") YbjszxxJz ybjszxxJz);
	
	public int delete(@Param("ybjszxxJz") YbjszxxJz ybjszxxJz);
	
}
