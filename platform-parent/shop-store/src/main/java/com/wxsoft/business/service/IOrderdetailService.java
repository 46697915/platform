package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Orderdetail;
import com.wxsoft.business.pojo.OrderdetailSummary;

import java.util.List;

public interface IOrderdetailService {

    public long findCount(Orderdetail orderdetail);

    public List<Orderdetail> findAll(PageHelper page, Orderdetail orderdetail);

    public long salesSummaryCount(Orderdetail orderdetail);

    public List<OrderdetailSummary> salesSummaryAll(PageHelper page, Orderdetail orderdetail);

    public long salesAllSummaryCount(Orderdetail orderdetail);

    public List<OrderdetailSummary> salesAllSummaryAll(PageHelper page, Orderdetail orderdetail);

    public String add(Orderdetail orderdetail);

    public String modify(Orderdetail orderdetail);

    public String delete(Orderdetail orderdetail);

    public long findMoneyCount(Orderdetail orderdetail);
}
