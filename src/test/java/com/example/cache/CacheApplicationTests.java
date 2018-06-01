package com.example.cache;

import com.example.cache.bean.Employee;
import com.example.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate<Object, Employee> redisTemplate;

    @Autowired
    EmployeeMapper mapper;

    /**
     * 测试添加字符串
     */
    @Test
    public void test() {
        stringRedisTemplate.opsForValue().append("String", "data");
        stringRedisTemplate.opsForList().leftPush("list", "test");
        stringRedisTemplate.opsForSet().add("set", "lisi", "zhangsan");
        stringRedisTemplate.opsForZSet().add("zset", "zset", 10000);
        stringRedisTemplate.opsForHash().put("hash", "name", "zhangsan");
        stringRedisTemplate.opsForHash().put("hash", "gender", "male");
    }

    /**
     * 测试添加对象
     */
    @Test
    public void test01() {
        Employee employee = mapper.getEmpById(1);
        redisTemplate.opsForValue().set("test01", employee);
    }

    @Test
    public void contextLoads() {
    }

}
