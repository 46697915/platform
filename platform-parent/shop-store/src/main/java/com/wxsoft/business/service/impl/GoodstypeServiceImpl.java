package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IGoodstypeDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goodstype;
import com.wxsoft.business.service.IGoodstypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("goodstypeService")
public class GoodstypeServiceImpl implements IGoodstypeService  {
    @Resource
    private IGoodstypeDao dao;


    public long findCount(Goodstype goodstype) {
        return dao.findCount(goodstype);
    }

    public List<Goodstype> findAll(PageHelper page,Goodstype goodstype) {
        List<Goodstype> r = dao.findAll(page,goodstype);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Goodstype goodstype) {
        this.dao.insert(goodstype);
        return "操作成功!";
    }

    public String modify(Goodstype goodstype) {
        this.dao.modify(goodstype);
        return "操作成功!";
    }

    public String delete(int id) {
        this.dao.delete(id);
        return "操作成功!";
    }

}
