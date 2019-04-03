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
 * @since 2019-04-04
 */
@TableName("sy_orderdetail_jz")
public class SyOrderdetailJz extends Model<SyOrderdetailJz> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String ordercode;

    /**
     * 订单项编号
     */
    private String detailcode;

    /**
     * 商品ID
     */
    private Integer goodsid;

    /**
     * 药品条码
     */
    private String barcode;

    /**
     * 药品名称
     */
    private String goodsname;

    /**
     * 通用名
     */
    private String commonname;

    /**
     * 赠品编码
     */
    private String giftcode;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 总金额
     */
    private BigDecimal money;

    /**
     * 配送费
     */
    private BigDecimal transportmoney;

    /**
     * 是否评价过
     */
    private String iscomment;

    /**
     * 收款金额
     */
    private BigDecimal getmoney;

    /**
     * 支付方式
     */
    private String paytype;

    /**
     * 支付银行
     */
    private String peybank;

    /**
     * 备注
     */
    private String remark;

    /**
     * 评论ID
     */
    private String commentid;

    /**
     * 库存是否充足
     */
    private String isenough;

    /**
     * 购货单位
     */
    private String units;

    /**
     * 创建日期
     */
    private Date createdate;

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
    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public String getDetailcode() {
        return detailcode;
    }

    public void setDetailcode(String detailcode) {
        this.detailcode = detailcode;
    }
    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }
    public String getCommonname() {
        return commonname;
    }

    public void setCommonname(String commonname) {
        this.commonname = commonname;
    }
    public String getGiftcode() {
        return giftcode;
    }

    public void setGiftcode(String giftcode) {
        this.giftcode = giftcode;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public BigDecimal getTransportmoney() {
        return transportmoney;
    }

    public void setTransportmoney(BigDecimal transportmoney) {
        this.transportmoney = transportmoney;
    }
    public String getIscomment() {
        return iscomment;
    }

    public void setIscomment(String iscomment) {
        this.iscomment = iscomment;
    }
    public BigDecimal getGetmoney() {
        return getmoney;
    }

    public void setGetmoney(BigDecimal getmoney) {
        this.getmoney = getmoney;
    }
    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
    public String getPeybank() {
        return peybank;
    }

    public void setPeybank(String peybank) {
        this.peybank = peybank;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
    public String getIsenough() {
        return isenough;
    }

    public void setIsenough(String isenough) {
        this.isenough = isenough;
    }
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
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
        return "SyOrderdetailJz{" +
        "id=" + id +
        ", ordercode=" + ordercode +
        ", detailcode=" + detailcode +
        ", goodsid=" + goodsid +
        ", barcode=" + barcode +
        ", goodsname=" + goodsname +
        ", commonname=" + commonname +
        ", giftcode=" + giftcode +
        ", price=" + price +
        ", amount=" + amount +
        ", money=" + money +
        ", transportmoney=" + transportmoney +
        ", iscomment=" + iscomment +
        ", getmoney=" + getmoney +
        ", paytype=" + paytype +
        ", peybank=" + peybank +
        ", remark=" + remark +
        ", commentid=" + commentid +
        ", isenough=" + isenough +
        ", units=" + units +
        ", createdate=" + createdate +
        ", transferId=" + transferId +
        "}";
    }
}
