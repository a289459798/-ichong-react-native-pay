package com.zzy.pay.module.alipay;

import android.text.TextUtils;
import com.zzy.pay.module.utils.PayUtils;

import java.net.URLEncoder;


/**
 * @Description 支付宝所需参数
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public final class AlipayInfo implements IAlipayInfo {
	// 签约的支付宝账号对应的支 付宝唯一用户号。 以 2088 开头的 16 位纯数字 组成。
	private String partner;

	private final String service = "mobile.securitypay.pay";

	// 商户网站使用的编码格式，固 定为 utf-8。
	private final String _input_charset = "utf-8";

	// 签名类型，目前仅支持 RSA
	private final String sign_type = "RSA";

	// 支付宝服务器主动通知商户 网站里指定的页面 http 路径。 需要 URL encode。
	private String notify_url;

	private String out_trade_no;

	// 商品的标题/交易标题/订单标 题/订单关键字等。 该参数最长为 128 个汉字。
	private String subject;

	// 支付类型。默认值为：1（商 品购买）
	private final String payment_type = "1";

	// 卖家支付宝账号（邮箱或手机 号码格式）或其对应的支付宝 唯一用户号（以 2088 开头的 纯 16 位数字）。
	private String seller_id;

	// 该笔订单的资金总额，单位为 RMB-Yuan。取值范围为 [0.01，100000000.00]，精确 到小数点后两位。
	private float total_fee;

	// 对一笔交易的具体描述信息。 如果是多种商品，请将商品描 述字符串累加传给 body。
	private String body;

	private String private_key;
	private String public_key;

	/**
	 * @return the seller_id
	 */
	public String getSeller_id() {
		return this.seller_id;
	}

	/**
	 * @return the privateKey
	 */
	public String getPrivate_key() {
		return this.private_key;
	}

	/**
	 * @param private_key
	 *            the privateKey to set
	 */
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	/**
	 * @return the public_key
	 */
	public String getPublic_key() {
		return this.public_key;
	}

	/**
	 * @param public_key
	 *            the public_key to set
	 */
	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	/**
	 * @param seller_id
	 *            the seller_id to set
	 */
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	/**
	 * @param partner
	 *            the partner to set
	 */
	public void setPartner(String partner) {
		this.partner = partner;
	}

	/**
	 * @return the _input_charset
	 */
	public String get_input_charset() {
		return this._input_charset;
	}

	/**
	 * @return the sign_type
	 */
	public String getSign_type() {
		return this.sign_type;
	}

	/**
	 * @return the notify_url
	 */
	public String getNotify_url() {
		return this.notify_url;
	}

	/**
	 * @param notify_url
	 *            the notify_url to set
	 */
	public void setNotify_url(String notify_url) {
		this.notify_url = URLEncoder.encode(notify_url);
	}

	/**
	 * @return the out_trade_no
	 */
	public String getOut_trade_no() {
		return this.out_trade_no;
	}

	/**
	 * @param out_trade_no
	 *            the out_trade_no to set
	 */
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return this.subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the payment_type
	 */
	public String getPayment_type() {
		return this.payment_type;
	}

	/**
	 * @return the total_fee
	 */
	public float getTotal_fee() {
		return this.total_fee;
	}

	/**
	 * @param total_fee
	 *            the total_fee to set
	 */
	public void setTotal_fee(float total_fee) {
		this.total_fee = total_fee;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return this.body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获取签名,在请求参数列表中，除去 sign、sign_type 两个参数外，其他需要使用到的参 数皆是要签名的参数
	 * 
	 * @return
	 * @author zzy
	 * @date 2014年11月4日 下午1:35:38
	 */
	private String getSign() {
		String sign = this.getParams();
		if (sign != null && (sign = PayUtils.sign(sign, this.private_key)) != null) {
			sign = URLEncoder.encode(sign);
		}
		return sign;
	}

	private String getParams() {
		if (this.checkParams()) {
			StringBuilder sb = new StringBuilder();
			sb.append("partner=\"");
			sb.append(this.partner);
			sb.append("\"&_input_charset=\"");
			sb.append(this._input_charset);
			sb.append("\"&subject=\"");
			sb.append(this.subject);
			sb.append("\"&body=\"");
			sb.append(this.body);
			sb.append("\"&total_fee=\"");
			sb.append(this.total_fee);
			sb.append("\"&notify_url=\"");
			sb.append(this.notify_url);
			sb.append("\"&payment_type=\"");
			sb.append(this.payment_type);
			sb.append("\"&seller_id=\"");
			sb.append(this.seller_id);
			sb.append("\"&service=\"");
			sb.append(this.service);
			sb.append("\"&out_trade_no=\"");
			sb.append(this.out_trade_no);
			sb.append("\"");
			return new String(sb);
		}
		return null;
	}

	/**
	 * 参数验证
	 * 
	 * @return
	 * @author zzy
	 * @date 2014年11月4日 下午1:40:01
	 */
	private boolean checkParams() {
		return !(TextUtils.isEmpty(this.private_key) || TextUtils.isEmpty(this.partner) || TextUtils.isEmpty(this._input_charset) || TextUtils.isEmpty(this.sign_type)
				|| TextUtils.isEmpty(this.out_trade_no) || TextUtils.isEmpty(this.subject) || TextUtils.isEmpty(this.payment_type) || TextUtils.isEmpty(this.seller_id)
				|| this.total_fee <= 0 || TextUtils.isEmpty(this.body) || TextUtils.isEmpty(this.notify_url));
	}

	/**
	 * 获取支付宝指定格式参数
	 * 
	 * @return
	 * @author zzy
	 * @date 2014年11月4日 下午1:32:35
	 */
	@Override
	public String getInfo() {
		String result = this.getParams();
		String sign = this.getSign();
		if (result != null && sign != null) {
			result += "&sign_type=\"" + this.sign_type + "\"&sign=\"" + sign + "\"";
			return result;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.book2345.reader.pay.alipay.AlipayInfoIface#getPartner()
	 */
	@Override
	public String getPartner() {
		// TODO Auto-generated method stub
		return this.partner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.book2345.reader.pay.alipay.AlipayInfoIface#getSeller()
	 */
	@Override
	public String getSeller() {
		// TODO Auto-generated method stub
		return this.seller_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.book2345.reader.pay.alipay.AlipayInfoIface#getPrivate()
	 */
	@Override
	public String getPrivate() {
		// TODO Auto-generated method stub
		return this.private_key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.book2345.reader.pay.alipay.AlipayInfoIface#getPublic()
	 */
	@Override
	public String getPublic() {
		// TODO Auto-generated method stub
		return this.public_key;
	}

	@Override
	public boolean checkData() {
		return this.checkParams();
	}

	@Override
	public String toString() {
		return "body:" + this.body + "\nnotify_url:" + this.notify_url + "\nout_trade_no:" + this.out_trade_no + "\npartner:" + this.partner + "\nseller_id:" + this.seller_id
				+ "\nsubject:" + this.subject + "\ntotal_fee:" + this.total_fee + "\nprivate_key:" + this.private_key + "\npublic_key: " + this.public_key;
	}

}
