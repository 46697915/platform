package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.OrderdetailJz;

import java.util.List;

public interface IOrderdetailJzService {

    public long findCount(OrderdetailJz orderdetailJz);

    public List<OrderdetailJz> findAll(PageHelper page, OrderdetailJz orderdetailJz);

    public String add(OrderdetailJz orderdetailJz);

    public String modify(OrderdetailJz orderdetailJz);

    public String delete(OrderdetailJz orderdetailJz);
}
