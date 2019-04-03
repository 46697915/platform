package com.wxsoft.business.service.impl;

import com.wxsoft.business.entity.StorecheckJz;
import com.wxsoft.business.entity.StorecheckJzVo;
import com.wxsoft.business.mapper.StorecheckJzMapper;
import com.wxsoft.business.service.IStorecheckJzService;
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
 * @since 2019-04-03
 */
@Service
public class StorecheckJzServiceImpl extends ServiceImpl<StorecheckJzMapper, StorecheckJz> implements IStorecheckJzService {

    @Autowired
    private StorecheckJzMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(StorecheckJzVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

}
