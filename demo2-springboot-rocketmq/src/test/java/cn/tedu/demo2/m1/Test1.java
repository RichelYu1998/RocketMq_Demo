package cn.tedu.demo2.m1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("demo1")
public class Test1 {
    @Autowired
    private Producer p;

    @Test
    public void test1() throws InterruptedException {
        p.send();
        Thread.sleep(30000);
    }

}
