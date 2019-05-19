package meng.quartz.config;

import meng.config.BaseConfig;
import meng.mybatis.config.MasterDataSourceConfig;
import meng.quartz.service.HelloQuartzService;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSourceInitializer;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@SpringBootConfiguration
@EnableAutoConfiguration //此注解用于寻找该Configuration之外的bean 并注入到当前@Bean修饰的方法中去
public class QuartzConfiguration {


    //Qualifier这个注解实在当前context中寻找名称为datasource的bean,并注入,若不加此注解 则默认使用Autowire
    //该配置是将Quartz中的数据源换成自己定义的数据源,若不加,则会在springboot的context中寻找,即为配置到primary datasource.若找不到则会抛出异常
    //其实很多自动配置的东西都可以通过此种方式进行灵活的动态配置,关键是找好切入点.
    //AutoConfig中的bean方法上基本都会携带@ConditionMissBean代表context中没有此类型的bean才会初始化
    @Bean
    public QuartzDataSourceInitializer quartzDataSourceInitializer(@Qualifier("dataSource") DataSource dataSource, ResourceLoader resourceLoader, QuartzProperties properties) {

        return new QuartzDataSourceInitializer(dataSource, resourceLoader, properties);
    }

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
