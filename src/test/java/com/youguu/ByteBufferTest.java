package com.youguu;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by leo on 2017/11/1.
 */
public class ByteBufferTest {
	public static void main(String[] args) {
//		byte[] msg = logonMsg();
//		System.out.println(msg.length);
		int a = 1;
		System.out.println();
	}

	public static byte[] logonMsg() {
		int msgType = 1;
		int bodyLength = 92;
		byte[] senderCompID; //20
		byte[] targetCompID;//20
		int heartBtInt = 6;
		byte[] password;//16
		byte[] defaultApplVerID;//32 版本号(Ver1.00γ)时,该字段应设置为 1.00

		byte[] senderCompIDarr = "senderComID".getBytes();
		senderCompID = new byte[20];
		Arrays.fill(senderCompID, (byte) ' ');
		System.arraycopy(senderCompIDarr, 0, senderCompID, 0, senderCompIDarr.length);

		byte[] targetCompIDarr = "targetCompID".getBytes();
		targetCompID = new byte[20];
		Arrays.fill(targetCompID, (byte) ' ');
		System.arraycopy(targetCompIDarr, 0, targetCompID, 0, targetCompIDarr.length);

		byte[] arrpass = "password1".getBytes();
		password = new byte[16];
		Arrays.fill(password, (byte) ' ');
		System.arraycopy(arrpass, 0, password, 0, arrpass.length);

		byte[] defaultApplVerIDarr = "".getBytes();
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

	public static int generateCheckSum(byte[] data, int dataLen) {
		long idx;
		int cks;
		for (idx = 0L, cks = 0; idx < dataLen; cks += data[(int) idx++]) {

		}
		return cks % 256;

	}
}
