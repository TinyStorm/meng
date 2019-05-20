package meng.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"meng.quartz","meng.mybatis.config"})
public class QuartzDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzDemoApplication.class);
    }
}
