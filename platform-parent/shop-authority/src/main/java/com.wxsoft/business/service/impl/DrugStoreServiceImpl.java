package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IDrugStoreDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugStore;
import com.wxsoft.business.service.IDrugStoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("drugStoreService")
public class DrugStoreServiceImpl implements IDrugStoreService  {
    @Resource
    private IDrugStoreDao dao;


    public long findCount(DrugStore drugStore) {
        return dao.findCount(drugStore);
    }

    public List<DrugStore> findAll(PageHelper page,DrugStore drugStore) {
        List<DrugStore> r = dao.findAll(page,drugStore);
        return r;
    }
    public List<DrugStore> findBy(DrugStore drugStore){
        List<DrugStore> r = dao.findBy(drugStore);
        return r;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(DrugStore drugStore) {
        this.dao.insert(drugStore);
        return "操作成功!";
    }
    public String addTable(List<String> tableList,String shortName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tablename", shortName);
        map.put("keys", tableList);
        this.dao.createNewTable(map);
        return "操作成功!";
    }

    public String modify(DrugStore drugStore) {
        this.dao.modify(drugStore);
        return "操作成功!";
    }

    public String delete(int id) {
        this.dao.delete(id);
        return "操作成功!";
    }
    public String deleteTables(List<String> tableList,String shortName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("tablename", shortName);
        map.put("keys", tableList);
        this.dao.deleteTables(map);
        return "操作成功!";
    }

    public DrugStore getDrugStoreById(String id) {
        // TODO Auto-generated method stub
        return this.dao.getDrugStoreById(id);
    }
    public DrugStore getDrugStoreByShotN(String shortname){
        return this.dao.getDrugStoreByShotN(shortname);

    }

}
