package service;

import meng.Application;
import meng.concurrent.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(value = SpringRunner.class)
public class TestAsync {


    @Autowired
    private MyService myService;

    @Test
    public void testAsync() throws InterruptedException {
        System.out.println("start");
        myService.printHello();
        Thread.sleep(10000);
        System.out.println("end");
    }
}
