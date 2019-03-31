package com.wxsoft.dao;

import com.wxsoft.entity.UserInfoPO;

public interface UserInfoPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoPO record);

    int insertSelective(UserInfoPO record);

    UserInfoPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoPO record);

    int updateByPrimaryKey(UserInfoPO record);
}