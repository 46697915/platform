package com.wxsoft.business.entity;

/**
 * <p>
 * 
 * </p>
 *
 * @author cl
 * @since 2019-04-04
 */
//@TableName("sy_orderdetail_jz")
public class SyOrderdetailJzVo extends SyOrderdetailJz {

    private String isenoughname;
    private String iscommentname;
    private String paytypename;

    private String createdate_begin;
    private String createdate_end;
    private String barcode_search;

    public String getIsenoughname() {
        return isenoughname;
    }

    public void setIsenoughname(String isenoughname) {
        this.isenoughname = isenoughname;
    }

    public String getIscommentname() {
        return iscommentname;
    }

    public void setIscommentname(String iscommentname) {
        this.iscommentname = iscommentname;
    }

    public String getPaytypename() {
        return paytypename;
    }

    public void setPaytypename(String paytypename) {
        this.paytypename = paytypename;
    }

    public String getCreatedate_begin() {
        return createdate_begin;
    }

    public void setCreatedate_begin(String createdate_begin) {
        this.createdate_begin = createdate_begin;
    }

    public String getCreatedate_end() {
        return createdate_end;
    }

    public void setCreatedate_end(String createdate_end) {
        this.createdate_end = createdate_end;
    }

    public String getBarcode_search() {
        return barcode_search;
    }

    public void setBarcode_search(String barcode_search) {
        this.barcode_search = barcode_search;
    }
}
