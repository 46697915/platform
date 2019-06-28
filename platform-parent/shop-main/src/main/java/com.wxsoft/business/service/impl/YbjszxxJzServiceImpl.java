package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IYbjszxxJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.YbjszxxJz;
import com.wxsoft.business.service.IYbjszxxJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("ybjszxxJzService")
public class YbjszxxJzServiceImpl implements IYbjszxxJzService  {
    @Resource
    private IYbjszxxJzDao dao;


    public long findCount(YbjszxxJz ybjszxxJz) {
        return dao.findCount(ybjszxxJz);
    }

    public List<YbjszxxJz> findAll(PageHelper page,YbjszxxJz ybjszxxJz) {
        List<YbjszxxJz> r = dao.findAll(page,ybjszxxJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(YbjszxxJz ybjszxxJz) {
        this.dao.insert(ybjszxxJz);
        return "操作成功!";
    }

    public String modify(YbjszxxJz ybjszxxJz) {
        this.dao.modify(ybjszxxJz);
        return "操作成功!";
    }

    public String delete(YbjszxxJz ybjszxxJz) {
        this.dao.delete(ybjszxxJz);
        return "操作成功!";
    }

}
