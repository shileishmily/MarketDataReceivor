package com.youguu.market.sh.handler;

import com.youguu.core.util.AnnotationScan;
import com.youguu.market.sh.annotations.Function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by leo on 2017/11/9.
 */
public class ShMsgHandlerFactory {
	private static final ShMsgHandlerFactory instance = new ShMsgHandlerFactory();
	private static Map<String, IShMsgHandler> map = new HashMap<>();

	private ShMsgHandlerFactory() {
	}

	static {
		AnnotationScan pp = AnnotationScan.getInstance();
		Set<Class<?>> set = pp.getPackageClass("com.youguu.market.sh.handler.impl", Function.class, true);
		for (Class<?> clazz : set) {
			Function function = clazz.getAnnotation(Function.class);
			String code = function.value();
			try {
				IShMsgHandler handler = (IShMsgHandler) clazz.newInstance();
				map.put(code, handler);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static IShMsgHandler get(String msgCode) {
		return map.get(msgCode);
	}


	public static ShMsgHandlerFactory get() {
		return instance;
	}

}
