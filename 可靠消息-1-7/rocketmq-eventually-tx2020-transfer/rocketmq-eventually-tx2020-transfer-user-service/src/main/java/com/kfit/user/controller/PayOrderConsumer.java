package com.kfit.user.controller;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangxy on 20/11/25.
 */
@Component
public class PayOrderConsumer {
    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "pay_consumer_order_group";
    private static final String TOPIC_1 = "pay_order_test";



    //顺序消费
    public PayOrderConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr("192.168.188.129:9876;192.168.188.130:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.subscribe(TOPIC_1,"*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                MessageExt message = msgs.get(0);
                int time = message.getReconsumeTimes();
                System.out.printf("消息重试次数为：" + time);
                try{

                    System.out.println("消息消费结果：" + Thread.currentThread().getName() +
                            "消息体为 ： " + new String(message.getBody(),"utf-8"));

                }catch (Exception e){
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }

}
