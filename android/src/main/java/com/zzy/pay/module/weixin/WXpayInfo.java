package com.zzy.pay.module.weixin;


import com.tencent.mm.opensdk.modelpay.PayReq;

/**
 * @Description 类说明
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public class WXpayInfo implements IWXpayInfo {

	// 应用唯一标识，在微信开放平台提交应用审核通 过后获得
	private String appId;
	private String nonceStr;
	private String packageValue;
	private String partnerId;
	private String prepayId;
	private String sign;
	private String timestamp;
	private String extData;

	@Override
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNonceStr() {
		return this.nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageValue() {
		return this.packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPrepayId() {
		return this.prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getExtData() {
		return this.extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

	@Override
	public PayReq getInfo() {

		PayReq payReq = new PayReq();
		payReq.packageValue = this.packageValue;
		payReq.appId = this.appId;
		payReq.prepayId = this.prepayId;
		payReq.sign = this.sign;
		payReq.timeStamp = this.timestamp;
		payReq.nonceStr = this.nonceStr;
		payReq.partnerId = this.partnerId;
		payReq.extData = this.extData;

		return payReq;
	}

	@Override
	public boolean checkData() {
		return false;
	}

}
