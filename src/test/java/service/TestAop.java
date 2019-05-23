package service;

import meng.aop.AopApplication;
import meng.aop.service.SayHelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = AopApplication.class)
@RunWith(SpringRunner.class)
public class TestAop {
    @Autowired
    private SayHelloService sayHelloService;

    @Test
    public void test1() {
        System.out.println(sayHelloService.sayHello("eee"));
    }
}
