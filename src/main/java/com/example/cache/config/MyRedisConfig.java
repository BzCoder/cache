package com.example.cache.config;

import com.example.cache.bean.Employee;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author BaoZhou
 * @date 2018/6/1
 */
@Configuration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Employee.class));
        return template;
    }

    public RedisCacheManager employCacheManager(RedisConnectionFactory redisConnectionFactory,
                                          ResourceLoader resourceLoader) {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(determineConfiguration(resourceLoader.getClassLoader()));
        List<String> cacheNames = this.cacheProperties.getCacheNames();
        if (!cacheNames.isEmpty()) {
            builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
        }
        return this.customizerInvoker.customize(builder.build());
    }

}

