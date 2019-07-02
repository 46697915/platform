package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IYbjsmxxxDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Ybjsmxxx;
import com.wxsoft.business.pojo.YbjsmxxxSummary;
import com.wxsoft.business.service.IYbjsmxxxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("ybjsmxxxService")
public class YbjsmxxxServiceImpl implements IYbjsmxxxService  {
    @Resource
    private IYbjsmxxxDao dao;


    public long findCount(Ybjsmxxx ybjsmxxx) {
        return dao.findCount(ybjsmxxx);
    }

    public List<Ybjsmxxx> findAll(PageHelper page,Ybjsmxxx ybjsmxxx) {
        List<Ybjsmxxx> r = dao.findAll(page,ybjsmxxx);
        return r;
    }
    public long ybSalesSummaryCount(Ybjsmxxx ybjsmxxx){
        return dao.ybSalesSummaryCount(ybjsmxxx);
    }
    public List<YbjsmxxxSummary> ybSalesSummaryAll(PageHelper page,Ybjsmxxx ybjsmxxx){
        List<YbjsmxxxSummary> r = dao.ybSalesSummaryAll(page,ybjsmxxx);
        return r;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Ybjsmxxx ybjsmxxx) {
        this.dao.insert(ybjsmxxx);
        return "操作成功!";
    }

    public String modify(Ybjsmxxx ybjsmxxx) {
        this.dao.modify(ybjsmxxx);
        return "操作成功!";
    }

    public String delete(Ybjsmxxx ybjsmxxx) {
        this.dao.delete(ybjsmxxx);
        return "操作成功!";
    }

}
