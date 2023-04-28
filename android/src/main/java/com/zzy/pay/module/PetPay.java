package com.zzy.pay.module;

import android.app.Activity;
import com.zzy.pay.module.alipay.Alipay;
import com.zzy.pay.module.listener.IPetPay;
import com.zzy.pay.module.listener.IPetPayCallback;
import com.zzy.pay.module.weixin.WXpay;
import com.zzy.pay.module.weixin.WXpayInfo;


/**
 * @Description 执行支付入口
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public class PetPay {
	public static void pay(Object payInfo, Activity activity, IPetPayCallback pay) {
		if (payInfo != null) {
			IPetPay zfPay = null;
			if (payInfo instanceof WXpayInfo) {
				zfPay = new WXpay(activity, (WXpayInfo) payInfo);
			} else {
				zfPay = new Alipay(activity, (String) payInfo);
			}

			if (zfPay != null) {
				zfPay.setPayListener(pay);
				zfPay.pay();
			}
		}
	}

	public static void init(Activity activity) {
	}

	public static void onPause() {
	}

	public static void onResume() {
	}

	public static void onDestroy() {
	}
}
