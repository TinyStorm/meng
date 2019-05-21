package meng.concurrent.service;

import org.springframework.scheduling.annotation.Async;

public interface MyService {
    @Async("testExecutor")
    void printHello();
}
