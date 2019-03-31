package com.wxsoft.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Builder;
import org.apache.hadoop.ipc.RPC.Server;

public class CalServer {
   public static int PORT = 8888;
    public static String IPAddress = "127.0.0.1";
 
    public static void main(String[] args) throws Exception {
        Cal proxy = new Cal();
        //proxy, IPAddress, PORT, new Configuration())
        Builder builder = new RPC.Builder(new Configuration());
        builder.setInstance(proxy);
        builder.setBindAddress("127.0.0.1");
        builder.setProtocol(ICalProtocol.class);
        builder.setPort(9999);
        Server server = builder.build();
        server.start();
    }
}