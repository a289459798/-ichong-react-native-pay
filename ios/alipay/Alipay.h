//
//  Alipay.h
//  PolyPetMarket
//
//  Created by zhangzy on 16/9/8.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

#include "Order.h"
#import "DataSigner.h"
#import <AlipaySDK/AlipaySDK.h>

@interface Alipay : NSObject

+ (void) pay:(NSString *)scheme Order: (Order *)order success:(void (^)(id responseObject)) successBlock failure:(void (^)(NSString *error)) failureBlock;

+ (void) payOfStr:(NSString *)scheme OrderStr: (NSString *)orderString success:(void (^)(id responseObject)) successBlock failure:(void (^)(NSString *error)) failureBlock;


@end
