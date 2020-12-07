package com.kfit.user.service;

import com.kfit.user.bean.PayOrder;

public interface PayOrderService {
	
	public PayOrder addPayOrder(PayOrder payOrder);
	
	public PayOrder getAccountPay(long id);
}
