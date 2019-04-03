package com.wxsoft.business.service;

import com.wxsoft.business.entity.YbjsmxxxJz;
import com.wxsoft.business.entity.YbjsmxxxJzVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
public interface IYbjsmxxxJzService extends IService<YbjsmxxxJz> {

    public List selectBy(YbjsmxxxJzVo vo);

    List ybSalesSummaryAll(YbjsmxxxJzVo vo);
}
