package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;
import com.wxsoft.business.pojo.InstorageSummary;

public interface IInstorageDao {


	long findCount(@Param("instorage") Instorage instorage);

	List<Instorage> findAll(@Param("page") PageHelper page, @Param("instorage") Instorage instorage);

	long instorageSummaryCount(@Param("instorage") Instorage instorage);

	List<InstorageSummary> instorageSummaryAll(@Param("page") PageHelper page, @Param("instorage") Instorage instorage);

	List<InstorageSummary> instorageSummaryByBarcode(@Param("instorage") Instorage instorage);
	
	public int insert(Instorage instorage);
	
	public int modify(Instorage instorage);
	
	public int delete(Instorage instorage);
	
}
