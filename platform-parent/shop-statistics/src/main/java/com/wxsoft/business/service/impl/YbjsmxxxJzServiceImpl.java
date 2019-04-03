package com.wxsoft.business.service.impl;

import com.wxsoft.business.entity.YbjsmxxxJz;
import com.wxsoft.business.entity.YbjsmxxxJzVo;
import com.wxsoft.business.mapper.YbjsmxxxJzMapper;
import com.wxsoft.business.service.IYbjsmxxxJzService;
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
 * @since 2019-04-04
 */
@Service
public class YbjsmxxxJzServiceImpl extends ServiceImpl<YbjsmxxxJzMapper, YbjsmxxxJz> implements IYbjsmxxxJzService {

    @Autowired
    private YbjsmxxxJzMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(YbjsmxxxJzVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

    @Override
    public List ybSalesSummaryAll(YbjsmxxxJzVo vo) {
        return mapper.ybSalesSummaryAll(vo);
    }

}
