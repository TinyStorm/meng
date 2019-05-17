package meng.quartz.config;

import meng.quartz.service.HelloQuartzService;
import org.quartz.*;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class QuartzConfiguration {
    @Bean
    public JobDetail hellWorldJob() {
        return JobBuilder.newJob(HelloQuartzService.class).withIdentity("hello_quartz_service")
                .storeDurably().build();
    }

    @Bean
    public Trigger hellWorldTrigger() {

        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).withRepeatCount(20);

        return TriggerBuilder.newTrigger().forJob(hellWorldJob())
                .withIdentity("hello_quartz_trigger").withSchedule(builder).build();
    }


}
