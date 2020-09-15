package Demo6;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Scanner;

public class Consumer {
    public static void main(String[] args) throws Exception {
        System.out.print("使用Tag过滤还是使用Sql过滤(tag/sql): ");
        String ts = new Scanner(System.in).nextLine();
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer-demo6");
        c.setNamesrvAddr("192.168.64.141:9876");
        if (ts.equalsIgnoreCase("tag")) {
            System.out.println("使用Tag过滤: TagA || TagB || TagC");
            c.subscribe("Topic7", "TagA || TagB || TagC");
        } else {
            System.out.println("使用Sql过滤: rnd=1 or rnd > 2");
            c.subscribe("Topic7", MessageSelector.bySql("rnd=1 or rnd > 2"));
        }
        c.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    System.out.println(new String(msg.getBody()) + " - " + msg.getUserProperty("rnd"));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        c.start();
        System.out.println("开始消费数据");
    }
}
