package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Paylog;

import java.util.List;

public interface IPaylogService {

    public long findCount(Paylog paylog);

    public List<Paylog> findAll(PageHelper page, Paylog paylog);

    public String add(Paylog paylog);

    public String modify(Paylog paylog);

    public String delete(Paylog paylog);
}
