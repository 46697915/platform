package com.wxsoft.mybatisplugins;

import com.wxsoft.annotation.PermissionAop;
import com.wxsoft.util.PermissionConfig;
import com.wxsoft.util.PermissionUtils;
import com.wxsoft.util.ReflectUtil;
import org.apache.ibatis.executor.statement.*;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * mybatis数据权限拦截器 - prepare
 *
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
@Component
public class PrepareInterceptor implements Interceptor {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info("进入 PrepareInterceptor 拦截器...");
        }
        if (invocation.getTarget() instanceof BaseStatementHandler) {
            log.info("invocation.getTarget()是BaseStatementHandler");
        }else if(invocation.getTarget() instanceof CallableStatementHandler) {
            log.info("invocation.getTarget()是CallableStatementHandler");
        }else if(invocation.getTarget() instanceof PreparedStatementHandler) {
            log.info("invocation.getTarget()是PreparedStatementHandler");
        }else if(invocation.getTarget() instanceof RoutingStatementHandler) {
            log.info("invocation.getTarget()是RoutingStatementHandler");
        }else if(invocation.getTarget() instanceof SimpleStatementHandler) {
            log.info("invocation.getTarget()是SimpleStatementHandler");
        }else if(invocation.getTarget() instanceof StatementHandler) {
            log.info("invocation.getTarget()是StatementHandler");
        }
        if (invocation.getTarget() instanceof StatementHandler) {
//            RoutingStatementHandler handlerer2 = (RoutingStatementHandler) invocation.getTarget();
//            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            StatementHandler handler = (StatementHandler) invocation.getTarget();

//            handler = (StatementHandler) ReflectUtil.getFieldValue(handler, "target");
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");

            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //千万不能用下面注释的这个方法，会造成对象丢失，以致转换失败
            //MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            PermissionAop permissionAop = PermissionUtils.getPermissionByDelegate(mappedStatement);
            if (permissionAop == null) {
                if (log.isInfoEnabled()) {
                    log.info("数据权限放行...");
                }
                return invocation.proceed();
            }
            if (log.isInfoEnabled()) {
                log.info("数据权限处理【拼接SQL】...");
            }
            BoundSql boundSql = delegate.getBoundSql();
            ReflectUtil.setFieldValue(boundSql, "sql", permissionSql(boundSql.getSql(), permissionAop));
        }
        return invocation.proceed();
    }

    /**
     * 权限sql包装
     *
     * @author GaoYuan
     * @date 2018/4/17 上午9:51
     */
    protected String permissionSql(String sql, PermissionAop permissionAop) {
        StringBuilder sbSql = new StringBuilder(sql);
        String depMethodPath = PermissionConfig.getConfig("permission.client.user.depatment.method");
        //当前登录人所在部门
        String depCode = (String) ReflectUtil.reflectByPath(depMethodPath);
        String userMethodPath = PermissionConfig.getConfig("permission.client.userid.method");
        //当前登录人
        String username = (String) ReflectUtil.reflectByPath(userMethodPath);

        //设置 权限 条件
        //select * from (select id,name,region_cd from sys_exam ) where region_cd like '${}%'
        String methodPath = PermissionConfig.getConfig("permission.client.params." + permissionAop.storeAlias());
        String permissionCode = " ";
        //如果 methodPath 则权限条件 设空
        if(methodPath!=null && !"".equals(methodPath)){
            //后续可根据 前登录人所在部门 和 当前登录人 传入此方法获取对应条件
            permissionCode = (String) ReflectUtil.reflectByPath(methodPath);
        }

        int i = sbSql.lastIndexOf("where");
        if (i <= 0) {
            i = sbSql.lastIndexOf("WHERE");
        }
        String endSql = sbSql.substring(i + 5);
        String startSql = sbSql.substring(0, i + 5);
        sbSql = new StringBuilder(startSql)
                .append(" ")
                .append(permissionAop.storeAlias())
                .append(" like concat('" + permissionCode + "','%') ");
        //出现过 = < > like
//        if(endSql.matches(".*(=|<|>|like).*")){
        //Pattern.CASE_INSENSITIVE 启用不区分大小写的匹配。
        // Pattern.DOTALL 启用Dotall模式。在dotall模式下，表达式<tt>匹配任何字符，包括一个行终止符。默认情况下，此表达式不匹配 线路端接器。
        // 因为 endSql 中 有\n\t\t等字符
        Pattern pattern = Pattern.compile(".*(=|<|>|like).*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(endSql);
        if (matcher.matches()) {
            sbSql.append(" and ");
        }
        sbSql.append(endSql);
//        sbSql = new StringBuilder("select * from (").append(sbSql).append(" ) s where s.storecode like concat(" + regionCd + ",'%')  ");

        return sbSql.toString();
    }

    public static void main(String[] args) {
        String endSql = " 1=1\n" +
                "\t\t \n" +
                "\t\t \n" +
                "\t\t \n" +
                "\t\t \n" +
                "\t\t \n" +
                "\t\t\tlimit ?,?";
        Pattern pattern = Pattern.compile(".*(=|<|>|like).*", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(endSql);
        if (matcher.matches()) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        if (endSql.matches(".*(=|<|>|like).*")) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
    }
}