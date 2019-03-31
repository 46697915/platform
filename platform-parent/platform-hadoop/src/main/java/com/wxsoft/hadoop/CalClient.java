package com.wxsoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.ProtocolProxy;
import org.apache.hadoop.ipc.RPC;

import java.net.InetSocketAddress;

public class CalClient {
   public static void main(String[] args) throws Exception{
      InetSocketAddress i = new InetSocketAddress("127.0.0.1", 9999);
      ProtocolProxy<ICalProtocol> proxy = RPC.waitForProtocolProxy(ICalProtocol.class, ICalProtocol.versionID, i, new Configuration());
      ICalProtocol proxy2 = proxy.getProxy();
      int add = proxy2.add(3, 4);
      System.out.println(add);
   }
  
}