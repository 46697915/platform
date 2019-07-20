package com.wxsoft.netty.test.push.server;

import com.wxsoft.netty.test.push.Message;
import com.wxsoft.netty.test.push.MessageType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatHandler extends SimpleChannelInboundHandler<Message> {

    //加入到在线列表，只有在线用户才可以实时推送
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChanneList.channels.add(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        //如果是心跳包ping，则返回pong
        if(message != null && message.getType() == MessageType.HEARTBEAT_REQ.getValue()){
            Message response = buildMessage(MessageType.HEARTBEAT_RESP.getValue());
            ctx.writeAndFlush(response);
        }else{
            ctx.fireChannelRead(message);
        }
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //此处对断网进行了处理
        ChanneList.channels.remove(ctx.channel());
    }

    private Message buildMessage(byte result){
        Message msg = new Message();
        msg.setType(result);
        return msg;
    }
}
