

/**
 * TestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wxsoft.axis.client.testservice;

    /*
     *  TestService java interface
     */

    public interface TestService {
          

        /**
          * Auto generated method signature
          * 
                    * @param invoke0
                
         */

         
                     public InvokeResponse invoke(

                             Invoke invoke0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param invoke0
            
          */
        public void startinvoke(

                Invoke invoke0,

                final TestServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    