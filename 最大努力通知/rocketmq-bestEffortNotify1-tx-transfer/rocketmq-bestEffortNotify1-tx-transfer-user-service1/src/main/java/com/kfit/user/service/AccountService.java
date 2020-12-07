package com.kfit.user.service;

import com.kfit.user.bean.PayOrder;

public interface AccountService {
	
	
	/**增加金额的方法*/
	public boolean increse(PayOrder payOrder);

	public PayOrder queryPayResult(int id);
	
}
