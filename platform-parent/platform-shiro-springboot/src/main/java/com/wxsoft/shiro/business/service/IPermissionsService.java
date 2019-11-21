package com.wxsoft.shiro.business.service;

import com.wxsoft.shiro.business.entity.Permissions;
import com.wxsoft.shiro.business.entity.PermissionsVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cl
 * @since 2019-11-12
 */
public interface IPermissionsService extends IService<Permissions> {

    List selectBy(PermissionsVo vo);

    List selectUserMenu();
}
