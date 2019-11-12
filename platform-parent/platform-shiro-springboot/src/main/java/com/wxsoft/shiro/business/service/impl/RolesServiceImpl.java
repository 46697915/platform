package com.wxsoft.shiro.business.service.impl;

import com.wxsoft.shiro.business.entity.Roles;
import com.wxsoft.shiro.business.entity.RolesVo;
import com.wxsoft.shiro.business.mapper.RolesMapper;
import com.wxsoft.shiro.business.service.IRolesService;
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
 * @since 2019-11-05
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements IRolesService {

    @Autowired
    private RolesMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(RolesVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }
    public boolean updateByIdMy(Roles vo){
        int r = mapper.updateById(vo);

        if(r>0){
            return true;
        }

        return false ;
    }

}
