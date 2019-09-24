package com.wxsoft.shiro.business.service.impl;

import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.entity.UserVo;
import com.wxsoft.shiro.business.mapper.UserMapper;
import com.wxsoft.shiro.business.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private UserMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(UserVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }
    public User findByUsername(String username){
        User u= mapper.findByUsername(username);
        return u ;
    }

}
