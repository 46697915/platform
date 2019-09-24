package com.wxsoft.shiro.business.mapper;

import com.wxsoft.shiro.business.entity.User;
import com.wxsoft.shiro.business.entity.UserVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cl
 * @since 2019-09-20
 */
public interface UserMapper extends BaseMapper<User> {

    List selectBy(UserVo vo);

    User findByUsername(@Param("username") String username);
}
