package com.kfit.user.controller;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

/**
 * Created by wangxy on 20/11/25.
 */
@Component
public class PayConsumer {
    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "pay_consumer_group";


    //并发消费
    public  PayConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr("192.168.188.129:9876;192.168.188.130:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("pay_topic_test","*");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, contxt) ->{
            MessageExt message = msgs.get(0);
            int time = message.getReconsumeTimes();
            String topic = message.getTopic();
            try {
                String body = new String(message.getBody(),"utf-8");
                String tags = message.getTags();
                String keys = message.getKeys();

                //模拟消息重试
               /* if(keys.equals("xianyue667788")){
                    throw new RuntimeException();
                }*/
                System.out.println("消息消费结果：" + "topic=" + topic + "," + "tags=" + tags + "," + "keys=" + keys  + "," + "message=" + body);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                if(time >=2){
                    //数据存到数据库或者通知运营处理，返回成功
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }

                System.out.println("消息重试中，重试次数为：" + time );
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        consumer.start();
    }

}
