package com.youguu.market.sh.handler.impl;

import com.youguu.market.common.MARKET;
import com.youguu.market.common.TimeUtil;
import com.youguu.market.sh.annotations.Function;
import com.youguu.market.sh.data.CommonStockInfo;
import com.youguu.market.sh.handler.IShMsgHandler;

/**
 * Created by leo on 2017/11/9.
 */
@Function(value = MARKET.ShMsg.MD001)
public class MD001Hander implements IShMsgHandler {
	@Override
	public void handle(CommonStockInfo stockInfo, String stockArr[]) {
		stockInfo.setVolume(Long.parseLong(stockArr[3].trim()));//成交量
		stockInfo.setTurnOver(Double.parseDouble(stockArr[4].trim()));//成交额
		stockInfo.setPreClose(Double.parseDouble(stockArr[5].trim()));//昨收
		stockInfo.setTodayOpen(Double.parseDouble(stockArr[6].trim()));//开盘价
		stockInfo.setHighPrice(Double.parseDouble(stockArr[7].trim()));//最高价
		stockInfo.setLowPrice(Double.parseDouble(stockArr[8].trim()));//最低价
		stockInfo.setLastPrice(Double.parseDouble(stockArr[9].trim()));//最新价
		stockInfo.setTodayClose( Double.parseDouble(stockArr[10]));//今收盘价

		String timestamp = stockArr[12].trim().substring(0, 8)	;
		long mdtime = TimeUtil.formatDate(TimeUtil.formatToDate(timestamp, "HH:mm:ss"), "HHmmss");
		stockInfo.setDatetime(mdtime);//更新时间
	}
}
