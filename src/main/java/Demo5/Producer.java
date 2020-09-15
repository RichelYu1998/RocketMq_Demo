package Demo5;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Scanner;

public class Producer { public static void main(String[] args) throws Exception {
    DefaultMQProducer p = new DefaultMQProducer("producer-demo5");
    p.setNamesrvAddr("192.168.64.141:9876");
    p.start();
    String topic = "Topic5";
    while (true) {
        System.out.print("输入消息,用逗号分隔多条消息: ");
        String[] a = new Scanner(System.in).nextLine().split(",");
        ArrayList<Message> messages = new ArrayList<>();
        for (String s : a) {
            messages.add(new Message(topic, s.getBytes()));
        }
        p.send(messages);
        System.out.println("批量消息已发送");
    }
}
}