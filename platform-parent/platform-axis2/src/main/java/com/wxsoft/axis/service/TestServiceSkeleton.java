
/**
 * TestServiceSkeleton.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package com.wxsoft.axis.service;

import com.wxsoft.axis.business.receive.ReceiveRequest;
import com.wxsoft.axis.core.util.GlobalValues;

/**
 * TestServiceSkeleton java skeleton for the axisService
 */
public class TestServiceSkeleton {

    /**
     * Auto generated method signature
     *
     * @param invoke
     * @return invokeResponse
     */

    public InvokeResponse invoke(Invoke invoke) {
        ReceiveRequest r = GlobalValues.getBean(ReceiveRequest.class);
        org.apache.axis2.databinding.types.soapencoding.String s1 = invoke.getJsonData();
        String result = r.receive(s1.getString());
        org.apache.axis2.databinding.types.soapencoding.String s2 = new org.apache.axis2.databinding.types.soapencoding.String();
        s2.setString(result);
        InvokeResponse ir = new InvokeResponse();
        ir.setInvokeReturn(s2);
        return ir;
    }

}
