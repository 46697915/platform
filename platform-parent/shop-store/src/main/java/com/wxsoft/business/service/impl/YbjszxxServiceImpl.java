package com.wxsoft.business.service.impl;

//import com.wxsoft.axis2.client.ToolsInXml;
//import com.wxsoft.axis2.client.YBJKclient;
import com.wxsoft.business.dao.*;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IYbjszxxService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

//import org.jdom.JDOMException;

@Service("ybjszxxService")
public class YbjszxxServiceImpl implements IYbjszxxService {
    private Logger logger = Logger.getLogger(this.getClass());
    @Resource
    private IYbjszxxDao dao;

    @Autowired
    private IYdybxxDao ydybxxDao;
    @Autowired
    private IYbjszxxDao ybjszxxDao;
    @Autowired
    private IYbjsmxxxDao ybjsmxxxDao;
    @Autowired
    private IGoodsDao goodsDao;
    @Autowired
    private IDrugsDao drugsDao;

    public long findCount(Ybjszxx ybjszxx) {
        return dao.findCount(ybjszxx);
    }

    public List<Ybjszxx> findAll(PageHelper page, Ybjszxx ybjszxx) {
        List<Ybjszxx> r = dao.findAll(page, ybjszxx);
        return r;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(Ybjszxx ybjszxx) {
        this.dao.insert(ybjszxx);
        return "操作成功!";
    }

    public String modify(Ybjszxx ybjszxx) {
        this.dao.modify(ybjszxx);
        return "操作成功!";
    }

    public String delete(Ybjszxx ybjszxx) {
        this.dao.delete(ybjszxx);
        return "操作成功!";
    }

    /**
     * 更新库存 通过日期
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String updateStoreByDate(User user, Ybjszxx ybjszxx)
            throws Exception {
        Ybjsmxxx mx = new Ybjsmxxx();
        mx.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
        mx.setHjrq_begin(ybjszxx.getYbjsrq_search());
        mx.setHjrq_end(ybjszxx.getYbjsrq_search());
        mx.setIsupdate("0");
        long mxCon = ybjsmxxxDao.findCount(mx);
        int mxConInt = Integer.valueOf(String.valueOf(mxCon));
        int rows = 100;
        int pages = mxConInt/rows+1 ;
        PageHelper page = new PageHelper();
        //查询出未更新的医保数据
        for(int i=0; i<pages; i++){
            page.setPage(i);
            page.setRows(rows);
            page.setStart(i*rows);
            page.setEnd((i+1)*rows);
            List noUpdateL = ybjsmxxxDao.findAll(page, mx);
            StringBuffer sb = new StringBuffer();
            for(int j=0; j<noUpdateL.size(); j++){
                Ybjsmxxx ybmx = (Ybjsmxxx) noUpdateL.get(j);
                ybmx.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
                //检查是否存在库存
                String noStoreDrugs = checkHasStore(ybmx);
                if(!"".equals(noStoreDrugs)){
                    sb.append(noStoreDrugs);
                }
            }
            if(sb!=null && !"".equals(sb.toString())){
                throw new RuntimeException(sb.toString());
            }
            for(int j=0; j<noUpdateL.size(); j++){
                Ybjsmxxx ybmx = (Ybjsmxxx) noUpdateL.get(j);
                ybmx.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
                //更新库存
                subtractInventory(ybmx);
            }
        }

        return "更新成功";
    }
    /**
     * 更新库存
     * @param ybmx
     * @return
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String subtractInventory(Ybjsmxxx ybmx){

        //获取条形码
        Drugs drugs = new Drugs();
        drugs.setDrugscode(ybmx.getYyxmbm());
        drugs.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        List dL = drugsDao.findBy(drugs);
        if(dL==null||dL.size()<=0){
            throw new RuntimeException("未找到【药品编码："+ybmx.getYyxmbm()+";药品名称："+ybmx.getYyxmmc()+";医保对应关系："+ybmx.getYbxmbm()+"】的药品，请增加药品，并做入库操作。");
        }
        Drugs d = (Drugs) dL.get(0);

        //更新医保明细为 已更新
        Ybjsmxxx newYbmx = new Ybjsmxxx();
        newYbmx.setOldIsupdate("0");
        newYbmx.setIsupdate("1");
        newYbmx.setBarcode(d.getBarcode());
        newYbmx.setId(ybmx.getId());
        newYbmx.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        int r = ybjsmxxxDao.modify(newYbmx);
        if(r<1){//如果已经更新，就不执行更新库存操作
            return "";
        }
        String type1 = d.getType1();	//药品类型：01诊疗类；02药品类
        if("01".equals(type1)){	//如果是 诊疗类 不需要检查或更新库存
            return "";
        }

        //获取当前库存
        Goods gs = new Goods();
        gs.setBarcode(d.getBarcode());
//		gs.setBarcode(d.getBarcode());
        gs.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        List gL = goodsDao.findBy(gs);
        if(gL==null||gL.size()<=0){
            throw new RuntimeException("库存中没有【药品编码："+d.getDrugscode()+";药品名称："+d.getDrugsname()+";医保对应关系："+ybmx.getYbxmbm()+"】的药品，请做入库操作。");
        }

        //更新库存
        Goods oldGoods = (Goods) gL.get(0);
        String stockStr = oldGoods.getStock();
        if(stockStr==null || "".equals(stockStr)){
            stockStr = "0";
        }
        double stock = Double.parseDouble(stockStr);
        double count = Double.parseDouble(ybmx.getXmsl());
        String newStock = String.valueOf(stock-count);

        Goods newGs = new Goods();
        newGs.setBarcode(d.getBarcode());
        newGs.setStock(newStock);
        newGs.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        goodsDao.modifyByBarcode(newGs);

        return "";
    }
    /**
     * 校验是否存在库存
     * @param ybmx
     * @return
     */
    public String checkHasStore(Ybjsmxxx ybmx){

        StringBuffer sb = new StringBuffer();

        //获取条形码
        Drugs drugs = new Drugs();
        drugs.setDrugscode(ybmx.getYyxmbm());
        drugs.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        List dL = drugsDao.findBy(drugs);
        if(dL==null||dL.size()<=0){
            sb.append("未找到【药品编码："+ybmx.getYyxmbm()+";药品名称："+ybmx.getYyxmmc()+";医保对应关系："+ybmx.getYbxmbm()+"】的药品，请增加药品，并做入库操作。");
        }
        if(!"".equals(sb.toString())){
            return sb.toString();
        }
        Drugs d = (Drugs) dL.get(0);
        String type1 = d.getType1();	//药品类型：01诊疗类；02药品类
        if("01".equals(type1)){	//如果是 诊疗类 不需要检查或更新库存
            return "";
        }

        //获取当前库存
        Goods gs = new Goods();
        gs.setBarcode(d.getBarcode());
        gs.setDrugStoreShortName(ybmx.getDrugStoreShortName());
        List gL = goodsDao.findBy(gs);
        if(gL==null||gL.size()<=0){
            sb.append("库存中没有【药品编码："+d.getDrugscode()+";药品名称："+d.getDrugsname()+";医保对应关系："+ybmx.getYbxmbm()+"】的药品，请做入库操作。");
        }

        return sb.toString();
    }

    /**
     * 根据时间段更新医保信息
     * @param user
     * @param ybjszxx
     * @return
     * @throws Exception
     */
//    public String getYBJSXXBySJD(User user, Ybjszxx ybjszxx) throws Exception{
//        String r = "没有更新数据" ;
//        String ybjsrqB = ybjszxx.getYbjsrq_begin_search();
//        String ybjsrqE = ybjszxx.getYbjsrq_end_search();
//        int result=ybjsrqB.compareTo(ybjsrqE);
//        while(result<=0){
//            ybjszxx.setYbjsrq_search(ybjsrqB);
//            r = getYBJSXX(user, ybjszxx);
//            ybjsrqB = DateUtil.getNextDay(ybjsrqB);
//            result=ybjsrqB.compareTo(ybjsrqE);
//        }
//        return r ;
//    }
//    /**
//     * 获取医保结算信息
//     * @throws IOException
//     * @throws JDOMException
//     */
//    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
//    public String getYBJSXX(User user, Ybjszxx ybjszxx) throws Exception {
//        // 获取药店医保信息
//        Ydybxx ydybxx = new Ydybxx();
//        ydybxx.setShortname(user.getPharmacy());
////		ydybxx.setShortname("SY");
//        if(user.getPharmacy()==null || "".equals(user.getPharmacy())){
//            return "当前用户不能获取:药店的医保信息！";
//        }
//        List<Ydybxx> ydybxxL = ydybxxDao.findBy(ydybxx);
//        if (ydybxxL != null && ydybxxL.size() > 0) {
//            ydybxx = ydybxxL.get(0);
//        } else {
//            return "当前用户不能获取:药店的医保信息！";
//        }
//        // 同步医保结算主信息
//        String ybjszxxXML = YBJKclient.getYBJSZXX(ydybxx.getYbjkyhm(), ydybxx
//                .getYbjkmm(), "", ydybxx.getYdbm(), ybjszxx.getYbjsrq_search(), "1", "100");
//        Map m = ToolsInXml.getMap(ybjszxxXML,Ybjszxx.class);
//        logger.debug("医保返回的map对象："+m);
//        // 同步医保明细信息
//        List l = (List) m.get("ReturnValue");
//        if(l!=null && l.size()>0){
//            for(int i=0; i<l.size(); i++){
//                Ybjszxx r = (Ybjszxx) l.get(i);
//                String ybjsmxxxXML = YBJKclient.getYBJSMXXX(ydybxx.getYbjkyhm(), ydybxx
//                        .getYbjkmm(), "", ydybxx.getYdbm(), r.getYbjsbh(), "1", "100");
//                Map mxM = ToolsInXml.getMap(ybjsmxxxXML,Ybjsmxxx.class);
//                List mxL = (List) mxM.get("ReturnValue");
//                for(int j=0; j<mxL.size(); j++){
//                    Ybjsmxxx mxR = (Ybjsmxxx) mxL.get(j);
//                    mxR.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
//                    long mxCon = ybjsmxxxDao.findCount(mxR);
//                    if(mxCon<=0){
//                        mxR.setIsupdate("0");
//                        ybjsmxxxDao.insert(mxR);
//                    }
//                }
//                r.setHjrq(ybjszxx.getYbjsrq_search());
//                r.setIsupdate("0");
//                r.setHjrq(ybjszxx.getYbjsrq_search());
//                r.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
//                long zCon = ybjszxxDao.findCount(r);
//                if(zCon<=0){
//                    ybjszxxDao.insert(r);
//                }
//            }
//        }
//
//        return "操作成功!";
//    }

}
