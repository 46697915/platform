package ${package.Service};

import ${package.Entity}.${entity};
import ${package.Entity}.${entity}Vo;
import ${superServiceClassPackage};

import java.util.List;
/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    public List selectBy(${entity}Vo vo);
}
</#if>
