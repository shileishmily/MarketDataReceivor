package com.youguu.market.sz.provider;

import java.nio.ByteBuffer;

/**
 * Created by leo on 2017/11/1.
 */
public class Packet {
	private int msgType;
	private int length;
	private ByteBuffer buffer;

	public int getMsgType() {
		return msgType;
	}

	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public ByteBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}
}
