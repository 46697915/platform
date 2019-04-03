package com.wxsoft.business.service;

import com.wxsoft.business.entity.SyOrderdetailJz;
import com.wxsoft.business.entity.SyOrderdetailJzVo;
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
public interface ISyOrderdetailJzService extends IService<SyOrderdetailJz> {

    public List selectBy(SyOrderdetailJzVo vo);

    List salesSummaryAll(SyOrderdetailJzVo orderdetail);

    List salesAllSummaryAll(SyOrderdetailJzVo orderdetail);
}
