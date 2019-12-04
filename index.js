import {NativeModules, NativeEventEmitter} from 'react-native';

const {RNPay} = NativeModules;

class Pay extends NativeEventEmitter {

    // 构造
    constructor(props) {
        super(RNPay);
        // 初始状态
        this.state = {};
    }

    alipay(info) {

        RNPay.alipay(info);
    }

    wxpay(info) {

        RNPay.wxpay(info);
    }

}

Pay = new Pay();
module.exports = Pay;
