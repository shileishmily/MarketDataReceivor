package com.youguu.market.sz.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by leo on 2017/11/2.
 */
public class QuoteData {
	public static Map<Integer, AtomicInteger> map = new ConcurrentHashMap<Integer, AtomicInteger>();

	public static void incr(int msgType){
		if(map.containsKey(msgType)){
			map.get(msgType).incrementAndGet();
		} else {
			AtomicInteger count = new AtomicInteger(1);
			map.put(msgType, count);
		}
	}
}
