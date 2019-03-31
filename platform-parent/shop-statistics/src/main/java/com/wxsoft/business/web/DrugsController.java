package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.Drugs;
import com.wxsoft.business.entity.DrugsVo;
import com.wxsoft.business.service.IDrugsService;
import com.wxsoft.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 6药品 前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-03-13
 */
@Controller
@RequestMapping("/drugs")
public class DrugsController {

    @Autowired
    private IDrugsService service;

    /**
     * 根据一些条件查询药品表
     * @param  drugs
     * @param response
     * @throws IOException
     */
    @RequestMapping("/findBy")
    public void findBy(HttpServletRequest request, DrugsVo drugs, HttpServletResponse response) throws IOException{
        List<Drugs> keyvalueList;// = service.findBy(drugs);

//		drugs.setDrugStoreShortName(StoreUtil.getSSNForTable(request));
//		keyvalueList = service.findBy(drugs);

        //将数据放到缓存 或 取出
        Object cacheDrugs = CacheUtil.getCache("drugs_list");
        if(cacheDrugs==null || "".equals(cacheDrugs)){
            this.drugsToCathe(request, drugs);
        }
        keyvalueList = (List<Drugs>) cacheDrugs;

        JsonUtil.toResponseNOPage(response, keyvalueList);
    }
    /**
     * 将药品信息放入缓存
     * @Description:TODO
     * @参数： @param request
     * @参数： @param page
     * @参数： @param goods
     * @参数： @param response
     * @参数： @throws IOException
     * @return void
     * @throws
     * @author: chenliang
     * @time:2018-10-1 上午11:02:26
     */
    public void drugsToCathe(HttpServletRequest request,DrugsVo drugs) throws IOException {

        //将数据放到缓存
        List<Drugs> keyvalueList;// = service.findBy(drugs);

        keyvalueList = service.selectBy(drugs);

        CacheUtil.putCache("drugs_list", keyvalueList);
    }
    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseResult list(DrugsVo vo){
        List r = service.selectBy(vo);
        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    /**
    * 菜单列表分页
    * @param
    * @return
    */
    @RequestMapping("/listForPage")
    @ResponseBody
    public ResponseEasyUI listForPage(PageEasyUI pe, DrugsVo vo){
        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo<DrugsVo> pi = new PageInfo<DrugsVo>(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }

    @RequestMapping("/add")
    @ResponseBody
    public ResponseEasyUI add(DrugsVo vo){
        boolean r = service.insert(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseEasyUI edit(DrugsVo vo){
        boolean r = service.updateById(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResponseEasyUI delete(HttpServletRequest request, String id) {
        boolean r = service.deleteById(id);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }
    @RequestMapping("deleteBatch")
    @ResponseBody
    public ResponseEasyUI deleteBatch(HttpServletRequest request,
    String ids[]) {
        boolean r = service.deleteBatchIds(Arrays.asList(ids));

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

}
