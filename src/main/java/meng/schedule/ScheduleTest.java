package meng.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ScheduleTest {
    @Scheduled(cron = "*/5 * * * * ?")
    public void test() {

        System.out.println("11111111111111");
    }
}
