package com.youguu.market.sz.data;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leo on 2017/11/2.
 */
public class Monitor implements Runnable {
	@Override
	public void run() {

		for (Map.Entry<Integer, AtomicInteger> entry : QuoteData.map.entrySet()) {
			System.out.println("MsgType = " + MessageEnum.findById(entry.getKey()) + "\t count = " + entry.getValue());
		}

		System.out.println("---------------------------------------");
	}
}
