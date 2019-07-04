package com.wxsoft.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 数据过滤，切面处理类
 * 注意-这个 数据处理方法 ，在sql语句中必须有‘filterSql’这个变量才可以，不可取
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年10月23日 下午13:33:35
 */
@Aspect
@Component
public class DataFilterAspect {
//    @Autowired
//    private SysRoleDeptService sysRoleDeptService;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.wxsoft.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    /**
     * 前置通知
     *
     * @param point 连接点
     */
//    @Before("dataFilterCut()")
//    public void dataFilter(JoinPoint point) {
//        //获取参数
//        Object params = point.getArgs()[0];
//        if (params != null && params instanceof Map) {
//            String storeShortName = StoreUtil.getStoreShortName();
//            User user = UserUtil.getUser();
//
//            //如果不是超级管理员，则只能查询本部门及子部门数据
//            if (user.getUsername() != Const.SUPER_ADMIN) {
//                Map map = (Map) params;
//                map.put("filterSql", getFilterSQL(user, point));
//            }
//
//            return;
//        }
//
//        throw new RRException("数据权限接口的参数必须为Map类型，且不能为NULL");
//    }

    /**
     * 获取数据过滤的SQL
     *
     * @param user  登录用户
     * @param point 连接点
     * @return sql
     */
//    private String getFilterSQL(SysUserEntity user, JoinPoint point) {
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
//
//        String userAlias = dataFilter.userAlias();
//        String deptAlias = dataFilter.deptAlias();
//
//        StringBuilder filterSql = new StringBuilder();
//        filterSql.append(" and ( ");
//        if (StringUtils.isNotEmpty(deptAlias) || StringUtils.isNotBlank(userAlias)) {
//            if (StringUtils.isNotEmpty(deptAlias)) {
//                //取出登录用户部门权限
////                String alias = getAliasByUser(user.getUserId());
//                //取出登录用户部门权限（不是通过角色中分配的数据权限）
//                String alias = String.valueOf(user.getDeptId());
//                filterSql.append(deptAlias);
//                filterSql.append(" in ");
//                filterSql.append(" ( ");
//                filterSql.append(alias);
//                filterSql.append(" ) ");
//                if (StringUtils.isNotBlank(userAlias)) {
//                    if (dataFilter.self()) {
//                        filterSql.append(" or ");
//
//                    } else {
//                        filterSql.append(" and ");
//                    }
//                }
//            }
//            if (StringUtils.isNotBlank(userAlias)) {
//                //没有部门数据权限，也能查询本人数据
//                filterSql.append(userAlias);
//                filterSql.append(" = ");
//                filterSql.append(user.getUserId());
//                filterSql.append(" ");
//            }
//        } else {
//            return "";
//        }
//        filterSql.append(" ) ");
//        return filterSql.toString();
//    }
//
//    /**
//     * 取出用户权限
//     *
//     * @param userId 登录用户Id
//     * @return 权限
//     */
//    private String getAliasByUser(Long userId) {
//        @SuppressWarnings("unchecked")
//        List<Long> roleOrglist = sysRoleDeptService.queryDeptIdListByUserId(userId);
//        StringBuilder roleStr = new StringBuilder();
//        String alias = "";
//        if (roleOrglist != null && !roleOrglist.isEmpty()) {
//            for (Long roleId : roleOrglist) {
//                roleStr.append(",");
//                roleStr.append("'");
//                roleStr.append(roleId);
//                roleStr.append("'");
//            }
//            alias = roleStr.toString().substring(1, roleStr.length());
//        }
//        return alias;
//    }
}
