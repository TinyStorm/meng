package meng.aop.service;

import meng.aop.log.LogSender;
import org.springframework.stereotype.Service;

@Service
public class SayHelloServiceImpl implements SayHelloService {


    @Override
    @LogSender(value = "got")
    public String sayHello(String name) {
        System.out.println("hello " + name);
        if ("eee123".equals(name)) {
            throw new RuntimeException("exp");
        }
        return "wm";
    }
}
