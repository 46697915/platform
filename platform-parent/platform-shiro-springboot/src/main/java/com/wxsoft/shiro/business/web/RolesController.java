package com.wxsoft.shiro.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.shiro.business.entity.RolesVo;
import com.wxsoft.shiro.business.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxsoftframwork.ui.core.vue.element.PageElementUI;
import org.wxsoftframwork.ui.core.vue.element.ResponseElementUI;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private IRolesService service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseElementUI list(RolesVo vo){
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
    public ResponseElementUI listForPage(PageElementUI pe, RolesVo vo){
        PageHelper.startPage(pe.getPageNO(),pe.getPageSize());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(pi);

        return rr;
    }

    @RequestMapping("/add")
    public ResponseElementUI add(RolesVo vo){
        boolean r = service.insert(vo);
        ResponseElementUI rr = ResponseElementUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseElementUI edit(RolesVo vo){
        boolean r = service.updateByIdMy(vo);
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
