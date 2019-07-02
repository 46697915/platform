package com.wxsoft.business.dao;


import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Transfer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ITransferDao {


	long findCount(@Param("transfer") Transfer transfer);

	List<Transfer> findAll(@Param("page") PageHelper page, @Param("transfer") Transfer transfer);
	
	public int insert(@Param("transfer") Transfer transfer);
	
	public int modify(@Param("transfer") Transfer transfer);
	
	public int delete(@Param("transfer") Transfer transfer);
	
}
