package com.wxsoft.business.service.impl;

import com.wxsoft.axis2.client.ToolsInXml;
import com.wxsoft.axis2.client.YBJKclient;
import com.wxsoft.business.dao.*;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.*;
import com.wxsoft.business.service.IYbjszxxService;
import com.wxsoft.util.common.DateUtil;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public String getYBJSXXBySJD(User user, Ybjszxx ybjszxx) throws Exception{
        String r = "没有更新数据" ;
        String ybjsrqB = ybjszxx.getYbjsrq_begin_search();
        String ybjsrqE = ybjszxx.getYbjsrq_end_search();
        int result=ybjsrqB.compareTo(ybjsrqE);
        while(result<=0){
            ybjszxx.setYbjsrq_search(ybjsrqB);
            r = getYBJSXX(user, ybjszxx);
            ybjsrqB = DateUtil.getNextDay(ybjsrqB);
            result=ybjsrqB.compareTo(ybjsrqE);
        }
        return r ;
    }
    /**
     * 获取医保结算信息
     * @throws IOException
     * @throws JDOMException
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String getYBJSXX(User user, Ybjszxx ybjszxx) throws Exception {
        // 获取药店医保信息
        Ydybxx ydybxx = new Ydybxx();
        ydybxx.setShortname(user.getPharmacy());
//		ydybxx.setShortname("SY");
        if(user.getPharmacy()==null || "".equals(user.getPharmacy())){
            return "当前用户不能获取:药店的医保信息！";
        }
        List<Ydybxx> ydybxxL = ydybxxDao.findBy(ydybxx);
        if (ydybxxL != null && ydybxxL.size() > 0) {
            ydybxx = ydybxxL.get(0);
        } else {
            return "当前用户不能获取:药店的医保信息！";
        }
        // 同步医保结算主信息
        String ybjszxxXML = YBJKclient.getYBJSZXX(ydybxx.getYbjkyhm(), ydybxx
                .getYbjkmm(), "", ydybxx.getYdbm(), ybjszxx.getYbjsrq_search(), "1", "100");
        Map m = ToolsInXml.getMap(ybjszxxXML,Ybjszxx.class);
        logger.debug("医保返回的map对象："+m);
        // 同步医保明细信息
        List l = (List) m.get("ReturnValue");
        if(l!=null && l.size()>0){
            for(int i=0; i<l.size(); i++){
                Ybjszxx r = (Ybjszxx) l.get(i);
                String ybjsmxxxXML = YBJKclient.getYBJSMXXX(ydybxx.getYbjkyhm(), ydybxx
                        .getYbjkmm(), "", ydybxx.getYdbm(), r.getYbjsbh(), "1", "100");
                Map mxM = ToolsInXml.getMap(ybjsmxxxXML,Ybjsmxxx.class);
                List mxL = (List) mxM.get("ReturnValue");
                for(int j=0; j<mxL.size(); j++){
                    Ybjsmxxx mxR = (Ybjsmxxx) mxL.get(j);
                    mxR.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
                    long mxCon = ybjsmxxxDao.findCount(mxR);
                    if(mxCon<=0){
                        mxR.setIsupdate("0");
                        ybjsmxxxDao.insert(mxR);
                    }
                }
                r.setHjrq(ybjszxx.getYbjsrq_search());
                r.setIsupdate("0");
                r.setHjrq(ybjszxx.getYbjsrq_search());
                r.setDrugStoreShortName(ybjszxx.getDrugStoreShortName());
                long zCon = ybjszxxDao.findCount(r);
                if(zCon<=0){
                    ybjszxxDao.insert(r);
                }
            }
        }

        return "操作成功!";
    }

    public static void main(String [] s) throws Exception{
        String ybjszxxXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SstResponse><Code>1000000</Code>" +
                "<Message>成功</Message>" +
                "<ReturnValue size=\"34\" comment=\"list01\">" +
                "<Resultset>" +
                "<tcqh>140301</tcqh>" +
                "<yyjgdm>67230158-6</yyjgdm>" +
                "<klxbh>1</klxbh><kahao>C10187419</kahao><bxid>140302198004260579</bxid>" +
                "<xzbh>301</xzbh>" +
                "<xzmc>职工基本医疗保险</xzmc>" +
                "<blh>120000011857370</blh>" +
                "" +
                "<rylb>11</rylb><rylbmc>在职</rylbmc>" +
                "" +
                "<sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc>" +
                "<zfy>129</zfy>" +
                "<jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf>" +
                "<bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf>" +
                "" +
                "<grzhzf>129</grzhzf>" +
                "<xjzh>0</xjzh>" +
                "<grzf>0</grzf>" +
                "<qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405422</ybjsbh><tsbt>0</tsbt></Resultset>" +
                "<Resultset>" +
                "<tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C10187419</kahao><bxid>140302198004260579</bxid>" +
                "<xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011857378</blh><rylb>11</rylb><rylbmc>在职</rylbmc>" +
                "<sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>420</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf>" +
                "<dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>420</grzhzf><xjzh>0</xjzh><grzf>33.6</grzf>" +
                "<qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405434</ybjsbh><tsbt>0</tsbt></Resultset>" +
                "<Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C10187419</kahao><bxid>140302198004260579</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011857402</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>22</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>22</grzhzf><xjzh>0</xjzh><grzf>1.5</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405465</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C10187419</kahao><bxid>140302198004260579</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011857472</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>112</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>112</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405542</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C10187419</kahao><bxid>140302198004260579</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011857485</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>6</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>6</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405560</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140311</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C06355184</kahao><bxid>140303195501290413</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011857669</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>7</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>7</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13405784</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140302</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C07520268</kahao><bxid>140302195709210949</bxid><xzbh>801</xzbh><xzmc>居民基本医疗保险</xzmc><blh>120000011858866</blh><rylb>31</rylb><rylbmc>普通成人</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>73</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>73</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>1</qzzf><qzqfx>0</qzqfx><ybjsbh>13407193</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C06582045</kahao><bxid>140302196911030815</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011859282</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>18</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>18</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13407627</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C0000202X</kahao><bxid>140322195912020054</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011860663</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>225</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>171.36</grzhzf><xjzh>53.64</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409024</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C0000202X</kahao><bxid>140322195912020054</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011860716</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>19</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>19</xjzh><grzf>1.05</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409069</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C0000202X</kahao><bxid>140322195912020054</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011860787</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>38</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>38</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409138</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C07276787</kahao><bxid>140302198901070820</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011860825</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>14</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>14</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409172</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C07276787</kahao><bxid>140302198901070820</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011860848</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>29</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>29</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409196</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140321</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C1016986X</kahao><bxid>14030219790422101X</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011861251</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>100</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>100</grzhzf><xjzh>0</xjzh><grzf>4.48</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409597</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00593034</kahao><bxid>140302195208110819</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011861518</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>70</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>70</grzhzf><xjzh>0</xjzh><grzf>5.6</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409859</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00593034</kahao><bxid>140302195208110819</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011861546</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>20.5</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>20.5</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13409890</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00414446</kahao><bxid>140302196307230458</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011863458</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>602</zfy><jbjjzf>421.4</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>180.6</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13411886</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140311</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C06169007</kahao><bxid>140303198110050037</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011863591</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>68</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>68</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>68</qzzf><qzqfx>0</qzqfx><ybjsbh>13412026</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>N03120793</kahao><bxid>140602197706301517</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864582</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>56.5</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>56.5</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>56.5</qzzf><qzqfx>0</qzqfx><ybjsbh>13412992</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>N03120793</kahao><bxid>140602197706301517</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864598</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>10</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>10</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>10</qzzf><qzqfx>0</qzqfx><ybjsbh>13413010</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00413785</kahao><bxid>140302196110060010</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864630</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>767.5</zfy><jbjjzf>537.25</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>230.25</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413038</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00413785</kahao><bxid>140302196110060010</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864644</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>95</zfy><jbjjzf>66.5</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>28.5</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413055</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C08075231</kahao><bxid>140302193506050819</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864722</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>152</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>125.36</grzhzf><xjzh>26.64</xjzh><grzf>12.16</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413132</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00413566</kahao><bxid>140302196003250855</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864769</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>485</zfy><jbjjzf>339.5</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>145.5</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413181</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00413566</kahao><bxid>140302196003250855</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864780</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>140</zfy><jbjjzf>98</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>42</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413194</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00641219</kahao><bxid>140302195611080816</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864921</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>414</zfy><jbjjzf>289.8</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>124.2</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413334</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00641219</kahao><bxid>140302195611080816</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011864952</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>390</zfy><jbjjzf>273</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>117</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413368</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00919161</kahao><bxid>140302197604290937</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011865036</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>126</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>126</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>1.5</qzzf><qzqfx>0</qzqfx><ybjsbh>13413451</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00919161</kahao><bxid>140302197604290937</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011865071</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>22</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>22</grzhzf><xjzh>0</xjzh><grzf>1.5</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413486</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00760170</kahao><bxid>140302195401020436</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011865107</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>12</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>12</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413520</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C0012625X</kahao><bxid>140302195603010842</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011865176</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>537</zfy><jbjjzf>375.9</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>76</grzhzf><xjzh>85.1</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413594</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C0012625X</kahao><bxid>140302195603010842</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011865194</blh><rylb>21</rylb><rylbmc>退休</rylbmc><sftsmz>1</sftsmz><tsmzfl>1</tsmzfl><tsmzflmc>慢性病</tsmzflmc><zfy>236</zfy><jbjjzf>165.2</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>0</grzhzf><xjzh>70.8</xjzh><grzf>0</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13413613</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>G01256274</kahao><bxid>140511199108135723</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011866101</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>35</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>35</grzhzf><xjzh>0</xjzh><grzf>2.8</grzf><qzzf>0</qzzf><qzqfx>0</qzqfx><ybjsbh>13414503</ybjsbh><tsbt>0</tsbt></Resultset><Resultset><tcqh>140301</tcqh><yyjgdm>67230158-6</yyjgdm><klxbh>1</klxbh><kahao>C00666811</kahao><bxid>140302198608060818</bxid><xzbh>301</xzbh><xzmc>职工基本医疗保险</xzmc><blh>120000011866137</blh><rylb>11</rylb><rylbmc>在职</rylbmc><sftsmz>0</sftsmz><tsmzfl></tsmzfl><tsmzflmc></tsmzflmc><zfy>25</zfy><jbjjzf>0</jbjjzf><dbjjzf>0</dbjjzf><dbeczf>0</dbeczf><bcjjzf>0</bcjjzf><gwyjjzf>0</gwyjjzf><grzhzf>25</grzhzf><xjzh>0</xjzh><grzf>0</grzf><qzzf>25</qzzf><qzqfx>0</qzqfx><ybjsbh>13414539</ybjsbh><tsbt>0</tsbt></Resultset></ReturnValue></SstResponse>";
        Map m = ToolsInXml.getMap(ybjszxxXML,Ybjszxx.class);

        System.out.println(m);
    }

}
