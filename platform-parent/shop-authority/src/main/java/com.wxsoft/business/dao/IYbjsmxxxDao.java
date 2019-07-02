package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ybjsmxxx;
import com.wxsoft.business.pojo.YbjsmxxxSummary;

public interface IYbjsmxxxDao {


	long findCount(@Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);

	List<Ybjsmxxx> findAll(@Param("page") PageHelper page, @Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);

	long ybSalesSummaryCount(@Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);

	List<YbjsmxxxSummary> ybSalesSummaryAll(@Param("page") PageHelper page, @Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);

	List<YbjsmxxxSummary> ybSalesSummaryByBarcode(@Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);

	List<YbjsmxxxSummary> ybSalesSummaryByYyxmbm(@Param("ybjsmxxx") Ybjsmxxx ybjsmxxx);
	
	public int insert(Ybjsmxxx ybjsmxxx);
	
	public int modify(Ybjsmxxx ybjsmxxx);
	
	public int delete(Ybjsmxxx ybjsmxxx);
	
}
