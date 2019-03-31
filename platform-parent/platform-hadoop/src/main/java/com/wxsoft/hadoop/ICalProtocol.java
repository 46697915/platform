package com.wxsoft.hadoop;

import org.apache.hadoop.ipc.VersionedProtocol;

/**
 * 协议
 * （1）Hadoop中所有自定义RPC接口都需要继承VersionedProtocol接口，它描述了协议的版本信息。
　（2）默认情况下，不同版本号的RPC Client和Server之间不能相互通信，因此客户端和服务端通过版本号标识。
 * @author liuxing
 *
 */
public interface ICalProtocol extends VersionedProtocol {
   public static final long versionID = 12345L;//版本号，默认情况下，不同版本号的RPC Client和Server之间不能相互通信，这个名字是固定的
   public int add(int a,int b);//提供的方法
}