package com.wxsoft.business.mapper;

import com.wxsoft.business.entity.StorecheckJz;
import com.wxsoft.business.entity.StorecheckJzVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-04-03
 */
public interface StorecheckJzMapper extends BaseMapper<StorecheckJz> {

    public List selectBy(StorecheckJzVo vo);
}
