
package com.zzy.pay;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.zzy.pay.module.PetPay;
import com.zzy.pay.module.listener.IPetPayCallback;
import com.zzy.pay.module.weixin.WXpayInfo;

public class RNPayModule extends ReactContextBaseJavaModule implements IPetPayCallback {

    private final ReactApplicationContext reactContext;
    static ReactApplicationContext mContext;

    public RNPayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNPay";
    }

    @ReactMethod
    public void alipay(final String info) {

        PetPay.pay(info, getCurrentActivity(), this);
    }


    @ReactMethod
    public void wxpay(final ReadableMap info) {

        System.out.print(info.toString());
        WXpayInfo wxInfo = new WXpayInfo();
        wxInfo.setAppId(info.getString("appId"));
        wxInfo.setNonceStr(info.getString("nonceStr"));
        wxInfo.setPackageValue(info.getString("packageValue"));
        wxInfo.setPartnerId(info.getString("partnerId"));
        wxInfo.setPrepayId(info.getString("prepayId"));
        wxInfo.setTimestamp(info.getInt("timestamp") + "");
        wxInfo.setSign(info.getString("sign"));

        PetPay.pay(wxInfo, getCurrentActivity(), this);
    }

    @ReactMethod
    public void addListener(String eventName) {

    }

    @ReactMethod
    public void removeListeners(Integer count) {

    }

    @Override
    public void payError(int errno, String error) {

        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("pay_error", error);
    }

    @Override
    public void paySuccess(String result) {

        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("pay_ok", result);
    }

    public static void sendPayStatus(String type, String data) {

        if (mContext != null) {

            mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(type, data);
        }
    }

    @Override
    public void payBefore() {

    }
}
