package com.wxsoft.shiro.business.mapper;

import com.wxsoft.shiro.business.entity.Permissions;
import com.wxsoft.shiro.business.entity.PermissionsVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-11-12
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

    public List selectBy(PermissionsVo vo);

    List selectByParentId(long parentId);
}
