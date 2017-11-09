package com.youguu.market.sh.client;

import com.youguu.market.common.MARKET;
import com.youguu.market.sh.data.BaseStockInfo;
import com.youguu.market.sh.data.CommonStockInfo;
import com.youguu.market.sh.handler.ShMsgHandlerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 2017/11/9.
 */
public class ShReceiveClient implements Runnable {
	public static final String DBF_FILE_PATH = "E:\\SecureCRT_download\\mktdt00.txt";


	@Override
	public void run() {
		while (true) {

		}
	}

	public List<BaseStockInfo> analyzeDBF(){
		List<BaseStockInfo> list = new ArrayList<BaseStockInfo>();

		File dbfFile = new File(DBF_FILE_PATH);
		if (!dbfFile.exists()) {
			System.out.println("File not found : " + DBF_FILE_PATH);
			return list;
		}

		try {
			RandomAccessFile accessFile = new RandomAccessFile(dbfFile, "r");

			//文件头判断
			byte[] header = new byte[82];
			accessFile.read(header);
			String[] headArray = new String(header).replaceAll("[\\s\\p{Zs}]+", "").split("\\|");
			if (headArray.length != 9) {
				return list;
			}

			int rowNum = Integer.parseInt(headArray[3]);

			//读取文件体
			for (int i = 0; i < rowNum; i++) {
				CommonStockInfo stockInfo = processRow(accessFile);
				list.add(stockInfo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){

		}
		return list;
	}

	/**
	 * MD001 表示指数行情数据格式类型
	 * MD002 表示股票（A、B股）行情数据格式类型
	 * MD003 表示债券行情数据格式类型
	 * MD004 表示基金行情数据格式类型
	 * @param accessFile
	 * @throws IOException
	 */
	public CommonStockInfo processRow(RandomAccessFile accessFile) throws IOException {
		StringBuffer rowBuffer = new StringBuffer();

		byte[] msgtypeb = new byte[6];
		accessFile.read(msgtypeb);
		String msgtypes = new String(msgtypeb);
		rowBuffer.append(msgtypes);

		byte[] restBytes = null;
		if(msgtypes.equals("MD001|")){
			restBytes = new byte[144];
		}else if("MD002|".equals(msgtypes)){
			restBytes = new byte[394];
		}else if("MD003|".equals(msgtypes)){
			restBytes = new byte[394];
		}else if("MD004|".equals(msgtypes)){
			restBytes = new byte[418];
		}else{
			System.out.println("未知消息类型："+msgtypes);
		}
		accessFile.read(restBytes);
		rowBuffer.append(new String(restBytes));
		String stockArr[] = rowBuffer.toString().replaceAll("[\\s\\p{Zs}]+", "").split("\\|");

		CommonStockInfo stockInfo = new CommonStockInfo();

		String msgType = stockArr[0].trim();//消息类型
		String stockCode = stockArr[1];//证券代码
		String stockName =	new String( stockArr[2].getBytes("gbk"), "gbk");//证券简称

		stockInfo.setStockCode(stockCode.trim());
		stockInfo.setCnName(stockName);
		stockInfo.setMarketId(MARKET.CNSSH);

		//根据消息类型做不同处理
		ShMsgHandlerFactory.get(msgType).handle(stockInfo, stockArr);

		return stockInfo;
	}

	public static void main(String[] args) {
		ShReceiveClient client = new ShReceiveClient();
		List<BaseStockInfo> list = client.analyzeDBF();
		System.out.println("stock size : " + list.size());
	}
}
