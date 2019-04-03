package com.wxsoft.business.mapper;

import com.wxsoft.business.entity.SyOrderdetailJz;
import com.wxsoft.business.entity.SyOrderdetailJzVo;
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
public interface SyOrderdetailJzMapper extends BaseMapper<SyOrderdetailJz> {

    public List selectBy(SyOrderdetailJzVo vo);

    List salesSummaryAll(SyOrderdetailJzVo orderdetail);

    List salesAllSummaryAll(SyOrderdetailJzVo orderdetail);
}
