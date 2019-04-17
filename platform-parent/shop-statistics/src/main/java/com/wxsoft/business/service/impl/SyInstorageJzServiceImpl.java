package com.wxsoft.business.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wxsoft.business.entity.InstorageSummary;
import com.wxsoft.business.entity.SyInstorageJz;
import com.wxsoft.business.entity.SyInstorageJzVo;
import com.wxsoft.business.mapper.SyInstorageJzMapper;
import com.wxsoft.business.service.ISyInstorageJzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 8入库单结转 服务实现类
 * </p>
 *
 * @author cl
 * @since 2019-03-11
 */
@Service
public class SyInstorageJzServiceImpl extends ServiceImpl<SyInstorageJzMapper, SyInstorageJz> implements ISyInstorageJzService {

    @Autowired
    private SyInstorageJzMapper mapper;


    /**
     * 入库汇总 信息
     */
    public List<InstorageSummary> instorageSummaryAll(SyInstorageJzVo instorage) {
        List<InstorageSummary> r = mapper.instorageSummaryAll(instorage);
        return r;
    }



    /**
    *
    * @param vo
    * @return
    */
    @Transactional
    public List selectBy(SyInstorageJzVo vo){
        List r = mapper.selectBy(vo);

        return r ;
    }

}
