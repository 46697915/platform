package com.wxsoft.common.service;

import com.wxsoft.common.entity.Keyvalue;

import java.util.List;

public interface IKeyvalueService {

    public List<Keyvalue> findByType(String type);

}
