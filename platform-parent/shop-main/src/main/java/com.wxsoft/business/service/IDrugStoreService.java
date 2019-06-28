package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugStore;

import java.util.List;

public interface IDrugStoreService {

    public long findCount(DrugStore drugStore);

    public List<DrugStore> findAll(PageHelper page, DrugStore drugStore);
    public List<DrugStore> findBy(DrugStore drugStore);

    public String add(DrugStore drugStore);
    public String addTable(List<String> tableList, String shortName);

    public String modify(DrugStore drugStore);

    public String delete(int id);

    public String deleteTables(List<String> tableList, String shortName);

    public DrugStore getDrugStoreById(String id);
    public DrugStore getDrugStoreByShotN(String shortname);
}
