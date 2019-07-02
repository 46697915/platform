package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Instorage;
import com.wxsoft.business.pojo.InstorageSummary;

import java.util.List;

public interface IInstorageService {

    public long findCount(Instorage instorage);

    public List<Instorage> findAll(PageHelper page, Instorage instorage);

    public long instorageSummaryCount(Instorage instorage);

    public List<InstorageSummary> instorageSummaryAll(PageHelper page, Instorage instorage);

    public String add(Instorage instorage);

    public String modify(Instorage instorage);

    public String delete(Instorage instorage);
}
