package com.example.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author BaoZhou
 * @date 2018/5/30
 */
@Configuration
public class MyKeyGenerateConfig {
    @Bean(name = "MyKeyGenerate")
    public KeyGenerator myKeyGenerator() {
        return (target, method, params) -> method.getName() + "[" + Arrays.asList(params).toString() + "]";
    }
}
