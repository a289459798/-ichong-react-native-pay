//
//  Alipay.m
//  PolyPetMarket
//
//  Created by zhangzy on 16/9/8.
//  Copyright © 2016年 Facebook. All rights reserved.
//

#import "Alipay.h"

@implementation Alipay

+ (void)pay:(NSString *)scheme Order:(Order *)order success:(void (^)(id responseObject)) successBlock failure:(void (^)(NSString *error)) failureBlock {
  
  
  //将商品信息拼接成字符串
  NSString *orderSpec = [order description];
  
  //获取私钥并将商户信息签名,外部商户可以根据情况存放私钥和签名,只需要遵循RSA签名规范,并将签名字符串base64编码和UrlEncode
  id<DataSigner> signer = CreateRSADataSigner(order.privateKey);
  NSString *signedString = [signer signString:orderSpec];
  
  //将签名成功字符串格式化为订单字符串,请严格按照该格式
  
  NSString *orderString = nil;
  if (signedString != nil) {
    orderString = [NSString stringWithFormat:@"%@&sign=\"%@\"&sign_type=\"%@\"",
                   orderSpec, signedString, @"RSA"];
    
      [self payOfStr:scheme OrderStr:orderString success:successBlock failure:failureBlock];
  } else {
      
      failureBlock(@"参数不对");
  }
}

+ (void)payOfStr:(NSString *)scheme OrderStr:(NSString *)orderString success:(void (^)(id))successBlock failure:(void (^)(NSString *))failureBlock {
    [[AlipaySDK defaultService] payOrder:orderString fromScheme:scheme callback:^(NSDictionary *resultDic) {
        
        switch ([[resultDic objectForKey:@"resultStatus"] integerValue]) {
            case 9000:
                
                successBlock(@"成功");
                break;
                
            case 6001:
                failureBlock(@"取消支付");
                break;
                
            case 6002:
                failureBlock(@"网络连接异常");
                break;
                
            case 6004:
                failureBlock(@"请检查订单状态");
                break;
                
            default:
                failureBlock(@"支付失败");
                break;
        }
        
    }];
}

@end
