package com.vwmin.nettyactionclient.client;

import com.vwmin.nettyactionclient.CustomProtocolDecoder;
import com.vwmin.nettyactionclient.CustomProtocolEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

public class CustomerHandleInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                //每隔5秒发送一个心跳包
                .addLast(new IdleStateHandler(0, 5, 0))
                .addLast(new LineBasedFrameDecoder(2048))
                .addLast(new CustomProtocolEncoder(CharsetUtil.UTF_8))
                .addLast(new CustomProtocolDecoder(CharsetUtil.UTF_8))
                .addLast(new EchoClientHandle());
    }
}