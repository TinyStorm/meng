package meng.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootConfiguration
@AutoConfigureOrder(1)
public class BaseConfig {
    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    DataSource dataSource() {

        return DataSourceBuilder.create().build();


    }

}
