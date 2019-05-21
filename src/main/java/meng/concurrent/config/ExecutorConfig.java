package meng.concurrent.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootConfiguration
@Slf4j
@EnableAsync
public class ExecutorConfig {
    @Bean("testExecutor")
    Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(10);
        //配置队列大小
        executor.setQueueCapacity(5);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");

        executor.setRejectedExecutionHandler(new MyRejectHandler());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
