package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.InstorageSummary;
import com.wxsoft.business.entity.SyInstorageJzVo;
import com.wxsoft.business.service.ISyInstorageJzService;
import com.wxsoft.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 8入库单结转 前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-03-11
 */
@RestController
@RequestMapping("/syInstorageJz")
public class SyInstorageJzController {

    @Autowired
    private ISyInstorageJzService service;


    /**
     * 入库查询页面 导出
     * @param request
     * @param page
     * @param instorage
     * @param response
     * @throws IOException
     */
    @RequestMapping("/exportInstorageExcel")
    public void exportInstorageExcel(HttpServletRequest request,PageHelper page, SyInstorageJzVo instorage,HttpServletResponse response) throws IOException{
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

        String title="rk";
        String[] headers = {"药品条码","药品名称","通用名","入库数量","入库类型","入库日期"
                ,"入库人","入库金额","生产日期","有效期","保质期"
                ,"生产批号","复核人","复合日期","备注"};
        List instorageList = service.selectBy(instorage);
        for(int j=1;j<instorageList.size()+1;j++){
            SyInstorageJzVo itg= (SyInstorageJzVo) instorageList.get(j-1);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("药品条码",itg.getBarcode());
            map.put("药品名称",itg.getDrugsname());
            map.put("通用名",itg.getCommonname());
            map.put("入库数量",StringUtil.toString(itg.getInquantity()));
            map.put("入库类型",itg.getIntypename());
            map.put("入库日期", DateUtil.format(itg.getIndate()));
            map.put("入库人",itg.getInperson());
            map.put("入库金额",StringUtil.toString(itg.getMoney()));
            map.put("生产日期",DateUtil.format(itg.getGeneratedate()));
            map.put("有效期",DateUtil.format(itg.getValidityperiod()));
            map.put("保质期",itg.getShelflife());
            map.put("生产批号",itg.getGeneratenum());
            map.put("复核人",itg.getReviewer());
            map.put("复合日期",DateUtil.format(itg.getReviewdate()));
            map.put("备注",itg.getRemark());
            listMap.add(map);
        }

        ExcelUtil.exportExcel(response, title, headers, listMap);

    }
    /**
     * 汇总页面导出
     * @param request
     * @param pe
     * @param instorage
     * @param response
     * @throws IOException
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request,PageEasyUI pe,
                            SyInstorageJzVo instorage,HttpServletResponse response) throws IOException{
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

//		String title="入库汇总"+instorage.getIndate_begin()+"至"+instorage.getIndate_end(); //乱码问题
        String title="RKHZ"+instorage.getIndate_begin()+"TO"+instorage.getIndate_end();
        String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};

//        PageHelper.startPage(pe.getPage(), pe.getRows());
        List<InstorageSummary> instoragedetailList = service.instorageSummaryAll(instorage);
        for(int j=0;j<instoragedetailList.size();j++){
            InstorageSummary o = instoragedetailList.get(j);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("通用名", o.getCommonname());
            map.put("数量", o.getTotal());
            map.put("总金额", o.getAmount());
            map.put("规格", o.getSpecifications());
            map.put("单位", o.getUnitsname());
            map.put("条形码", o.getBarcode());
            listMap.add(map);
        }
        ExcelUtil.exportExcel(response, title, headers, listMap);

    }
    @RequestMapping("/instorageSummaryData")
    public ResponseEasyUI instorageSummaryData(PageEasyUI pe, SyInstorageJzVo instorage) throws IOException {

        PageHelper.startPage(pe.getPage(), pe.getRows());
        List<InstorageSummary> r = service.instorageSummaryAll(instorage);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr ;
    }
    /**
     * 菜单列表分页
     *
     * @param
     * @return
     */
    @RequestMapping("/listForPage")
    public ResponseEasyUI listForPage(PageEasyUI pe, SyInstorageJzVo vo) {
        PageHelper.startPage(pe.getPage(), pe.getRows());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo<SyInstorageJzVo> pi = new PageInfo<SyInstorageJzVo>(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setTotal( pi.getTotal());
        rr.setRows(pi.getList());
        return rr;
    }

    @RequestMapping("/add")
    public ResponseResult add(SyInstorageJzVo vo) {
        boolean r = service.insert(vo);
        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr;
    }



    /**
     * 查询列表数据
     *
     * @param vo
     * @return
     */
    @RequestMapping("/list")
    public ResponseResult list(SyInstorageJzVo vo) {
        List r = service.selectBy(vo);
        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    @RequestMapping("/edit")
    public ResponseResult edit(SyInstorageJzVo vo) {
        boolean r = service.updateById(vo);
        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    @RequestMapping("delete")
    public ResponseResult delete(HttpServletRequest request, String id) {
        boolean r = service.deleteById(id);

        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr;
    }

    @RequestMapping("deleteBatch")
    public ResponseResult deleteBatch(HttpServletRequest request,
                                      String ids[]) {
        boolean r = service.deleteBatchIds(Arrays.asList(ids));

        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(r);
        return rr;
    }

}
