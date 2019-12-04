
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import <React/RCTEventEmitter.h>

#include "WXApi.h"
#import "WXApiManager.h"
#include "Alipay.h"

@interface RNPay : RCTEventEmitter <RCTBridgeModule>
+ (void) sendAlipayResult:(NSDictionary *)resultDic;
@end
  
