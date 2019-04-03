package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.StorecheckJzVo;
import com.wxsoft.business.service.IStorecheckJzService;
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
 *  前端控制器
 * </p>
 *
 * @author cl
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/storecheckJz")
public class StorecheckJzController {

    @Autowired
    private IStorecheckJzService service;

    /**
    * 查询列表数据
    * @param vo
    * @return
    */
    @RequestMapping("/list")
    public ResponseEasyUI list(StorecheckJzVo vo){
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
    public ResponseEasyUI listForPage(PageEasyUI pe, StorecheckJzVo vo){
        PageHelper.startPage(pe.getPage(),pe.getRows(),"crrcheckdate desc");
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo pi = new PageInfo(r);

        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setRows(pi.getList());
        rr.setTotal(pi.getTotal());

        return rr;
    }
    @RequestMapping("/exportStoreCheckExcel")
    public void exportStoreCheckExcel(HttpServletRequest request, StorecheckJzVo storeCheck, HttpServletResponse response) throws IOException {
        List<LinkedHashMap<String, String>> listMap = new ArrayList<LinkedHashMap<String, String>>();

        String title="kcpd";
        String[] headers = {"药品条码","药品名称","通用名","期初库存","入库数量",
                "销售数量","最新库存","当前实际库存","本次盘点日期","上次盘点日期",
                "盘点人","盘点日期","是否最后一次盘点","上次盘点ID","医保对应关系",
                "药品内部代码"};
        List storeCheckList = service.selectBy(storeCheck);
        for(int j=1;j<storeCheckList.size()+1;j++){
            StorecheckJzVo sc = (StorecheckJzVo) storeCheckList.get(j-1);
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

            map.put("药品条码", sc.getBarcode());
            map.put("药品名称", sc.getDrugsname());
            map.put("通用名", sc.getCommonname());
            map.put("期初库存", StringUtil.toString(sc.getInitstore()));
            map.put("入库数量", StringUtil.toString(sc.getInstore()));
            map.put("销售数量", StringUtil.toString(sc.getSalecount()));
            map.put("最新库存", StringUtil.toString(sc.getNewstore()));
            map.put("当前实际库存", StringUtil.toString(sc.getCurrstore()));
            map.put("本次盘点日期", DateUtil.format(sc.getCrrcheckdate()));
            map.put("上次盘点日期", DateUtil.format(sc.getLastcheckdate()));
            map.put("盘点人", sc.getChecker());
            map.put("盘点日期", DateUtil.format(sc.getCheckdate()));
            map.put("是否最后一次盘点", sc.getIslastcheck());
            map.put("上次盘点ID", StringUtil.toString(sc.getLastcheckid()));
            map.put("医保对应关系", sc.getBxdygx());
            map.put("药品内部代码", sc.getDrugscode());
            listMap.add(map);
        }
        ExcelUtil.exportExcel(response, title, headers, listMap);

    }


    @RequestMapping("/add")
    public ResponseEasyUI add(StorecheckJzVo vo){
        boolean r = service.insert(vo);
        ResponseEasyUI rr = ResponseEasyUI.getResponseResult();
        rr.setResult(r);
        return rr ;
    }
    @RequestMapping("/edit")
    public ResponseEasyUI edit(StorecheckJzVo vo){
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
