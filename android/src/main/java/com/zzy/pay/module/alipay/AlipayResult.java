package com.zzy.pay.module.alipay;


import android.text.TextUtils;

import com.zzy.pay.module.listener.IPetPayResult;

import java.util.Map;

public class AlipayResult implements IPetPayResult {

	private Map<String, String> mResult;

	String resultStatus;
	String memo;
	String result;
	boolean isSignOk;

	@Override
	public Object getResult(Map<String, String> result) {
		this.mResult = result;
		return this.parseResult();
	}

	private AlipayResult parseResult() {
		try {

			for (String key : this.mResult.keySet()) {
				if (TextUtils.equals(key, "resultStatus")) {
					resultStatus = this.mResult.get(key);
				} else if (TextUtils.equals(key, "result")) {
					result = this.mResult.get(key);
				} else if (TextUtils.equals(key, "memo")) {
					memo = this.mResult.get(key);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public String toString() {
		return "resultStatus={" + this.resultStatus + "};memo={" + this.memo + "};result={" + this.result + "}";
	}

	private String gatValue(String content, String key) {
		String prefix = key + "={";
		return content.substring(content.indexOf(prefix) + prefix.length(), content.lastIndexOf("}"));
	}

	/**
	 * @return the resultStatus
	 */
	public String getResultStatus() {
		return this.resultStatus;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return this.result;
	}

}
