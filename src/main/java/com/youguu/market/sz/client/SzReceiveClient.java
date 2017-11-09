package com.youguu.market.sz.client;

import com.youguu.market.sz.handler.ClientHandler;
import com.youguu.market.sz.handler.ConnectionHandler;
import com.youguu.market.sz.handler.DecodeHandler;
import com.youguu.market.sz.handler.EncodeHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class SzReceiveClient {
    private NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
    private static Channel channel;
    private static Bootstrap bootstrap;
    private static String hostIp;
    private static int port;

    public SzReceiveClient(String hostIp, int port) {
        this.hostIp = hostIp;
        this.port = port;
    }

    public void start() {
        try {
            bootstrap = new Bootstrap();
            bootstrap
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pip = socketChannel.pipeline();
                            pip.addLast("ping", new IdleStateHandler(3, 3, 3));
                            pip.addLast(new ConnectionHandler());
//                            pip.addLast("encode", new EncodeHandler());
                            pip.addLast("decode", new DecodeHandler());
                            pip.addLast(new ClientHandler());
                        }
                    });
            doConnect();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void doConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }

        ChannelFuture future = bootstrap.connect(hostIp, port);

        future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture futureListener) throws Exception {
                if (futureListener.isSuccess()) {
                    channel = futureListener.channel();
                    System.out.println("连接到服务器成功");
                } else {
                    System.out.println("和服务器断开连接，10秒后重连......");

                    futureListener.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 10, TimeUnit.SECONDS);
                }
            }
        });
    }

}
