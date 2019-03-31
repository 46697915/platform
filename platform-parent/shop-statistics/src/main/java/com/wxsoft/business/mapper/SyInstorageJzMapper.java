package com.wxsoft.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wxsoft.business.entity.InstorageSummary;
import com.wxsoft.business.entity.SyInstorageJz;
import com.wxsoft.business.entity.SyInstorageJzVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 8入库单结转 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-03-11
 */
public interface SyInstorageJzMapper extends BaseMapper<SyInstorageJz> {

    public List selectBy(SyInstorageJzVo vo);

    List<InstorageSummary> instorageSummaryAll(@Param("instorage")SyInstorageJzVo instorage);

}
