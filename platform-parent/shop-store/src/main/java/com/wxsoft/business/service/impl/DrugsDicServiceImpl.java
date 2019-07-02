package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IDrugsDicDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.DrugsDic;
import com.wxsoft.business.service.IDrugsDicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("drugsDicService")
public class DrugsDicServiceImpl implements IDrugsDicService  {
    @Resource
    private IDrugsDicDao dao;


    public long findCount(DrugsDic drugsDic) {
        return dao.findCount(drugsDic);
    }

    public List<DrugsDic> findAll(PageHelper page,DrugsDic drugsDic) {
        List<DrugsDic> r = dao.findAll(page,drugsDic);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(DrugsDic drugsDic) {
        this.dao.insert(drugsDic);
        return "操作成功!";
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String modify(DrugsDic drugsDic) {
        this.dao.modify(drugsDic);
        return "操作成功!";
    }
    /**
     * 如果barcode2存在则更新，否则插入
     * @Description:TODO
     * @参数： @return
     * @return String
     * @throws
     * @author: chenliang
     * @time:2018-9-17 下午2:22:59
     */
    @Transactional
    public String addOrModify(DrugsDic drugsDic){

        DrugsDic drugsDicSearch = new DrugsDic();
        drugsDicSearch.setDrugStoreShortName(drugsDic.getDrugStoreShortName());
        drugsDicSearch.setBarcode2(drugsDic.getBarcode2());
        List<DrugsDic> l = dao.findBy(drugsDicSearch);
        if(l!=null && l.size()>0){
            DrugsDic OlDd = l.get(0);
            drugsDic.setId(OlDd.getId());
            this.dao.modify(drugsDic);
        }else{
            this.dao.insert(drugsDic);
        }

        return "操作成功!";
    }

    public String delete(DrugsDic drugsDic) {
        this.dao.delete(drugsDic);
        return "操作成功!";
    }

}
