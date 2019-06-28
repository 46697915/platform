package com.wxsoft.business.service;

import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Transfer;

import java.util.List;

public interface ITransferService {

    public long findCount(Transfer transfer);

    public List<Transfer> findAll(PageHelper page, Transfer transfer);

    public String batchAddForDel(Transfer transfer);
    public String batchAdd(Transfer transfer);
    public String add(Transfer transfer);

    public String modify(Transfer transfer);

    public String delete(Transfer transfer);
}
