package meng.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"meng.redis","meng.mybatis"},
        exclude = {QuartzAutoConfiguration.class})
@EnableCaching
public class RedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class);
    }
}
