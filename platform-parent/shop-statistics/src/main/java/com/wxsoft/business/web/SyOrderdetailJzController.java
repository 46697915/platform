package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.OrderdetailSummary;
import com.wxsoft.business.entity.SyOrderdetailJzVo;
import com.wxsoft.business.service.ISyOrderdetailJzService;
import com.wxsoft.utils.ExcelUtil;
import com.wxsoft.utils.PageEasyUI;
import com.wxsoft.utils.ResponseEasyUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/syOrderdetailJz")
public class SyOrderdetailJzController {

    @Autowired
    private ISyOrderdetailJzService service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseEasyUI list(SyOrderdetailJzVo vo){
        List r = service.selectBy(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    /**
    * 菜单列表分页
    * @param
    * @return
    */
    @RequestMapping("/listForPage")
    public ResponseEasyUI listForPage(PageEasyUI pe, SyOrderdetailJzVo vo){
        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }

    /**
     * 订单销售数据汇总返回数据
     * @param orderdetail
     * @param response
     */
    @RequestMapping("/salesSummaryData")
    public ResponseEasyUI salesSummaryData(PageEasyUI pe, SyOrderdetailJzVo orderdetail, HttpServletResponse response){
        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.salesSummaryAll(orderdetail);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }
    /**
     * 订单销售数据汇总导出
     * @param orderdetail
     * @param response
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(SyOrderdetailJzVo orderdetail,HttpServletResponse response){
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

        String title="销售汇总";
        String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
        List<OrderdetailSummary> orderdetailList = service.salesSummaryAll( orderdetail);
        for(int j=1;j<orderdetailList.size()+1;j++){
            OrderdetailSummary o=orderdetailList.get(j-1);
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

    /**
     * 订单销售数据汇总返回数据(医保和现金)
     * @param orderdetail
     * @param response
     */
    @RequestMapping("/salesAllSummaryData")
    public ResponseEasyUI salesAllSummaryData(PageEasyUI pe,  SyOrderdetailJzVo orderdetail,HttpServletResponse response){
        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.salesAllSummaryAll(orderdetail);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }
    /**
     * 订单销售数据汇总导出(医保和现金)
     * @param orderdetail
     * @param response
     */
    @RequestMapping("/exportAllExcel")
    public void exportAllExcel(SyOrderdetailJzVo orderdetail,HttpServletResponse response){
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

//		String title="所有销售汇总"+orderdetail.getCreatedate_begin()+"至"+orderdetail.getCreatedate_end();	//入库汇总
        String title="SYSSHZ"+orderdetail.getCreatedate_begin()+"TO"+orderdetail.getCreatedate_end();
        String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
        List<OrderdetailSummary> orderdetailList = service.salesAllSummaryAll(orderdetail);
        for(int j=1;j<orderdetailList.size()+1;j++){
            OrderdetailSummary o=orderdetailList.get(j-1);
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

    @RequestMapping("/add")
    public ResponseEasyUI add(SyOrderdetailJzVo vo){
        boolean r = service.insert(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseEasyUI edit(SyOrderdetailJzVo vo){
        boolean r = service.updateById(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }

    @RequestMapping("delete")
    public ResponseEasyUI delete(HttpServletRequest request, String id) {
        boolean r = service.deleteById(id);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }
    @RequestMapping("deleteBatch")
    public ResponseEasyUI deleteBatch(HttpServletRequest request,
    String ids[]) {
        boolean r = service.deleteBatchIds(Arrays.asList(ids));

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr;
    }

}
