package com.ihrm.system.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : HK意境
 * @ClassName : RedisTest
 * @date : 2021/11/22 11:23
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate ;


    @Test
    public void stringRedisTemplateTest(){
        redisTemplate.opsForValue().set("testKey", "this is test string");
    }

    @Test
    public void redisTemplateTest(){

    }


}

