package meng.concurrent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyServiceImpl implements MyService {
    @Override
    public void printHello() {
        log.info("---------------");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
        log.info("---------------");
    }
}
