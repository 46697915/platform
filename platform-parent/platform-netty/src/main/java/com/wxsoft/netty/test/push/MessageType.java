package com.wxsoft.netty.test.push;

public enum MessageType {

    //连接请求
    CONNECT_REQ((byte)1),
    //连接成功
    CONNECT_SUCCESS((byte)2),
    //连接失败
    CONNECT_FAIL((byte)3),
    //心跳请求
    HEARTBEAT_REQ((byte)4),
    //心跳相应
    HEARTBEAT_RESP((byte)5),
    //消息推送
    MSG_PUSH((byte)6);

    private byte value;

    private MessageType(byte value){
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
