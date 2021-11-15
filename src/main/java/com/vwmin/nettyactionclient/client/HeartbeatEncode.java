package com.vwmin.nettyactionclient.client;

import com.google.gson.Gson;
import com.vwmin.nettyactionclient.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class HeartbeatEncode extends MessageToByteEncoder<CustomProtocol> {
    private static Gson gson =  new Gson();
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) {
        String jsonStr = gson.toJson(msg)+"\n";
        out.writeBytes(jsonStr.getBytes(Charset.defaultCharset()));
    }
}