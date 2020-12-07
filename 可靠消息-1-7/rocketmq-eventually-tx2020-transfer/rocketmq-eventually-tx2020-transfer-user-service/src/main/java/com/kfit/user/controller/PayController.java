package com.kfit.user.controller;

import com.alibaba.fastjson.JSON;
import com.kfit.user.message.event.AccountChangeEvent;
import com.kfit.user.message.event.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by wangxy on 20/11/24.
 */
@RestController
public class PayController {

    @Autowired
    private PayProducer payProducer;

    private static final String TOPIC = "pay_topic_test";

    private static final String TOPIC_1 = "pay_order_test";




    //同步发送
    @RequestMapping("/api/v1/pay")
    public Object senMessage(BigDecimal money, Long uid, Long toUid) {
        AccountChangeEvent event = new AccountChangeEvent();
        event.setMoney(money);
        event.setUid(uid);
        event.setToUid(toUid);

        Message message = new Message(TOPIC, "weatch", "xianyue667788", JSON.toJSONBytes(event));
        //同步发送
        try {
            SendResult sendResult = payProducer.getProducer().send(message);
            return sendResult;
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


  //局部顺序发送，用于同一消息：例如创建订单，支付订单，完成订单。
/*  @RequestMapping("/api/v1/pay")
  public Object sendMessage(String id) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    List<ProductOrder> list = ProductOrder.getOrderList();
      SendResult result = new SendResult();
    for(ProductOrder order : list){
        Message message = new Message(TOPIC_1,"order",String.valueOf(order.getOrderId()), JSON.toJSONBytes(order));

        result = payProducer.getProducer().send(message, new MessageQueueSelector() {

            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message message, Object arg) {
                long id = (Long)arg;
                long index  = id % mqs.size();
                return mqs.get((int)index);
            }
        },order.getOrderId());

        System.out.printf("发送结果=%s,sendResult=%s,orderId=%s,type=%s\n",
                result.getSendStatus(),result.toString(),order.getOrderId(),order.getType());
    }
      return "send is ok";

  }*/


    //异步调用
    /*SendResult sendResult1 = new SendResult();
    try {
      payProducer.getProducer().send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
              System.out.printf("发送结果=%s, msg=%s", sendResult.getSendStatus(),sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
              //根据业务需要是否需要重试

            }
          }
      );
    } catch (RemotingException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    sendResult1.setSendStatus(SendStatus.SEND_OK);
    return sendResult1;*/
}
