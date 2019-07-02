package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.YbjszxxJz;

import java.util.List;

public interface IYbjszxxJzService {

    public long findCount(YbjszxxJz ybjszxxJz);

    public List<YbjszxxJz> findAll(PageHelper page, YbjszxxJz ybjszxxJz);

    public String add(YbjszxxJz ybjszxxJz);

    public String modify(YbjszxxJz ybjszxxJz);

    public String delete(YbjszxxJz ybjszxxJz);
}
