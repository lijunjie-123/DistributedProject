package com.kfit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kfit.user.bean.PayOrder;
import com.kfit.user.service.PayOrderService;

@RestController
public class PayOrderController {

	@Autowired
	private PayOrderService payOrderService;

	/**
	 * 充值 ,对于充值的orderSN可以是另外一个系统作为参数传递过来，这里就以PayOrder的id进行操作了。
	 * @param payOrder
	 * @return
	 */
	@GetMapping(value = "/pay")
	public PayOrder pay(PayOrder payOrder) {
		return payOrderService.addPayOrder(payOrder);
	}
	
	//查询充值结果
	@GetMapping(value = "/payresult/{id}")
	public PayOrder pay(@PathVariable("id") int id) {
		return payOrderService.getAccountPay(id);
	}
	
}
