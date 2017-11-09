package com.youguu.market.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeUtil {

	/**
	 * 字符串转日期
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date formatToDate(String str, String format) {
		Date adate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			adate = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return adate;
	}

	/**
	 * 日期转Long类型
	 * @param date
	 * @param format
	 * @return
	 */
	public static long formatDate(Date date, String format) {
		long day = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		day = Long.parseLong(str);
		return day;
	}
}
