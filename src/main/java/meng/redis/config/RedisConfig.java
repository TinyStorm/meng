package meng.redis.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

@SpringBootConfiguration
@EnableAutoConfiguration
public class RedisConfig {


    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        //创建自定义序列化器
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        //包装成SerializationPair类型
        RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(serializer);
        //redis默认配置文件,并且设置过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1));
        //设置序列化器
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(serializationPair);
        //RedisCacheManager 生成器创建
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisCacheConfiguration);
        return builder.build();

    }
}
