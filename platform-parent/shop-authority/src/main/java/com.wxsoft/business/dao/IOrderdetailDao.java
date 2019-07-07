package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.OrderdetailSummary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderdetailDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Orderdetail> findAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	long salesSummaryCount(@Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<OrderdetailSummary> salesSummaryAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	long salesAllSummaryCount(@Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<OrderdetailSummary> salesAllSummaryAll(@Param("page") PageHelper page, @Param("orderdetail") Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<OrderdetailSummary> salesSummaryByBarcode(@Param("orderdetail") Orderdetail orderdetail);

	public int insert(Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(Orderdetail orderdetail);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findMoneyCount(Orderdetail orderdetail);
	
}
