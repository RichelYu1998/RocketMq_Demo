package cn.tedu.demo2.m2;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
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
        Message<String> msg = MessageBuilder.withPayload("Hello world!").build();

        rocketMQTemplate.sendMessageInTransaction("Topic2", msg, null);
    }

    @RocketMQTransactionListener
    class TxMsgListener implements RocketMQLocalTransactionListener {
        @Override
        public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
            System.out.println("在这里执行本地事务");
            return RocketMQLocalTransactionState.UNKNOWN;
        }
        @Override
        public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
            System.out.println("处理消息回查");
            return RocketMQLocalTransactionState.COMMIT;
        }
    }
}
