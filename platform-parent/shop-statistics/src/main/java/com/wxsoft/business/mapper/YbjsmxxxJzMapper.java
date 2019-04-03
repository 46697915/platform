package com.wxsoft.business.mapper;

import com.wxsoft.business.entity.YbjsmxxxJz;
import com.wxsoft.business.entity.YbjsmxxxJzVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
public interface YbjsmxxxJzMapper extends BaseMapper<YbjsmxxxJz> {

    public List selectBy(YbjsmxxxJzVo vo);

    List ybSalesSummaryAll(YbjsmxxxJzVo vo);
}
