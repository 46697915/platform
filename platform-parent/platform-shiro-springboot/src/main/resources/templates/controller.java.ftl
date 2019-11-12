package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity}Vo;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.wxsoftframwork.ui.core.vue.element.PageElementUI;
import org.wxsoftframwork.ui.core.vue.element.ResponseElementUI;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseElementUI list(${entity}Vo vo){
        List r = service.selectBy(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    /**
    * 菜单列表分页
    * @param
    * @return
    */
    @RequestMapping("/listForPage")
    public ResponseElementUI listForPage(PageElementUI pe, ${entity}Vo vo){
        PageHelper.startPage(pe.getPageNO(),pe.getPageSize());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(pi);

        return rr;
    }

    @RequestMapping("/add")
    public ResponseElementUI add(${entity}Vo vo){
        boolean r = service.insert(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseElementUI edit(${entity}Vo vo){
        boolean r = service.updateById(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }

    @RequestMapping("delete")
    public ResponseElementUI delete(HttpServletRequest request, String id) {
        boolean r = service.deleteById(id);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }
    @RequestMapping("deleteBatch")
    public ResponseElementUI deleteBatch(HttpServletRequest request,
    String ids[]) {
        boolean r = service.deleteBatchIds(Arrays.asList(ids));

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

}
</#if>
</#if>
