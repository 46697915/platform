package com.wxsoft.shiro.business.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxsoft.shiro.business.common.UserHelper;
import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.entity.UserVo;
import com.wxsoft.shiro.business.mapper.UserMapper;
import com.wxsoft.shiro.business.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cl
 * @since 2019-09-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(UserVo vo){
        List r = baseMapper.selectBy(vo);

        return r ;
    }
    public User findByUsername(String username){
        User u= baseMapper.findByUsername(username);
        return u ;
    }

    /**
     * 根据登录的用户，获取有权限的菜单id
     * @return
     */
    @Override
    public List getMenuListByUser() {
        User user = UserHelper.getUser();
        List r = baseMapper.queryAllMenuIdByUserId(user.getId());

        return r ;
    }

    /**
     * 判断是否是超级管理员；后期增加
     * @return
     */
    @Override
    public boolean isSuperAdmin() {
        User user = UserHelper.getUser();
        return true;
    }

}
