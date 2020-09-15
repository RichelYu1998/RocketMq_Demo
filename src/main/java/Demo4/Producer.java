package Demo4;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer p = new DefaultMQProducer("producer-demo4");
        p.setNamesrvAddr("192.168.64.141:9876");
        p.start();
        while (true){
            System.out.print("输入消息,用逗号分隔多条消息: ");
            String[] a = new Scanner(System.in).nextLine().split(",");
            for (String s:a){
                Message msg = new Message("Topic4", s.getBytes());
                msg.setDelayTimeLevel(3);
                p.send(msg);
            }
        }
    }
}
