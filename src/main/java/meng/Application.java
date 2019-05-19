package meng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
/**
 * 打成war包方式
 *
 * 1.pom文件中maven打包插件
 * 2.去除web模块的Tomcat依赖并加上servlet依赖
 * 3.入口程序集成SpringBootServletInitializer并重写configure方法
 */
public class Application /*extends SpringBootServletInitializer*/ {
    /*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
