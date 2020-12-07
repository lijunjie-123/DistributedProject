package com.kfit.user.message.event;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.kfit.user.bean.PayOrder;
import com.kfit.user.service.AccountService;

@Component 
@RocketMQMessageListener(consumerGroup="consumer_group_notifyPay",topic = "topic_notifyPay")
public class ConsumerPayListener implements RocketMQListener<PayOrder>{
	
	@Autowired
	private AccountService accountService;
	
	public void onMessage(PayOrder payOrder) {
		System.out.println("ConsumerPayListener.onMessage()="+payOrder.getId());
		
		accountService.increse(payOrder);
	}
	

}
