package com.vwmin.nettyactionclient.client;

import com.google.gson.Gson;
import com.vwmin.nettyactionclient.CustomProtocol;
import com.vwmin.nettyactionclient.HeartBeatConfig;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
public class EchoClientHandle extends SimpleChannelInboundHandler<CustomProtocol> {

    private final CustomProtocol heartBeat = HeartBeatConfig.heartBeat;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol msg) {
        //从服务端收到消息时被调用
        log.info("收到来自[{}]的消息 >>> {}", msg.getId(), msg.getContent()) ;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;

            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                log.info("正在发送心跳包 >>> {}", heartBeat.getContent());
                //向服务端发送消息
//                ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
                ctx.channel().writeAndFlush(heartBeat);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause instanceof IOException){
            log.info("对方主机关闭了连接");
            return;
        }
        super.exceptionCaught(ctx, cause);
    }
}
