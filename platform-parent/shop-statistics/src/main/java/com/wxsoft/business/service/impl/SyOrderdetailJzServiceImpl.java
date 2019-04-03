package com.wxsoft.business.service.impl;

import com.wxsoft.business.entity.SyOrderdetailJz;
import com.wxsoft.business.entity.SyOrderdetailJzVo;
import com.wxsoft.business.mapper.SyOrderdetailJzMapper;
import com.wxsoft.business.service.ISyOrderdetailJzService;
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
public class SyOrderdetailJzServiceImpl extends ServiceImpl<SyOrderdetailJzMapper, SyOrderdetailJz> implements ISyOrderdetailJzService {

    @Autowired
    private SyOrderdetailJzMapper mapper;

    /**
    *
    * @param vo
    * @return
    */
    public List selectBy(SyOrderdetailJzVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

    @Override
    public List salesSummaryAll(SyOrderdetailJzVo orderdetail) {
        return mapper.salesSummaryAll(orderdetail);
    }

    @Override
    public List salesAllSummaryAll(SyOrderdetailJzVo orderdetail) {
        return mapper.salesAllSummaryAll(orderdetail);
    }

}
