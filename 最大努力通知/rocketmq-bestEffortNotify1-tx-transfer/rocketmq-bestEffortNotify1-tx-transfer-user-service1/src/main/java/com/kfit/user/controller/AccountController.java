package com.kfit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kfit.user.bean.PayOrder;
import com.kfit.user.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
//	@RequestMapping("/increse")
//	public boolean increse(long uid,BigDecimal money) {
//		return accountService.increse(uid, money);
//	}
	
	
	//主动查询充值结果
    @GetMapping(value = "/payresult/{id}")
    public PayOrder result(@PathVariable("id") int id){
        return accountService.queryPayResult(id);
    }
	
}
