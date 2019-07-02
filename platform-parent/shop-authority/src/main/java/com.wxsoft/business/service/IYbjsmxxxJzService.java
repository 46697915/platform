package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.YbjsmxxxJz;

import java.util.List;

public interface IYbjsmxxxJzService {

    public long findCount(YbjsmxxxJz ybjsmxxxJz);

    public List<YbjsmxxxJz> findAll(PageHelper page, YbjsmxxxJz ybjsmxxxJz);

    public String add(YbjsmxxxJz ybjsmxxxJz);

    public String modify(YbjsmxxxJz ybjsmxxxJz);

    public String delete(YbjsmxxxJz ybjsmxxxJz);
}
