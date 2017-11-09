package com.youguu.market.sh.handler.impl;

import com.youguu.market.common.MARKET;
import com.youguu.market.sh.annotations.Function;
import com.youguu.market.sh.data.CommonStockInfo;
import com.youguu.market.sh.handler.IShMsgHandler;

/**
 * Created by leo on 2017/11/9.
 */
@Function(value = MARKET.ShMsg.MD004)
public class MD004Hander implements IShMsgHandler {
	@Override
	public void handle(CommonStockInfo stockInfo, String stockArr[]) {

	}
}
