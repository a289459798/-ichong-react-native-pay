package com.zzy.pay.module.alipay;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.zzy.pay.module.BasePetPay;
import com.zzy.pay.module.PetPayResult;
import com.zzy.pay.module.listener.IPetPay;
import com.zzy.pay.module.utils.PayCode;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author zzy
 * @version V1.0.0
 */

public class Alipay extends BasePetPay implements IPetPay {

    private final Activity mActivity;
    private final IAlipayInfo mInfo;

    public Alipay(Activity activity, IAlipayInfo info) {
        this.mActivity = activity;
        this.mInfo = info;
    }

    @Override
    public String pay() {

        this.sendPayBefore();
        Observable.create(new Observable.OnSubscribe<Subscriber>() {
            @Override
            public void call(Subscriber<? super Subscriber> subscriber) {

                PayTask alipay = new PayTask(Alipay.this.mActivity);

                System.out.println("data:" + Alipay.this.mInfo.getInfo());

                String result = alipay.pay((String) Alipay.this.mInfo.getInfo(), true);

                AlipayResult rs = (AlipayResult) PetPayResult.result(new AlipayResult(), result);

                int code = PayCode.PAY_CODE_OTHER_ERROR;
                if (!TextUtils.equals(rs.getResultStatus(), PayCode.PAY_CODE_ALIPAY_SUCCESS + "")) {
                    if (PayCode.ResultStatus.containsKey(Integer.parseInt(rs.getResultStatus()))) {
                        code = Integer.parseInt(rs.getResultStatus());
                    }

                    Alipay.this.sendPayError(code, PayCode.ResultStatus.get(code));
                    subscriber.unsubscribe();
                } else {
                    subscriber.onCompleted();
                }

            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Subscriber>() {
                @Override
                public void onCompleted() {
                    Alipay.this.sendPaySuccess("");
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    Alipay.this.sendPayError(PayCode.PAY_CODE_OTHER_ERROR, e.getMessage());
                }

                @Override
                public void onNext(Subscriber subscriber) {

                }
            });

//        try {
//            sendPayBefore();
//            new Thread() {
//                public void run() {
//                    PayTask alipay = new PayTask(mActivity);
//                    System.out.println("data:" + ((String) mInfo.getInfo()));
//                    String result = alipay.pay((String) mInfo.getInfo(), true);
//                    try {
//                        AlipayResult rs = (AlipayResult) PetPayResult.result(new AlipayResult(), result);
//                        System.out.println("rs:" + rs);
//                        int code = PayCode.PAY_CODE_OTHER_ERROR;
//                        if (TextUtils.equals(rs.getResultStatus(), PayCode.PAY_CODE_ALIPAY_SUCCESS + "")) {
//                            sendPaySuccess(rs.getResult());
//                        } else {
//                            if (PayCode.ResultStatus.containsKey(Integer.parseInt(rs.getResultStatus()))) {
//                                code = Integer.parseInt(rs.getResultStatus());
//                            }
//                            sendPayError(code, PayCode.ResultStatus.get(code));
//                        }
//                    } catch (NumberFormatException e) {
//                        sendError(PayCode.PAY_CODE_OTHER_ERROR, PayCode.ResultStatus.get(PayCode.PAY_CODE_OTHER_ERROR));
//                    }
//
//                }
//            }.start();
//        } catch (Exception e) {
//            // TODO: handle exception
//            sendError(PayCode.PAY_CODE_OTHER_ERROR, PayCode.ResultStatus.get(PayCode.PAY_CODE_OTHER_ERROR));
//        }
        return null;
    }

}
