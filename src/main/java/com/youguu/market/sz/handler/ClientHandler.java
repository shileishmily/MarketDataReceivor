package com.youguu.market.sz.handler;

import com.youguu.market.sz.data.QuoteData;
import com.youguu.market.sz.provider.Packet;
import com.youguu.market.sz.provider.PacketHandlerProvider;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		System.out.println("收到服务端消息：" + msg);

		this.receiveMsg(ctx, msg);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("向服务端发送登录消息");
		byte[] msg = PacketHandlerProvider.get().createLogonMsg();
		ByteBuf byteBuf = ctx.alloc().buffer(msg.length);
		byteBuf.writeBytes(msg);

		ctx.channel().writeAndFlush(byteBuf);
	}

	private void receiveMsg(ChannelHandlerContext ctx, Object msg) {
		Packet packet = (Packet)msg;
		int msgType = packet.getMsgType();
		int length = packet.getLength();
//		System.out.println("msgType="+msgType+", length="+length);

		QuoteData.incr(msgType);

		switch (msgType){
			case 1:
//				System.out.println("登录响应报文：msgType="+msgType);
				break;
			case 3:
//				System.out.println("收到心跳报文：msgType="+msgType);
				break;
			case 390012:
//				System.out.println("公告报文：msgType="+msgType);
				break;
			case 390013:
//				System.out.println("证券实时状态消息：msgType="+msgType);
				break;
			case 390019:
//				System.out.println("市场实时状态消息：msgType="+msgType);
				break;
			case 390090:
//				System.out.println("快照统计报文：msgType="+msgType);
				break;
			case 390095:
//				System.out.println("频道心跳：msgType="+msgType);
				break;
			case 309011:
//				System.out.println("指数快照报文：msgType="+msgType);
				break;
			case 309111:
//				System.out.println("成交量统计指标行情快照：msgType="+msgType);
				break;
			case 300111:
//				System.out.println("行情快照报文：msgType="+msgType);
				break;

			case 300792:
//				System.out.println("转融通证券出借逐笔行情：msgType="+msgType);
				break;
			case 306311:
//				System.out.println("港股实时行情快照扩展字段：msgType="+msgType);
				break;
			case 300611:
//				System.out.println("盘后定价交易业务行情快照：msgType="+msgType);
				break;
			default:
				System.out.println("未知消息");
				break;
		}

	}

}  