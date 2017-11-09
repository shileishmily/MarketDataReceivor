package com.youguu.market.sz;

import com.youguu.market.sh.client.ShReceiveClient;
import com.youguu.market.sz.client.SzReceiveClient;
import com.youguu.market.sz.data.Monitor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by leo on 2017/11/1.
 */
public class Bootstrap {

	public static void main(String[] args) {
		/**
		 * 深圳Binary行情
		 */
		SzReceiveClient client = new SzReceiveClient("192.168.1.23", 5016);

		Monitor monitor = new Monitor();

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(monitor, 10, 30, TimeUnit.SECONDS);

		client.start();

		/**
		 * 上海mktdt00行情解析
		 */
		ShReceiveClient shReceiveClient = new ShReceiveClient();
		Thread shThread = new Thread(shReceiveClient);
		shThread.start();
	}
}
