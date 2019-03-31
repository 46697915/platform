package com.wxsoft.business.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxsoft.business.entity.InstorageSummary;
import com.wxsoft.business.entity.SyInstorageJzVo;
import com.wxsoft.business.service.ISyInstorageJzService;
import com.wxsoft.utils.ExcelUtil;
import com.wxsoft.utils.PageEasyUI;
import com.wxsoft.utils.ResponseEasyUI;
import com.wxsoft.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

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

    /**
     * 菜单列表分页
     *
     * @param
     * @return
     */
    @RequestMapping("/listForPage")
    public ResponseResult listForPage(PageEasyUI pe, SyInstorageJzVo vo) {
        PageHelper.startPage(pe.getPage(), pe.getRows());
        List r = service.selectBy(vo);

        //获取分页信息
        PageInfo<SyInstorageJzVo> pi = new PageInfo<SyInstorageJzVo>(r);

        Map<String, Object> rMap = new HashMap<String, Object>();
        rMap.put("total", pi.getTotal());
        rMap.put("list", pi.getList());

        ResponseResult rr = ResponseResult.getResponseResult();
        rr.setResult(rMap);
        return rr;
    }

    @RequestMapping("/add")
    public ResponseResult add(SyInstorageJzVo vo) {
        boolean r = service.insert(vo);
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
