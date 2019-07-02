package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IGoodsDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Goods;
import com.wxsoft.business.service.IGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService  {
    @Resource
    private IGoodsDao dao;


    public long findCount(Goods goods) {
        return dao.findCount(goods);
    }

    public List<Goods> findAll(PageHelper page,Goods goods) {
        List<Goods> r = dao.findAll(page,goods);
        return r;
    }

    public long findCountForXS(Goods goods) {
        return dao.findCountForXS(goods);
    }

    public List<Goods> findAllForXS(PageHelper page,Goods goods) {
        List<Goods> r = dao.findAllForXS(page,goods);
        return r;
    }

    public List<Goods> findBy(Goods goods) {
        List<Goods> r = dao.findBy(goods);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Goods goods) {
        this.dao.insert(goods);
        return "操作成功!";
    }

    public String modify(Goods goods) {
        this.dao.modify(goods);
        return "操作成功!";
    }

    public String delete(Goods goods) {
        this.dao.delete(goods);
        return "操作成功!";
    }

    public Goods findById(Goods goods) {
        return this.dao.findById(goods);
    }

}
