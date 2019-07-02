package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ybjsmxxx;
import com.wxsoft.business.pojo.YbjsmxxxSummary;

import java.util.List;

public interface IYbjsmxxxService {

    public long findCount(Ybjsmxxx ybjsmxxx);

    public List<Ybjsmxxx> findAll(PageHelper page, Ybjsmxxx ybjsmxxx);

    public long ybSalesSummaryCount(Ybjsmxxx ybjsmxxx);

    public List<YbjsmxxxSummary> ybSalesSummaryAll(PageHelper page, Ybjsmxxx ybjsmxxx);

    public String add(Ybjsmxxx ybjsmxxx);

    public String modify(Ybjsmxxx ybjsmxxx);

    public String delete(Ybjsmxxx ybjsmxxx);
}
