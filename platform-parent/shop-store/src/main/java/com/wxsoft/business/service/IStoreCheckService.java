package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StoreCheck;

import java.text.ParseException;
import java.util.List;

public interface IStoreCheckService {

    public long findCount(StoreCheck storeCheck);

    public List<StoreCheck> findAll(PageHelper page, StoreCheck storeCheck);

    public String add(StoreCheck storeCheck);

    public String modify(StoreCheck storeCheck);

    public String delete(StoreCheck storeCheck);

    public String check(StoreCheck storeCheck) throws ParseException;

    public String deleteSCByDate(StoreCheck storeCheck);

}
