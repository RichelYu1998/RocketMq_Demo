package cn.tedu.demo2.m2;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "Topic2", consumerGroup = "consumer-demo2")
public class Consumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String o) {
        System.out.println("收到： "+o);
    }
}
