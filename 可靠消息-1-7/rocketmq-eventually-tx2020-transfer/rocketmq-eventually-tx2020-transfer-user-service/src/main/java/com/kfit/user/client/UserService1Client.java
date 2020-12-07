package com.kfit.user.client;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service1")
public interface UserService1Client {

	@RequestMapping("/increse")
	public boolean increse(@RequestParam("uid")long uid,@RequestParam("money")BigDecimal money);
	
}
