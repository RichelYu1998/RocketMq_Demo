package Demo2;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer p = new DefaultMQProducer("producer-demo2");
        p.setNamesrvAddr("192.168.64.141:9876");
        p.start();
        String topic = "Topic2";
        String tag = "TagA";
        while (true) {
            System.out.println("输入消息，用逗号分隔多条信息");
            String[] a = new Scanner(System.in).nextLine().split(",");
            for (String s: a){
                Message msg = new Message(topic, tag, s.getBytes());
                p.sendOneway(msg);
            }
            System.out.println("--------------------消息已送出-----------------------");
        }
    }
}
