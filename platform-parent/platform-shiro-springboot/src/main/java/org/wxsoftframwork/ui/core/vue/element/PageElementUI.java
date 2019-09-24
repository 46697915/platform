package org.wxsoftframwork.ui.core.vue.element;

import java.io.Serializable;


public class PageElementUI implements Serializable {
    private Integer pageNO = 1;//默认显示第一页
    private Integer pageSize = 10;//每页显示记录数

    public Integer getPageNO() {
        return pageNO;
    }

    public void setPageNO(Integer pageNO) {
        this.pageNO = pageNO;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}