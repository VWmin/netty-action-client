package com.vwmin.nettyactionclient.client;

import com.vwmin.nettyactionclient.CustomProtocolDecoder;
import com.vwmin.nettyactionclient.CustomProtocolEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * 通过用户定义的操作对Channel（两端沟通的媒介）进行初始化
 * 例如这里{@link IdleStateHandler}定义了一个空闲事件
 * 如果5s没有写，则发送一个{@link IdleStateEvent}，
 * 在{@link EchoClientHandle}中触发，并完成自定义操作
 */
public class CustomerHandleInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        ch.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                //每隔5秒发送一个心跳包
                .addLast(new IdleStateHandler(0, 5, 0))
                // 按行分包
                .addLast(new LineBasedFrameDecoder(2048))
                // 自定义编码解码
                .addLast(new CustomProtocolEncoder(CharsetUtil.UTF_8))
                .addLast(new CustomProtocolDecoder(CharsetUtil.UTF_8))
                // 自定义的消息处理器
                .addLast(new EchoClientHandle());
    }
}