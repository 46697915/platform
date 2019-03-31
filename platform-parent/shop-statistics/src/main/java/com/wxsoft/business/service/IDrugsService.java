package com.wxsoft.business.service;

import com.wxsoft.business.entity.Drugs;
import com.wxsoft.business.entity.DrugsVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 * 6药品 服务类
 * </p>
 *
 * @author cl
 * @since 2019-03-13
 */
public interface IDrugsService extends IService<Drugs> {

    public List selectBy(DrugsVo vo);
}
