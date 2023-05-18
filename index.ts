import { type } from 'os';
import {NativeModules, NativeEventEmitter, Platform} from 'react-native';
const {RNPay} = NativeModules;


type WePayProps = {
    appId: string;
    nonceStr: string;
    packageValue: string;
    partnerId: string;
    prepayId: string;
    timestamp: number;
    sign: string;
}

class Pay extends NativeEventEmitter {

    // 构造
    constructor() {
        super(RNPay);
    }

    alipay(info: string, scheme?: string) {
        if(Platform.OS == 'android') {
            RNPay.alipay(info);
        } else {
            RNPay.alipay(scheme, info);
        }
    }

    wxpay(info: WePayProps) {
        RNPay.wxpay(info);
    }
}

export type {
    WePayProps
}

export default new Pay();