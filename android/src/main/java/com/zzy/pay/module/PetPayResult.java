package com.zzy.pay.module;


import com.zzy.pay.module.alipay.AlipayResult;

/**
 * @Description 解析充值返回数据
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public class PetPayResult {
	public static Object result(AlipayResult payResult, String result) {
		return payResult.getResult(result);
	}
}
