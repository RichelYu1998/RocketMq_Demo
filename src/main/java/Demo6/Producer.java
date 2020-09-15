package Demo6;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Random;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer p = new DefaultMQProducer("producer-demo6");
        p.setNamesrvAddr("192.168.64.141:9876");
        p.start();
        String topic="Topic6";
        while (true){
            System.out.print("输入消息,用逗号分隔多条消息: ");
            String[] a = new Scanner(System.in).nextLine().split(",");
            System.out.print("输入Tag: ");
            String tag = new Scanner(System.in).nextLine();
            for (String s:a) {
                Message msg = new Message(topic, tag, s.getBytes());
                msg.putUserProperty("rnd",""+new Random().nextInt(4));
                p.send(msg);
            }
        }
    }
}
