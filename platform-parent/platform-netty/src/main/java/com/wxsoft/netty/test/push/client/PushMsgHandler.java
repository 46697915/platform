package com.wxsoft.netty.test.push.client;

import com.wxsoft.netty.test.push.Message;
import com.wxsoft.netty.test.push.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
public class PushMsgHandler extends SimpleChannelInboundHandler<Message>{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        if(message.getType() == MessageType.MSG_PUSH.getValue()){
            System.out.println(message.getMsg());
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {

    }
}