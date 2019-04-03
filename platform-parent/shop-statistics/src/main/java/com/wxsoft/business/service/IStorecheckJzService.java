package com.wxsoft.business.service;

import com.wxsoft.business.entity.StorecheckJz;
import com.wxsoft.business.entity.StorecheckJzVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cl
 * @since 2019-04-03
 */
public interface IStorecheckJzService extends IService<StorecheckJz> {

    public List selectBy(StorecheckJzVo vo);
}
