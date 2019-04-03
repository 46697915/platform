package com.wxsoft.business.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
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
 * @since 2019-04-04
 */
@TableName("sy_m_ybjsmxxx_jz")
public class YbjsmxxxJz extends Model<YbjsmxxxJz> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 医保结算号
     */
    private String ybjsh;

    /**
     * 住院号
     */
    private String zyh;

    /**
     * 处方号
     */
    private String cfh;

    /**
     * 处方内序号
     */
    private String cfnxh;

    /**
     * 医院项目编号
     */
    private String yyxmbm;

    /**
     * 医院项目名称
     */
    private String yyxmmc;

    /**
     * 对应医保项目编码
     */
    private String ybxmbm;

    /**
     * 项目规格
     */
    private String xmgg;

    /**
     * 项目单位
     */
    private String xmdw;

    /**
     * 项目剂型
     */
    private String xmjx;

    /**
     * 项目单价
     */
    private BigDecimal xmdj;

    /**
     * 项目数量
     */
    private BigDecimal xmsl;

    /**
     * 项目金额
     */
    private BigDecimal xmje;

    /**
     * 划价日期
     */
    private String hjrq;

    /**
     * 开单医生姓名
     */
    private String kdysxm;

    /**
     * 取药窗口/执行科室
     */
    private String zxks;

    /**
     * 是否医保项目
     */
    private String sfybxm;

    /**
     * 每次用量
     */
    private BigDecimal mcyl;

    /**
     * 使用频次
     */
    private BigDecimal sypc;

    /**
     * 用法
     */
    private String yf;

    /**
     * 执行天数
     */
    private BigDecimal zxts;

    /**
     * 是否更新库存
     */
    private String isupdate;

    /**
     * 药品条码
     */
    private String barcode;

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
    public String getYbjsh() {
        return ybjsh;
    }

    public void setYbjsh(String ybjsh) {
        this.ybjsh = ybjsh;
    }
    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }
    public String getCfh() {
        return cfh;
    }

    public void setCfh(String cfh) {
        this.cfh = cfh;
    }
    public String getCfnxh() {
        return cfnxh;
    }

    public void setCfnxh(String cfnxh) {
        this.cfnxh = cfnxh;
    }
    public String getYyxmbm() {
        return yyxmbm;
    }

    public void setYyxmbm(String yyxmbm) {
        this.yyxmbm = yyxmbm;
    }
    public String getYyxmmc() {
        return yyxmmc;
    }

    public void setYyxmmc(String yyxmmc) {
        this.yyxmmc = yyxmmc;
    }
    public String getYbxmbm() {
        return ybxmbm;
    }

    public void setYbxmbm(String ybxmbm) {
        this.ybxmbm = ybxmbm;
    }
    public String getXmgg() {
        return xmgg;
    }

    public void setXmgg(String xmgg) {
        this.xmgg = xmgg;
    }
    public String getXmdw() {
        return xmdw;
    }

    public void setXmdw(String xmdw) {
        this.xmdw = xmdw;
    }
    public String getXmjx() {
        return xmjx;
    }

    public void setXmjx(String xmjx) {
        this.xmjx = xmjx;
    }
    public BigDecimal getXmdj() {
        return xmdj;
    }

    public void setXmdj(BigDecimal xmdj) {
        this.xmdj = xmdj;
    }
    public BigDecimal getXmsl() {
        return xmsl;
    }

    public void setXmsl(BigDecimal xmsl) {
        this.xmsl = xmsl;
    }
    public BigDecimal getXmje() {
        return xmje;
    }

    public void setXmje(BigDecimal xmje) {
        this.xmje = xmje;
    }
    public String getHjrq() {
        return hjrq;
    }

    public void setHjrq(String hjrq) {
        this.hjrq = hjrq;
    }
    public String getKdysxm() {
        return kdysxm;
    }

    public void setKdysxm(String kdysxm) {
        this.kdysxm = kdysxm;
    }
    public String getZxks() {
        return zxks;
    }

    public void setZxks(String zxks) {
        this.zxks = zxks;
    }
    public String getSfybxm() {
        return sfybxm;
    }

    public void setSfybxm(String sfybxm) {
        this.sfybxm = sfybxm;
    }
    public BigDecimal getMcyl() {
        return mcyl;
    }

    public void setMcyl(BigDecimal mcyl) {
        this.mcyl = mcyl;
    }
    public BigDecimal getSypc() {
        return sypc;
    }

    public void setSypc(BigDecimal sypc) {
        this.sypc = sypc;
    }
    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }
    public BigDecimal getZxts() {
        return zxts;
    }

    public void setZxts(BigDecimal zxts) {
        this.zxts = zxts;
    }
    public String getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(String isupdate) {
        this.isupdate = isupdate;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
        return "YbjsmxxxJz{" +
        "id=" + id +
        ", ybjsh=" + ybjsh +
        ", zyh=" + zyh +
        ", cfh=" + cfh +
        ", cfnxh=" + cfnxh +
        ", yyxmbm=" + yyxmbm +
        ", yyxmmc=" + yyxmmc +
        ", ybxmbm=" + ybxmbm +
        ", xmgg=" + xmgg +
        ", xmdw=" + xmdw +
        ", xmjx=" + xmjx +
        ", xmdj=" + xmdj +
        ", xmsl=" + xmsl +
        ", xmje=" + xmje +
        ", hjrq=" + hjrq +
        ", kdysxm=" + kdysxm +
        ", zxks=" + zxks +
        ", sfybxm=" + sfybxm +
        ", mcyl=" + mcyl +
        ", sypc=" + sypc +
        ", yf=" + yf +
        ", zxts=" + zxts +
        ", isupdate=" + isupdate +
        ", barcode=" + barcode +
        ", transferId=" + transferId +
        "}";
    }
}
