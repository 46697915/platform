package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderlog;

import java.util.List;

public interface IOrderlogService {

    public long findCount(Orderlog orderlog);

    public List<Orderlog> findAll(PageHelper page, Orderlog orderlog);

    public String add(Orderlog orderlog);

    public String modify(Orderlog orderlog);

    public String delete(Orderlog orderlog);
}
