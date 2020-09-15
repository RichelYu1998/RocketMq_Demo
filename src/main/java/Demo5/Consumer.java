package Demo5;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer { public static void main(String[] args) throws Exception {
    DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer-demo5");
    c.setNamesrvAddr("192.168.64.141:9876");
    c.subscribe("Topic5", "*");
    c.registerMessageListener(new MessageListenerConcurrently() {
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            for (MessageExt msg :
                    list) {
                System.out.println("收到: "+new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    });
    c.start();
    System.out.println("开始消费数据");
}
}
