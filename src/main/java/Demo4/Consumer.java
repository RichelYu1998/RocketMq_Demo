package Demo4;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer-demo4");
        c.setNamesrvAddr("192.168.64.141:9876");
        c.subscribe("Topic4", "*");
        c.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println("------------------------------");
                for (MessageExt msg : list) {
                    long t = System.currentTimeMillis() - msg.getBornTimestamp();
                    System.out.println(new String(msg.getBody()) + " - 延迟: " + t);
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        c.start();
        System.out.println("开始消费数据");
    }
}
