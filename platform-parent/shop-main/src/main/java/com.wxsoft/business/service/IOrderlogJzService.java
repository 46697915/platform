package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderlogJz;

import java.util.List;

public interface IOrderlogJzService {

    public long findCount(OrderlogJz orderlogJz);

    public List<OrderlogJz> findAll(PageHelper page, OrderlogJz orderlogJz);

    public String add(OrderlogJz orderlogJz);

    public String modify(OrderlogJz orderlogJz);

    public String delete(OrderlogJz orderlogJz);
}
