package com.wxsoft.business.mapper;

import com.wxsoft.business.entity.Drugs;
import com.wxsoft.business.entity.DrugsVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
/**
 * <p>
 * 6药品 Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-03-13
 */
public interface DrugsMapper extends BaseMapper<Drugs> {

    public List selectBy(DrugsVo vo);
}
