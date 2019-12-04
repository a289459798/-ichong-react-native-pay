package com.zzy.pay.module;

import com.zzy.pay.module.listener.IPetPayCallback;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @Description 类说明
 * @author zzy
 * @date 2014年6月3日 下午6:26:17
 * @version V1.0.0
 */

public abstract class BasePetPay<T> {
	protected IPetPayCallback mPay;
	public static Retrofit mRetrofit;

	public static Retrofit.Builder mRetrofitBuild;

	public static OkHttpClient mClient;

	public static OkHttpClient.Builder mClientBuild;

	protected void sendPaySuccess(String result) {
		if (this.mPay != null) {
			this.mPay.paySuccess(result);
		}
	}

	protected void sendPayError(int errno, String error) {
		if (this.mPay != null) {
			this.mPay.payError(errno, error);
		}
	}

	protected void sendPayBefore() {
		if (this.mPay != null) {
			this.mPay.payBefore();
		}
	}

	public void setPayListener(IPetPayCallback pay) {
		this.mPay = pay;
	}

	public BasePetPay setBaseUrl(String baseUrl) {

		mRetrofitBuild.baseUrl(baseUrl);
		return this;
	}

	public BasePetPay initRetrofit() {

		mClientBuild = new OkHttpClient.Builder()
			.retryOnConnectionFailure(true)
			.connectTimeout(10, TimeUnit.SECONDS);

		mRetrofitBuild = new Retrofit.Builder()
			.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create());

		return this;
	}

	public void build() {

		mClient = mClientBuild.build();
		mRetrofitBuild.client(mClient);
		mRetrofit = mRetrofitBuild.build();
	}

	public static  <T> T createRetrofit(Class<T> service) {

		return mRetrofit.create(service);
	}

	public void request(Observable observable, Subscriber<T> subscriber) {

		observable.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(subscriber);

	}

}
