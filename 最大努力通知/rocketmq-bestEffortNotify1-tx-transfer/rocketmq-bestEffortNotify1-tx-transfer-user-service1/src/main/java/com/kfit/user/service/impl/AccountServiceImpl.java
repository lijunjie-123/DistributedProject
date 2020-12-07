package com.kfit.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kfit.user.bean.Account;
import com.kfit.user.bean.PayOrder;
import com.kfit.user.bean.TxNo2;
import com.kfit.user.client.PayOrderClient;
import com.kfit.user.repository.AccountRepository;
import com.kfit.user.repository.TxNo2Repository;
import com.kfit.user.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TxNo2Repository txNo2Repository; 
	@Autowired
	private PayOrderClient payOrderClient;
	
	/**
	 * 	充值成功增加金额,此方法需要确保幂等性，如果多次回调，金额就会不对。
	 */
	@Transactional
	public boolean increse(PayOrder payOrder) {
		if(txNo2Repository.existsById(payOrder.getTxNO())) {
			return false;
		}
		//增加金额
		Account account = accountRepository.findByUid(payOrder.getUid());
		if(account ==  null) {
			throw new RuntimeException("Account is not exist");
		}
		
		
		account.setMoney(account.getMoney().add(payOrder.getPayAmount()));
		accountRepository.save(account);

		txNo2Repository.save(new TxNo2(payOrder.getTxNO()));
		return true;
	}
	
	public PayOrder queryPayResult(int id) {
		//本地可以维护一个状态，不需要每次都去请求查询。
		PayOrder payOrder = payOrderClient.queryPayResult(id);
		if("success".equals(payOrder.getResult())) {
			this.increse(payOrder);
		}
		return payOrder;
	}
}
