package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ydybxx;

import java.util.List;

public interface IYdybxxService {

    public long findCount(Ydybxx ydybxx);

    public List<Ydybxx> findAll(PageHelper page, Ydybxx ydybxx);

    public String add(Ydybxx ydybxx);

    public String modify(Ydybxx ydybxx);

    public String delete(int id);
}
