package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IDrugsService {

    public long findCount(Drugs drugs);

    public List<Drugs> findAll(PageHelper page, Drugs drugs);
    public List<Drugs> findBy(Drugs drugs);

    public String add(Drugs drugs) throws IllegalAccessException, InvocationTargetException;

    public String modify(Drugs drugs) throws IllegalAccessException, InvocationTargetException;

    public String delete(Drugs drugs, HttpServletRequest request);
    public String stop(Drugs drugs, HttpServletRequest request);


    public String updateStore(Drugs drugs, HttpServletRequest request);
}
