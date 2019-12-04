package com.zzy.pay.module.listener;

/**
 * @Description 充值回调接口
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public interface IPetPayCallback {

	void payError(int errno, String error);

	void paySuccess(String result);

	void payBefore();
}
