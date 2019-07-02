package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.User;
import com.wxsoft.business.pojo.Ybjszxx;

import java.util.List;

public interface IYbjszxxService {

    public long findCount(Ybjszxx ybjszxx);

    public List<Ybjszxx> findAll(PageHelper page, Ybjszxx ybjszxx);

    public String add(Ybjszxx ybjszxx);

    public String modify(Ybjszxx ybjszxx);

    public String delete(Ybjszxx ybjszxx);

//    public String getYBJSXX(User user, Ybjszxx ybjszxx) throws Exception;

//    public String getYBJSXXBySJD(User user, Ybjszxx ybjszxx) throws Exception;

    public String updateStoreByDate(User user, Ybjszxx ybjszxx) throws Exception;
}
