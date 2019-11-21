package com.wxsoft.shiro.business.service;

import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.entity.UserVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cl
 * @since 2019-09-20
 */
public interface IUserService extends IService<User> {

    List selectBy(UserVo vo);
    User findByUsername(String username);

    List getMenuListByUser();

    boolean isSuperAdmin();
}
