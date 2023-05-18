
#import "RNPay.h"

@implementation RNPay

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(alipay:(NSString *)scheme Info:(NSString *)info) {
    
    dispatch_async(dispatch_get_main_queue(), ^{
        [Alipay payOfStr:scheme OrderStr:info success:^(id responseObject) {

            [self sendEventWithName:@"pay_ok" body:responseObject];
        } failure:^(NSString *error) {

            [self sendEventWithName:@"pay_error" body:error];
        }];
    });
}

RCT_EXPORT_METHOD(wxpay:(NSDictionary *)info) {
    
    PayReq* req             = [[PayReq alloc] init];
    req.partnerId           = [info objectForKey:@"partnerId"];
    req.prepayId            = [info objectForKey:@"prepayId"];
    req.nonceStr            = [info objectForKey:@"nonceStr"];
    req.timeStamp           = [[info objectForKey:@"timestamp"] intValue];
    req.package             = [info objectForKey:@"packageValue"];
    req.sign                = [info objectForKey:@"sign"];

    dispatch_async(dispatch_get_main_queue(), ^{
        [WXApi sendReq:req completion:^(BOOL success) {
            NSLog(@"success:%d", success);
        }];
    });
}

- (NSArray<NSString *> *)supportedEvents {
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    
    [arr addObject:@"pay_ok"];
    [arr addObject:@"pay_error"];
    
    return arr;
}

- (void) handleSend:(NSNotification *)notification {
    
    [self sendEventWithName:notification.name body:notification.object];
}

- (void) wxpayHandle:(NSNotification *)notification {
    
    BaseResp *resp = notification.object;
    
    switch (resp.errCode) {
        case WXSuccess:
            [self sendWXpayResult:@"pay_ok" error:@"支付成功"];
            break;
            
        case WXErrCodeUserCancel:
            
            [self sendWXpayResult:@"pay_error" error:@"取消支付"];
            break;
            
        case WXErrCodeAuthDeny:
            
            [self sendWXpayResult:@"pay_error" error:@"授权失败"];
            break;
            
        case WXErrCodeUnsupport:
            
            [self sendWXpayResult:@"pay_error" error:@"微信版本不支持"];
            break;
            
        default:
            
            [self sendWXpayResult:@"pay_error" error:@"支付失败"];
            break;
    }
}


- (void)startObserving
{
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(handleSend:)
                                                 name:@"pay_ok"
                                               object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(handleSend:)
                                                 name:@"pay_error"
                                               object:nil];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(wxpayHandle:) name:WXPAY_NOTIFITION_RESULT object:nil];
    
}

- (void)stopObserving
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void) sendWXpayResult:(NSString *)type error:(NSString *)error {
    
    [[NSNotificationCenter defaultCenter] postNotificationName:type object:error];
}

+ (void) sendAlipayResult:(NSDictionary *)resultDic {
    
    switch ([[resultDic objectForKey:@"resultStatus"] integerValue]) {
        case 9000:
            
            [[NSNotificationCenter defaultCenter] postNotificationName:@"pay_ok" object:@"成功"];
            break;
            
        case 6001:
            [[NSNotificationCenter defaultCenter] postNotificationName:@"pay_error" object:@"取消支付"];
            break;
            
        case 6002:
            [[NSNotificationCenter defaultCenter] postNotificationName:@"pay_error" object:@"网络连接异常"];
            
            break;
            
        case 6004:
            [[NSNotificationCenter defaultCenter] postNotificationName:@"pay_error" object:@"请检查订单状态"];
            
            break;
            
        default:
            [[NSNotificationCenter defaultCenter] postNotificationName:@"pay_error" object:@"支付失败"];
            
            break;
    }
}


@end
  
