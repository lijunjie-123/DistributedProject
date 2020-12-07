package com.kfit.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kfit.user.bean.PayOrder;

@FeignClient(value = "user-service", fallback = PayFallback.class)
public interface PayOrderClient {
	
	@GetMapping("/payresult/{id}")
	public PayOrder queryPayResult(@PathVariable("id") int txNo);
}
