package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.InstorageJz;

import java.util.List;

public interface IInstorageJzService {

    public long findCount(InstorageJz instorageJz);

    public List<InstorageJz> findAll(PageHelper page, InstorageJz instorageJz);

    public String add(InstorageJz instorageJz);

    public String modify(InstorageJz instorageJz);

    public String delete(InstorageJz instorageJz);
}
