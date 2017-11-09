package com.youguu.market.sz.handler;

import com.youguu.market.sz.client.SzReceiveClient;
import com.youguu.market.sz.provider.PacketHandlerProvider;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by lenovo on 2017/9/5.
 * 功能 基础链接管理
 * 1.连接认证
 * 2.连接关闭处理
 */
public class ConnectionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        ctx.fireChannelRegistered();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        ctx.fireChannelActive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //TODO 连接关闭，回收资源
        System.out.println("连接关闭");
        super.channelInactive(ctx);
        SzReceiveClient.doConnect();

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //TODO 异常捕捉, 回收资源
        System.out.println("连接异常");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleEvt = (IdleStateEvent) evt;
            if (idleEvt.state() == IdleState.WRITER_IDLE) {
//                System.out.println("发送心跳");
                byte[] msg = PacketHandlerProvider.get().createHearBeart();
                ByteBuf byteBuf = ctx.alloc().buffer(msg.length);
                byteBuf.writeBytes(msg);

                ctx.channel().writeAndFlush(byteBuf);
            }
        }
    }
}
