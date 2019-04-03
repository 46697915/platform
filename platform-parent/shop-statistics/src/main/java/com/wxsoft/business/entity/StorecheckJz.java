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
 * 
 * </p>
 *
 * @author cl
 * @since 2019-04-03
 */
@TableName("sy_m_storecheck_jz")
public class StorecheckJz extends Model<StorecheckJz> {

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
     * 医保对应关系
     */
    private String bxdygx;

    /**
     * 药品内部代码
     */
    private String drugscode;

    /**
     * 期初库存
     */
    private BigDecimal initstore;

    /**
     * 期初库存金额
     */
    private BigDecimal initamount;

    /**
     * 入库数量
     */
    private BigDecimal instore;

    /**
     * 入库金额
     */
    private BigDecimal instoreamount;

    /**
     * 销售数量
     */
    private BigDecimal salecount;

    /**
     * 销售金额
     */
    private BigDecimal saleamount;

    /**
     * 最新库存
     */
    private BigDecimal newstore;

    /**
     * 最新库存金额
     */
    private BigDecimal newamount;

    /**
     * 当前实际库存
     */
    private BigDecimal currstore;

    /**
     * 当前实际库存金额
     */
    private BigDecimal curramount;

    /**
     * 本次盘点日期
     */
    private Date crrcheckdate;

    /**
     * 上次盘点日期
     */
    private Date lastcheckdate;

    /**
     * 盘点人
     */
    private String checker;

    /**
     * 盘点日期
     */
    private Date checkdate;

    /**
     * 是否最后一次盘点
     */
    private String islastcheck;

    /**
     * 上次盘点ID
     */
    private Integer lastcheckid;

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
    public String getBxdygx() {
        return bxdygx;
    }

    public void setBxdygx(String bxdygx) {
        this.bxdygx = bxdygx;
    }
    public String getDrugscode() {
        return drugscode;
    }

    public void setDrugscode(String drugscode) {
        this.drugscode = drugscode;
    }
    public BigDecimal getInitstore() {
        return initstore;
    }

    public void setInitstore(BigDecimal initstore) {
        this.initstore = initstore;
    }
    public BigDecimal getInitamount() {
        return initamount;
    }

    public void setInitamount(BigDecimal initamount) {
        this.initamount = initamount;
    }
    public BigDecimal getInstore() {
        return instore;
    }

    public void setInstore(BigDecimal instore) {
        this.instore = instore;
    }
    public BigDecimal getInstoreamount() {
        return instoreamount;
    }

    public void setInstoreamount(BigDecimal instoreamount) {
        this.instoreamount = instoreamount;
    }
    public BigDecimal getSalecount() {
        return salecount;
    }

    public void setSalecount(BigDecimal salecount) {
        this.salecount = salecount;
    }
    public BigDecimal getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(BigDecimal saleamount) {
        this.saleamount = saleamount;
    }
    public BigDecimal getNewstore() {
        return newstore;
    }

    public void setNewstore(BigDecimal newstore) {
        this.newstore = newstore;
    }
    public BigDecimal getNewamount() {
        return newamount;
    }

    public void setNewamount(BigDecimal newamount) {
        this.newamount = newamount;
    }
    public BigDecimal getCurrstore() {
        return currstore;
    }

    public void setCurrstore(BigDecimal currstore) {
        this.currstore = currstore;
    }
    public BigDecimal getCurramount() {
        return curramount;
    }

    public void setCurramount(BigDecimal curramount) {
        this.curramount = curramount;
    }
    public Date getCrrcheckdate() {
        return crrcheckdate;
    }

    public void setCrrcheckdate(Date crrcheckdate) {
        this.crrcheckdate = crrcheckdate;
    }
    public Date getLastcheckdate() {
        return lastcheckdate;
    }

    public void setLastcheckdate(Date lastcheckdate) {
        this.lastcheckdate = lastcheckdate;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }
    public String getIslastcheck() {
        return islastcheck;
    }

    public void setIslastcheck(String islastcheck) {
        this.islastcheck = islastcheck;
    }
    public Integer getLastcheckid() {
        return lastcheckid;
    }

    public void setLastcheckid(Integer lastcheckid) {
        this.lastcheckid = lastcheckid;
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
        return "StorecheckJz{" +
        "id=" + id +
        ", barcode=" + barcode +
        ", drugsname=" + drugsname +
        ", commonname=" + commonname +
        ", bxdygx=" + bxdygx +
        ", drugscode=" + drugscode +
        ", initstore=" + initstore +
        ", initamount=" + initamount +
        ", instore=" + instore +
        ", instoreamount=" + instoreamount +
        ", salecount=" + salecount +
        ", saleamount=" + saleamount +
        ", newstore=" + newstore +
        ", newamount=" + newamount +
        ", currstore=" + currstore +
        ", curramount=" + curramount +
        ", crrcheckdate=" + crrcheckdate +
        ", lastcheckdate=" + lastcheckdate +
        ", checker=" + checker +
        ", checkdate=" + checkdate +
        ", islastcheck=" + islastcheck +
        ", lastcheckid=" + lastcheckid +
        ", transferId=" + transferId +
        "}";
    }
}
