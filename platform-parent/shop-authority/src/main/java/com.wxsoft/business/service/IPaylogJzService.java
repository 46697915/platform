package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.PaylogJz;

import java.util.List;

public interface IPaylogJzService {

    public long findCount(PaylogJz paylogJz);

    public List<PaylogJz> findAll(PageHelper page, PaylogJz paylogJz);

    public String add(PaylogJz paylogJz);

    public String modify(PaylogJz paylogJz);

    public String delete(PaylogJz paylogJz);
}
