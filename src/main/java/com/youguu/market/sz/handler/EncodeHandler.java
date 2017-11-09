package com.youguu.market.sz.handler;

import com.youguu.codec.Packet;
import com.youguu.codec.PacketBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * Created by lenovo on 2017/9/5.
 */
public class EncodeHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        Packet p = (Packet)msg;

        PacketBuffer buf=PacketBuffer.allocate(128);

        buf.putInt(0);

        buf.putInt(p.getSeq());

        buf.putInt(p.getOperateCode());

        p.encode(buf);

        int len = buf.getWriteIndex();

        buf.setInt(0, len);

        ByteBuf byteBuf = ctx.alloc().buffer(len);

        byteBuf.writeBytes(buf.getBuffer());

        ctx.write(byteBuf);
        ctx.flush();
    }
}
