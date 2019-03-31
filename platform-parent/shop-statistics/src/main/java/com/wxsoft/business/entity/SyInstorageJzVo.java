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
public class SyInstorageJzVo extends SyInstorageJz {

    @TableField(exist=false)
    private String indate_begin;
    @TableField(exist=false)
    private String indate_end;
    @TableField(exist=false)
    private String barcode_search;

    public String getIndate_begin() {
        return indate_begin;
    }

    public void setIndate_begin(String indate_begin) {
        this.indate_begin = indate_begin;
    }

    public String getIndate_end() {
        return indate_end;
    }

    public void setIndate_end(String indate_end) {
        this.indate_end = indate_end;
    }

    public String getBarcode_search() {
        return barcode_search;
    }

    public void setBarcode_search(String barcode_search) {
        this.barcode_search = barcode_search;
    }
}
