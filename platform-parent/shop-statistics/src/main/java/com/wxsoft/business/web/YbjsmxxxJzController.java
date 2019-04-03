package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.YbjsmxxxJzVo;
import com.wxsoft.business.entity.YbjsmxxxSummary;
import com.wxsoft.business.service.IYbjsmxxxJzService;
import com.wxsoft.utils.ExcelUtil;
import com.wxsoft.utils.PageEasyUI;
import com.wxsoft.utils.ResponseEasyUI;
import com.wxsoft.utils.StringUtil;
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
 *  前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
@RestController
@RequestMapping("/ybjsmxxxJz")
public class YbjsmxxxJzController {

    @Autowired
    private IYbjsmxxxJzService service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseEasyUI list(YbjsmxxxJzVo vo){
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
    public ResponseEasyUI listForPage(PageEasyUI pe, YbjsmxxxJzVo vo){
        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, YbjsmxxxJzVo ybjsmxxx, HttpServletResponse response) throws IOException {
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

        String title="ybjsmx";
        String[] headers = {"医保结算号","医院项目名称","项目单价","项目数量","项目金额","划价日期"
                ,"开单医生姓名","取药窗口/执行科室","处方号","处方内序号","医院项目编号"
                ,"对应医保项目编码","是否更新库存","项目规格","项目单位","项目剂型"
                ,"是否医保项目","每次用量","使用频次","用法","执行天数","住院号"};
        List<YbjsmxxxJzVo> ybjsmxxxlList = service.selectBy(ybjsmxxx);
        for(int j=1;j<ybjsmxxxlList.size()+1;j++){
            YbjsmxxxJzVo ybxxx = ybjsmxxxlList.get(j-1);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("医保结算号", ybxxx.getYbjsh());
            map.put("医院项目名称", ybxxx.getYyxmmc());
            map.put("项目单价", StringUtil.toString(ybxxx.getXmdj()));
            map.put("项目数量", StringUtil.toString(ybxxx.getXmsl()));
            map.put("项目金额", StringUtil.toString(ybxxx.getXmje()));
            map.put("划价日期", ybxxx.getHjrq());
            map.put("开单医生姓名", ybxxx.getKdysxm());
            map.put("取药窗口/执行科室", ybxxx.getZxks());
            map.put("处方号", ybxxx.getCfh());
            map.put("处方内序号", ybxxx.getCfnxh());
            map.put("医院项目编号", ybxxx.getYyxmbm());
            map.put("对应医保项目编码", ybxxx.getYbxmbm());
            map.put("是否更新库存", ybxxx.getIsupdate());
            map.put("项目规格", ybxxx.getXmgg());
            map.put("项目单位", ybxxx.getXmdw());
            map.put("项目剂型", ybxxx.getXmjx());
            map.put("是否医保项目", ybxxx.getSfybxm());
            map.put("每次用量", StringUtil.toString(ybxxx.getMcyl()));
            map.put("使用频次", StringUtil.toString(ybxxx.getSypc()));
            map.put("用法", ybxxx.getYf());
            map.put("执行天数", StringUtil.toString(ybxxx.getZxts()));
            map.put("住院号", ybxxx.getZyh());


            listMap.add(map);
        }
        ExcelUtil.exportExcel(response, title, headers, listMap);

    }
    /**
     * 医保订单销售数据汇总返回数据
     * @param response
     * @throws IOException
     */
    @RequestMapping("/ybSalesSummaryData")
    public ResponseEasyUI ybSalesSummaryData(PageEasyUI pe,YbjsmxxxJzVo vo,HttpServletResponse response) throws IOException{

        PageHelper.startPage(pe.getPage(),pe.getRows());
        List r = service.ybSalesSummaryAll(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }
    @RequestMapping("/exportYbSalesSummaryExcel")
    public void exportYbSalesSummaryExcel(HttpServletRequest request,YbjsmxxxJzVo ybjsmxxx,HttpServletResponse response) throws IOException{
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

        String title="ybxshz";
        String[] headers = {"通用名","数量","总金额","规格","单位","条形码"};
        List<YbjsmxxxSummary> ybjsmxxxList = service.ybSalesSummaryAll(ybjsmxxx);
        for(int j=1;j<ybjsmxxxList.size()+1;j++){
            YbjsmxxxSummary ybxxx = ybjsmxxxList.get(j-1);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

            map.put("通用名", ybxxx.getCommonname());
            map.put("数量", ybxxx.getTotal());
            map.put("总金额", ybxxx.getAmount());
            map.put("规格", ybxxx.getSpecifications());
            map.put("单位", ybxxx.getUnitsname());
            map.put("条形码", ybxxx.getBarcode2());
            listMap.add(map);
        }
        ExcelUtil.exportExcel(response, title, headers, listMap);

    }

    @RequestMapping("/add")
    public ResponseEasyUI add(YbjsmxxxJzVo vo){
        boolean r = service.insert(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseEasyUI edit(YbjsmxxxJzVo vo){
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
