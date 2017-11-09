package com.youguu.market.common;

/**
 * Created by leo on 2017/11/9.
 */
public final class MARKET {

	public final static int CNSSH = 1;//上交所
	public final static int CNSSZ = 2;//深交所

	/**
	 * MD001 表示指数行情数据格式类型
	 * MD002 表示股票（A、B股）行情数据格式类型
	 * MD003 表示债券行情数据格式类型
	 * MD004 表示基金行情数据格式类型
	 */
	public class ShMsg{
		public final static String MD001 = "MD001";
		public final static String MD002 = "MD002";
		public final static String MD003 = "MD003";
		public final static String MD004 = "MD004";
	}
}
