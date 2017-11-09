package com.youguu.market.sz.provider;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by leo on 2017/11/1.
 */
public class PacketHandlerProvider {

	private static final PacketHandlerProvider instance = new PacketHandlerProvider();

	private PacketHandlerProvider() {
	}

	public static PacketHandlerProvider get() {
		return instance;
	}

	public byte[] createHearBeart(){
		byte[] heartBeat = new byte[]{0,0,0,3,0,0,0,0,0,0,0,3};
		return heartBeat;
	}

	/**
	 * 创建登录消息
	 * @return
	 */
	public byte[] createLogonMsg() {
		int msgType = 1;
		int bodyLength = 92;
		byte[] senderCompID; //20
		byte[] targetCompID;//20
		int heartBtInt = 6;
		byte[] password;//16
		byte[] defaultApplVerID;//32 版本号(Ver1.00γ)时,该字段应设置为 1.00

		byte[] senderCompIDarr = "user1".getBytes();
		senderCompID = new byte[20];
		Arrays.fill(senderCompID, (byte) ' ');
		System.arraycopy(senderCompIDarr, 0, senderCompID, 0, senderCompIDarr.length);

		byte[] targetCompIDarr = "".getBytes();
		targetCompID = new byte[20];
		Arrays.fill(targetCompID, (byte) ' ');
		System.arraycopy(targetCompIDarr, 0, targetCompID, 0, targetCompIDarr.length);

		byte[] arrpass = "password1".getBytes();
		System.out.println(arrpass.length);
		password = new byte[16];
		Arrays.fill(password, (byte) ' ');
		System.arraycopy(arrpass, 0, password, 0, arrpass.length);

		byte[] defaultApplVerIDarr = "1.02".getBytes();
		defaultApplVerID = new byte[32];
		Arrays.fill(defaultApplVerID, (byte) ' ');
		System.arraycopy(defaultApplVerIDarr, 0, defaultApplVerID, 0, defaultApplVerIDarr.length);

		byte[] msg = new byte[100];
		ByteBuffer byteBuf = ByteBuffer.wrap(msg);
		byteBuf.putInt(msgType);
		byteBuf.putInt(bodyLength);
		byteBuf.put(senderCompID);
		byteBuf.put(targetCompID);
		byteBuf.putInt(heartBtInt);
		byteBuf.put(password);
		byteBuf.put(defaultApplVerID);

		int checkSum = generateCheckSum(msg, msg.length);
		byte[] reqmsg = new byte[104];
		ByteBuffer reqmsgBuf = ByteBuffer.wrap(reqmsg);
		reqmsgBuf.put(msg);
		reqmsgBuf.putInt(checkSum);

		return reqmsgBuf.array();
	}
//
//	public byte[] createRebuildMsg(){
//		int msgType = 390094;
//		int bodyLength = 44;
//		byte resendType = 1;
//		long begSeqNum = 1L;
//		long endSeqNum = 2L;
//		short channelNo = (short) paramMap.get("channelNo");
//		byte[] newsID = new byte[8];
//		// 返回时用的
//		byte resendStatus =1;
//		byte[] rejectText = new byte[16];
//
//		byte[] requestRebuildMsg = new byte[52];
//		ByteBuffer byteBuf = ByteBuffer.wrap(requestRebuildMsg);
//
//		byteBuf.putInt(msgType);
//		byteBuf.putInt(bodyLength);
//		byteBuf.put(resendType);
//		byteBuf.putShort(channelNo);
//		byteBuf.putLong(begSeqNum);
//		byteBuf.putLong(endSeqNum);
//		byteBuf.put(newsID);
//		byteBuf.put(resendStatus);
//		byteBuf.put(rejectText);
//		int checkSum = generateCheckSum(requestRebuildMsg, requestRebuildMsg.length);
//		byte[] reqRebMsg = new byte[52+4];
//		ByteBuffer reqRebMsgBuf = ByteBuffer.wrap(reqRebMsg);
//		reqRebMsgBuf.put(requestRebuildMsg);
//		reqRebMsgBuf.putInt(checkSum);
//		outStream.write(reqRebMsgBuf.array());
//	}
	/**
	 * 消息尾计算
	 * @param data
	 * @param dataLen
	 * @return
	 */
	public int generateCheckSum(byte[] data, int dataLen) {
		long idx;
		int cks;
		for (idx = 0L, cks = 0; idx < dataLen; cks += data[(int) idx++]) {

		}
		return cks % 256;

	}
}
