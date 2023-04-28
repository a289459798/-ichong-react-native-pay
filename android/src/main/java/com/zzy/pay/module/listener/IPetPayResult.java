package com.zzy.pay.module.listener;

import java.util.Map;

/**
 * @Description 支付结果处理
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public interface IPetPayResult {
	Object getResult(Map<String, String> result);
}
