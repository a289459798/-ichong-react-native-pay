package com.zzy.pay.module.listener;

/**
 * @Description 类说明
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public interface IPetPay {
	String pay();

	void setPayListener(IPetPayCallback pay);
}
