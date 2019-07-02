package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goodstype;

import java.util.List;

public interface IGoodstypeService {

    public long findCount(Goodstype goodstype);

    public List<Goodstype> findAll(PageHelper page, Goodstype goodstype);

    public String add(Goodstype goodstype);

    public String modify(Goodstype goodstype);

    public String delete(int id);
}
