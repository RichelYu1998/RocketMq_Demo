package Demo1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer p = new DefaultMQProducer("producer-demo1");
        p.setNamesrvAddr("192.168.64.141:9876");
        p.start();
        String topic = "Topic1";
        String tag = "TagA";
        while (true) {
            System.out.print("输入消息,用逗号分隔多条消息: ");
            String[] a = new Scanner(System.in).nextLine().split(",");
            for (String s : a) {
                Message msg = new Message(topic, tag, s.getBytes());
                SendResult r = p.send(msg);
                System.out.println(r);
            }
        }
    }
}

