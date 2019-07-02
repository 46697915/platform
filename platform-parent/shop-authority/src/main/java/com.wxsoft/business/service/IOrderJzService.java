package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderJz;

import java.util.List;

public interface IOrderJzService {

    public long findCount(OrderJz orderJz);

    public List<OrderJz> findAll(PageHelper page, OrderJz orderJz);

    public String add(OrderJz orderJz);

    public String modify(OrderJz orderJz);

    public String delete(OrderJz orderJz);
}
