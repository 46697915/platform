package com.wxsoft.business.dao;


import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StoreCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStoreCheckDao {


	@PermissionAop(storeAlias ="drugstoreshortname" )
	long findCount(@Param("storeCheck") StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<StoreCheck> findAll(@Param("page") PageHelper page, @Param("storeCheck") StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<StoreCheck> findBy(@Param("storeCheck") StoreCheck storeCheck);
	/**
	 * 根据结转日期获取 最大的 盘点记录
	 * @Description:TODO
	 * @参数： @param storeCheck
	 * @参数： @return
	 * @return List<StoreCheck>  
	 * @throws
	 * @author: chenliang
	 * @time:2018-6-10 下午10:10:34
	 */
	@PermissionAop(storeAlias ="drugstoreshortname" )
	List<StoreCheck> findMaxCheckdateByJZ(@Param("storeCheck") StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	String findMaxCheckdate(@Param("storeCheck") StoreCheck storeCheck);
	
	public int insert(StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int modify(StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int delete(StoreCheck storeCheck);

	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int deleteSCByDate(StoreCheck storeCheck);
	@PermissionAop(storeAlias ="drugstoreshortname" )
	public int updateLastCheck(StoreCheck storeCheck);
	
}
