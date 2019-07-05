package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IGoodsDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("goods") Goods goods);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Goods> findAll(@Param("page") PageHelper page, @Param("goods") Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCountForXS(@Param("goods") Goods goods);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Goods> findAllForXS(@Param("page") PageHelper page, @Param("goods") Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<Goods> findBy(@Param("goods") Goods goods);
	String getStockBy(@Param("goods") Goods goods);
	
	public int insert(Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modifyByBarcode(Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(@Param("goods") Goods goods);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int deleteBy(@Param("goods") Goods goods);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	Goods findById(Goods goods);
	
}
