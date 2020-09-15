package Demo7;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("consumer-demo7");
        c.setNamesrvAddr("192.168.64.141:9876");
        c.subscribe("Topic8", "*");
        c.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext ctx) {
                for (MessageExt msg : list) {
                    System.out.println(new String(msg.getBody()) + " - " + msg);
                }
                if (Math.random()<0.5) {
                    System.out.println("消息处理完成");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } else {
                    System.out.println("消息处理失败, 要求服务器稍后重试发送消息");
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        });
        c.start();
        System.out.println("开始消费数据");
    }
}

