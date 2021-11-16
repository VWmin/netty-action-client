package com.vwmin.nettyactionclient;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.internal.ObjectUtil;

import java.nio.charset.Charset;

/**
 * 自定义消息类型编码器
 * 将{@link CustomProtocol}转换为byte网络传输
 */
public class CustomProtocolEncoder extends MessageToByteEncoder<CustomProtocol> {
    private final Charset charset;
    private final Gson gson;

    public CustomProtocolEncoder() {
        this(Charset.defaultCharset());
    }

    public CustomProtocolEncoder(Charset charset) {
        this.charset = ObjectUtil.checkNotNull(charset, "charset");
        this.gson = new Gson();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf out) throws Exception {
        // 换行符标识包结束位置
        String jsonStr = gson.toJson(msg)+"\n";
        out.writeBytes(jsonStr.getBytes(charset));
    }
}
