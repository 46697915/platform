package com.wxsoft.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤
 * @author GaoYuan
 * @date 2018/4/17 下午2:40
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAop {

    /**
     * sql中数据创建用户（通常传入CREATE_USER_ID）的别名
     */
    String userAlias() default "";

    /**
     * sql中数据storecode的别名
     */
    String storeAlias() default "";

    /**
     * true：没有部门数据权限，也能查询本人数据
     */
    boolean self() default true;
}