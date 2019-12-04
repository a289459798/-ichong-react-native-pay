package com.zzy.pay.module.alipay;


import com.zzy.pay.module.listener.IPetPayInfo;

/**
 * @Description 类说明
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public interface IAlipayInfo extends IPetPayInfo {

	String getPartner();

	String getSeller();

	String getPrivate();

	String getPublic();
}
