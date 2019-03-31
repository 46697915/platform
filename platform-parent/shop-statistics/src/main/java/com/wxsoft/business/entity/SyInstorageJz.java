package com.wxsoft.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 8入库单结转
 * </p>
 *
 * @author cl
 * @since 2019-03-11
 */
@TableName("sy_instorage_jz")
public class SyInstorageJz extends Model<SyInstorageJz> {

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
     * 生产批号
     */
    private String generatenum;

    /**
     * 生成日期
     */
    private Date generatedate;

    /**
     * 有效期
     */
    private Date validityperiod;

    /**
     * 保质期
     */
    private String shelflife;

    /**
     * 入库数量
     */
    private BigDecimal inquantity;

    /**
     * 入库类型
     */
    private String intype;

    /**
     * 入库金额
     */
    private BigDecimal money;

    /**
     * 入库日期
     */
    private Date indate;

    /**
     * 入库人
     */
    private String inperson;

    /**
     * 入库记录日期
     */
    private Date loggingdate;

    /**
     * 复核人
     */
    private String reviewer;

    /**
     * 复合日期
     */
    private Date reviewdate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 标记类型（扩展用）
     */
    private String signtype;

    /**
     * 结转记录id
     */
    @TableField("transfer_id")
    private String transferId;

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
    public String getGeneratenum() {
        return generatenum;
    }

    public void setGeneratenum(String generatenum) {
        this.generatenum = generatenum;
    }
    public Date getGeneratedate() {
        return generatedate;
    }

    public void setGeneratedate(Date generatedate) {
        this.generatedate = generatedate;
    }
    public Date getValidityperiod() {
        return validityperiod;
    }

    public void setValidityperiod(Date validityperiod) {
        this.validityperiod = validityperiod;
    }
    public String getShelflife() {
        return shelflife;
    }

    public void setShelflife(String shelflife) {
        this.shelflife = shelflife;
    }
    public BigDecimal getInquantity() {
        return inquantity;
    }

    public void setInquantity(BigDecimal inquantity) {
        this.inquantity = inquantity;
    }
    public String getIntype() {
        return intype;
    }

    public void setIntype(String intype) {
        this.intype = intype;
    }
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }
    public String getInperson() {
        return inperson;
    }

    public void setInperson(String inperson) {
        this.inperson = inperson;
    }
    public Date getLoggingdate() {
        return loggingdate;
    }

    public void setLoggingdate(Date loggingdate) {
        this.loggingdate = loggingdate;
    }
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    public Date getReviewdate() {
        return reviewdate;
    }

    public void setReviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSigntype() {
        return signtype;
    }

    public void setSigntype(String signtype) {
        this.signtype = signtype;
    }
    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SyInstorageJz{" +
        "id=" + id +
        ", barcode=" + barcode +
        ", drugsname=" + drugsname +
        ", commonname=" + commonname +
        ", generatenum=" + generatenum +
        ", generatedate=" + generatedate +
        ", validityperiod=" + validityperiod +
        ", shelflife=" + shelflife +
        ", inquantity=" + inquantity +
        ", intype=" + intype +
        ", money=" + money +
        ", indate=" + indate +
        ", inperson=" + inperson +
        ", loggingdate=" + loggingdate +
        ", reviewer=" + reviewer +
        ", reviewdate=" + reviewdate +
        ", remark=" + remark +
        ", signtype=" + signtype +
        ", transferId=" + transferId +
        "}";
    }
}
