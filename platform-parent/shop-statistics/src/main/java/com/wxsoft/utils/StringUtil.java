package com.wxsoft.utils;

import java.math.BigDecimal;

public class StringUtil {

    /**
     * 转成 字符串
     * @param bd
     * @return
     */
    public static String toString(BigDecimal bd){
        return bd!=null?bd.toString():"";
    }
}
