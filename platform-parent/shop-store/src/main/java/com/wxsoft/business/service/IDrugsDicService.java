package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugsDic;

import java.util.List;

public interface IDrugsDicService {

    public long findCount(DrugsDic drugsDic);

    public List<DrugsDic> findAll(PageHelper page, DrugsDic drugsDic);

    public String addOrModify(DrugsDic drugsDic);

    public String add(DrugsDic drugsDic);

    public String modify(DrugsDic drugsDic);

    public String delete(DrugsDic drugsDic);
}
