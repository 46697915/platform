package com.wxsoft.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 6药品
 * </p>
 *
 * @author cl
 * @since 2019-03-13
 */
public class Drugs extends Model<Drugs> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 药品条码
     */
    private String barcode;

    /**
     * 药品名称
     */
    private String drugsname;

    /**
     * 通用名
     */
    private String commonname;

    /**
     * 所属类别1
     */
    private String type1;

    /**
     * 所属类别2
     */
    private String type2;

    /**
     * 所属类别3
     */
    private String type3;

    /**
     * 药品剂型
     */
    private String dosageform;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 单位
     */
    private String units;

    /**
     * 生成厂家
     */
    private String manufactor;

    /**
     * 批准文号
     */
    private String approvalnum;

    /**
     * 内部编码
     */
    private String drugscode;

    /**
     * 通用名拼音简码
     */
    private String commonshotspell;

    /**
     * 通用名拼音码
     */
    private String commonnamespell;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作日期
     */
    private Date operatedate;

    /**
     * 医保对应关系
     */
    private String bxdygx;

    /**
     * 药品条码2真正条形码
     */
    private String barcode2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getDrugsname() {
        return drugsname;
    }

    public void setDrugsname(String drugsname) {
        this.drugsname = drugsname;
    }
    public String getCommonname() {
        return commonname;
    }

    public void setCommonname(String commonname) {
        this.commonname = commonname;
    }
    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }
    public String getDosageform() {
        return dosageform;
    }

    public void setDosageform(String dosageform) {
        this.dosageform = dosageform;
    }
    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }
    public String getApprovalnum() {
        return approvalnum;
    }

    public void setApprovalnum(String approvalnum) {
        this.approvalnum = approvalnum;
    }
    public String getDrugscode() {
        return drugscode;
    }

    public void setDrugscode(String drugscode) {
        this.drugscode = drugscode;
    }
    public String getCommonshotspell() {
        return commonshotspell;
    }

    public void setCommonshotspell(String commonshotspell) {
        this.commonshotspell = commonshotspell;
    }
    public String getCommonnamespell() {
        return commonnamespell;
    }

    public void setCommonnamespell(String commonnamespell) {
        this.commonnamespell = commonnamespell;
    }
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    public Date getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(Date operatedate) {
        this.operatedate = operatedate;
    }
    public String getBxdygx() {
        return bxdygx;
    }

    public void setBxdygx(String bxdygx) {
        this.bxdygx = bxdygx;
    }
    public String getBarcode2() {
        return barcode2;
    }

    public void setBarcode2(String barcode2) {
        this.barcode2 = barcode2;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Drugs{" +
        "id=" + id +
        ", barcode=" + barcode +
        ", drugsname=" + drugsname +
        ", commonname=" + commonname +
        ", type1=" + type1 +
        ", type2=" + type2 +
        ", type3=" + type3 +
        ", dosageform=" + dosageform +
        ", specifications=" + specifications +
        ", units=" + units +
        ", manufactor=" + manufactor +
        ", approvalnum=" + approvalnum +
        ", drugscode=" + drugscode +
        ", commonshotspell=" + commonshotspell +
        ", commonnamespell=" + commonnamespell +
        ", operator=" + operator +
        ", operatedate=" + operatedate +
        ", bxdygx=" + bxdygx +
        ", barcode2=" + barcode2 +
        "}";
    }
}
