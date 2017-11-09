package com.youguu.market.sh.handler;

import com.youguu.market.sh.data.CommonStockInfo;

/**
 * Created by lenovo on 2017/9/5.
 */
public interface IShMsgHandler {

    void handle(CommonStockInfo stockInfo, String stockArr[]);
}
