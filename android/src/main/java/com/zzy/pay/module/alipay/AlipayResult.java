package com.zzy.pay.module.alipay;


import com.zzy.pay.module.listener.IPetPayResult;

public class AlipayResult implements IPetPayResult {

	private String mResult;

	String resultStatus;
	String memo;
	String result;
	boolean isSignOk;

	@Override
	public Object getResult(String result) {
		this.mResult = result;
		return this.parseResult();
	}

	private AlipayResult parseResult() {
		try {

			String[] resultParams = this.mResult.split(";");
			for (String resultParam : resultParams) {
				if (resultParam.startsWith("resultStatus")) {
					this.resultStatus = this.gatValue(resultParam, "resultStatus");
				}
				if (resultParam.startsWith("result")) {
					this.result = this.gatValue(resultParam, "result");
				}
				if (resultParam.startsWith("memo")) {
					this.memo = this.gatValue(resultParam, "memo");
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
