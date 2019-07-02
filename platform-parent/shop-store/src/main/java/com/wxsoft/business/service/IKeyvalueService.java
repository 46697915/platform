package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Keyvalue;

import java.util.List;

public interface IKeyvalueService {

    public long findCount(Keyvalue keyvalue);

    public List<Keyvalue> findAll(PageHelper page, Keyvalue keyvalue);

    public List<Keyvalue> findByType(String type);

    public String add(Keyvalue keyvalue);

    public String modify(Keyvalue keyvalue);

    public String delete(int id);
}
