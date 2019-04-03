package com.wxsoft.business.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author cl
 * @since 2019-04-03
 */
//@TableName("sy_m_storecheck_jz")
public class StorecheckJzVo extends StorecheckJz {


    private String drugStoreShortName ;
    private String iserrorstore;
    private String newstore_search;

    private String crrcheckdate_begin;
    private String crrcheckdate_end;

    private String barcode2;

    public String getBarcode2() {
        return barcode2;
    }

    public void setBarcode2(String barcode2) {
        this.barcode2 = barcode2;
    }

    public String getDrugStoreShortName() {
        return drugStoreShortName;
    }

    public void setDrugStoreShortName(String drugStoreShortName) {
        this.drugStoreShortName = drugStoreShortName;
    }

    public String getIserrorstore() {
        return iserrorstore;
    }

    public void setIserrorstore(String iserrorstore) {
        this.iserrorstore = iserrorstore;
    }

    public String getNewstore_search() {
        return newstore_search;
    }

    public void setNewstore_search(String newstore_search) {
        this.newstore_search = newstore_search;
    }

    public String getCrrcheckdate_begin() {
        return crrcheckdate_begin;
    }

    public void setCrrcheckdate_begin(String crrcheckdate_begin) {
        this.crrcheckdate_begin = crrcheckdate_begin;
    }

    public String getCrrcheckdate_end() {
        return crrcheckdate_end;
    }

    public void setCrrcheckdate_end(String crrcheckdate_end) {
        this.crrcheckdate_end = crrcheckdate_end;
    }
}
