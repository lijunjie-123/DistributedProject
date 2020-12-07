package com.kfit.user.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kfit.user.bean.PayOrder;
import com.kfit.user.repository.PayOrderRepository;
import com.kfit.user.service.PayOrderService;

@Service
public class PayOrderServiceImpl implements PayOrderService {
	
	@Autowired
	private PayOrderRepository payOrderRepository;
	
	@Autowired
	private RocketMQTemplate rocketMQTemplate; 
	
	/**
	 *  @Transactional 确保本地事务要能够执行成功。
	 */
	@Transactional
	public PayOrder addPayOrder(PayOrder payOrder) {
		payOrder.setCreateTime(new Date());
		payOrder.setTxNO(UUID.randomUUID().toString());
		payOrder.setResult("success");//如果充值有多步可以增加中间状态.
		payOrder = payOrderRepository.save(payOrder);
		if(payOrder.getId()>0) {
			//发送消息,如果有中间步骤的话，那么最后状态为success的时候在发送消息.
			rocketMQTemplate.convertAndSend("topic_notifyPay",payOrder);
		}
		return payOrder;
	}

	public PayOrder getAccountPay(long id) {
		PayOrder payOrder = payOrderRepository.getOne(id);
		System.out.println(payOrder.getId()+"--"+payOrder.getUid());
		return payOrder;
	}
	
	

}
