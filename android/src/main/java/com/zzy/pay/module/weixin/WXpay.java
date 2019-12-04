package com.zzy.pay.module.weixin;

import android.app.Activity;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zzy.pay.module.BasePetPay;
import com.zzy.pay.module.listener.IPetPay;
import com.zzy.pay.module.utils.PayCode;

/**
 * @Description 微信支付
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public class WXpay extends BasePetPay implements IPetPay {

	private final Activity mActivity;
	private final IWXpayInfo mInfo;
	private final IWXAPI iWXApi;

	public WXpay(Activity activity, IWXpayInfo info) {
		this.mActivity = activity;
		this.mInfo = info;
		this.iWXApi = WXAPIFactory.createWXAPI(this.mActivity, this.mInfo.getAppId());
	}

	@Override
	public String pay() {

		if (this.check()) {
			// 注册到微信
			this.iWXApi.registerApp(this.mInfo.getAppId());

			PayReq payReq = (PayReq) this.mInfo.getInfo();

			if (payReq.checkArgs()) {

				this.iWXApi.sendReq(payReq);
			} else {

				this.sendPayError(PayCode.PAY_CODE_ORDER_INFO_ERROR, PayCode.ResultStatus.get(PayCode.PAY_CODE_ORDER_INFO_ERROR));
			}
			return "";

		} else {
			// 不支持微信支付
			this.sendPayError(PayCode.PAY_CODE_WX_ENV_ERROR, PayCode.ResultStatus.get(PayCode.PAY_CODE_WX_ENV_ERROR));
		}
		return null;
	}

	/**
	 * 检测是否支持微信支付
	 * 
	 * @return
	 * @author zzy
	 * @date 2014年11月4日 下午5:21:42
	 */
	private boolean check() {
		return this.iWXApi != null && this.iWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
	}

}
