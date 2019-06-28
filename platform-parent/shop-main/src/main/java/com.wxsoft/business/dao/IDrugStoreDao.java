package com.wxsoft.business.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugStore;

public interface IDrugStoreDao {


	long findCount(@Param("drugStore") DrugStore drugStore);

	List<DrugStore> findAll(@Param("page") PageHelper page, @Param("drugStore") DrugStore drugStore);
	List<DrugStore> findBy(@Param("drugStore") DrugStore drugStore);
	
	public int insert(DrugStore drugStore);
	public int createNewTable(Map<String, Object> map);
	public int modify(DrugStore drugStore);
	public String deleteTables(Map<String, Object> map);
	public int delete(int id);
	public DrugStore getDrugStoreById(String id);
	public DrugStore getDrugStoreByShotN(String shortname);
	
}
