package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IYbjsmxxxJzDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.YbjsmxxxJz;
import com.wxsoft.business.service.IYbjsmxxxJzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("ybjsmxxxJzService")
public class YbjsmxxxJzServiceImpl implements IYbjsmxxxJzService  {
    @Resource
    private IYbjsmxxxJzDao dao;


    public long findCount(YbjsmxxxJz ybjsmxxxJz) {
        return dao.findCount(ybjsmxxxJz);
    }

    public List<YbjsmxxxJz> findAll(PageHelper page,YbjsmxxxJz ybjsmxxxJz) {
        List<YbjsmxxxJz> r = dao.findAll(page,ybjsmxxxJz);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(YbjsmxxxJz ybjsmxxxJz) {
        this.dao.insert(ybjsmxxxJz);
        return "操作成功!";
    }

    public String modify(YbjsmxxxJz ybjsmxxxJz) {
        this.dao.modify(ybjsmxxxJz);
        return "操作成功!";
    }

    public String delete(YbjsmxxxJz ybjsmxxxJz) {
        this.dao.delete(ybjsmxxxJz);
        return "操作成功!";
    }

}
