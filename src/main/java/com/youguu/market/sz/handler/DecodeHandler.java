package com.youguu.market.sz.handler;

import com.youguu.market.sz.provider.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteBuffer;
import java.util.List;

public class DecodeHandler extends ByteToMessageDecoder {

	public static final int HEAD_LENGTH = 8;
	public static final int TAIL_LENGTH = 4;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

		try {
			//消息头长度
			if (in.readableBytes() < (HEAD_LENGTH + TAIL_LENGTH)) {
				return;
			}
			in.markReaderIndex();

			int msgType = in.readInt();//消息类型
			int bodyLen = in.readInt();//消息长度

			//读到的消息体长度如果小于我们传送过来的消息长度,返回
			if (in.readableBytes() < bodyLen + TAIL_LENGTH) {
				in.resetReaderIndex();
				return;
			}

			byte[] result = new byte[bodyLen];

			in.readBytes(result);

			in.skipBytes(4);//舍弃消息尾

			Packet p = new Packet();
			p.setMsgType(msgType);
			p.setLength(bodyLen);
			p.setBuffer(ByteBuffer.wrap(result));

			out.add(p);
		} catch (Exception e){
			e.printStackTrace();
		}


	}

}
