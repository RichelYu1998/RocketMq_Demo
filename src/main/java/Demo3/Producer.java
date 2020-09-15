package Demo3;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.Scanner;

public class Producer {
    static String[] msgs = {
            "15103111039,创建",
            "15103111065,创建",
            "15103111039,付款",
            "15103117235,创建",
            "15103111065,付款",
            "15103117235,付款",
            "15103111065,完成",
            "15103111039,推送",
            "15103117235,完成",
            "15103111039,完成"
    };

    public static void main(String[] args) throws Exception {
        DefaultMQProducer p = new DefaultMQProducer("producer-demo3");
        p.setNamesrvAddr("192.168.64.141:9876");
        p.start();
        String topic = "Topic3";
        String tag = "TagA";
        for (String s : msgs) {
            System.out.println("按回车发送此消息: " + s);
            new Scanner(System.in).nextLine();
            Message msg = new Message(topic, tag, s.getBytes());
            String[] a = s.split(",");
            long orderId = Long.parseLong(a[0]);
            SendResult r = p.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> queueList, Message message, Object o) {
                    Long orderId = (Long) o;
                    //订单id对队列数量取余, 相同订单id得到相同的队列索引
                    long index = orderId % queueList.size();
                    System.out.println("消息已发送到: " + queueList.get((int) index));
                    return queueList.get((int) index);
                }
            }, orderId);
            System.out.println(r + "\n\n");
        }
    }
}
