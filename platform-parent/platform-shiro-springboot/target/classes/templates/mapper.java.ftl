package ${package.Mapper};

import ${package.Entity}.${entity};
import ${package.Entity}.${entity}Vo;
import ${superMapperClassPackage};

import java.util.List;
/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

    public List selectBy(${entity}Vo vo);
}
</#if>
