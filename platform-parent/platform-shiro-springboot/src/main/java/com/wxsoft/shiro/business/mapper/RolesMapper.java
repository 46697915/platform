package com.wxsoft.shiro.business.mapper;

import com.wxsoft.shiro.business.entity.Roles;
import com.wxsoft.shiro.business.entity.RolesVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-11-05
 */
public interface RolesMapper extends BaseMapper<Roles> {

    public List selectBy(RolesVo vo);
}
