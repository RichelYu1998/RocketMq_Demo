package cn.tedu.demo2.m2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("demo2")
public class Test2 {
    @Autowired
    private Producer p;
    @Test
    public void test1() throws InterruptedException {
        p.send();
        Thread.sleep(30000);
    }
}
