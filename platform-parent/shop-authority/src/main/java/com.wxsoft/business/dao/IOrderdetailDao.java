package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.OrderdetailSummary;

public interface IOrderdetailDao {


	long findCount(@Param("orderdetail") Orderdetail orderdetail);

	List<Orderdetail> findAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	long salesSummaryCount(@Param("orderdetail") Orderdetail orderdetail);

	List<OrderdetailSummary> salesSummaryAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	long salesAllSummaryCount(@Param("orderdetail") Orderdetail orderdetail);

	List<OrderdetailSummary> salesAllSummaryAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	List<OrderdetailSummary> salesSummaryByBarcode(@Param("orderdetail") Orderdetail orderdetail);

	public int insert(Orderdetail orderdetail);
	
	public int modify(Orderdetail orderdetail);
	
	public int delete(Orderdetail orderdetail);
	
	long findMoneyCount(Orderdetail orderdetail);
	
}
