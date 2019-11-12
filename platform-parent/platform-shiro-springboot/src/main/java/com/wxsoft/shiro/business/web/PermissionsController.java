package com.wxsoft.shiro.business.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxsoft.shiro.business.service.IPermissionsService;
import com.wxsoft.shiro.business.entity.PermissionsVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.wxsoftframwork.ui.core.vue.element.PageElementUI;
import org.wxsoftframwork.ui.core.vue.element.ResponseElementUI;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    @Autowired
    private IPermissionsService service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseElementUI list(PermissionsVo vo){
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
    public ResponseElementUI listForPage(PageElementUI pe, PermissionsVo vo){
        PageHelper.startPage(pe.getPageNO(),pe.getPageSize());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(pi);

        return rr;
    }

    @RequestMapping("/add")
    public ResponseElementUI add(PermissionsVo vo){
        boolean r = service.insert(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseElementUI edit(PermissionsVo vo){
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
