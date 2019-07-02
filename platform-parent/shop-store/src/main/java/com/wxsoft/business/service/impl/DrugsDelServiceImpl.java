package com.wxsoft.business.service.impl;

import com.wxsoft.business.dao.IDrugsDao;
import com.wxsoft.business.dao.IDrugsDelDao;
import com.wxsoft.business.model.easyui.PageHelper;
import com.wxsoft.business.pojo.Drugs;
import com.wxsoft.business.pojo.DrugsDel;
import com.wxsoft.business.service.IDrugsDelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("drugsDelService")
public class DrugsDelServiceImpl implements IDrugsDelService  {
    @Resource
    private IDrugsDelDao dao;

    @Resource
    private IDrugsDao drugsDao;

    public long findCount(DrugsDel drugsDel) {
        return dao.findCount(drugsDel);
    }

    public List<DrugsDel> findAll(PageHelper page,DrugsDel drugsDel) {
        List<DrugsDel> r = dao.findAll(page,drugsDel);
        return r;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String add(DrugsDel drugsDel) {
        this.dao.insert(drugsDel);
        return "操作成功!";
    }

    public String modify(DrugsDel drugsDel) {
        this.dao.modify(drugsDel);
        return "操作成功!";
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String delete(DrugsDel drugsDel) {

        //取删除的删除表信息
        DrugsDel oldDD = new DrugsDel();
        List<DrugsDel> ddL = dao.findBy(drugsDel);
        if(ddL!=null && ddL.size()>0){
            oldDD = ddL.get(0);
        }
        //判断药品表中是否增加了 相同条形码的药品
        Drugs drugsSearch = new Drugs();
        drugsSearch.setDrugStoreShortName(drugsDel.getDrugStoreShortName());
        drugsSearch.setBarcode(oldDD.getBarcode());
        List<Drugs> l = drugsDao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            throw new RuntimeException("已存在条码【"+OlDd.getBarcode()+"】的药品【"+OlDd.getDrugsname()+"】，不能恢复！");
        }
        //判断药品表中是否增加了 相同内部编码的药品
        drugsSearch.setBarcode("");
        drugsSearch.setDrugscode(oldDD.getDrugscode());
        l = drugsDao.findBy(drugsSearch);
        if(l!=null && l.size()>0){
            Drugs OlDd = l.get(0);
            throw new RuntimeException("已存在内部编码【"+OlDd.getDrugscode()+"】的药品【"+OlDd.getDrugsname()+"】，不能恢复！");
        }

        this.dao.insertDrugs(drugsDel);
        this.dao.delete(drugsDel);

        return "操作成功!";
    }


}
