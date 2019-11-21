package com.wxsoft.shiro.business.common;

import com.wxsoft.shiro.business.entity.User;
import org.apache.shiro.SecurityUtils;

public class UserHelper {

    /**
     * 获取用户对象
     *
     * @return
     */
    public static User getUser() {
        Object o = SecurityUtils.getSubject().getPrincipal();
        System.out.println("用户："+o);
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static Integer getUserId() {
        return getUser().getId();
    }


}
