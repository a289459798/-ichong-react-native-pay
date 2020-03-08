
# react-native-pay

集成微信支付和支付宝支付

为开源事业做一份绵薄之力，欢迎加入群：161263093

作者：zhangzy  QQ：289459798  微信：zhangzy816

react-native 版本 > 0.6

## 效果

![image](/pic/error.gif)
![image](/pic/success.gif)

## Getting started

`$ npm install git+https://gitee.com/petdoctor/react-native-pay.git --save`

### Mostly automatic installation

`$ react-native link react-native-pay`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-pay` and add `RNPay.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNPay.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.zzy.pay.RNPayPackage;` to the imports at the top of the file
  - Add `new RNPayPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-pay'
  	project(':react-native-pay').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-pay/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-pay')
  	```


## Usage

```objective-c

// 在AppDelegate.m 增加

- (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey, id> *)options
{
    // 其他如支付等SDK的回调
    if ([url.host isEqualToString:@"safepay"]) {
      //跳转支付宝钱包进行支付，处理支付结果
      [[AlipaySDK defaultService] processOrderWithPaymentResult:url standbyCallback:^(NSDictionary *resultDic) {
        
        [RNPay sendAlipayResult:resultDic];
      }];
    } else if([url.host isEqualToString:@"pay"]) {
      
      return [WXApi handleOpenURL:url delegate:[WXApiManager sharedManager]];
    } else {
        // 其他
    }
    
}

```

```javascript
import RNPay from '@ichong/react-native-pay';

let payInfo = JSON.parse(str);

// 微信支付
RNPay.wxpay({
    appId: payInfo.appId,
    extData: payInfo.extData,
    nonceStr: payInfo.nonceStr,
    packageValue: payInfo.packageValue,
    partnerId: payInfo.partnerId,
    prepayId: payInfo.prepayId,
    timestamp: payInfo.timestamp,
    sign: payInfo.sign,
});

// 支付宝支付
RNPay.alipay({
    partner: payInfo.partner,
    private_key: payInfo.private_key,
    body: payInfo.body,
    seller_id: payInfo.seller_id,
    out_trade_no: payInfo.out_trade_no,
    subject: payInfo.subject,
    total_fee: payInfo.total_fee,
    notify_url: payInfo.notify_url,
    schemes: 'academyalipay',   // 仅对ios有效，需要在url type 中注册，用于支付宝返回app
});

// 监听支付结果
RNPay.addListener('pay_ok', () => {
    // 成功
});

RNPay.addListener('pay_error', (error) => {
    // 失败
});

```
