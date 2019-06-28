package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IYdybxxDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ydybxx;
import com.wxsoft.business.service.IYdybxxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("ydybxxService")
public class YdybxxServiceImpl implements IYdybxxService  {
    @Resource
    private IYdybxxDao dao;


    public long findCount(Ydybxx ydybxx) {
        return dao.findCount(ydybxx);
    }

    public List<Ydybxx> findAll(PageHelper page,Ydybxx ydybxx) {
        List<Ydybxx> r = dao.findAll(page,ydybxx);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Ydybxx ydybxx) {
        this.dao.insert(ydybxx);
        return "操作成功!";
    }

    public String modify(Ydybxx ydybxx) {
        this.dao.modify(ydybxx);
        return "操作成功!";
    }

    public String delete(int id) {
        this.dao.delete(id);
        return "操作成功!";
    }

}
