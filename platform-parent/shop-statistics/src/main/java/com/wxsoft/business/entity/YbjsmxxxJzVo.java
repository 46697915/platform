package com.wxsoft.business.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
//@TableName("sy_m_ybjsmxxx_jz")
public class YbjsmxxxJzVo extends YbjsmxxxJz {

    private String drugStoreShortName;
    private String hjrq_begin;
    private String hjrq_end;
    private String oldIsupdate;

    public String getDrugStoreShortName() {
        return drugStoreShortName;
    }

    public void setDrugStoreShortName(String drugStoreShortName) {
        this.drugStoreShortName = drugStoreShortName;
    }

    public String getHjrq_begin() {
        return hjrq_begin;
    }

    public void setHjrq_begin(String hjrq_begin) {
        this.hjrq_begin = hjrq_begin;
    }

    public String getHjrq_end() {
        return hjrq_end;
    }

    public void setHjrq_end(String hjrq_end) {
        this.hjrq_end = hjrq_end;
    }

    public String getOldIsupdate() {
        return oldIsupdate;
    }

    public void setOldIsupdate(String oldIsupdate) {
        this.oldIsupdate = oldIsupdate;
    }
}
