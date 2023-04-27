import { type } from 'os';
import {NativeModules, NativeEventEmitter} from 'react-native';
const {RNPay} = NativeModules;

type AliPayProps = {
    partner: string;
    private_key: string;
    public_key: string;
    body: string;
    seller_id: string;
    out_trade_no: string;
    subject: string;
    total_fee: number;
    notify_url: string;
}

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

    alipay(info: AliPayProps) {
        RNPay.alipay(info);
    }

    wxpay(info: WePayProps) {
        RNPay.wxpay(info);
    }
}

export type {
    AliPayProps,
    WePayProps
}

export default new Pay();