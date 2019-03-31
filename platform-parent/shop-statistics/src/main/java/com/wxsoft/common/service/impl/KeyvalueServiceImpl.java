package com.wxsoft.common.service.impl;

import com.wxsoft.common.entity.Keyvalue;
import com.wxsoft.common.mapper.IKeyvalueDao;
import com.wxsoft.common.service.IKeyvalueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeyvalueServiceImpl implements IKeyvalueService {
    @Resource
    private IKeyvalueDao dao;

    /**
     * 根据类型返回字段选项
     */
    public List<Keyvalue> findByType(String type){
        List<Keyvalue> r = dao.findByType(type);
        return r ;
    }
}
