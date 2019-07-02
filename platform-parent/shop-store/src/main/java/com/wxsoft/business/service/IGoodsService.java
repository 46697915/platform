package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;

import java.util.List;

public interface IGoodsService {

    public long findCount(Goods goods);
    public List<Goods> findAll(PageHelper page, Goods goods);

    public long findCountForXS(Goods goods);
    public List<Goods> findAllForXS(PageHelper page, Goods goods);

    public List<Goods> findBy(Goods goods);

    public String add(Goods goods);

    public String modify(Goods goods);

    public String delete(Goods goods);
}
