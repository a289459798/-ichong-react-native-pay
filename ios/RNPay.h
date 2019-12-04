
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif

@interface RNPay : NSObject <RCTBridgeModule>
+ (void) sendAlipayResult:(NSDictionary *)resultDic;
@end
  
