package com.youguu;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leo on 2017/11/2.
 */
public class DateTest {
	public static void main(String[] args) {
		long time = 1675088785049501L;

		Date date = new Date(time);
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dft.format(date));
	}
}
