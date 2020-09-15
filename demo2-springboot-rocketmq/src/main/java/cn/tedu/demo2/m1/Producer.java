package cn.tedu.demo2.m1;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    public void send() {
        //发送同步消息
        rocketMQTemplate.convertAndSend("Topic1:TagA","Hello world!");
        //封装成 Spring 通用消息对象
        Message<String> msg = MessageBuilder.withPayload("Hello Spring message!").build();
        rocketMQTemplate.convertAndSend("Topic1:TagA", msg);
        //发送异步消息
        rocketMQTemplate.asyncSend("Topic1:TagA", "Hello world!", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功");
            }
            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送失败");
            }
        });
        rocketMQTemplate.syncSendOrderly("Topic1", "98456237,创建", "98456237");
        rocketMQTemplate.syncSendOrderly("Topic1", "98456237,支付", "98456237");
        rocketMQTemplate.syncSendOrderly("Topic1", "98456237,完成", "98456237");
    }
}









