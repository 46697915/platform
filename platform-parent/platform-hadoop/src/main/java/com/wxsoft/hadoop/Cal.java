package com.wxsoft.hadoop;

import org.apache.hadoop.ipc.ProtocolSignature;

import java.io.IOException;

public class Cal implements ICalProtocol{
 
   @Override
   public long getProtocolVersion(String protocol, long clientVersion) throws IOException {
      return versionID;
   }
 
   @Override
   public ProtocolSignature getProtocolSignature(String protocol, long clientVersion, int clientMethodsHash)
         throws IOException {
      return null;
   }
 
   @Override
   public int add(int a, int b) {
      System.out.println("加法运算！");
      return a+b;
   }
 
}