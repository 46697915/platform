package com.wxsoft.business.service;

import com.baomidou.mybatisplus.service.IService;
import com.wxsoft.business.entity.InstorageSummary;
import com.wxsoft.business.entity.SyInstorageJz;
import com.wxsoft.business.entity.SyInstorageJzVo;

import java.util.List;

/**
 * <p>
 * 8入库单结转 服务类
 * </p>
 *
 * @author cl
 * @since 2019-03-11
 */
public interface ISyInstorageJzService extends IService<SyInstorageJz> {

    public List selectBy(SyInstorageJzVo vo);
    public List<InstorageSummary> instorageSummaryAll(SyInstorageJzVo instorage);

}
