package com.ihrm.system.config;

import com.ihrm.common.utils.IdWorker;
import com.ihrm.common.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @author : HK意境
 * @ClassName : BeanConfig
 * @date : 2021/11/14 14:14
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker getIdWorker(){
        return new IdWorker() ;
    }


    //@Bean
    public JwtUtils getJwtUtils(){
        return new JwtUtils();
    }


    // 解决 no session 的问题
    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
        return new OpenEntityManagerInViewFilter();
    }




}
