package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugsDel;

import java.util.List;

public interface IDrugsDelService {

    public long findCount(DrugsDel drugsDel);

    public List<DrugsDel> findAll(PageHelper page, DrugsDel drugsDel);

    public String add(DrugsDel drugsDel);

    public String modify(DrugsDel drugsDel);

    public String delete(DrugsDel drugsDel);
}
