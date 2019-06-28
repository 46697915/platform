package com.wxsoft.business.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StoreCheck;

public interface IStoreCheckDao {


	long findCount(@Param("storeCheck") StoreCheck storeCheck);

	List<StoreCheck> findAll(@Param("page") PageHelper page, @Param("storeCheck") StoreCheck storeCheck);

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
	List<StoreCheck> findMaxCheckdateByJZ(@Param("storeCheck") StoreCheck storeCheck);
	
	String findMaxCheckdate(@Param("storeCheck") StoreCheck storeCheck);
	
	public int insert(StoreCheck storeCheck);
	
	public int modify(StoreCheck storeCheck);
	
	public int delete(StoreCheck storeCheck);

	public int deleteSCByDate(StoreCheck storeCheck);
	public int updateLastCheck(StoreCheck storeCheck);
	
}
