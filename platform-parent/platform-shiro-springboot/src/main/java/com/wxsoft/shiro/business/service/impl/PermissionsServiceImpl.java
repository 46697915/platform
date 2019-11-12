package com.wxsoft.shiro.business.service.impl;

import com.wxsoft.shiro.business.entity.Permissions;
import com.wxsoft.shiro.business.entity.PermissionsVo;
import com.wxsoft.shiro.business.mapper.PermissionsMapper;
import com.wxsoft.shiro.business.service.IPermissionsService;
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
 * @since 2019-11-12
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

    @Autowired
    private PermissionsMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(PermissionsVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

}
