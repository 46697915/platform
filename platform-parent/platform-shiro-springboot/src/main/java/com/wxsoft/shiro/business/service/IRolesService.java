package com.wxsoft.shiro.business.service;

import com.wxsoft.shiro.business.entity.Roles;
import com.wxsoft.shiro.business.entity.RolesVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cl
 * @since 2019-11-05
 */
public interface IRolesService extends IService<Roles> {

    List selectBy(RolesVo vo);

    boolean updateByIdMy(Roles vo);
}
