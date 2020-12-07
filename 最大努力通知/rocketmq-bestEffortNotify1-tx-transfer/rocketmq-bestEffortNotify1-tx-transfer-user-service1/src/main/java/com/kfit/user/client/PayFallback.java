package com.kfit.user.client;

import org.springframework.stereotype.Component;

import com.kfit.user.bean.PayOrder;

@Component
public class PayFallback implements PayOrderClient {

    public PayOrder queryPayResult(int id) {
    	PayOrder payOrder = new PayOrder();
    	payOrder.setResult("fail");
    	payOrder.setId(id);
        return payOrder;
    }
}