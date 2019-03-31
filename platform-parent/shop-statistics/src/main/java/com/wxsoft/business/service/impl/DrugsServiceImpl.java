package com.wxsoft.business.service.impl;

import com.wxsoft.business.entity.Drugs;
import com.wxsoft.business.entity.DrugsVo;
import com.wxsoft.business.mapper.DrugsMapper;
import com.wxsoft.business.service.IDrugsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * <p>
 * 6药品 服务实现类
 * </p>
 *
 * @author cl
 * @since 2019-03-13
 */
@Service
public class DrugsServiceImpl extends ServiceImpl<DrugsMapper, Drugs> implements IDrugsService {

    @Autowired
    private DrugsMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(DrugsVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

}
