package com.zzy.pay.module.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 类说明
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public class PayCode {
	public static final Map<Integer, String> ResultStatus;
	public static final int PAY_CODE_ALIPAY_SUCCESS = 9000;
	public static final int PAY_CODE_OTHER_ERROR = 99999;
	public static final int PAY_CODE_TOKEN_ERROR = 80000;
	public static final int PAY_CODE_TOKEN_FORMAT_ERROR = 80001;
	public static final int PAY_CODE_WX_ENV_ERROR = 80002;
	public static final int PAY_CODE_WX_NO_RESULT_ERROR = 80003;
	public static final int PAY_CODE_WX_NO_TOKEN_ERROR = 80004;
	public static final int PAY_CODE_WX_NO_PAYPERID_ERROR = 80005;
	public static final int PAY_CODE_PARAMS_ERROR = 80006;
	public static final int PAY_CODE_SYSTEM_BUSY = 4000;
	public static final int PAY_CODE_ORDER_INFO_ERROR = 4001;
	public static final int PAY_CODE_CANCEL_ERROR = 6001;
	public static final int PAY_CODE_NET_ERROR = 6002;
	public static final int PAY_CODE_SHENG_KEY_NOT_MATCH = 70000;
	public static final int PAY_CODE_PAY_ERROR = 70001;
	public static final int PAY_CODE_SYSTEM_ERROR = 70002;
	static {
		ResultStatus = new HashMap<Integer, String>();
		ResultStatus.put(PAY_CODE_SYSTEM_ERROR, "系统异常");
		ResultStatus.put(PAY_CODE_SYSTEM_BUSY, "系统繁忙，请稍候再试");
		ResultStatus.put(PAY_CODE_ORDER_INFO_ERROR, "订单参数错误");
		ResultStatus.put(PAY_CODE_CANCEL_ERROR, "取消支付");
		ResultStatus.put(PAY_CODE_NET_ERROR, "网络连接异常");
		ResultStatus.put(PAY_CODE_TOKEN_ERROR, "Token获取失败");
		ResultStatus.put(PAY_CODE_TOKEN_FORMAT_ERROR, "Token格式错误");
		ResultStatus.put(PAY_CODE_WX_ENV_ERROR, "微信版本不支持");
		ResultStatus.put(PAY_CODE_WX_NO_RESULT_ERROR, "微信支付无返回值");
		ResultStatus.put(PAY_CODE_WX_NO_TOKEN_ERROR, "微信支付获取access_token错误");
		ResultStatus.put(PAY_CODE_WX_NO_PAYPERID_ERROR, "微信支付获取payperid错误");
		ResultStatus.put(PAY_CODE_PARAMS_ERROR, "参数错误");
		ResultStatus.put(PAY_CODE_PAY_ERROR, "支付失败");
		ResultStatus.put(PAY_CODE_SHENG_KEY_NOT_MATCH, "证书不匹配");

		ResultStatus.put(PAY_CODE_OTHER_ERROR, "其他问题");
	}
}
