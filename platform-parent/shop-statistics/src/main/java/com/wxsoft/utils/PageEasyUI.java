package com.wxsoft.utils;

import java.io.Serializable;

public class PageEasyUI implements Serializable {
    private Integer page=1;//默认显示第一页

    private Integer rows = 10;//每页显示记录数


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
