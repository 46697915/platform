package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.StorecheckJz;

import java.util.List;

public interface IStorecheckJzService {

    public long findCount(StorecheckJz storecheckJz);

    public List<StorecheckJz> findAll(PageHelper page, StorecheckJz storecheckJz);

    public String add(StorecheckJz storecheckJz);

    public String modify(StorecheckJz storecheckJz);

    public String delete(StorecheckJz storecheckJz);
}
